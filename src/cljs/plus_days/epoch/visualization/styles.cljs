(ns plus-days.epoch.visualization.styles
  (:require [cljs-css-modules.macro :refer-macros [defstyle]]
            [plus-days.palette :as palette]
            [garden.color :refer [as-hex]]
            [plus-days.constants :refer [css-vendors]]))

(defstyle barChart css-vendors
  [".bar" {:fill palette/pale-yellow}])
