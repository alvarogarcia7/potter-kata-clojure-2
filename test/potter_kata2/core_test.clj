(ns potter-kata2.core-test
  (:require [clojure.test :refer :all]
            [potter-kata2.core :refer :all]
            [midje.sweet :refer :all]))

(def cheap
  {:1 2 :2 2 :3 2 :4 1 :5 1})

(defn
  price
  [book-frequencies]
  1)

(facts
  "about books"
  (fact
    "price the books at the cheapest possible"
    (price cheap) => (+ (* 4 0.8 8) (* 4 0.8 8))))

