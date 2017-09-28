package com.example.yupenglei.unittest

/**
 * Created by yupenglei on 17/9/28.
 */
class Calculator {
    fun add(one: Int, another: Int) = one + another
    fun multiply(one: Int, another: Int) = one * another
    fun divide(divident: Double, dividor: Double): Double {
        if (dividor == 0.0) throw IllegalArgumentException("dividor cannot be 0")
        return divident / dividor
    }
}