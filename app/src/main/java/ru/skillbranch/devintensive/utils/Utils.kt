package ru.skillbranch.devintensive.utils

import kotlin.collections.HashMap

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {

        if (fullName == null) {
            return Pair(null, null)
        }

        val parts = fullName.split(" ")
        val firstName = parts.getOrNull(0)
        val lastName = parts.getOrNull(1)

        return Pair(firstName, lastName)

    }

    fun transliteration(payload: String, divider: String = " "): String {

        val map = getMap()
        var payloadNew = ""

        for (item in payload) {

            val itemStr = item.toString()

            if (itemStr == " ") {
                payloadNew = "$payloadNew$divider"
                continue
            }

            var itemNew = map[itemStr.toLowerCase()]

            if (itemNew == null) {
                payloadNew = "$payloadNew$item"
                continue
            }

            if (item.isUpperCase()) {
                itemNew = if (itemNew.length > 1) {
                    "${itemNew[0].toString().toUpperCase()}${itemNew.subSequence(
                        1,
                        itemNew.length - 1
                    )}"
                } else {
                    itemNew.toUpperCase()
                }
            }
            payloadNew = "$payloadNew$itemNew"
        }
        return payloadNew
    }

    private fun getMap(): HashMap<String, String> {
        return hashMapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya"
        )
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        if (firstName == null && lastName == null) return null

        var firstN = firstName ?: ""
        var lastN = lastName ?: ""

        firstN = firstN.replace(" ", "")
        lastN = lastN.replace(" ", "")

        if (firstN.isEmpty() && lastN.isEmpty()) return null

        if (firstN.isNotEmpty()) firstN = firstN[0].toUpperCase().toString()
        if (lastN.isNotEmpty()) lastN = lastN[0].toUpperCase().toString()

        return "$firstN$lastN"
    }
}