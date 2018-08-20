(ns plus-days.epoch.component
  (:require [re-frame.core :as re-frame :refer [subscribe]]
            [plus-days.epoch.subscriptions]
            [plus-days.epoch.events]
            [plus-days.epoch.styles :refer [style]]
            [plus-days.epoch.visualization.bar_chart :as bar-chart]))

(defn- chart-size [window-size]
  (js/console.log "resized" {:height window-size})
  {:height 500 :width 500})

(defn component[]
  (let [window-size (subscribe [:plus-days.epoch.subscriptions/window-size])]
       [chart-size (chart-size window-size)]
     (fn []
       [:div {:class-name (:container style)}
         [:h1 "Epoch component"]
         [:div {:id "epoch"}
           [bar-chart/component (:height chart-size) (:width chart-size) [2 5 3 8]]]])))
