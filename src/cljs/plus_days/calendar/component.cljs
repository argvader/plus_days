(ns plus_days.calendar.component
  (:require [plus_days.calendar.styles :refer [style]]))

(defn component[]
  [:div {:class-name (:container style)} "Calendar component"])
