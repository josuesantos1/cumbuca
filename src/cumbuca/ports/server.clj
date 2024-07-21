(ns cumbuca.ports.server)

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(def ^{:init/tags [:reitit/route-data]} customer-routes
  ["/customers"
   {:tags #{"customers"}}
   ["/"
    {:post {:summary "Create a new customer"
            :parameters {:body [:map
                                [:name string?]
                                [:email string?]
                                [:password string?]
                                [:tax-id string?]]}
            :responses {200 {:body [:map
                                    [:name string?]
                                    [:email string?]]}}
            :handler (fn [{{{:keys [name email]} :body} :parameters}]
                       {:status 200
                        :body {:name name
                               :email email}})}}]])

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(def ^{:init/tags [:reitit/route-data]} auth-routes
  ["/auth"
   {:tags #{"Authorization"}}
   ["/login"
    {:post {:summary "Login"
            :parameters {:body [:map
                                [:email string?]
                                [:password string?]]}
            :responses {200 {:body [:map
                                    [:token string?]]}}
            :handler (fn [{{{:keys [name email]} :body} :parameters}]
                       {:status 200
                        :body {:name name
                               :email email}})}}]])

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(def ^{:init/tags [:reitit/route-data]} transaction-routes
  ["/transactions"
   {:tags #{"Transactions"}}
   ["/"
    {:post {:summary "Create a new transaction"
            :parameters {:body [:map
                                [:sender uuid?]
                                [:receiver string?]
                                [:amount int?]]}
            :responses {200 {:body [:map
                                    [:name string?]
                                    [:email string?]]}}
            :handler (fn [{{{:keys [name email]} :body} :parameters}]
                       {:status 200
                        :body {:name name
                               :email email}})}
     :put {:summary "Chargeback a transaction"
           :parameters {:body [:map
                               [:transaction-id uuid?]]}
           :responses {200 {:body [:map
                                   [:transaction-id uuid?]]}}
           :handler (fn [{{{:keys [transaction-id]} :body} :parameters}]
                      {:status 200
                       :body {:transaction-id transaction-id}})}
     :get {:summary "View all transactions"
           :parameters {:query [:map
                                [:start-date string?]
                                [:end-date string?]]}
           :responses {200 {:body [:vector
                                   [:map
                                    [:transaction-id uuid?]]]}}
           :handler (fn [{{{:keys [start-date end-date]} :query} :parameters}]
                      (prn start-date end-date)
                      {:status 200
                       :body [{:transaction-id (random-uuid)}
                              {:transaction-id (random-uuid)}
                              {:transaction-id (random-uuid)}]})}}]])

