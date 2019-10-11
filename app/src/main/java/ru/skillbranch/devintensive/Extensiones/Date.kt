package ru.skillbranch.devintensive.Extensiones

import ru.skillbranch.devintensive.Extensiones.TimeUnits.*
import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60* SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

private val secondsp = listOf<String>(
    "секунд",
    "секунда",
    "секунды",
    "секунд"
)

val minutesp = listOf<String>(
    "минут",
    "минута",
    "минуты",
    "минут"
)

val hoursp = listOf<String>(
    "часов",
    "час",
    "часа",
    "часов"

)

val daysp = listOf<String>(
    "дней",
    "день",
    "дня"
)

fun Date.format(pattern:String="HH:mm:ss dd.MM,yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits=TimeUnits.SECOND): Date{
    var time=this.time
    time += when (units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()):String{
    //TODO("not implemented") //Домашнеее задание определить разницу между текущей датой и датой в днях или часах
    // например "10 секунд назад, четыре дня назад, с учетом склонения числительных
    val diffValue = Date().time - this.time
    val humanDate:Pair<Long, TimeUnits>
    if (diffValue >= DAY )  humanDate = Pair(diffValue / DAY , TimeUnits.DAY)
    else if (diffValue >= HOUR ) humanDate = Pair(diffValue / HOUR , TimeUnits.HOUR)
    else if (diffValue >= MINUTE ) humanDate = Pair(diffValue / MINUTE , TimeUnits.MINUTE)
    else humanDate = Pair(diffValue / SECOND , TimeUnits.SECOND)
    return humanDate.first.toString() +  " " + humanDate.second.plural(humanDate.first) //plural(humanDate.first, humanDate.second)
}

enum class TimeUnits {
    SECOND{
        override fun plural(volume: Long)=when(volume%10){
            1L -> "секунду"
            in 2L .. 4L -> "секунды"
            else -> "секунд"
        }
    },
    MINUTE {
        override fun plural(volume: Long): String=when(volume%10) {
            1L -> "минуту"
            in 2L..4L -> "минуты"
            else -> "минут"
        }
    },
    HOUR {
        override fun plural(volume: Long): String=when(volume%10) {
            1L -> "час"
            in 2L..4L -> "часа"
            else -> "часов"
        }
    },
    DAY {
        override fun plural(volume: Long): String=when(volume%10) {
            1L -> "день"
            in 2L..4L -> "дня"
            else -> "дней"
        }
    };

    abstract fun plural(volume :Long):String
}

//private fun plural(value:Long, timeUmit :TimeUnits):String {
//    var strPlural:String? =""
//    val lastDigit: Int= value.toInt() % 10
//    val pluralIndex: Int = when(lastDigit){
//        0 -> 0
//        1 -> 1
//        in 2 .. 4 -> 2
//        in 5..9 -> 0
//        else -> 0
//    }
//    if (timeUmit==TimeUnits.SECOND) strPlural= secondsp[pluralIndex]
//    else if (timeUmit==TimeUnits.MINUTE) strPlural= minutesp[pluralIndex]
//    else if (timeUmit==TimeUnits.HOUR) strPlural= hoursp[pluralIndex]
//    else if (timeUmit==TimeUnits.DAY) strPlural= daysp[pluralIndex]
//    else strPlural="unknow"
//    return strPlural
//}