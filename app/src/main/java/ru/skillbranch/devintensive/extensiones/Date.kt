package ru.skillbranch.devintensive.extensiones

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

fun Date.humanizeDiff_(date: Date = Date()):String{
    //TODO("not implemented") //Домашнеее задание определить разницу между текущей датой и датой в днях или часах
    // например "10 секунд назад, четыре дня назад, с учетом склонения числительных
    val diffValue = date.time - this.time
    val humanDate:Pair<Long, TimeUnits>
    if (diffValue >= DAY )  humanDate = Pair(diffValue / DAY , TimeUnits.DAY)
    else if (diffValue >= HOUR ) humanDate = Pair(diffValue / HOUR , TimeUnits.HOUR)
    else if (diffValue >= MINUTE ) humanDate = Pair(diffValue / MINUTE , TimeUnits.MINUTE)
    else humanDate = Pair(diffValue / SECOND , TimeUnits.SECOND)
    return humanDate.first.toString() +  " " + humanDate.second.plural(humanDate.first) //plural(humanDate.first, humanDate.second)
}

fun Date.humanizeDiff(date: Date = Date()):String {
    val diffValue = date.time - Date().time

    when{
        diffValue > 360*DAY ->  return "более чм через год"

        diffValue >= DAY   -> return "через " + diffValue / DAY +  " " +  TimeUnits.DAY.plural(diffValue / DAY)
        diffValue >= HOUR  -> return "через " + diffValue / HOUR +  " " +  TimeUnits.HOUR.plural(diffValue / HOUR)
        diffValue >= MINUTE -> return "через " + diffValue / MINUTE +  " " +  TimeUnits.MINUTE.plural(diffValue / MINUTE)
        diffValue >= 0      -> return "через " + diffValue / SECOND +  " " +  TimeUnits.SECOND.plural(diffValue / SECOND)

        diffValue >= -1*SECOND ->  return "только что"                                    //0с - 1с "только что"

        diffValue >= -45*SECOND -> return "несколько секунд назад"                        //1с - 45с "несколько секунд назад"

        diffValue >= -75*SECOND -> return "минуту назад"                                 //45с - 75с "минуту назад"

        diffValue >= -45*MINUTE -> return "${-1*diffValue/MINUTE} " +  TimeUnits.HOUR.plural(diffValue / MINUTE) + " назад"         //75с - 45мин "N минут назад"

        diffValue >= -75*MINUTE -> return "час назад"                                    //45мин - 75мин "час назад"

        diffValue >= -22*HOUR -> return "${-1*diffValue / HOUR} " + TimeUnits.HOUR.plural(diffValue / HOUR) + " назад"            //75мин 22ч "N часов назад"

        diffValue >= -26*HOUR -> return "день назад"                                  //22ч - 26ч "день назад"

        diffValue >= -360*DAY -> return "${-1*diffValue/ DAY} " +   TimeUnits.DAY.plural(diffValue  / DAY) + " назад"          //26ч - 360д "N дней назад"

        diffValue < -360*DAY -> return "более года назад"                //>360д "более года назад"

        else                 -> return "не знаю такого интервала"
    }
}


enum class TimeUnits {
    SECOND{
        override fun plural(volume: Long)=when(abs(volume)%10){
            1L -> "секунду"
            in 2L .. 4L -> "секунды"
            else -> "секунд"
        }
    },
    MINUTE {
        override fun plural(volume: Long): String=when(abs(volume)%10) {
            1L -> "минуту"
            in 2L..4L -> "минуты"
            else -> "минут"
        }
    },
    HOUR {
        override fun plural(volume: Long): String=when(abs(volume)%10) {
            1L -> "час"
            in 2L..4L -> "часа"
            else -> "часов"
        }
    },
    DAY {
        override fun plural(volume: Long): String=when(abs(volume)%10) {
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