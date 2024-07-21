(ns cumbuca.logics.transactions)

(defn have-money?
  [balance amount]
  (>= balance amount))
