(ns cumbuca.core
  (:gen-class)
  (:require
   [init.core :as init]
   [init.discovery :as discovery]))

(def config (discovery/static-scan '[cumbuca]))

(defn -main
  "I don't do a whole lot ... yet."
  [& _args]
  (-> (init/start config)
      (init/stop-on-shutdown)))
