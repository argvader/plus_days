(ns plus_days.tasks.component
  (:require [plus_days.tasks.styles :refer [style]]
            [plus_days.tasks.subscriptions]
            [re-frame.core :as re-frame :refer [subscribe]]))

(defn component[]
  (let [data (subscribe [:tasks])]
    [:div {:class-name (:container style)} (str "Tasks "  @data)]))
