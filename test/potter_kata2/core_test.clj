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
  (for [i cheap] [[i]]))

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
  find-maxes-one-generation
  [coll]
  (let [coll-and-fit (map #(-> [% (fitness-fn %)]) coll #_(map #(-> [%]) (split-in-groups-of-1 coll)))
        max-fit (reduce (fn [acc [_ fit]] (max fit acc)) 0 coll-and-fit)]
    (apply concat (map first (filter (fn [[_ fit]] (= max-fit fit)) coll-and-fit)))))

(defn
  candidates-next-gen
  [gen1]
  (->> (range 0 (count gen1))
    (map
      #(let [[a b] (split-at % gen1)
             selected (first b)
             rest- (concat a (rest b))]
         {:selected selected :rest rest- }))))

(defn
  find-single-xxx [candidates]
                 (find-maxes-one-generation (let [{selected :selected rest- :rest} candidates]
                                              (for [r rest-]
                                                [(into [] (concat (flatten selected) (flatten r)))]))))

(defn
  find-next-gen
  [generation]
  (letfn []
    (map find-single-xxx generation)
    ))

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

(->> cheap
     split-in-groups-of-1
     find-maxes-one-generation
     candidates-next-gen
     find-next-gen
     find-maxes-one-generation
     candidates-next-gen
     find-next-gen
     )
