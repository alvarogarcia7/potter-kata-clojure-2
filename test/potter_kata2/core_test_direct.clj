(ns potter-kata2.core-test-direct
  (:require [potter-kata2.core-test :only [:fitness-fn]]))

(def cheap
  [1 1 2 2 3 3 4 5])

(defn
  select-max
  [coll]
  (first (filter #(= (first %) (apply max (map first coll))) coll)))

(defn assign
  [ele groups]
  (let [candidates (concat [ele] (map #(conj % ele) groups))]
    (map #(-> [potter-kata2.core-test/fitness-fn %] %) candidates)))

(defn
  xx
  [coll]
  (reduce (fn [groups ele] (assign ele groups)) coll))
