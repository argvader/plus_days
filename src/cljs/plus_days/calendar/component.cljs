(ns plus_days.calendar.component
  (:require [plus_days.calendar.styles :refer [style]]
            [plus_days.calendar.events]
            [plus_days.calendar.subscriptions]
            [plus_days.calendar.header.component :as header]
            [plus_days.calendar.utils :refer [today? date->key offset-start-day]]
            [cljs-time.core :as time]
            [reagent.core :as r]
            [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(def days-of-week ["Sunday" "Monday" "Tuesday" "Wednesday" "Thursday" "Friday" "Saturday"])

(defn- build-month [date-time]
  (let [start-date (time/first-day-of-the-month- date-time)
        number-of-days (time/number-of-days-in-the-month start-date)]
     (for [day (range 0 number-of-days)]
       {:key (str "dated_day_" day)
        :date (time/plus start-date (time/days day))})))

(defn- handle-drag [event]
  (.preventDefault event))

(defn- handle-drop [event date calendar-tasks]
  (.preventDefault event)
  (let [task-id (.getData (.-dataTransfer event) "application/x-task")]
    (dispatch [:plus_days.calendar.events/completed-task  calendar-tasks date task-id])))

(defn- count-completed-tasks [calendar-tasks date]
  (let [date-key (date->key date)]
    (if (contains? calendar-tasks date-key)
      (count (calendar-tasks date-key))
      "")))

(defn- render-month [calendar-tasks month]
  [:table
   [:thead
      [:tr nil
        (map #(vector :th {:key %} %) days-of-week)]]
   [:tbody
      (for [week month]
         [:tr {:key (str "tr-" "week" (random-uuid)) :id (str "tr-" "week")}
           (for [day week]
             (if (contains? day :date)
               [:td {:key (:key day)
                     :on-drag-over handle-drag
                     :on-drop #(handle-drop % (:date day) calendar-tasks)
                     :class-name (if (today? (:date day)) "today")}
                 [:span (count-completed-tasks calendar-tasks (:date day))]
                 [:aside (time/day (:date day))]]
               [:td {:key (str (random-uuid))} ""]))])]])

(defn component[]
  (let [current-month (subscribe [:plus_days.calendar.subscriptions/current-month])
        calendar-tasks (subscribe [:plus_days.calendar.subscriptions/calendar-tasks])]
     (fn []
      [:div {:class-name (:container style)}
         (header/component @current-month)
         (->> @current-month
              (build-month)
              (offset-start-day)
              (partition 7 7 {})
              (render-month @calendar-tasks))])))
