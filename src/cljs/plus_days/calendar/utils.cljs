(ns plus-days.calendar.utils
  (:require   [cljs-time.core :as time]
              [cljs-time.coerce :as coerce]))

(defn today? [date]
  (let [today (time/now)]
     (every? identity
       [(= (time/month date) (time/month today))
        (= (time/day date) (time/day today))
        (= (time/year date) (time/year today))])))

(defn date->key [date]
  (keyword (str (coerce/to-epoch date))))

(defn offset-start-day [days]
  (-> (time/day-of-week (:date (first days)))
      (mod 7)
      (repeat {})
      (concat days)))
