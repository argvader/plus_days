(ns plus_days.epoch.component
  (:require [plus_days.epoch.styles :refer [style]]
            [plus_days.epoch.visualization.bar_chart :as bar-chart]))

(defn component[]
   (fn []
     [:div {:class-name (:container style)}
       [:h1 "Epoch component"]
       [:div {:id "epoch"}
         [bar-chart/component 500 500 [2 5 3 8]]]]))
