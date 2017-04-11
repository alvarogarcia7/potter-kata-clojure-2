(ns potter-kata2.core-test
  (:require [midje.sweet :refer :all]))

(def cheap
  [1 1 2 2 3 3 4 5])

(defn
  discount
  [coll]
  (let [freq (frequencies (concat coll))
        all-different (not-any? (fn [pair] (>= (val pair) 2)) freq)]
    (if all-different
      (case (count coll)
        1 0
        2 5
        3 10
        4 20
        5 25)
      0)))

(defn
  split-in-groups-of-1
  [cheap]
  (for [i cheap] [i]))

(defn
  pairs
  [coll]
  (mapv #(-> [%1 %2]) coll (rest coll)))

#_(defn grouped-all?
  [coll-of-groups]
  (not-any? (fn [group] (= 1 (count group))) coll-of-groups))

(defn
  fitness-fn
  [coll-of-groups]
  (->>
    coll-of-groups
    (map discount)
    (apply +)))

(defn
  price
  [books]
  1)

(facts
  "about books"
  (fact
    "price the books at the cheapest possible"
    (price cheap) => (+ (* 4 0.8 8) (* 4 0.8 8)))
  (fact
    "about discounts"
    (discount [1]) => 0
    (discount [1 2]) => 5
    (discount [1 2 3]) => 10
    (discount [1 2 3 4]) => 20
    (discount [1 2 3 4 5]) => 25))

