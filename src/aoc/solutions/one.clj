(ns aoc.solutions.one
  (:require
    [clojure.string :as s]))


(defn- format
  [pairs]
  (->> (s/split pairs #" ")
       (filter #(seq %))
       (map #(Integer/parseInt %))))


(defn- split-sort-list
  [l f]
  (sort (map f l)))


(defn- diff
  [pair]
  (let [p1 (first pair)
        p2 (last pair)]
    (if (> p1 p2)
      (- p1 p2)
      (- p2 p1))))


(defn- distance
  [d]
  (reduce + (map diff d)))


(defn- calc-similarity
  [num freqs]
  (let [freq (get freqs num 0)]
    (* num freq)))


(defn- similarity
  [d freqs]
  (reduce + (map #(calc-similarity % freqs) d)))


(defn solve
  [input]
  (let [l (map format input)
        d1 (split-sort-list l first)
        d2 (split-sort-list l last)
        sorted (map vector d2 d1)
        d (distance sorted)
        s (similarity d1 (frequencies d2))]

    {:distance d
     :similarity s}))
