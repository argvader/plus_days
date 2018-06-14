(ns plus_days.epoch.component
  (:require [re-frame.core :as re-frame :refer [subscribe]]
            [plus_days.epoch.events]
            [plus_days.epoch.subscriptions]
            [plus_days.epoch.styles :refer [style]]
            [plus_days.epoch.visualization.bar_chart :as bar-chart]))

(defn component[]
  (let [window-size (subscribe [:plus_days.epoch.subscriptions/window-size])]
     (fn []
       [:div {:class-name (:container style)}
         [:h1 "Epoch component"]
         [:div {:id "epoch"}
           [bar-chart/component 200 (:width @window-size) [2 5 3 8]]]])))
