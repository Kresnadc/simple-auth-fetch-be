package com.example.simplefetch.model.exception

import java.lang.Exception

class LogicException(override val message: String?): Exception(message) {

}