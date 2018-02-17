(ns word-count
  (:require [clojure.string :as str]))

(defn find-valid-words [words-lst]
  (filter some? (map #(re-find #"[a-zA-Z0-9]+" %) words-lst)))

(defn split-words [words-str]
  (str/split words-str #" "))

(defn word-count [words-str]
  (-> words-str
      str/lower-case
      split-words
      find-valid-words
      frequencies))
