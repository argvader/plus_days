(ns plus-days.tasks.subscriptions
  (:require [re-frame.core :as re-frame :refer [reg-sub subscribe]]
            [com.degel.re-frame-firebase.core :as core]))

(reg-sub
  ::fetch
  (fn [_ _]
    (subscribe [:firebase/on-value {:path [:tasks]}]))
  (fn [tasks _]
    tasks))
