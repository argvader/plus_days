(ns plus_days.epoch.visualization.bar-chart
  (:require [plus_days.epoch.visualization.utils :as utils]
            [plus_days.epoch.visualization.styles :refer [barChart]]
            [clojure.string :as str]
            [reagent.core :as r]
            [cljsjs.d3]))

(defn height-scale [maxDomain maxRange]
  (-> js/d3
      (.scaleLinear)
      (.domain #js [0 maxDomain])
      (.range #js [0 maxRange])))

(defn draw-graph [node data]
  (let [dataMax (apply max data)
        yScale  (height-scale dataMax 100)]
    (-> (utils/data-join node "rect" (:bar barChart) data)
        (utils/attrs {"x" (fn [_ index]
                            (* index 25))
                      "y" (fn [d]
                            (- 100 (yScale d)))
                      "height" (fn [d]
                                 (yScale d))
                      "width" 25}))))


(defn component[height width data]
  (let [!ref (atom nil)]
    (r/create-class
      {:display-name "epoch"
       :component-did-mount #(draw-graph (js/d3.select @!ref) data)
       :reagent-render
         (fn []
            [:svg {:ref (fn [node] (reset! !ref node))
                   :width height
                   :height width}])})))
