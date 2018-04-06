(ns plus_days.tasks.component
  (:require [plus_days.tasks.styles :refer [style]]
            [plus_days.tasks.subscriptions]
            [plus_days.tasks.events]
            [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(defn component[]
  (let [data (subscribe [:tasks])]
    [:div {:class-name (:container style)}
      (str "Tasks "  @data)
      [:button {:on-click #(dispatch [:new-task "New Task"])} "Add"]]))
