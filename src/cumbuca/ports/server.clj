(ns cumbuca.ports.server)

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

