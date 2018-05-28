(ns plus_days.epoch.visualization.styles
  (:require [cljs-css-modules.macro :refer-macros [defstyle]]
            [plus_days.palette :as palette]
            [garden.color :refer [as-hex]]
            [plus_days.constants :refer [css-vendors]]))

(defstyle barChart css-vendors
  [".bar" {:fill palette/pale-yellow}])
