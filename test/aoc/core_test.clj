(ns aoc.core-test
  (:require
    [aoc.core :as core]
    [clojure.test :refer [deftest is testing]]))


(deftest aoc
  (testing "AOC 2024"
    (testing "day 1"
      (let [{:keys [distance similarity]} (core/solve-for-day 1)]
        (is (= 1580061 distance))
        (is (=  23046913 similarity))))
    (testing "day 2"
      (let [{:keys [safe fixed]} (core/solve-for-day 2)]
        (is (= 402 safe))
        (is (= -1000 fixed))))

    (testing "day 3"
      (let [{:keys [simple advance]} (core/solve-for-day 3)]
        (is (= 181345830 simple))
        (is (= -1000 advance))))
    (testing "day 4"
      (let [{:keys []} (core/solve-for-day 4)]
        (is (= -1000 0))
        (is (= -1000 0))))))
