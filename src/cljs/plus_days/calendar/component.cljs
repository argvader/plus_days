(ns plus_days.calendar.component
  (:require [plus_days.calendar.styles :refer [style]]
            [cljs-time.core :as time]))

(def months ["January" "February" "March" "April" "May" "June" "July" "August" "September" "October" "November" "December"])
(def days ["Sunday" "Monday" "Tuesday" "Wednesday" "Thursday" "Friday" "Saturday"])

(defn render-month [number]
  [:table
     [:thead
        [:tr nil
          (map #(vector :th %) days)]]])

(defn component[]
  [:div {:class-name (:container style)}
     (render-month 1)])
