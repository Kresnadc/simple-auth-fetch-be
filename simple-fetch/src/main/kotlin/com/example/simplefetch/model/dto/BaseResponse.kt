package com.example.simplefetch.model.dto

import java.util.*

data class BaseResponse<T>(val time: Date, val message: String, val data: T)
