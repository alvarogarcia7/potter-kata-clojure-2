(ns potter-kata2.core-test
  (:require [clojure.test :refer :all]
            [potter-kata2.core :refer :all]
            [midje.sweet :refer :all]))

(def cheap
  [1 1 2 2 3 3 4 5])

(defn
  price
  [books]
  1)

(facts
  "about books"
  (fact
    "price the books at the cheapest possible"
    (price cheap) => (+ (* 4 0.8 8) (* 4 0.8 8))))

