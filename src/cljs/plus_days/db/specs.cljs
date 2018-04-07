(ns plus_days.db.specs
  (:require [clojure.spec.alpha :as s]))

(s/def ::uid string?)
(s/def ::provider-data any?)
(s/def ::display-name (s/nilable string?))
(s/def ::photo-url (s/nilable string?))
(s/def ::email string?)
(s/def ::user (s/nilable
               (s/keys :req-un [::uid ::provider-data ::display-name ::photo-url ::email])))

(s/def ::db-keys (s/keys :req [::user]))
