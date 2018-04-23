(ns plus_days.tasks.task.styles
  (:require [cljs-css-modules.macro :refer-macros [defstyle]]
            [garden.color :refer [as-hex]]
            [plus_days.palette :as palette]
            [plus_days.constants :refer [css-vendors]]))

(defstyle style css-vendors
  [".task"
    {:border (str "1px solid " (as-hex palette/near-white))
     :color palette/near-white
     :margin "3px"
     :padding "9px"
     :position "relative"}
    [:aside {:position "absolute"
             :bottom "6px"
             :right "6px"}]])
