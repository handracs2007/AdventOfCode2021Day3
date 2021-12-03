import java.io.File
import kotlin.math.pow

fun String.toIntBinary(): Int {
    var base10 = 0

    for (i in this.length - 1 downTo 0) {
        base10 += 2.0.pow(this.length - 1 - i.toDouble()).toInt() * (this[i] - '0')
    }

    return base10
}

fun solvePart1(report: List<String>) {
    val length = report[0].length

    var gamma = ""
    var epsilon = ""
    for (i in 0 until length) {
        val partition = report.map { it[i] }.partition { it == '1' }
        val count1 = partition.first.size
        val count0 = partition.second.size

        if (count1 > count0) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
    }

    val gammaRate = gamma.toIntBinary()
    val epsilonRate = epsilon.toIntBinary()

    println("PART 1 ANSWER")
    println(gammaRate * epsilonRate)
}

fun solvePart2(report: List<String>) {
    val length = report[0].length

    val o2Copy = report.toMutableList()
    val co2Copy = report.toMutableList()
    for (i in 0 until length) {
        if (o2Copy.size > 1) {
            val partition = o2Copy.map { it[i] }.partition { it == '1' }
            val count1 = partition.first.size
            val count0 = partition.second.size

            if (count1 >= count0) {
                o2Copy.removeIf { it[i] == '0' }
            } else {
                o2Copy.removeIf { it[i] == '1' }
            }
        }

        if (co2Copy.size > 1) {
            val partition = co2Copy.map { it[i] }.partition { it == '1' }
            val count1 = partition.first.size
            val count0 = partition.second.size

            if (count1 >= count0) {
                co2Copy.removeIf { it[i] == '1' }
            } else {
                co2Copy.removeIf { it[i] == '0' }
            }
        }

        if (o2Copy.size == 1 && co2Copy.size == 1)
            break
    }

    val o2Rate = o2Copy[0].toIntBinary()
    val co2Rate = co2Copy[0].toIntBinary()

    println("PART 2 ANSWER")
    println(o2Rate * co2Rate)
}

fun main(args: Array<String>) {
    val report = File("input.txt").readLines()

    solvePart1(report)
    solvePart2(report)
}