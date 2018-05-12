(ns plus_days.calendar.subscriptions
  [:require [re-frame.core :as re-frame :refer [reg-sub]]
            [com.degel.re-frame-firebase.core :as core]])

(reg-sub
  ::current-month
  (fn [db _]
    (:current-month db)))
