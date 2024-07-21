(ns cumbuca.config.server
  (:require
   [malli.util :as mu]
   [muuntaja.core :as m]
   [reitit.coercion.malli]
   [reitit.dev.pretty :as pretty]
   [reitit.openapi :as openapi]
   [reitit.ring :as ring]
   [reitit.ring.coercion :as coercion]
   [reitit.ring.malli]
   [reitit.ring.middleware.exception :as exception]
   [reitit.ring.middleware.muuntaja :as muuntaja]
   [reitit.ring.middleware.parameters :as parameters]
   [reitit.ring.spec :as spec]
   [reitit.swagger :as swagger]
   [reitit.swagger-ui :as swagger-ui]
   [ring.adapter.jetty :as jetty]))

(def app
  (ring/ring-handler
   (ring/router
    [["/openapi.json"
      {:get {:no-doc true
             :openapi {:info {:title "Cumbuca Docs"
                              :description "Cumbuca Docs Api"
                              :version "0.0.1"}
                       :components {:securitySchemes {"auth" {:type :apiKey
                                                              :in :header
                                                              :name "api-key"}}}}
             :handler (openapi/create-openapi-handler)}}]
     ["/service"
      {:tags #{"service"}}
      ["/hello"
       {:get {:summary "Hello world!"
              :responses {200 {:body [:map [:version string?]]}}
              :handler (fn [_]
                         {:status 200
                          :body {:version "Hello world!"}})}}]]]

    {:validate spec/validate
     :exception pretty/exception
     :data {:coercion (reitit.coercion.malli/create
                       {:error-keys #{#_:type :coercion :in :schema :value :errors :humanized #_:transformed}
                        :compile mu/closed-schema
                        :strip-extra-keys true
                        :default-values true
                        :options nil})
            :muuntaja m/instance
            :middleware [swagger/swagger-feature
                         openapi/openapi-feature
                         parameters/parameters-middleware
                         muuntaja/format-negotiate-middleware
                         muuntaja/format-response-middleware
                         exception/exception-middleware
                         muuntaja/format-request-middleware
                         coercion/coerce-response-middleware
                         coercion/coerce-request-middleware]}})
   (ring/routes
    (swagger-ui/create-swagger-ui-handler
     {:path "/"
      :config {:validatorUrl nil
               :urls [{:name "swagger", :url "swagger.json"}
                      {:name "openapi", :url "openapi.json"}]
               :urls.primaryName "openapi"
               :operationsSorter "alpha"}})
    (ring/create-default-handler))))

(defn start []
  (jetty/run-jetty #'app {:port 3000, :join? false})
  (println "server running in port 3000"))

(comment
  (start))


