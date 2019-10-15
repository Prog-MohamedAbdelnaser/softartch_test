package com.softtech.fcmtask.data.exceptions

class APIException(var code: String, override var message: String) : RuntimeException()