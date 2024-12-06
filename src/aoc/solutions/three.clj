(ns aoc.solutions.three)


(defn- find-muls
  [matches matcher]
  (if-let [m (re-find matcher)]
    (recur (conj matches (first m)) matcher)
    matches))


(defn- strip-mul
  [s]
  (clojure.string/replace (clojure.string/replace s #"(mul\()" "") #"\)" ""))


(defn- nums
  [s]
  (clojure.string/split s #","))


(defn- *s
  [s]
  (map Integer/parseInt s))


(defn solve
  [input]
  (let [matcher (re-matcher #"((mul+\([0-9,]+\)))" (first input))
        muls (find-muls [] matcher)
        m (->> muls
               (map strip-mul)
               (map nums)
               (map *s)
               (map #(reduce * %))
               (reduce +))]


    {:mult m}))
