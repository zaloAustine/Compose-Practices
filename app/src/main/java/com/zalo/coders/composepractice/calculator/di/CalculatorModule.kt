package com.zalo.coders.composepractice.calculator.di

import com.zalo.coders.composepractice.calculator.core.CalculatorManager
import com.zalo.coders.composepractice.calculator.core.CalculatorManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable
/**
Created by zaloaustine in 6/10/24.
 */
@Module
@InstallIn(SingletonComponent::class)
object CalculatorModule {

    @Provides
    @Singleton
    fun provideCalculatorContext(): Context {
        return Context.enter()
    }

    @Provides
    @Singleton
    fun provideCalculatorScriptable(context: Context): Scriptable {
        context.optimizationLevel = -1
        return context.initStandardObjects()
    }

    @Provides
    @Singleton
    fun provideCalculatorManager(context: Context, scriptable: Scriptable): CalculatorManager {
        return CalculatorManagerImpl(calculatorContext = context, scriptable = scriptable)
    }
}