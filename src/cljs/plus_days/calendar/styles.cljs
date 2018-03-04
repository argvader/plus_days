(ns plus_days.calendar.styles
  (:require [cljs-css-modules.macro :refer-macros [defstyle]]))

(defstyle style
  [".container"
    {:background-color "blue"
     :grid-area "calendar"}])
