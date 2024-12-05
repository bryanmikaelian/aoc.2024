(ns aoc.solutions.three)


(defn- find-muls
  [matches matcher]
  (let [m (re-find matcher)]
    (if (seq m)
      (recur (conj matches (first m)) matcher)
      matches)))


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
  (let [i (filter seq input)
        ms (clojure.string/join #"\n" i)
        matcher (re-matcher #"(mul+\(\d{1,3}(,\d{1,3})\))" ms)
        muls (find-muls [] matcher)
        m (->> muls
               (map strip-mul)
               (map nums)
               (map *s)
               (map #(reduce * %))
               (reduce +))]


    {:mult m}))


(re-find (re-matcher #"(mul+\((\d{1,3}(,\d{1,3}))+\))" "mul(43,1)"))
