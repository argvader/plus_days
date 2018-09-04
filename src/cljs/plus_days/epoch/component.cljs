(ns plus-days.epoch.component
  (:require [re-frame.core :as re-frame :refer [subscribe]]
            [plus-days.constants :as constants :refer [window-size]]
            [plus-days.epoch.subscriptions]
            [plus-days.epoch.events]
            [plus-days.epoch.styles :refer [style]]
            [plus-days.epoch.visualization.bar_chart :as bar-chart]))

(defn component[]
   (fn []
     [:div {:class-name (:container style)}
       [:h1 "Epoch component"]
       [:div {:id "epoch"}
         [bar-chart/component (:height @window-size) (:width @window-size) [2 5 3 8]]]]))
