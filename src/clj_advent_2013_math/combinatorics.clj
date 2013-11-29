(ns clj-advent-2013-math.combinatorics
  (require [clojure.math.combinatorics :as combo]))


;; selections

(->> (combo/selections [1 5 10 50 100 500] 4)
     (count ,,))

(->> (combo/selections [1 5 10 50 100 500] 4)
     (drop 100 ,,)
     (take 3 ,,))

(->> (combo/selections [1 5 10 50 100 500] 4)
     (map #(reduce + %) ,,)
     (drop 100 ,,)
     (take 3 ,,))

(->> (combo/selections [1 5 10 50 100 500] 4)
     (map #(reduce + %) ,,)
     (into #{} ,,)
     (sort ,,))

(->> (combo/selections [1 5 10 50 100 500] 4)
     (map #(reduce + %) ,,)
     (into #{} ,,)
     (count ,,))



;; cartesian-product

(combo/cartesian-product [:s :d :h :c]
                         (concat [:A] (range 2 11) [:J :Q :K]))




