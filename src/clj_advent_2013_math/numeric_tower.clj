(ns clj-advent-2013-math.numeric-tower
  (require [clojure.math.numeric-tower :as nt]))

;; expt
(nt/expt 10 3)

(nt/expt 10 100000)

(nt/expt 1.5 2.3)

(nt/expt 1.2 3456.7)

(nt/expt 1.2 34567.8)

(nt/expt 5 0)

(nt/expt 3/2 7/5)

(nt/expt 7 7/5)

(nt/expt 3/2 8)

;; abs
(nt/abs -100)

(nt/abs -3.5)

(nt/abs -4/5)


;; gcd lcm
(nt/gcd 12 16)

(nt/lcm 12 16)

(nt/lcm 3.8 4.2)

(nt/gcd 3/2 7/9)



;; floor, ceil, round
(map (juxt nt/floor nt/ceil nt/round) [3 3.2 3.5 3.7])


;; sqrt
(nt/sqrt 10)

(nt/sqrt 169)

(nt/exact-integer-sqrt 10)


;; prime num
(defn prime? [n]
  (let [[int-sqrt _] (nt/exact-integer-sqrt n)]
    (loop [test-nums (range 2 (inc int-sqrt))]
      (cond (empty? test-nums) true
            (zero? (rem n (first test-nums))) false
            :else (recur (next test-nums))))))

;; yet another
(defn prime2? [n]
  (let [[int-sqrt _] (nt/exact-integer-sqrt n)]
    (every? pos?
            (map (partial rem n) (range 2 (inc int-sqrt))))))

