(ns plus-days.constants
  (:require [re-frame.interop :as interop :refer [ratom]]))

(def css-vendors {:vendors ["webkit"]
                  :auto-prefix #{"mask"}})

(defonce window-size (let [a (ratom {:width  (.-innerWidth  js/window)
                                     :height (.-innerHeight js/window)})]
                       (.addEventListener js/window "resize"
                         (fn [] (reset! a {:width  (.-innerWidth  js/window)
                                           :height (.-innerHeight js/window)})))
                       a))
