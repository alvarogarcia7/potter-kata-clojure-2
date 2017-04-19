(ns potter-kata2.core-test-direct
  (:require [potter-kata2.core-test :only [:fitness-fn]]))

;; Idea: add the elements, one by one, to the group where discount is maximized
;; data flow
;; [], [1 2 3] ; best set(empty), remaining books
;; [[1 2] [3 3]], [1] ; best set, remaining books
;; [[1 2] [3 3 1]], [] ; best set, remaining books.
;;       computation is finished when the remaining books collection is empty


(defn price
  "calculates the cheapest price for the book set, splitting in series to apply the maximum discount"
  ([books]
   (price [] books))
  ([best-set remaining-books]
    (if (empty? remaining-books)
      best-set
      (let [current-book (first remaining-books)
            new-remaining-books (rest remaining-books)
            new-best-set (add-to-set best-set current-book)]
        (price new-best-set new-remaining-books)))))

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
