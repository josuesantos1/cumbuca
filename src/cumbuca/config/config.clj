(ns cumbuca.config.config
  (:require
   [aero.core :as aero]
   [clojure.java.io :as io]))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn load-config
  {:init/name ::config
   :init/tags [:app/config]}
  []
  (-> (io/resource "config.edn")
      (aero/read-config)))


