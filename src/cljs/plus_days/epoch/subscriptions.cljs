(ns plus_days.epoch.subscriptions
  (:require [re-frame.core :as re-frame :refer [reg-sub]]
            [com.degel.re-frame-firebase.core :as core]))

(reg-sub
  ::window-size
  (fn [db _]
    (:window-size db)))
