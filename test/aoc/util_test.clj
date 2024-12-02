(ns aoc.util-test
  (:require
    [aoc.util :as util]
    [clojure.test :refer [deftest is testing]]))


(deftest test-read-input
  (testing "valid input"
    (is (= '("test") (util/read-input-for-day 0))))
  (testing "invalid input"
    (is (thrown? IllegalArgumentException (util/read-input-for-day 99)))))
