package ru.skillbranch.devintensive.extensions
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {

    var time = this.time

    time +=
        when (units) {
            TimeUnits.SECOND -> value * SECOND
            TimeUnits.MINUTE -> value * MINUTE
            TimeUnits.HOUR -> value * HOUR
            TimeUnits.DAY -> value * DAY
        }

    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {

    val prf: String
    var timePerf = ""

    var delta = date.time - this.time // если больше 0 значит date в прошлом.
    var past = false
    if (delta < 0) {
        delta = this.time - Date().time
        past = true
    }


    when {
        delta > 360000L * 60 * 60 * 24 -> {
            timePerf = if (!past) "более года назад" else "более чем через год"
        }

        delta in 26000L * 60 * 60 until (360000L * 60 * 60 * 24) -> {
            prf = TimeUnits.DAY.plural((delta / 86_400_000).toInt())
            timePerf = if (!past) "$prf назад" else "через $prf"
        }

        delta in 22000L * 60 * 60 until (26000L * 60 * 60) -> {
            timePerf = if (!past) "день назад" else "через день"
        }

        delta in 75000L * 60 until (22000L * 60 * 60) -> {
            prf = TimeUnits.HOUR.plural((delta / 3_600_000).toInt())
            timePerf = if (!past) "$prf назад" else "через $prf"
        }

        delta in 45000L * 60 until (75000L * 60) -> {
            timePerf = if (!past) "час назад" else "через час"
        }

        delta in 75000L until (45000L * 60) -> {
            prf = TimeUnits.MINUTE.plural((delta / 60_000).toInt())
            timePerf = if (!past) "$prf назад" else "через $prf"
        }

        delta in 45000L until 75000L -> {
            timePerf = if (!past) "минуту назад" else "через минуту"
        }

        delta in 1000L until 45000L -> {
            timePerf = if (!past) "несколько секунд назад" else "через несколько секунд"
        }

        delta in 0L until 1000L -> {
            timePerf = "только что"
        }
    }
    return timePerf
}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {

        val rem = getLastNum(value)
        var perf = ""

        if (this == SECOND) {
            when (rem) {
                1 -> perf = "секунду"
                in 2..4 -> perf = "секунды"
                in 5..20 -> perf = "секунд"
            }
        }

        if (this == MINUTE) {
            when (rem) {
                1 -> perf = "минуту"
                in 2..4 -> perf = "минуты"
                in 5..20 -> perf = "минут"
            }
        }

        if (this == HOUR) {
            when (rem) {
                1 -> perf = "час"
                in 2..4 -> perf = "часа"
                in 5..20 -> perf = "часов"
            }
        }

        if (this == DAY) {
            when (rem) {
                1 -> perf = "день"
                in 2..4 -> perf = "дня"
                in 5..20 -> perf = "дней"
            }
        }

        return "$value $perf"
    }

    private fun getLastNum(value: Int): Int {

        if (value < 20) {
            return value
        }

        val valueStr = value.toString()
        return valueStr[valueStr.lastIndex].toString().toInt()
    }
}