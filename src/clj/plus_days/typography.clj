(ns plus_days.typography
  (:refer-clojure :exclude [rem])
  (:require [garden.units :as units :refer (px pt pc em rem vw)]
            [garden.def :refer [defrule defstyles]]))

(def lato ["Lato" "Avenir" "Georgia" "Times" "serif"])
(def sans ["Lato" "Avenir" "Helvetica" "sans-serif"])
(def mono ["Inconsolata" "Menlo" "Courier" "monospace"])

(defn font [family size weight kerning leading & options]
  {:font-family family
   :font-size (rem size)
   :font-weight weight
   :letter-spacing (rem kerning)
   :line-height (em leading)
   :text-align "left"
   :text-transform (get options :text-transform "none")})

(defn typeset [serif sans mono]
  [[:body :p (font sans 1 300 0.1 1.5)]
   [:h1 (font lato 3 600 0.5 1.5)]
   [:h2 (font lato 3 400 0.5 1.5)]
   [:h3 (font lato 2 300 0.5 1.3)]
   [:h4 (font lato 1.5 300 0.3 1.3)]
   [:h5 :h6 (font mono 1.2 300 0.2 1.2)]
   [:header (font lato 4 700 0.3 1.2 "small-caps")]
   [:footer (font sans 1 100 0.3 1.2)]])

(defstyles fonts
  (typeset lato sans mono))
