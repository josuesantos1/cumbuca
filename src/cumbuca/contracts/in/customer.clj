(ns cumbuca.contracts.in.customer)

(def customer
  [:map
   [:name string?]
   [:email string?]
   [:password string?]
   [:tax-id string?]])
