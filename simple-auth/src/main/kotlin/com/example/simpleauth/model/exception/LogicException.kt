package com.example.simpleauth.model.exception

import java.lang.Exception

class LogicException(override val message: String?): Exception(message) {

}