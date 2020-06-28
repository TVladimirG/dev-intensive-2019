package ru.skillbranch.devintensive.utils

import android.text.Editable

object Utils {


    fun parseFullName(fullName: String?): Pair<String?, String?> {

    //  Utils.parseFullName(null) //null null
        var inComFullName = fullName
        if (inComFullName == null) {
            return Pair(null, null)
        }

    //  Utils.parseFullName("") //null null
    //  Utils.parseFullName(" ") //null null
        inComFullName = inComFullName.trim()
        if (inComFullName.isEmpty())  return Pair(null, null)


        val parts = inComFullName.split(" ")
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

        var firstInit = firstName ?: ""
        var lastInit = lastName ?: ""

        firstInit = firstInit.replace(" ", "")
        lastInit = lastInit.replace(" ", "")

        if (firstInit.isEmpty() && lastInit.isEmpty()) return null

        if (firstInit.isNotEmpty()) firstInit = firstInit[0].toUpperCase().toString()
        if (lastInit.isNotEmpty()) lastInit = lastInit[0].toUpperCase().toString()

        return "$firstInit$lastInit"
    }


    fun repositoryIsVal(s: Editable?): Boolean {

        // Null или ""
        if (s.isNullOrEmpty()) {
            return true
        }

        // зарезервированные слова
        var tmtSet = setOf(
            "enterprise",
            "features",
            "topics",
            "collections",
            "trending",
            "events",
            "marketplace",
            "pricing",
            "nonprofit",
            "customer-stories",
            "security",
            "login",
            "join"
        )

        for (wrd in tmtSet) {
            if (s.contains(wrd, true)) {
                return false
            }
        }

        //***************************************************************************
        var isValid = false

        tmtSet = setOf(
            "https://www.github.com/",
            "https://github.com/",
            "www.github.com/",
            "github.com/"
        )

        for (wrd in tmtSet) {
            if (s.contains(wrd, true)) {
                isValid = true
                break
            }
        }

        if (isValid) {

            // val s = "https://www.github.com/aksjdhkasjdh/"
            val s2 = s.toString().replace("https://", "", true)
            val arr = s2.split("/")

            when {
                arr.size < 2 -> {
                    return false
                }
                arr.size == 2 && arr[1].isEmpty() -> {
                    return false
                }
                arr.size == 2 && arr[1].isNotEmpty() -> {
                    return true
                }
                arr.size == 3 && arr[2].isEmpty() -> {
                    return true
                }
                arr.size == 3 && arr[2].isNotEmpty() -> {
                    return false
                }
                arr.size > 3 -> {
                    return false
                }
                else -> {
                    isValid = false
                }
            }
        }
        return isValid
    }

}