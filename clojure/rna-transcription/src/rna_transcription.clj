(ns rna-transcription)

(defn to-rna1 [dna]
  (if-let [[k v] (find {\G \C, \C \G, \T \A, \A \U} dna)]
    v
    (throw (AssertionError. dna))))

(defn to-rna [dna]
  (apply str (map to-rna1 dna)))
