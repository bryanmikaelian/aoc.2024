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


(defn- safe-pair?
  [pair]
  (every? #(and (< (diff %) 4) (> (diff %) 0)) pair))


(defn- broken-pair?
  [pair]
  (> (diff pair) 3))


(defn- fixable-level?
  [level]
  (= 1 (count (filter broken-pair? level))))


(defn- fix-level
  [level]
  (if (fixable-level? level)
    (filter #(not (broken-pair? %)) level)
    level))


(defn solve
  [input]
  (let [d (map format input)
        levels (filter valid-direction? d)
        pairs (map build-levels-pairs levels)
        safe-pairs (filter safe-pair? pairs)
        fixable-safe-pairs (filter safe-pair? (map fix-level levels))]
    {:safe (count safe-pairs)
     :fixed (count fixable-safe-pairs)}))


;; (filter #(not (broken-pair? %)) (build-levels-pairs [1 2 3 9 10 11]))
