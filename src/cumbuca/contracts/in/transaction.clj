(ns cumbuca.contracts.in.transaction)

(def transaction
  [:map
   [:sender uuid?]
   [:receiver string?]
   [:amount int?]])
