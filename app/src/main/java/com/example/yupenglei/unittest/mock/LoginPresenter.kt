package com.example.yupenglei.unittest.mock

/**
 * Created by yupenglei on 17/9/28.
 */
class LoginPresenter {
    var userManager = UserManager()

    fun login(username: String, password: String) {
        if (username.isEmpty()) return
        if (password.length < 6) return
        userManager.performLogin(username, password)
    }
}