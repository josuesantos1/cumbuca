(ns cumbuca.config.database
  (:require [datomic.api :as d]
            [cumbuca.contracts.schemas.customer :refer [customer]]))

(def ^:private datomic-schemas
  (->> [customer]
       (map rest)))

(defn create-schema
  [[field type]]
  {:db/ident       field
   :db/unique      :db.unique/identity
   :db/valueType   type
   :db/cardinality :db.cardinality/one})

(defn coerce-type
  [[field type]]
  (cond (= type string?) [field :db.type/string]
        (= type int?) [field :db.type/long]))

(defn coerce-types
  [field]
  (->> field
       (map coerce-type)
       (map create-schema)))

(defn- create-datomic-schema
  [datomic]
  (->> datomic-schemas
       (map coerce-types)
       flatten
       (d/transact datomic)))

(defn datomic
  {:init/tags [:datomic/connect]
   :init/inject [[:get :app/config :datomic-uri]]}
  [uri]
  (let [_ (d/create-database uri)
        datomic (d/connect uri)]
    (create-datomic-schema datomic)
    datomic))
