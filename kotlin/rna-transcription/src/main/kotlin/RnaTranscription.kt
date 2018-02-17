
fun transcribeToRna(dna: String): String {
    val rna = StringBuilder()
    for (x in dna) {
        val r = when (x) {
            'G' -> 'C'
            'C' -> 'G'
            'T' -> 'A'
            'A' -> 'U'
            else -> {
                throw IllegalStateException("Unknown DNA")
            }
        }
        rna.append(r)
    }
    return rna.toString()
}
