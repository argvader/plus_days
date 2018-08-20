(ns plus-days.tasks.component
  (:require [plus-days.tasks.styles :refer [style]]
            [plus-days.tasks.subscriptions]
            [plus-days.tasks.events]
            [plus-days.tasks.task.component :as task]
            [reagent.core :as r]
            [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(defn new-task []
  (let [name (r/atom "")
        duration (r/atom 60)
        click-fn (fn []
                    (dispatch [:plus-days.tasks.events/new @name @duration])
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
  (let [tasks (subscribe [:plus-days.tasks.subscriptions/fetch])]
    (fn []
      [:div {:class-name (:container style)}
        (map #(task/component (val %)) @tasks)
        [new-task]])))
