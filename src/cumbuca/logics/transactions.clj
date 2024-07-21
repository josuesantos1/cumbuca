(ns cumbuca.logics.transactions)

(defn have-money?
  [amount balance]
  (>= balance amount))

