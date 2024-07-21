(ns cumbuca.contracts.schemas.customer)

(def customer
  [:map
   [:name string?]
   [:email string?]
   [:password string?]
   [:tax-id string?]
   [:balance int?]])
