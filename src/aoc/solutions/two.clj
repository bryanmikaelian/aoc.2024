(ns aoc.solutions.two
  (:require
    [clojure.string :as s]))


(defn- format
  [xs]
  (->> (s/split xs #" ")
       (filter #(seq %))
       (map #(Integer/parseInt %))))


(defn- build-levels-pairs
  [d]
  (loop [xs d, levels []]
    (if (or (nil? xs) (nil? (first (next xs))))
      levels
      (recur (next xs) (conj levels [(first xs) (first (next xs))])))))


(defn- diff
  [level]
  (abs (reduce - level)))


(def increasing +)
(def decreasing -)


(defn- valid-direction?
  [level]
  (or (= (sort-by increasing level) level)
      (= (sort-by decreasing level) level)))


(defn- safe-level?
  [level]
  (if-not
    false
    (every? #(and (< (diff %) 4) (> (diff %) 0)) level)))


(defn solve
  [input]
  (let [d (map format input)
        d1 (filter valid-direction? d)
        levels (map build-levels-pairs d1)
        safe-levels (filter safe-level? levels)]
    (prn (first safe-levels))
    {:safe (count safe-levels)}))
