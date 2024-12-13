(ns aoc.solutions.three)


(defn- strip-mul
  [s]
  (clojure.string/replace (clojure.string/replace s #"(mul\()" "") #"\)" ""))


(defn- nums
  [s]
  (clojure.string/split s #","))


(defn- *s
  [s]
  (map Integer/parseInt s))


(defn- valid-ops
  [valid state ops]
  (if (empty? ops)
    valid
    (let [current-op (first ops)
          next-ops (next ops)]

      (if (= "do()" current-op)
        (recur valid true next-ops)
        (if (or (false? state) (= "don't()" current-op))
          (recur valid false next-ops)
          (recur (conj valid current-op) true next-ops))))))


(defn- sum
  [re s]
  (->> (re-seq re s)
       (valid-ops [] true)
       (map strip-mul)
       (map nums)
       (map *s)
       (map #(reduce * %))
       (reduce +)))


(defn solve
  [input]
  (let [i [(clojure.string/join "\n" input)]
        m (reduce + (map #(sum #"mul\(\d{1,3},\d{1,3}\)" %) i))
        m2 (reduce + (map #(sum #"do\(\)|don't\(\)|mul\(\d{1,3},\d{1,3}\)" %) i))]

    {:simple m
     :advance m2}))


(defn f
  [s]
  (remove #(clojure.string/includes? "don't" %) s))


(def d1 (re-seq #"mul\(\d{1,3},\d{1,3}\)" "mul(43,1)don't()mul(1,1)do()mul(2,2)"))
(def d2 (re-seq #"do\(\)|don't\(\)|mul\(\d{1,3},\d{1,3}\)" "mul(43,1)don't()mul(1,1)do()mul(2,2)"))
