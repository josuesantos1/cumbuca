(ns cumbuca.config.database
  (:require
   [cumbuca.contracts.schemas.transaction :refer [transaction]]
   [datomic.api :as d]))

(def ^:private datomic-schemas
  (->> [transaction]
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
        (= type int?) [field :db.type/long]
        (= type keyword?) [field :db.type/keyword]
        (= type uuid?) [field :db.type/uuid]
        (= type inst?) [field :db.type/instant]))

(defn coerce-types
  [field]
  (->> field
       (map coerce-type)
       (map create-schema)))

(defn create-datomic-schema
  [datomic]
  (try (->> datomic-schemas
            (map coerce-types)
            flatten
            (d/transact datomic))
       (catch Exception e
         (prn (.getMessage e)))))

(defn datomic
  {:init/tags [:datomic/connect]
   :init/inject [[:get :app/config :datomic-uri]]}
  [uri]
  (let [_ (d/create-database uri)
        datomic (d/connect uri)]
    (create-datomic-schema datomic)
    datomic))
