package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60* SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String {
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



fun Date.humanizeDiff(date: Date = Date()):String {
    val diffValue = this.time - date.time

    when{
        diffValue > 360*DAY ->  return "более чем через год"

        diffValue >= DAY   -> return "через " + TimeUnits.DAY.plural(diffValue / DAY)
        diffValue >= HOUR  -> return "через " + TimeUnits.HOUR.plural(diffValue / HOUR)
        diffValue >= MINUTE -> return "через " + TimeUnits.MINUTE.plural(diffValue / MINUTE)
        diffValue > 0      -> return "через " + TimeUnits.SECOND.plural(diffValue / SECOND)

        diffValue >= -1*SECOND ->  return "только что"                                    //0с - 1с "только что"

        diffValue >= -45*SECOND -> return "несколько секунд назад"                        //1с - 45с "несколько секунд назад"

        diffValue >= -75*SECOND -> return "минуту назад"                                 //45с - 75с "минуту назад"

        diffValue >= -45*MINUTE -> return TimeUnits.HOUR.plural(diffValue / MINUTE) + " назад"         //75с - 45мин "N минут назад"

        diffValue >= -75*MINUTE -> return "час назад"                                    //45мин - 75мин "час назад"

        diffValue >= -22*HOUR -> return TimeUnits.HOUR.plural(diffValue / HOUR) + " назад"            //75мин 22ч "N часов назад"

        diffValue >= -26*HOUR -> return "день назад"                                  //22ч - 26ч "день назад"

        diffValue >= -360*DAY -> return TimeUnits.DAY.plural(diffValue  / DAY) + " назад"          //26ч - 360д "N дней назад"

        diffValue < -360*DAY -> return "более года назад"                //>360д "более года назад"

        else                 -> return "не знаю такого интервала"
    }
}


enum class TimeUnits {
    SECOND{
        override fun plural(volume: Long)=when(abs(volume)%10){
            1L -> "${abs(volume)} секунду"
            in 2L .. 4L -> "${abs(volume)} секунды"
            else -> "${abs(volume)} секунд"
        }
    },
    MINUTE {
        override fun plural(volume: Long): String=when(abs(volume)%10) {
            1L -> "${abs(volume)} минуту"
            in 2L..4L -> "${abs(volume)} минуты"
            else -> "${abs(volume)} минут"
        }
    },
    HOUR {
        override fun plural(volume: Long): String=when(abs(volume)%10) {
            1L -> "${abs(volume)} час"
            in 2L..4L -> "${abs(volume)} часа"
            else -> "${abs(volume)} часов"
        }
    },
    DAY {
        override fun plural(volume: Long): String=when(abs(volume)%10) {
            1L -> "${abs(volume)} день"
            in 2L..4L -> "${abs(volume)} дня"
            else -> "${abs(volume)} дней"
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