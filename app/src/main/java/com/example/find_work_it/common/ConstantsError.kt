package com.example.find_work_it.common

object ConstantsError {
    //Error messages
    const val NETWORK_ERROR = "Что-то пошло не так... Проверьте подключение к интернету"
    const val ERROR_OCCURRED = "Произошла ошибка... Повторите попытку"
    const val FAVORITE_ERROR_NETWORK = "Не удалось добавить вакансию и избранное. Проверьте подключение к интернету"
    const val FAVORITE_ERROR_OCCURRED = "Не удалось добавить вакансию и избранное."
    const val FAVORITE_ERROR_DELETE_NETWORK = "Не удалось удалить вакансию из избранного. Проверьте подключение к интернету"
    const val FAVORITE_ERROR_DELETE_OCCURRED = "Не удалось удалить вакансию из избранного."

    const val USER_ACCESS_ERROR = "Ошибка авторизации пользователя"

    const val PUT_USER_ERROR_NETWORK = "Не удалось изменить данные профиля. Проверьте подключение к интернету"
    const val PUT_USER_ERROR_OCCURRED = "Произошла ошибка. Не удалось изменить данные профиля."


    const val USER_FIRSTNAME_ERROR_VALIDATE = "Имя пользователя может содержать только буквы!"
    const val USER_LASTNAME_ERROR_VALIDATE = "Фамилия пользователя может содержать только буквы!"
    const val USER_MIDDLENAME_ERROR_VALIDATE = "Отчество пользователя может содержать только буквы!"
}