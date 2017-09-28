package com.example.yupenglei.unittest

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Ignore

/**
 * simple JUnit exercise
 * Created by yupenglei on 17/9/28.
 */
class CalculatorTest {
    private lateinit var calculator: Calculator
    @Before
    fun setUp() {
        calculator = Calculator()
        println("hah")
    }

    @Test
    fun add() {
        val sum = calculator.add(1, 3)
//        assertEquals("this is error hint", sum, 5)
        assertEquals(sum, 4)
    }

    @Test
    fun multiply() {
        val product = calculator.multiply(3, 8)
        assertEquals(product, 24)
    }

    @Test
    @Ignore("not implemented yet") //ignore this function
    fun testFactorial() {
    }

    @Test(expected = IllegalArgumentException::class) // except exception
    fun divide() {
        calculator.divide(4.0, 0.0)
    }

}