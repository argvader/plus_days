(ns plus-days.calendar.events
  (:require [cljs-time.core :as time]
            [cljs-time.coerce :as coerce]
            [plus-days.calendar.utils :refer [date->key]]
            [re-frame.core :as re-frame :refer [reg-event-fx reg-event-db debug]]))

(reg-event-db
  ::shift-month
  (fn [db [_ current-month direction]]
    (assoc db :current-month (if (= direction :left)
                               (time/minus current-month (time/months 1))
                               (time/plus current-month (time/months 1))))))

(reg-event-fx
  ::completed-task
  (fn [{db :db} [_ calendar-tasks date task-id]]
      (let [epoch-date (str (coerce/to-epoch date))
            current-tasks (calendar-tasks (date->key date))
            db-commands (remove nil? (vector
                                      (if (nil? current-tasks)
                                         [:firebase/write {:path [:calendar epoch-date]
                                                           :value []
                                                           :on-success #(js/console.log "Made date entry")
                                                           :on-failure [:handle-failure]}])
                                      (if (not (contains? (set (vals current-tasks)) task-id))
                                         [:firebase/push  {:path [:calendar epoch-date]
                                                           :value task-id
                                                           :on-success #(js/console.log "Added task id")
                                                           :on-failure [:handle-failure]}])))]

        (if (> (count db-commands) 0)
          {:firebase/multi db-commands}))))
