package com.example.yupenglei.unittest.mock

/**
 * Created by yupenglei on 17/9/28.
 */
class LoginPresenter {
    var userManager = UserManager()
    var passwordValidator = PasswordValidator()

    fun login(username: String, password: String) {
        if (username.isEmpty()) return
        if (password.length < 6) return
        userManager.performLogin(username, password)
    }

    fun loginWithValidate(username: String, password: String) {
        if (username.isEmpty()) return
        if (!passwordValidator.verifyPassword(password)) {
            println("validate failed")
            return
        } else println("validate success")
        userManager.performLogin(username, password)
    }

    fun loginCallbackVersion(username: String, password: String) {
        if (username.isEmpty()) return
        if (!passwordValidator.verifyPassword(password)) return
        userManager.performLoginCallbackVersion(username, password, object : UserManager.NetWorkCallback {
            override fun onSuccess() {
                println("onSuccess")
            }

            override fun onFailed() {
                println("onFailed")
            }
        })
    }
}