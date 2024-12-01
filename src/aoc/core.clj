(ns aoc.core
  (:require
    [aoc.solutions.one :as one]
    [aoc.util :refer [read-input-for-day]]))


(def solutions {:1 one/solve})


(defn- find-solution-fn-for-day
  [d]
  (-> (str d)
      (keyword)
      (solutions)))


(defn solve-for-day
  [d]
  (let [f (find-solution-fn-for-day d)
        input (read-input-for-day d)]

    (f input)))
