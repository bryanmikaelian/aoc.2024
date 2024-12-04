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
  (loop [xs d
         levels []]
    (if (or (nil? xs) (nil? (first (next xs))))
      levels
      (recur (next xs) (conj levels [(first xs) (first (next xs))])))))


(defn- build-level-from-pair
  [pair]
  (distinct (flatten pair)))


(def increasing +)
(def decreasing -)


(defn- valid-direction?
  [level]
  (or (= (sort-by increasing level) level)
      (= (sort-by decreasing level) level)))


(defn- distance
  [pair]
  (reduce - pair))


(defn- safe-distance?
  [level]
  (let [pairs (build-levels-pairs level)
        sorted (map #(sort-by - %) pairs)]
    (every? #(and (< (distance %) 4) (> (distance %) 0)) sorted)))


(defn- safe-level?
  [level]
  (if (seq level)
    (let [valid-direction (valid-direction? level)
          safe-distance (safe-distance? level)]
      (and valid-direction safe-distance))
    false))


(defn- fixable-distances
  [level]
  (let [pairs (build-levels-pairs level)]
    (filter #(> (distance %) 3) pairs)))


(defn- fixable-distance?
  [level]
  (< (count (fixable-distances level)) 2))


(defn- diff-directions
  [level]
  (let [diffs (map - (next level) level)
        backwards-diffs (filter neg? diffs)
        forward-diffs (filter pos? diffs)
        neutral-diffs (filter zero? diffs)
        increasing? (< (count backwards-diffs) (count forward-diffs))]
    ;; If the diffs are the same, we don't have a determined direction. Assume increasing list
    (if (= (count backwards-diffs) (count forward-diffs))
      forward-diffs
      (if (seq neutral-diffs)
        neutral-diffs
        (if increasing?
          backwards-diffs
          forward-diffs)))))


(defn- fixable-direction?
  [level]
  (= (count (map abs (diff-directions level))) 1))


(defn fixable-level?
  [level]
  (or (fixable-distance? level) (fixable-direction? level)))


(defn- fix-distance
  [level d]
  (let [invalid-num (apply max (first (filter #(= (abs (distance %)) d) (build-levels-pairs level))))
        fixed (filter #(not (= % invalid-num)) level)]
    fixed))


(defn- fix-order
  [level d]
  (let [pairs (build-levels-pairs level)
        invalid (first (filter #(> (apply - %) d) pairs))
        fixed (filter #(not (= % invalid)) pairs)]
    (build-level-from-pair fixed)))


(defn attempt-level-fix
  [level total-fixes]
  ;; We're only allowed to fix a level at most 1 time
  ;; If we were able to fix it at least once, return that level
  ;; Otherwise, find the first number that causes an invalid order or distance and remove it
  ;; The level still may require more fixes
  (if (> total-fixes 0)
    level
    ;; else: F Attempt to fix this level by removing the pairing that causes the largest distance
    (let [largest-distance (apply max (map - (next level) level))]
      ;; If the largest distance is < 4, then the distances are finebut
      ;; ;; Check if there is an order issue and remove that number
      (if (< largest-distance 4)
        (if-let [d (first (diff-directions level))]
          (recur (fix-order level d) (inc total-fixes))
          level)
        (recur (fix-distance level largest-distance) (inc total-fixes))))))


(defn solve
  [input]
  (let [levels (map format input)
        safe-levels (filter safe-level? levels)
        unsafe-levels (filter #(not (safe-level? %)) levels)
        candidates (filter fixable-level? unsafe-levels)
        safe-levels-fixed (filter safe-level? (map #(attempt-level-fix % 0) candidates))]
    {:safe (count safe-levels)
     :fixed (+ (count safe-levels) (count safe-levels-fixed))}))


(def d [58 59 62 63 64 63])
(def dx (map - (next d) d))
(def dxx (first (diff-directions d)))
(def p (build-levels-pairs d))
(build-level-from-pair (fix-order d dxx))
(filter #(not (= % [64 63])) p)

(apply - (second p))

(apply min '(-5 -3 -1))

(apply - [64 63])

(> 1 0)
