package com.zalo.coders.composepractice.calculator

import com.zalo.coders.composepractice.calculator.core.isValidInput
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
Created by zaloaustine in 6/10/24.
 */

class UtilTest {

    @Test
    fun testIsValidOperation(){
        assertTrue(isValidInput("123"))
        assertTrue(isValidInput("1+2"))
        assertTrue(isValidInput("1-2"))
        assertTrue(isValidInput("1*2"))
        assertTrue(isValidInput("1/2"))
        assertTrue(isValidInput("1%2"))
    }

    @Test
    fun testInvalidOperation(){
        assertFalse(isValidInput("*123"))
        assertFalse(isValidInput("/123"))
        assertFalse(isValidInput("%123"))
        assertFalse(isValidInput(".123"))
        assertFalse(isValidInput("123++456"))
    }
}