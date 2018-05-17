(set-env!
 :source-paths    #{"src/cljs" "src/clj", "src/cljc"}
 :resource-paths  #{"resources"}
 :dependencies '[[boot/core                     "2.7.2"           :scope "provided"]
                 [adzerk/boot-cljs              "2.1.4"           :scope "test"]
                 [adzerk/boot-cljs-repl         "0.4.0-SNAPSHOT"  :scope "test"]
                 [adzerk/boot-reload            "0.5.2"           :scope "test"]
                 [pandeiro/boot-http            "0.8.3"           :scope "test"]
                 [com.cemerick/piggieback       "0.2.2"           :scope "test"]
                 [org.clojure/tools.nrepl       "0.2.13"          :scope "test"]
                 [weasel                        "0.7.0"           :scope "test"]
                 [crisptrutski/boot-cljs-test   "0.3.0"           :scope "test"]
                 [org.martinklepsch/boot-garden "1.3.2-0"         :scope "test"]
                 [powerlaces/boot-cljs-devtools "0.2.0"           :scope "test"]
                 [tolitius/boot-check           "0.1.9"           :scope "test"]
                 [binaryage/dirac               "1.2.9"           :scope "test"]
                 [day8.re-frame/re-frame-10x    "0.3.3-react16"]
                 [org.clojure/clojurescript     "1.10.238"]
                 [reagent                       "0.8.1"]
                 [re-frame                      "0.10.5"]
                 [com.degel/iron                "0.2.0"]
                 [com.degel/re-frame-firebase   "0.5.0"]
                 [com.andrewmcveigh/cljs-time   "0.5.2"]
                 [rid3                          "0.2.1"]
                 [cljs-css-modules              "0.2.1"]])

(require
 '[adzerk.boot-cljs              :refer [cljs]]
 '[adzerk.boot-cljs-repl         :refer [cljs-repl start-repl]]
 '[adzerk.boot-reload            :refer [reload]]
 '[pandeiro.boot-http            :refer [serve]]
 '[crisptrutski.boot-cljs-test   :refer [test-cljs]]
 '[org.martinklepsch.boot-garden :refer [garden]]
 '[powerlaces.boot-cljs-devtools :refer [cljs-devtools dirac]]
 '[tolitius.boot-check           :as check]
 '[tolitius.reporter.html])

(deftask check-sources []
  (set-env! :source-paths #{"src/cljs" "src/clj"})
  (comp
    (check/with-kibit)))

(deftask build
  "This task contains all the necessary steps to produce a build
   You can use 'profile-tasks' like `production` and `development`
   to change parameters (like optimizations level of the cljs compiler)"
  []
  (comp (cljs)
        (garden :styles-var 'plus_days.styles/screen
                :vendors ["webkit"]
                :auto-prefix #{:mask}
                :output-to "css/styles.css")))

(deftask run
  "The `run` task wraps the building of your application in some
   useful tools for local development: an http server, a file watcher
   a ClojureScript REPL and a hot reloading mechanism"
  []
  (comp (serve)
        (watch)
        (cljs-repl)
        (dirac)
        (reload)
        (build)))

(deftask production []
  (task-options! cljs {:optimizations :advanced}
                      garden {:pretty-print false})
  identity)

(deftask development []
  (task-options! cljs {:optimizations :none}
                 cljs-repl {:nrepl-opts {:middleware ['cemerick.piggieback/wrap-cljs-repl]}}
                 reload {:on-jsload 'plus_days.app/init})
  identity)

(deftask dev
  "Simple alias to run application in development mode"
  []
  (comp (development)
        (run)))


(deftask testing []
  (set-env! :source-paths #(conj % "test/cljs"))
  identity)

;;; This prevents a name collision WARNING between the test task and
;;; clojure.core/test, a function that nobody really uses or cares
;;; about.
(ns-unmap 'boot.user 'test)

(deftask test []
  (comp (testing)
        (test-cljs :js-env :phantom
                   :exit?  true)))

(deftask auto-test []
  (comp (testing)
        (watch)
        (test-cljs :js-env :phantom)))
