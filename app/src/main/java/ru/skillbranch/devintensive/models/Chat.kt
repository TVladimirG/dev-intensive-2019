package ru.skillbranch.devintensive.models

import java.lang.reflect.Member

class Chat(
    val id: String,
    val member: MutableList<User> = mutableListOf(),
    val messages: MutableList<BaseMessage> = mutableListOf()

) {
}