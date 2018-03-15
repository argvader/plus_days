(ns plus_days.styles
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule]]
            [plus_days.palette :as palette]
            [plus_days.layout :as layout]
            [plus_days.resets :as resets]
            [plus_days.typography :as typography]))

(defrule html)
(defrule body)
(defrule application :#application)

(defstyles screen
    resets/reset-common-selectors
    typography/fonts
    layout/grid
    (html
      {:height "100%"}
      [:* {:box-sizing "border-box"}])
    (body
      {:height "100%"
       :background palette/base-orange-dark-21
       :font-size   "16px"
       :line-height 1.5})
    (application
      {:width "100%"
       :height "100%"}))
