package com.example.test5.dataclass

import com.squareup.moshi.Json

data class NewCourse(
    val id: String,
    @Json(name = "icon_type")
    val iconType: String,
    val duration: String,
    val title: String,
    val question: String,
    @Json(name = "main_color")
    val mainColor: String
)
