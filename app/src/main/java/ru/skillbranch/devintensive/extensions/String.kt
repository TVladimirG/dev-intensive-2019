package ru.skillbranch.devintensive.extensions

fun String.truncate(numChar: Int = 16): String {

    val aggregate = "..."
    var sourceString = this

    sourceString = sourceString.trimEnd()

    if (sourceString.isEmpty() || sourceString.length <= numChar) {
        return sourceString
    }

    sourceString = sourceString.substring(0, numChar)
    sourceString = sourceString.trimEnd()

    return "$sourceString$aggregate"

}

fun String.stripHtml(): String {

    var sourceString = this
    sourceString = sourceString.substringAfter(">")
    sourceString = sourceString.substringBefore("</")

    while (true) if (sourceString.contains("  ")) {
        sourceString = sourceString.replace("  ", " ", true)
    } else {
        break
    }

    return sourceString
}


