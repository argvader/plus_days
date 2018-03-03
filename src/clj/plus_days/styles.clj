(ns plus_days.styles
  (:require [garden.def :refer [defrule defstyles]]
            [garden.stylesheet :refer [rule]]
            [plus_days.typography :as typography]))

(defrule body)

(defstyles screen
    typography/fonts
    (body
     {:font-size   "16px"
      :line-height 1.5}))
