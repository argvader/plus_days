(ns plus_days.calendar.header.styles
  (:require [cljs-css-modules.macro :refer-macros [defstyle]]
            [plus_days.palette :as palette]
            [plus_days.constants :refer [css-vendors]]))

(defstyle style css-vendors
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
