package com.example.whatsappclone.utils

import java.util.*

class Constants {
    object Keys {
        object PrefKeys {
            const val DEFAULT = "default"
            const val SERVICE = "service"
            const val AD = "ad"
            const val SERVICE_STATE = "service_state"
        }
    }

    object Default {
        val NULL = null
        const val BOOLEAN = false
        const val CHARACTER = 0.toChar()
        const val INT = 0
        const val LONG = 0L
        const val FLOAT = 0f
        const val DOUBLE = 0.0
        const val STRING = ""
        val LIST = Collections.emptyList<Any>()
    }
}