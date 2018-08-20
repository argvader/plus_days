(ns plus-days.calendar.header.component
   (:require [plus-days.calendar.events]
             [plus-days.calendar.header.styles :refer [style]]
             [cljs-time.format :refer [unparse formatter]]
             [re-frame.core :as re-frame :refer [subscribe dispatch]]))

(defn component [current-month]
    [:header {:class-name (:header style)}
       [:section {:class-name "left"
                  :on-click #(dispatch [:plus-days.calendar.events/shift-month  current-month :left])}]
       [:section (unparse (formatter "MMMM YYYY") current-month)]
       [:section {:class-name "right"
                  :on-click #(dispatch [:plus-days.calendar.events/shift-month current-month :right])}]])
