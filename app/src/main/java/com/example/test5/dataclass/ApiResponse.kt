package com.example.test5.dataclass

import com.squareup.moshi.Json

data class ApiResponse(
    @Json(name = "new_courses")
    val newCourse: List<NewCourse>,
    @Json(name = "active_courses")
    val activeCourse: List<ActiveCourse>
)
