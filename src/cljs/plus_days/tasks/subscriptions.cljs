(ns plus_days.tasks.subscriptions
  [:require [re-frame.core :as re-frame :refer [reg-sub reg-sub-raw]]
            [com.degel.re-frame-firebase.core :as core]])

(reg-sub-raw
  ::fetch
  (fn [db]
    (core/firebase-on-value-sub db [1 {:path [:tasks]}])))
