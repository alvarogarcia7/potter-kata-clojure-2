(ns potter-kata2.core-test-direct
  (:require [potter-kata2.core-test :only [:fitness-fn]]))

;; Idea: add the elements, one by one, to the group where discount is maximized
;; data flow
;; [[]], [1 2 3] ; remaining books
;; [[1 2] [3 3]], [1] ; best set, remaining books


(def cheap
  [1 1 2 2 3 3 4 5])

(defn
  select-max
  [coll]
  (first (filter #(= (first %) (apply max (map first coll))) coll)))

;; ele = 1
;; group = [[1 2]]
(defn generate-candidates
  "docstring"
  [ele group]
  (map (fn [group] {:fit (potter-kata2.core-test/fitness-fn group) :group group})
       (map (fn [a] [a])
            (candidates ele group))))

(defn
  candidates
  [ele groups]
  (concat [[ele]] (map #(conj % ele) groups)))

(defn assign
  [ele groups]
  (map #(-> [potter-kata2.core-test/fitness-fn %] %) candidates))

(defn
  xx
  [coll]
  (reduce (fn [groups ele] (assign ele groups)) coll))
