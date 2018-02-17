(ns run-length-encoding)

(defn n-elts [elt n]
  (if (> n 1)
    (list n elt)
    elt))

(defn compr [elt n lst]
  (if (empty? lst)
    (list (n-elts elt n))
    (let [next (first lst)]
      (if (= next elt)
        (compr elt (inc n) (rest lst))
        (cons (n-elts elt n)
              (compr next 1 (rest lst)))))))

(defn enc->str [x]
  (if (list? x)
    (str (nth x 0) (nth x 1))
    (str x)))

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [s]
  (if (empty? s)
    s
    (reduce str (map enc->str (compr (first s) 1 (rest s))))))

(defn parse-int [s]
  (try
    (Integer. (re-find  #"\d+" s ))
    (catch NumberFormatException e
      \A)))

(defn uncompress [elt n lst]
  (if (empty? lst)
    (list (n-elts elt n))
    (let [elt-num (parse-int (str elt))
          next (first lst)
          rest (rest lst)]
      (if (number? elt-num)
        (uncompress next
                    (+ (* n 10) elt-num)
                    rest)
        (cons (n-elts elt n)
              (uncompress next 0 rest))))))

(defn list-of [n elt]
  (if (zero? n)
    nil
    (repeat n elt)))

(defn dec->str [x]
  (if (list? x)
    (list-of (nth x 0) (nth x 1))
    x))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [s]
  (if (empty? s)
    s
    (reduce str (flatten (map dec->str (uncompress (first s) 0 (rest s)))))))
