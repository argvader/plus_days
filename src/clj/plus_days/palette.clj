(ns plus_days.palette
  (:require [garden.color :refer [rgb]]
            [plus_days.macros :refer [shades]]))

(shades base-grey (rgb 120 120 120) 3)
(shades base-red (rgb 208 0 21) 3)
(shades base-blue (rgb 18 128 178) 3)
(shades base-orange (rgb 227 148 8) 3)
(shades base-green (rgb 85 143 110) 3)
(shades base-brown (rgb 155 137 113) 3)
(shades base-yellow (rgb 255 205 48) 3)

(def near-black (rgb 4 4 4))
(def near-white (rgb 244 244 244))
