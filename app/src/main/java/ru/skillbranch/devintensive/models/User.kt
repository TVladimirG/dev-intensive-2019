package ru.skillbranch.devintensive.models


import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    var id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = Date(),
    val isOnline: Boolean = false
) {

    var introBit: String

    constructor(
        id: String,
        firstName: String?,
        lastName: String?
    ) : this(id, firstName, lastName, null)

    constructor(id: String) : this(id, "John", "Doe (id=$id)")

    init {
        introBit = getIntro()
        println(
            " It's Alive!!! $firstName $lastName " +
                    " ${if (lastName === "Doe") " His name is $firstName $lastName" else "And his name is $firstName $lastName!!!"} \n" +
                    ""  //  " ${getIntro()} "
        )
    }

    private fun getIntro(): String = """
                                     intro intro
                                     intro
                                  """.trimIndent()

    fun printMe() = println(
        """
            id = $id
            firstName = $firstName
            lastName = $lastName
            avatar = $avatar
            rating = $rating
            respect = $respect
            lastVisit = $lastVisit
            isOnline = $isOnline
            """.trimIndent()
    )

    companion object Factory {

        private var lastId = -1

        fun makeUser(fullName: String?): User? {

            val (firstName, lastName) = Utils.parseFullName(fullName)

            lastId++

            if (fullName == null) {
                return User(id = "$lastId")
            } else {
                return User(id = "$lastId", firstName = firstName, lastName = lastName)
            }
        }
    }
}
