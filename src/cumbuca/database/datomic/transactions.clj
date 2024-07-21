(ns cumbuca.database.datomic.transactions
  (:require
   [datomic.api :as d]))

(defn insert [transaction datomic]
  (d/transact datomic [transaction]))
