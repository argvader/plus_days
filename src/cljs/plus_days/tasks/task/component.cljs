(ns plus_days.tasks.task.component
  (:require [plus_days.tasks.task.styles :refer [style]]
            [reagent.core :as r]))

(defn handle-drag-start [event task-id]
  (.setData (.-dataTransfer event) "application/x-task" task-id))

(defn component [{:keys [id duration name]}]
  [:div {:key (str "tsk--" id)
         :class-name (:task style)
         :on-drag-start #(handle-drag-start % id)
         :draggable true}
    name
    [:aside duration]])
