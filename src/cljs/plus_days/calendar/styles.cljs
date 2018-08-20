(ns plus-days.calendar.styles
  (:require [cljs-css-modules.macro :refer-macros [defstyle]]
            [plus-days.palette :as palette]
            [plus-days.constants :refer [css-vendors]]))

(defstyle style css-vendors
  [".container"
    {:background-color palette/dirt
     :grid-area "calendar"
     :margin "1em"}
    [".today"
      {:border-color (palette/near-white)
       :background-color (palette/shade palette/dirt :dark 10)}]
    [:table
      {:width "100%"
       :table-layout "fixed"
       :padding-top "3px"
       :height "calc(100% - 36px)"}
      [:th {:background-color palette/near-black
            :text-align "center"
            :width "13%"
            :color palette/near-white}]
      [:td  {:position "relative"
             :border "1px solid"
             :border-color (palette/shade palette/dirt :light 20)}
        [:aside {:position "absolute"
                  :right "6px"
                  :bottom "6px"}]]]]
  [".header"
    {:display "flex"
     :flex-wrap "nowrap"
     :justify-content "center"
     :font-size "2.0rem"
     :height "36px"}
    [".left"
      {:width "20px"
       :mask "url(/images/btn-prev.svg) 0 50% no-repeat"
       :background-color (palette/shade palette/dirt :dark 20)}]
    [".right"
      {:width "20px"
       :mask "url(/images/btn-next.svg) 0 50% no-repeat"
       :background-color (palette/shade palette/dirt :dark 20)}]])
