(ns plus-days.layout
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule at-media]]))

(defrule grid-rule ".grid")

(defstyles grid
  (grid-rule
    {:display "grid"
     :height "inherit"
     :width "inherit"
     :grid-auto-flow "column"
     :grid-gap "10px"
     :grid-template-rows "40% 1fr"
     :grid-template-columns "repeat(3, 1fr)"
     :grid-template-areas "\"epoch epoch epoch\" \n\"tasks calendar calendar\""}))
