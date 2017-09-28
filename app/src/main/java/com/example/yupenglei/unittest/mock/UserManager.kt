package com.example.yupenglei.unittest.mock

import java.util.*

/**
 * Created by yupenglei on 17/9/28.
 */
class UserManager {
    fun performLogin(username: String, password: String) {
        println("performLogin: $username = $password")
    }

    fun performLoginCallbackVersion(username: String, password: String, callback: NetWorkCallback) {
        Thread.sleep(5000)
        if (Random().nextBoolean()) callback.onSuccess() else callback.onFailed()
    }

    interface NetWorkCallback {
        fun onSuccess()
        fun onFailed()
    }
}