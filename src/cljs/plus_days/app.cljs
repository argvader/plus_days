(ns plus_days.app
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :as re-frame]
            [plus_days.db.firebase :as fb]
            [plus_days.epoch.component :as epoch]
            [plus_days.tasks.component :as tasks]
            [plus_days.calendar.component :as calendar]))

(defn plus-days []
  [:div {:class-name "grid"}
   [epoch/component]
   [tasks/component]
   [calendar/component]])

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render-component [plus-days]
                  (.getElementById js/document "application")))



(defn init []
  (fb/init)
  (mount-root))
