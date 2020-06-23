package ru.skillbranch.devintensive.models

data class Profile(
    val rating: Int = 0,
    val respect: Int = 0,
    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String
) {
    val rank: String = "Junior Android Developer"
    val nickName = "John Dow" // TODO implement me

    fun toMap(): Map<String, Any> = mapOf(
        "nickName" to nickName,
        "rank" to rank,
        "rating" to rating,
        "respect" to respect,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository
    )


}