(ns cumbuca.config.database
  (:require [datomic.api :as d]))

(defn database
  {:init/tags [:datomic/connect]
   :init/inject [[:get :app/config :datomic-uri]]}
  [uri]
  (d/create-database uri)
  (d/connect uri))
