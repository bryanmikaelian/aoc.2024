(ns aoc.solutions.one
  (:require
    [aoc.util :refer [read-input-for-day]]
    [clojure.string :as s]))


(defn- format
  [pairs]
  (->> (s/split pairs #" ")
       (filter #(seq %))
       (map #(Integer/parseInt %))))


(defn- split
  [pair]
  (first pair))


(defn- diff
  [pair]
  (let [p1 (first pair)
        p2 (last pair)]
    (if (> p1 p2)
      (- p1 p2)
      (- p2 p1))))


(defn- score
  [num freq])


(defn- similarity
  [d freqs]
  [1])


(defn solve
  [input]
  (let [d (map format input)
        d1 (sort (map first d))
        d2 (sort (map last d))
        sorted (map vector d2 d1)
        distance (reduce + (map diff sorted))
        s (reduce * (similarity d1 (frequencies d2)))]

    {:distance distance
     :similarity -1}))


(solve (read-input-for-day 1))
