(ns plus_days.tasks.component
  (:require [plus_days.tasks.styles :refer [style]]
            [plus_days.tasks.subscriptions]
            [plus_days.tasks.events]
            [plus_days.tasks.task.component :as task]
            [reagent.core :as r]
            [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(defn new-task []
  (let [name (r/atom "")
        duration (r/atom 60)
        click-fn (fn []
                    (dispatch [:plus_days.tasks.events/new @name @duration])
                    (reset! duration 60)
                    (reset! name ""))]
    (fn []
      [:div
        [:input {:type "text"
                 :value @name
                 :on-change #(reset! name (.. % -target -value))}]
        [:input {:type "text"
                 :value @duration
                 :on-change #(reset! duration (.. % -target -value))}]
        [:button {:on-click #(click-fn)} "Add Task"]])))

(defn component[]
  (let [tasks (subscribe [:plus_days.tasks.subscriptions/fetch])]
    (fn []
      (js/console.log "rendering fn")
      [:div {:class-name (:container style)}
        (map #(task/component (val %)) @tasks)
        [new-task]])))
