package com.example.yupenglei.unittest.mock

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

/**
 * Created by yupenglei on 17/9/28.
 */
class LoginPresenterTest {
    @Before
    fun setUp() {
    }

    @Test
    fun login() {
        val username = "tom"
        val password = "tom`s password"
        val userManagerMocked = Mockito.mock(UserManager::class.java) // mock 对象
        val loginPresenter = LoginPresenter()
        loginPresenter.userManager = userManagerMocked

        loginPresenter.login(username, password) //执行

        Mockito.verify(userManagerMocked).performLogin(username, password) // 验证
    }

}