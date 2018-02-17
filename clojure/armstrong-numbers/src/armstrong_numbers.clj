(ns armstrong-numbers)

(defn digit [number n]
  (int (rem (/ number (Math/pow 10 n)) 10)))

(defn armstrong-number [n]
  (let [len (count (str n))]
    (loop [cnt 0
           result 0]
      (if (>= cnt len)
        result
        (recur (inc cnt) (+ result (int (Math/pow (digit n cnt) len))))))))

(defn armstrong? [n]
  (= n (armstrong-number n)))
