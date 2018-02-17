(ns isbn-verifier
  (:require [clojure.string :as str]))

(def number-digit #{\0 \1 \2 \3 \4
                    \5 \6 \7 \8 \9})

(defn digit->number [isbn-digit]
  ({\0 0, \1 1, \2 2, \3 3, \4 4,
    \5 5, \6 6, \7 7, \8 8, \9 9,
    \X 10} isbn-digit))

(defn calc-isbn [isbn-number]
  (loop [result 0
         count 10
         isbn isbn-number]
    (if (= count 0)
      result
      (recur (+ result (* count (first isbn)))
             (dec count)
             (rest isbn)))))

(defn is-X-only-as-check-digit [isbn-lst]
  (loop [result true
         lst isbn-lst
         count 0]
    (if (empty? lst)
      result
      (if (and (not= (number-digit (first lst)) (first lst)) (not= count 9))
        false
        (recur result (rest lst) (inc count))))))

(defn isbn? [isbn]
  (let [isbn-lst (filter #(not= \- %) isbn)
        last-digit (last isbn-lst)
        valid-digit #{\0 \1 \2 \3 \4
                      \5 \6 \7 \8 \9
                      \X}]
    (if (or (not= (count isbn-lst) 10)
            (not= (valid-digit last-digit) last-digit)
            (not (is-X-only-as-check-digit isbn-lst)))
      false
      (= 0 (mod (calc-isbn (map digit->number isbn-lst))
                11)))))
