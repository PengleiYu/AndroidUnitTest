package com.example.yupenglei.unittest.mock

import org.junit.Test
import org.mockito.Mockito

/**
 * Created by yupenglei on 17/9/28.
 */
class LoginPresenterTest {

    val username = "tom"
    val password = "tom`s password"

    @Test
    fun login() {
        val userManagerMocked = Mockito.mock(UserManager::class.java) // mock 对象
        val loginPresenter = LoginPresenter()
        loginPresenter.userManager = userManagerMocked

        loginPresenter.login(username, password) //执行

        Mockito.verify(userManagerMocked).performLogin(username, password) // 验证
    }

    @Test fun loginWithValidate() {
        val rightPwd = "tom is handsome"
        val wrongPwd = "tom is not handsome"
        val mockValidator = Mockito.mock(PasswordValidator::class.java)
        //指定mock对象的函数返回值
        Mockito.`when`(mockValidator.verifyPassword(rightPwd)).thenReturn(true)
        Mockito.`when`(mockValidator.verifyPassword(wrongPwd)).thenReturn(false)
//        Mockito.`when`(mockValidator.verifyPassword(Mockito.anyString())).thenReturn(true)

        val mockUserManager = Mockito.mock(UserManager::class.java)

        val pwd = rightPwd
        LoginPresenter().apply { userManager = mockUserManager }
                .apply { passwordValidator = mockValidator }
                .loginWithValidate(username, pwd)

        Mockito.verify(mockUserManager).performLogin(username, pwd)
    }

    @Test fun loginCallbackVersion() {
        val mockValidator = Mockito.mock(PasswordValidator::class.java)
        Mockito.`when`(mockValidator.verifyPassword(Mockito.anyString())).thenReturn(true)
        val mockUserManager = Mockito.mock(UserManager::class.java)
        Mockito.doAnswer {
            val callback = it.arguments[2] as UserManager.NetWorkCallback
            callback.onFailed(500, "server error")
            return@doAnswer 22
        }.`when`(mockUserManager.performLoginCallbackVersion(Mockito.anyString(),
                Mockito.anyString(), Mockito.any(UserManager.NetWorkCallback::class.java)
        ))

        val loginPresenter = LoginPresenter()
        loginPresenter.loginCallbackVersion(username, password)
    }

}