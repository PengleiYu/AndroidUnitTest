package com.example.yupenglei.unittest.mock

import java.util.*

/**
 * 验证密码有效性
 * Created by yupenglei on 17/9/28.
 */
class PasswordValidator {
    /**
     * 耗时5秒进行验证
     */
    fun verifyPassword(password: String): Boolean {
        Thread.sleep(5000)
        return Random().nextBoolean()
    }
}