(ns aoc.core
  (:require
    [aoc.solutions.one :as one]
    [aoc.solutions.three :as three]
    [aoc.solutions.two :as two]
    [aoc.util :refer [read-input-for-day]]))


(def solutions
  {:1 one/solve
   :2 two/solve
   :3 three/solve})


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
