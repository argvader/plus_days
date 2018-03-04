(ns plus_days.app
  (:require [reagent.core :as reagent :refer [atom]]
            [plus_days.epoch.component :as epoch]
            [plus_days.tasks.component :as tasks]
            [plus_days.calendar.component :as calendar]))


(defn plus-days []
  [:div {:class-name "grid"}
   [epoch/component]
   [tasks/component]
   [calendar/component]])

(defn init []
  (reagent/render-component [plus-days]
                            (.getElementById js/document "application")))
