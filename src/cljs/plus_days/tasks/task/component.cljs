(ns plus_days.tasks.task.component
  (:require [plus_days.tasks.task.styles :refer [style]]
            [reagent.core :as r]))

(defn component [{:keys [id duration name]}]
  [:div {:key (str "tsk--" id)
         :class-name (:task style)
         :draggable true}
    name
    [:aside duration]])
