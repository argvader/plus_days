(ns plus_days.tasks.events
  [:require [re-frame.core :as re-frame :refer [reg-event-fx debug]]])

(reg-event-fx
  ::new
  (fn [{db :db} [_ name duration]]
    (let [task {:id (str (random-uuid))
                :name name
                :duration duration}]
      {:firebase/push {:path [:tasks]
                        :value task
                        :on-success #(js/console.log "Added Task")
                        :on-failure [:handle-failure]}})))
