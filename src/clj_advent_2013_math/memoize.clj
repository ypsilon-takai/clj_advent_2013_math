(ns clj-advent-2013-math.memoize
  (require [clojure.core.memoize :as memo]))


;; sample func
;; returns twice of arg.
(defn twice [x]
  (Thread/sleep 10)
  (* x 2))


;; referential transparency
(defn make-acc []
  (let [acc (atom 0)]
    (fn [n]
      (swap! acc + n)
      @acc)))

;; basic usage
(def fifo-twice (memo/fifo twice))

(def fifo3-twice (memo/fifo twice :fifo/threshold 3))

(def lru3-twice (memo/lru twice :lru/threshold 3))

(def lu3-twice (memo/lu twice :lu/threshold 3))

(def ttl10-twice (memo/ttl twice :ttl/threshold 10000))

;; fibonatti

(defn fibo [n]
  (if (< n 2)
    1N
    (+ (fibo (- n 2))
       (fibo (- n 1)))))

(def fibo (memo/fifo fibo :fifo/threshold 3))

(->> (map fibo (iterate inc 0N))  
     (drop 10000)
     (first))


;; base argument

(defn make-base [seed]
  (into {}
        (for [[k v] seed]
          [k (reify
               clojure.lang.IDeref
               (deref [this] v))])))

(def fifo-twice
  (memo/fifo twice (make-base {[3] "fizz"})))

