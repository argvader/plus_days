(ns plus_days.macros
  (:require [garden.types #?@(:cljs [:refer [CSSFunction CSSAtRule]])]
            [garden.util :as util]
            [garden.core]
            [garden.color :refer [lighten darken]]
            [garden.stylesheet :refer [at-media]])
  #?(:clj
      (:import garden.types.CSSFunction
               garden.types.CSSAtRule)))

(defmacro defbreakpoint [name media-params]
  `(defn ~name [& rules#]
     (at-media ~media-params
       [:& rules#])))

(defmacro shades [base-name base-color step]
  `(doseq [~'s (range 0 50 ~step)]
     (if (= ~'s 0)
       (def ~base-name ~base-color)
       (do
         (def ~(symbol (str (quote base-name) "-light-" 's)) (lighten ~base-color ~'s))
         (def ~(symbol (str (quote base-name) "-dark-" 's)) (darken ~base-color ~'s))))))
