(ns plus_days.epoch.styles
  (:require [cljs-css-modules.macro :refer-macros [defstyle]]))

(defstyle style
  [".container"
    {:background-color "green"
     :grid-area "epoch"}])
