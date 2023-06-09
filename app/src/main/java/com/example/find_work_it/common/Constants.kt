package com.example.find_work_it.common

import com.example.find_work_it.BuildConfig


object Constants {
    const val CLIENT_ID = BuildConfig.CLIENT_ID
    const val CLIENT_SECRET = BuildConfig.CLIENT_SECRET

    const val BASE_URL = "https://api.hh.ru/"

    const val REDIRECT_URI = "https://redirect.findworkit.com/oauth"
    const val AUTHORIZATION_ENDPOINT = "https://hh.ru/oauth/authorize"
    const val TOKEN_ENDPOINT = "https://hh.ru/oauth/token"

    const val CONTENT_TYPE_HEADER = "Content-Type: application/x-www-form-urlencoded"

    const val PARAM_VACANCY_ID = "vacancyId"
    const val PARAM_RESUME_ID = "resumeId"

}