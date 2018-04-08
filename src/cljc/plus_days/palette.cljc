(ns plus_days.palette
  #?(:clj
       (:require [garden.color :refer [rgb]]
                 [plus_days.macros :refer [shades]])
     :cljs
       (:require [garden.color :refer [rgb]]
                 [plus_days.macros :refer-macros [shades]])))


(shades grey (rgb 120 120 120) 3)
(shades steel-blue (rgb 43 69 92) 3)
(shades hero-blue (rgb 27 114 159) 3)
(shades dirt (rgb 212 149 43) 3)
(shades pale-yellow (rgb 247 210 129) 3)
(shades brown (rgb 120 61 43) 3)
(shades night-sky (rgb 26 50 62) 3)
(shades angry-red (rgb 180 28 23) 3)
(shades hero-red (rgb 242 60 39) 3)

(def near-black (rgb 4 4 4))
(def near-white (rgb 244 244 244))
