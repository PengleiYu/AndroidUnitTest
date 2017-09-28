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
        if (Random().nextBoolean()) callback.onSuccess("This is data")
        else callback.onFailed(404, "not fund")
    }

    interface NetWorkCallback {
        fun onSuccess(data: Any)
        fun onFailed(error: Int, msg: String)
    }
}