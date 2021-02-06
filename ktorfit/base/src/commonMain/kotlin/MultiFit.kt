package com.overlord.annotations

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class BaseURL(val baseURL: String)

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class GET(val route: String)

