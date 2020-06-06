package ru.skillbranch.devintensive.models

import android.text.TextUtils
import java.util.*

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    private var numberIncorrectAnswers: Int = 0

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {

        val (newText, isMistake) = validationResponse(question, answer)
        if (isMistake) {
            return ("$newText\n" +
                    "${question.question}" to status.color)
        }

        if (question.answers.contains(answer)) {

            numberIncorrectAnswers = 0
            question = question.nextQuestion()

            return ("Отлично - ты справился\n" +
                    "${question.question}" to status.color)
        } else {

            numberIncorrectAnswers++
            return if (numberIncorrectAnswers < 4) {
                status = status.nextStatus()
                ("Это неправильный ответ\n${question.question}" to status.color)

            } else {
                numberIncorrectAnswers = 0
             //   status = status.firstStatus()
             //   question = question.firstQuestion()
                status = Status.NORMAL
                question = Question.NAME

                ("Это неправильный ответ. Давай все по новой\n${question.question}" to status.color)
            }
        }
    }

    private fun validationResponse(question: Question, answer: String): Pair<String, Boolean> {

        when (question) {
            Question.NAME -> {
                if (answer == answer.toLowerCase(Locale.ROOT)) return "Имя должно начинаться с заглавной буквы" to true
            }
            Question.PROFESSION -> {
                if (answer != answer.toLowerCase(Locale.ROOT)) return "Профессия должна начинаться со строчной буквы" to true
            }
            Question.MATERIAL -> {
                if (answer.find { it.isDigit() } != null) return "Материал не должен содержать цифр" to true
            }
            Question.BDAY -> {
                if (!TextUtils.isDigitsOnly(answer)) return "Год моего рождения должен содержать только цифры" to true
            }
            Question.SERIAL -> {
                if (!TextUtils.isDigitsOnly(answer) || answer.length != 7) return "Серийный номер содержит только цифры, и их 7" to true
            }
            else -> return "" to false
        }

        return "" to false
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 255, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }

        fun firstStatus(): Status {
            return NORMAL
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("Бендер", "Bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf("")) {
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question

        fun firstQuestion(): Question {
            return NAME
        }
    }

    /*
    Валидация
    Question.NAME -> "Имя должно начинаться с заглавной буквы"
    Question.PROFESSION -> "Профессия должна начинаться со строчной буквы"
    Question.MATERIAL -> "Материал не должен содержать цифр"
    Question.BDAY -> "Год моего рождения должен содержать только цифры"
    Question.SERIAL -> "Серийный номер содержит только цифры, и их 7"
    Question.IDLE -> //игнорировать валидацию
     */
}