(ns aoc.util
  (:require
    [clojure.java.io :as io]))


(defn read-input-for-day
  [d]
  (-> (io/resource (str "day/" d "/input.txt"))
      io/reader
      slurp))
