(ns plus-days.resets
  (:require [garden.core :refer [css]]))

(defn clearfix [cls]
  [cls {:*zoom 1}
    [:&:before :&:after {:display "table"
                         :content " "
                         :line-height 0}]
    [:&:after {:clear "both"}]])

(def reset-common-selectors
  [:html :body
   :h1 :h2 :h3 :h4 :h5 :h6
   :p :dt :dd :ol :ul :li
   :fieldset :form :lable :legend
   :th :td
   :article :section :aside :figure :footer :header
   :hgroup :menu :nav :section
   {:margin 0
    :padding 0
    :border 0}])

(def reset-padding
  {:padding "0 !important"})
