(ns plus_days.epoch.component
  (:require [plus_days.epoch.styles :refer [style]]))

(defn component[]
  [:div {:class-name (:container style)}
     [:h1 "Epoch component"]])
