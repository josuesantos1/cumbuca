(ns cumbuca.contracts.schemas.transaction)

(def transaction
  [:map
   [:transaction/sender uuid?]
   [:transaction/receiver string?] ;; TODO: change to uuid?
   [:transaction/amount int?]
   [:transaction/transacted-at inst?]
   [:transaction/status keyword?]])
