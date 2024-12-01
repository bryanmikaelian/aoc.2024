(ns aoc.core
  (:require 
    [aoc.util :refer [read-input]]
    [aoc.solutions.one :as one]))


(def solutions {:1 one/solve})


(defn- find-solution-fn [d]
  (-> (str d)
      (keyword)
      (solutions)))

(defn solve [d]
  (let [f (find-solution-fn d)
        input (read-input d)]

    (f input)))
