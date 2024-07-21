(ns cumbuca.unit.logics.transactions-test
  (:require
   [clojure.test :refer [deftest is testing]]
   [cumbuca.logics.transactions :as logics.transactions]))

(deftest have-money?-test
  (testing "Check if have money to transaction"
    (is (logics.transactions/have-money? 100 10))
    (is (logics.transactions/have-money? 100 100)))
  (testing "Check if NOT have money to transaction"
    (is (not (logics.transactions/have-money? 10 100)))))

