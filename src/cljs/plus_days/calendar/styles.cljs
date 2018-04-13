(ns plus_days.calendar.styles
  (:require [cljs-css-modules.macro :refer-macros [defstyle]]
            [plus_days.palette :as palette]))

(defstyle style
  [".container"
    {:background-color palette/dirt
     :grid-area "calendar"}
    [:table
      {:width "100%"}
      [:th {:background-color palette/near-black
            :color palette/near-white}]
      [:td  {:border "1px solid"
             :border-color (palette/shade palette/dirt :light 20)}]]])
