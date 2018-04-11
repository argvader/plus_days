(ns plus_days.typography
  (:refer-clojure :exclude [rem])
  (:require [garden.units :as units :refer (px pt pc em rem vw)]
            [garden.stylesheet :refer [at-font-face]]
            [garden.def :refer [defrule defstyles]]))

(def title ["Komika" "Comic Sans", "sans-serif"])
(def basic ["Lato" "Avenir" "Helvetica" "sans-serif"])
(def mono ["Inconsolata" "Menlo" "Courier" "monospace"])

(defn font [family size weight kerning leading & options]
  {:font-family family
   :font-size (rem size)
   :font-weight weight
   :letter-spacing (rem kerning)
   :line-height (em leading)
   :text-align "left"
   :text-transform (get options :text-transform "none")})

(defn typeset [title sans mono]
  [[:body :p (font sans 1 300 0.1 1.5)]
   [:h1 (font title 3 600 0.5 1.5)]
   [:h2 (font title 3 400 0.5 1.5)]
   [:h3 (font title 2 300 0.5 1.3)]
   [:h4 (font title 1.5 300 0.3 1.3)]
   [:h5 :h6 (font mono 1.2 300 0.2 1.2)]
   [:header (font title 4 700 0.3 1.2 "small-caps")]
   [:footer (font basic 1 100 0.3 1.2)]])

(defstyles fonts
  (at-font-face {:font-family "Komika"
                 :src "url('/fonts/komikax_.woff2') format('woff2'), url('/fonts/komikax_.woff') format('woff')"
                 :font-weight "normal"
                 :font-style "normal"})
  (typeset title basic mono))
