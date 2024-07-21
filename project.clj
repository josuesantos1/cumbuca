(defproject cumbuca "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [metosin/jsonista "0.3.8"]
                 [ring/ring-jetty-adapter "1.12.1"]
                 [metosin/reitit "0.7.1"]
                 [metosin/ring-swagger-ui "5.9.0"]
                 [com.fbeyer/init "0.2.96"]
                 [aero/aero "1.1.6"]]
  :main ^:skip-aot cumbuca.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
