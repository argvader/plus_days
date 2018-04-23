(ns plus_days.tasks.styles
  (:require [cljs-css-modules.macro :refer-macros [defstyle]]
            [plus_days.palette :as palette]
            [plus_days.constants :refer [css-vendors]]))

(defstyle style css-vendors
  [".container"
    {:background-color palette/night-sky
     :grid-area "tasks"}])
