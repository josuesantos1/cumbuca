(ns cumbuca.controllers.transactions 
  (:require [cumbuca.database.datomic.transactions :as datomic.transactions]))

(defn create
  [transaction
   datomic]
  (datomic.transactions/insert transaction datomic)
  {:transaction-id (random-uuid)})
