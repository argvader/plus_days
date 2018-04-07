(ns plus_days.db.firebase
  (:require [com.degel.re-frame-firebase :as firebase]
            [re-frame.core :as re-frame]
            [re-frame.loggers :refer [console]]
            [iron.re-utils :as re-utils :refer [sub2 <sub >evt]]
            [iron.utils :refer [ci-includes? validate]]
            [plus_days.db.specs :as specs]))


(comment Need spec for db schema)

(defonce timestamp-marker
  (-> js/firebase .-database .-ServerValue .-TIMESTAMP))

(defonce firebase-app-info
  {:apiKey "AIzaSyDz_UFZOqejd7n-xlWWxqw3gBHe1kaki9o"
   :authDomain "plusdays-4efdf.firebaseapp.com"
   :databaseURL "https://plusdays-4efdf.firebaseio.com"
   :storageBucket "plusdays-4efdf.appspot.com"})

(defn private-fb-path
  ([path]
   (private-fb-path path (<sub [::uid])))
  ([path uid]
   (when uid
     (into [:private uid] path))))

(defn all-shared-fb-path
  [path]
  (into [:shared] path))


(defn my-shared-fb-path
  ([path]
   (my-shared-fb-path path (<sub [::uid])))
  ([path uid]
   (when uid
     (into [:shared (first path) uid] (rest path)))))

(sub2 ::user [::user])
(sub2 ::uid  [::user :uid])

(re-frame/reg-event-fx
 ::set-user
 (fn [{db :db} [_ user]]
   (into {:db (assoc db ::user user)}
         (when user
           {:firebase/write {:path       (my-shared-fb-path [:users-details] (:uid user))
                             :value      (into {:timestamp timestamp-marker}
                                               (select-keys user [:display-name :email :photo-url]))
                             :on-success #(console :log "Logged in:" (:display-name user))
                             :on-failure #(console :error "Login failure: " %)}}))))


(re-frame/reg-event-fx
 :sign-in
 (fn [_ _] {:firebase/google-sign-in {:sign-in-method :popup}}))

(re-frame/reg-event-fx
 :sign-out
 (fn [_ _] {:firebase/sign-out nil}))

(re-frame/reg-event-fx
 :firebase-error
 (fn [_ [_ error]]
   (js/console.error (str "FIREBASE ERROR:\n" error))))

(defn authenticate []
  (re-frame/dispatch-sync [:sign-in]))

(defn logged-in? [db]
  (some? (get-in db [::user :uid])))

(defn ^:export init []
  (js/console.log "init")
  (firebase/init :firebase-app-info      firebase-app-info
                 :get-user-sub           [::user]
                 :set-user-event         [::set-user]
                 :default-error-handler  [:firebase-error]))
