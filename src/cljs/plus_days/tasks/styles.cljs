(ns plus_days.tasks.styles
  (:require [cljs-css-modules.macro :refer-macros [defstyle]]))

(defstyle style
  [".container"
    {:background-color "cyan"
     :height "200px"}])