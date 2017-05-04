(ns potter-kata2.core-test-direct
  (:require [potter-kata2.core-test :only [:fitness-fn]]))

;; Idea: add the elements, one by one, to the group where discount is maximized
;; data flow
;; [], [1 2 3] ; best set(empty), remaining books
;; [[1 2] [3 3]], [1] ; best set, remaining books
;; [[1 2] [3 3 1]], [] ; best set, remaining books.
;;       computation is finished when the remaining books collection is empty


(defn
  candidates
  [ele groups]
  (concat [[ele]] (map #(conj % ele) groups)))

(defn generate-candidates
  [ele group]
  (map (fn [group] {:fit (potter-kata2.core-test/fitness-fn group) :group group})
       (map (fn [a] [a])
            (candidates ele group))))

(defn
  max-fitness
  [xxx]
  (apply max (map :fit xxx)))

(defn
  select-max
  [xxx]
  (let [max-fit (max-fitness xxx)]
    (filter #(= max-fit (:fit %)) xxx)))

(defn add-to-set
  "creates the cheapes set of books given the previous generation of sets, plus the new book"
  [set element]                                             ; set = [[1 2] [2]], element = 1
    (let [candidates (generate-candidates element set)]
      (:group (select-max candidates))))

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



(defn assign
  [ele groups]
  (map #(-> [potter-kata2.core-test/fitness-fn %] %) candidates))

(defn
  xx
  [coll]
  (reduce (fn [groups ele] (assign ele groups)) coll))

; (next-gen 1 [[1] [2]])
; ([[2 1]])
(defn
  next-gen
  [ele group]
  (map :group (select-max (map (fn [group] {:fit (potter-kata2.core-test/fitness-fn group) :group group})
                           (map (fn [a] [a])
                                (candidates ele group))))))


#_(map (fn [group] {:fit (potter-kata2.core-test/fitness-fn group) :group group})
     (map (fn [a] [a])
            (candidates 1 [[1] [2]])))
