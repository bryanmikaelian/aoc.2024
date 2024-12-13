(ns aoc.util
  (:require
    [clojure.java.io :as io]
    [clojure.string :as s]))


(defn- path-for-day
  [d]
  (str "day/" d "/input.txt"))


(defn- read-file
  [path]
  (io/resource path))


(defn read-input-for-day
  [d]
  (let [i (-> (path-for-day d)
              read-file
              io/reader
              line-seq)
        valid (filter seq i)]

    valid))
