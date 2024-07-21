(ns cumbuca.adapters.transactions)

(defn in->model
  [{:keys [sender receiver amount]}]
  {:transaction/sender        sender
   :transaction/receiver      receiver
   :transaction/amount        amount
   :transaction/transacted-at (java.util.Date.)
   :transaction/status        :transaction.type/in-progress})

