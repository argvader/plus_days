(ns plus_days.tasks.styles
  (:require [cljs-css-modules.macro :refer-macros [defstyle]]
            [plus_days.palette :as palette]))



(defstyle style
  [".container"
    {:background-color palette/night-sky
     :grid-area "tasks"}])
