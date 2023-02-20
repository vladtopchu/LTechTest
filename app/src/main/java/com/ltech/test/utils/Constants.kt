package com.ltech.test.utils

object Constants {
    const val BASE_URL = "http://dev-exam.l-tech.ru/api/"

    const val HTTP_ERROR_MESSAGE = "Сервер вернул ошибку. Повторите позже"
    const val IO_ERROR_MESSAGE = "Произошла ошибка. Проверьте подключение к сети"
    const val DEFAULT_MASK = "+7 (XXX) XXX-XX-XX"

    object AuthForm {
        const val PHONE = "phone"
        const val PASSWORD = "password"
    }
}