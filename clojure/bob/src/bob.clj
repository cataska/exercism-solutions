(ns bob
  (:require [clojure.string :as str]))

(defn question? [s]
  (.endsWith s "?"))

(defn shout? [s]
  (.endsWith s "!"))

(defn nothing? [s]
  (= 0 (count (.replaceAll s " " ""))))

(defn yell? [s]
  (and (not (nothing? s))
       (= (.toUpperCase s) s)
       (not (= (re-find #"[A-Z]+" s) nil))))

(defn response-for [asks]
  (cond
    (nothing? asks) "Fine. Be that way!"
    (yell? asks) "Whoa, chill out!"
    (question? asks) "Sure."
    :else "Whatever.")
)
