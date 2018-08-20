(ns plus-days.app
  (:require [reagent.core :as reagent :refer [atom]]
            [re-frame.core :as re-frame]
            [plus-days.db.firebase :as fb]
            [plus-days.events]
            [plus-days.epoch.component :as epoch]
            [plus-days.tasks.component :as tasks]
            [plus-days.calendar.component :as calendar]))

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
  (re-frame/dispatch-sync [:initialize-app])
  (mount-root))
