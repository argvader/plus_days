(ns plus_days.calendar.component
  (:require [plus_days.calendar.styles :refer [style]]
            [plus_days.calendar.events]
            [plus_days.calendar.subscriptions]
            [plus_days.calendar.header.component :as header]
            [cljs-time.core :as time]
            [reagent.core :as r]
            [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(def days ["Sunday" "Monday" "Tuesday" "Wednesday" "Thursday" "Friday" "Saturday"])

(defn today? [date]
  (let [today (time/now)]
     (every? identity
       [(= (time/month date) (time/month today))
        (= (time/day date) (time/day today))
        (= (time/year date) (time/year today))])))


(defn offset-start-day [days]
  (-> (time/day-of-week (:date (first days)))
      (mod 7)
      (repeat {})
      (concat days)))

(defn build-month [date-time]
  (let [start-date (time/first-day-of-the-month- date-time)
        number-of-days (time/number-of-days-in-the-month start-date)]
     (for [day (range 0 number-of-days)]
       {:key (str "dated_day_" day)
        :date (time/plus start-date (time/days day))})))

(defn handle-drag [event]
  (.preventDefault event))

(defn handle-drop [event date]
  (.preventDefault event)
  (let [task-id (.getData (.-dataTransfer event) "application/x-task")]
    (dispatch [:plus_days.calendar.events/completed-task  date task-id])))


(defn render-month [month]
  [:table
   [:thead
      [:tr nil
        (map #(vector :th {:key %} %) days)]]
   [:tbody
      (for [week month]
         [:tr {:key (str "tr-" "week" (random-uuid)) :id (str "tr-" "week")}
           (for [day week]
             (if (contains? day :date)
               [:td {:key (:key day)
                     :on-drag-over handle-drag
                     :on-drop #(handle-drop % (:date day))
                     :class-name (if (today? (:date day)) "today")}
                 [:aside (time/day (:date day))]]
               [:td {:key (str (random-uuid))} ""]))])]])

(defn component[]
  (let [current-month (subscribe [:plus_days.calendar.subscriptions/current-month])]
     (js/console.log @current-month)
     (fn []
      [:div {:class-name (:container style)}
         (header/component @current-month)
         (->> @current-month
              (build-month)
              (offset-start-day)
              (partition 7 7 {})
              (render-month))])))
