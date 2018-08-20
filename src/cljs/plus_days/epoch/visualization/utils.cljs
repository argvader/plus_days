(ns plus-days.epoch.visualization.utils
  (:require [cljsjs.d3]))

(defn attrs [el m]
  (doseq [[k v] m]
    (.attr el k v)))

(defn data-join
  [parent tag class data]
  (let [join  (-> parent
                  (.selectAll (str tag "." class))
                  (.data (into-array data)))
        enter (-> join
                  (.enter)
                  (.append tag)
                  (.classed class true))]
    (-> join (.exit) (.remove))
    (.merge join enter)))
