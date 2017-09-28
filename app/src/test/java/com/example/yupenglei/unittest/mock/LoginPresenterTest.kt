package com.example.yupenglei.unittest.mock

import com.nhaarman.mockito_kotlin.*
import org.junit.Assert
import org.junit.Test

/**
 * Created by yupenglei on 17/9/28.
 */
class LoginPresenterTest {

    val username = "tom"
    val password = "tom`s password"

    @Test
    fun login() {
//        val userManagerMocked = Mockito.mock(UserManager::class.java) // mock 对象

        val userManagerMocked: UserManager = mock() // mock 对象
        val loginPresenter = LoginPresenter()
        loginPresenter.userManager = userManagerMocked

        loginPresenter.login(username, password) //执行

        verify(userManagerMocked).performLogin(username, password) // 验证
    }

    @Test fun loginWithValidate() {
        val rightPwd = "tom is handsome"
        val wrongPwd = "tom is not handsome"
        val mockValidator: PasswordValidator = mock {
            on { verifyPassword(rightPwd) }.thenReturn(true)
            on { verifyPassword(wrongPwd) }.thenReturn(false)
        }
        //指定mock对象的函数返回值
//        Mockito.`when`(mockValidator.verifyPassword(rightPwd)).thenReturn(true)
//        Mockito.`when`(mockValidator.verifyPassword(wrongPwd)).thenReturn(false)
//        Mockito.`when`(mockValidator.verifyPassword(Mockito.anyString())).thenReturn(true)

        val mockUserManager: UserManager = mock()

        val pwd = rightPwd
        LoginPresenter().apply { userManager = mockUserManager }
                .apply { passwordValidator = mockValidator }
                .loginWithValidate(username, pwd)

        verify(mockUserManager).performLogin(username, pwd)
    }

    @Test fun loginCallbackVersion() {
        val mockValidator: PasswordValidator = mock {
            //注意whenever和on的区别
            whenever(it.verifyPassword(any())).thenReturn(true)
        }
        val mockUserManager: UserManager = mock {
            //指定mock对象函数行为
            on { performLoginCallbackVersion(any(), any(), any()) }.doAnswer {
                val callback = it.arguments[2] as UserManager.NetWorkCallback
                callback.onFailed(500, "server error")
            }
        }
        LoginPresenter().apply { passwordValidator = mockValidator }
                .apply { userManager = mockUserManager }
                .loginCallbackVersion(username, password)
    }

    @Test fun testRealFunction() {
        val mockValidator: PasswordValidator = mock {
            on { verifyPassword(any()) }
                    .thenCallRealMethod()
//                    .thenReturn(true)
        }
//        Assert.assertEquals(mockValidator.verifyPassword(""), true)
        mockValidator.verifyPassword("hello")
        verify(mockValidator).verifyPassword("hello")
    }

    @Test fun testSpy() {
        //spy与mock的不同在于spy默认调用真实函数，mock默认调用空方法，返回默认值
        val spyValidator: PasswordValidator = spy()
        Assert.assertEquals(spyValidator.verifyPassword2("Hello"), true)
        Assert.assertEquals(spyValidator.verifyPassword2("Hello2"), false)

        whenever(spyValidator.verifyPassword2("Hello")).thenReturn(false)
        Assert.assertEquals(spyValidator.verifyPassword2("Hello"), false)
    }
}