(ns plus_days.macros
  (:require [garden.types]
            [garden.util :as util]
            [garden.core]
            [garden.color :refer [lighten darken]]
            [garden.stylesheet :refer [at-media]])
  (:import garden.types.CSSFunction
           garden.types.CSSAtRule))

(defmacro defbreakpoint [name media-params]
  `(defn ~name [& rules#]
     (at-media ~media-params
       [:& rules#])))

(defmacro shades [base-name base-color step]
  `(def ~base-name ~base-color)
  `(doseq [~'s (range 0 50 ~step)]
     (intern *ns* (symbol (str (quote ~base-name) "-light-" ~'s)) (lighten ~base-color ~'s))
     (intern *ns* (symbol (str (quote ~base-name) "-dark-" ~'s)) (darken ~base-color ~'s))))
