(ns plus-days.epoch.styles
  (:require [cljs-css-modules.macro :refer-macros [defstyle]]
            [plus-days.constants :refer [css-vendors]]))

(defstyle style css-vendors
  [".container"
    {:background-color "green"
     :grid-area "epoch"}]
  ["#epoch"
    {:width "100%"
     :height "calc(100% - 80px)"}])
