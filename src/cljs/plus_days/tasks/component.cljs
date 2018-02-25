(ns plus_days.tasks.component
  (:require [plus_days.tasks.styles :refer [style]]))

(defn component[]
  [:div {:class-name (:container style)} "Tasks component"])
