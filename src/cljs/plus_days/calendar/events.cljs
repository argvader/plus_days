(ns plus_days.calendar.events
  [:require [cljs-time.core :as time]
            [cljs-time.coerce :as coerce]
            [re-frame.core :as re-frame :refer [reg-event-fx reg-event-db debug]]])

(reg-event-db
  ::shift-month
  (fn [db [_ current-month direction]]
    (assoc db :current-month (if (= direction :left)
                               (time/minus current-month (time/months 1))
                               (time/plus current-month (time/months 1))))))

(reg-event-fx
  ::completed-task
  (fn [{db :db} [_ date task-id]]
      (let [epoch-date (coerce/to-epoch date)]
        (js/console.log "date " date)
        (js/console.log "epoch " epoch-date)
        {:firebase/multi [[:firebase/write {:path [:calendar (str epoch-date)]
                                            :value []
                                            :on-success #(js/console.log "Made date entry")
                                            :on-failure [:handle-failure]}]
                          [:firebase/push  {:path [:calendar (str epoch-date)]
                                            :value task-id
                                            :on-success #(js/console.log "Added task id")
                                            :on-failure [:handle-failure]}]]})))
