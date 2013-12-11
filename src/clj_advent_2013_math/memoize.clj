(ns clj-advent-2013-math.memoize
  (require [clojure.core.memoize :as memo]))


;; sample func
;; returns twice of arg.
(defn twice [x]
  (Thread/sleep 10)
  (* x 2))


;; basic usage
(def fifo-twice (memo/fifo twice))

(def fifo3-twice (memo/fifo twice :fifo/threshold 3))

(def lru3-twice (memo/lru twice :lru/threshold 3))

(def lu3-twice (memo/lu twice :lu/threshold 3))

(defn make-base [seed]
  (into {}
        (for [[k v] seed]
          [k (reify
               clojure.lang.IDeref
               (deref [this] v))])))

