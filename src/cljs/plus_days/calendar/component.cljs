(ns plus_days.calendar.component
  (:require [plus_days.calendar.styles :refer [style]]
            [cljs-time.core :as time]))

(def months ["January" "February" "March" "April" "May" "June" "July" "August" "September" "October" "November" "December"])
(def days ["Sunday" "Monday" "Tuesday" "Wednesday" "Thursday" "Friday" "Saturday"])

(defn render-month [date-time]
  (js/console.log "date" (time/last-day-of-the-month- date-time))
  [:table
     [:thead
        [:tr nil
          (map #(vector :th %) days)]]
     [:tbody
        (for [week (range 1 6)]
           [:tr {:key (str "tr-" week)}
             (for [day (range 0 7)]
               [:td {:key (str "td-" (get days day) week)} (str (get days day) week)])])]])


(defn component[]
  [:div {:class-name (:container style)}
     (render-month (time/now))])
