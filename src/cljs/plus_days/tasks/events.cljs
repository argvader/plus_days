(ns plus_days.tasks.events
  [:require [re-frame.core :as re-frame :refer [reg-event-fx]]])

(reg-event-fx
  :new-task
  (fn [{db :db} [_ task]]
    {:firebase/write {:path [:test]
                      :value task
                      :on-success #(js/console.log "Wrote status")
                      :on-failure [:handle-failure]}}))
