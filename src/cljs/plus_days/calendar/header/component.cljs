(ns plus_days.calendar.header.component
   (:require [plus_days.calendar.events]
             [plus_days.calendar.header.styles :refer [style]]
             [cljs-time.format :refer [unparse formatter]]
             [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(defn component [current-month]
    [:header {:class-name (:header style)}
       [:section {:class-name "left"
                  :on-click #(dispatch [:plus_days.calendar.events/shift-month  current-month :left])}]
       [:section (unparse (formatter "MMMM YYYY") current-month)]
       [:section {:class-name "right"
                  :on-click #(dispatch [:plus_days.calendar.events/shift-month current-month :right])}]])
