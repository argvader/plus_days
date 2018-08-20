(ns plus-days.palette
       (:require [garden.color :refer [rgb lighten darken]]))

(defn shade [color direction step]
  (if (= direction :light)
    (lighten color step)
    (darken color step)))

(def grey (rgb 120 120 120))
(def steel-blue (rgb 43 69 92))
(def hero-blue (rgb 27 114 159))
(def dirt (rgb 212 149 43))
(def pale-yellow (rgb 247 210 129))
(def brown (rgb 120 61 43))
(def night-sky (rgb 26 50 62))
(def angry-red (rgb 180 28 23))
(def hero-red (rgb 242 60 39))

(def near-black (rgb 4 4 4))
(def near-white (rgb 244 244 244))
