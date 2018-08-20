(ns plus-days.tasks.styles
  (:require [cljs-css-modules.macro :refer-macros [defstyle]]
            [plus-days.palette :as palette]
            [plus-days.constants :refer [css-vendors]]))

(defstyle style css-vendors
  [".container"
    {:background-color palette/night-sky
     :grid-area "tasks"}])
