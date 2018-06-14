(ns plus_days.events
  (:require [re-frame.core :as re-frame :refer [reg-event-fx reg-event-db]]
            [district0x.re-frame.window-fx]
            [cljs-time.core :as time]))

(reg-event-fx
  :initialize-app
  (fn [cofx _]
    {:window/on-resize {:dispatch [::window-resized]
                        :debounce-ms 200}
     :db (assoc (:db cofx) :current-month (time/now))}))

(reg-event-fx
  ::window-resized
   (fn [cofx [_ height width]]
      {:db (assoc (:db cofx) :window-size {:height height :width width})}))
