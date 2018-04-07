(ns plus_days.tasks.component
  (:require [plus_days.tasks.styles :refer [style]]
            [plus_days.tasks.subscriptions]
            [plus_days.tasks.events]
            [reagent.core :as r]
            [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(defn render-task [{:keys [id duration name]}]
  [:div {:key (str "tsk--" id)}
    name
    [:aside duration]])

(defn new-task []
  (let [name (r/atom "")
        duration (r/atom 60)
        click-fn (fn []
                    (dispatch [:new-task @name @duration])
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
  (let [tasks (subscribe [:tasks])]
    (fn []
      [:div {:class-name (:container style)}
        (map #(render-task (val %)) @tasks)
        [new-task]])))
