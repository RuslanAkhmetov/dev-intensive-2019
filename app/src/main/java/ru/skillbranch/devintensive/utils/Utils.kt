package ru.skillbranch.devintensive.utils

object Utils {
    private val CyrilicToLatMap: HashMap<Char, String> = hashMapOf(
        'а' to "a",
        'б' to "b",
        'в' to "v",
        'г' to "g",
        'д' to "d",
        'е' to "e",
        'ё' to "e",
        'ж' to "zh",
        'з' to "z",
        'и' to "i",
        'й' to "i",
        'к' to "k",
        'л' to "l",
        'м' to "m",
        'н' to "n",
        'о' to "o",
        'п' to "p",
        'р' to "r",
        'с' to "s",
        'т' to "t",
        'у' to "u",
        'ф' to "f",
        'х' to "h",
        'ц' to "c",
        'ч' to "ch",
        'ш' to "sh",
        'щ' to "sh'",
        'ъ' to "",
        'ы' to "i",
        'ь' to "",
        'э' to "e",
        'ю' to "yu",
        'я' to "ya"
    )

    fun parseFullName(fullName:String?):Pair<String?, String?>{
        //Todo Fix me
        val parts:List<String>? = fullName?.split(" ")
        var firstName :String? = parts?.getOrNull(0)
        var lastName :String? = parts?.getOrNull(1)
        if (firstName!= null && firstName.length == 0) firstName = null
        if (lastName!= null && lastName.length == 0) lastName = null
        //        return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload:String, divider:String = " "):String {
        // Проебразовать кирилицу латиницу
        //var latStr= Array(2){i->""}
        if (payload.length == 0 ) return payload
        var lStr:String =""
        var letter : String? =""
        //var index: Int = 0
        var lCapital: Boolean  = true
        var dAdded : Boolean = false
        for (ch in payload.trim().toLowerCase()) {
            if (ch != null) {
                if (divider == ch.toString() || ch == ' ' ) {
                        lStr = lStr + divider
                        lCapital = true

                    }
                else {
                        if (CyrilicToLatMap[ch] == null) letter = ch.toString()
                        else letter = CyrilicToLatMap[ch]
                        if (lCapital==true) {
                            lStr = lStr + letter?.capitalize()
                            lCapital=false
                        }
                        else lStr = lStr + letter
                    }
                    //latStr[index] = latStr[index] + letter
                }
            }
        return lStr
        }



    fun toInitials(firstName:String?, lastName:String?):String? {
        //Преобразовать имя и фамилию в инициалы
        var firstL: Char?
        var secondL:Char?
        var initialse: String? = ""

        if (firstName == null || firstName.trim().length == 0) firstL = null
        else firstL = firstName.trim().first()

        if (lastName == null || lastName.trim().length == 0) secondL = null
        else secondL = lastName.trim().first()

        initialse = when  {
            firstL == null && secondL == null                    -> null
            firstL != null && secondL != null                    -> firstL.toString() + secondL.toString()
            firstL != null                                       -> firstL.toString()
            secondL != null                                      -> secondL.toString()
            else                                                 -> "sombody else"
        }
        if (initialse!=null) return initialse.toUpperCase()
        else return null
    }
}


