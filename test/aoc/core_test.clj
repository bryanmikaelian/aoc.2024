(ns aoc.core-test
  (:require
    [aoc.core :as core]
    [clojure.test :refer [deftest is testing]]))


(deftest aoc
  (testing "AOC 2024"
    (testing "day 1"
      (let [{:keys [distance similarity]} (core/solve-for-day 1)]
        (is (> distance -1))
        (is (> similarity -1))))))
