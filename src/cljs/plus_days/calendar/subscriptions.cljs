(ns plus-days.calendar.subscriptions
  (:require [re-frame.core :as re-frame :refer [reg-sub subscribe]]
            [com.degel.re-frame-firebase.core :as core]))

(reg-sub
  ::current-month
  (fn [db _]
    (:current-month db)))

(reg-sub
  ::calendar-tasks
  (fn [_ _]
    (subscribe [:firebase/on-value {:path [:calendar]}]))
  (fn [calendar _]
    calendar))
