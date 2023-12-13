import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)


fun List<String>.to2DArray() = Array(size) {
    get(it).toCharArray()
}

fun Array<CharArray>.to2DSize() = size to get(0).size

fun <R> List<String>.parts(map: (List<String>) -> R): List<R> = buildList {
    var curr = ArrayList<String>()
    for (s in this@parts) {
        if (s == "") {
            add(map(curr))
            curr = ArrayList()
            continue
        }
        curr.add(s)
    }
    if (curr.isNotEmpty()) {
        add(map(curr))
    }
}