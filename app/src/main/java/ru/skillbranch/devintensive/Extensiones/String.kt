package ru.skillbranch.devintensive.Extensiones

fun String.truncate(index:Int=16):String?{
    var convStr:String =""
    if (this!=null && index != 0){
        if (this.length <= index) return this
        else convStr = this.take(index).trimEnd() + "..."
    }
    else if (index==0) convStr=""
    return convStr
}

fun String.stripHtml():String?{
    //for (ch:Char in this){}
    var convString:String =this
    var regex = Regex(pattern="""<[\/\w\s\d\=\"\']+>|\&[\#\w\d]+\;""")
    convString = regex.replace(this,"")
    regex="""\s\s*""".toRegex()
    convString = regex.replace(convString," ")

    return convString
}