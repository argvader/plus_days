(ns plus-days.events
  (:require [re-frame.core :as re-frame :refer [reg-event-fx reg-event-db]]
            [district0x.re-frame.window-fx]
            [cljs-time.core :as time]))

(reg-event-fx
  :initialize-app
  (fn [cofx _]
    {:db (assoc (:db cofx) :current-month (time/now))}))
