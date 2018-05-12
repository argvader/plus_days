(ns plus_days.events
  [:require [re-frame.core :as re-frame :refer [reg-event-db]]
            [cljs-time.core :as time]])

(reg-event-db
  :initialize-app
  (fn [db _]
    (assoc db :current-month (time/now))))
