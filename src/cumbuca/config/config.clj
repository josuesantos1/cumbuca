(ns cumbuca.config.config
  (:require [clojure.java.io :as io]
            [aero.core :as aero]))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn load-config
  {:init/name ::config
   :init/tags [:app/config]}
  []
  (-> (io/resource "config.edn")
      (aero/read-config)))


