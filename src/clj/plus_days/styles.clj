(ns plus-days.styles
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule]]
            [plus-days.palette :as palette]
            [plus-days.layout :as layout]
            [plus-days.resets :as resets]
            [plus-days.typography :as typography]))

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
       :background (palette/shade palette/hero-blue :light 10)
       :font-size   "16px"
       :line-height 1.5})
    (application
      {:width "100%"
       :height "100%"}))
