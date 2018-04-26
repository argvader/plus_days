(ns plus_days.calendar.component
  (:require [plus_days.calendar.styles :refer [style]]
            [cljs-time.core :as time]
            [cljs-time.format :refer [unparse formatter]]
            [reagent.core :as r]))

(def days ["Sunday" "Monday" "Tuesday" "Wednesday" "Thursday" "Friday" "Saturday"])
(def current-month (r/atom (time/now)))

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

(defn handle-drop [event]
  (.preventDefault event)
  (js/console.log "id " (.getData (.-dataTransfer event) "application/x-task")))

(defn render-header []
  (let [shift-month (fn [direction]
                      (reset! current-month (if (= direction :left)
                                              (time/minus @current-month (time/months 1))
                                              (time/plus @current-month (time/months 1)))))]
    [:header {:class-name (:header style)}
       [:section {:class-name "left"
                  :on-click #(shift-month :left)}]
       [:section (unparse (formatter "MMMM YYYY") @current-month)]
       [:section {:class-name "right"
                  :on-click #(shift-month :right)}]]))

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
                     :on-drop handle-drop
                     :class-name (if (today? (:date day)) "today")}
                 [:aside (time/day (:date day))]]
              [:td {:key (str (random-uuid))} ""]))])]])

(defn component[]
  [:div {:class-name (:container style)}
     (render-header)
     (->> @current-month
          (build-month)
          (offset-start-day)
          (partition 7 7 {})
          (render-month))])
