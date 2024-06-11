package com.zalo.coders.calculator

import com.zalo.coders.calculator.core.isValidInput
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
Created by zaloaustine in 6/10/24.
 */

class UtilTest {

    @Test
    fun testIsValidOperation(){
        assertTrue(com.zalo.coders.calculator.core.isValidInput("123"))
        assertTrue(com.zalo.coders.calculator.core.isValidInput("1+2"))
        assertTrue(com.zalo.coders.calculator.core.isValidInput("1-2"))
        assertTrue(com.zalo.coders.calculator.core.isValidInput("1*2"))
        assertTrue(com.zalo.coders.calculator.core.isValidInput("1/2"))
        assertTrue(com.zalo.coders.calculator.core.isValidInput("1%2"))
    }

    @Test
    fun testInvalidOperation(){
        assertFalse(com.zalo.coders.calculator.core.isValidInput("*123"))
        assertFalse(com.zalo.coders.calculator.core.isValidInput("/123"))
        assertFalse(com.zalo.coders.calculator.core.isValidInput("%123"))
        assertFalse(com.zalo.coders.calculator.core.isValidInput(".123"))
        assertFalse(com.zalo.coders.calculator.core.isValidInput("123++456"))
    }
}