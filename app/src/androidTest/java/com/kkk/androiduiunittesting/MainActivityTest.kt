package com.kkk.androiduiunittesting

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kkk.androiduiunittesting.ui.activity.MainActivity
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(
        MainActivity::class.java
    )


    @Test
    @Throws(Exception::class)
    fun testRequireFieldsWhenClickRegisterButton() {
        val name = "Kyaw Khine"
        val phone = "09423696548"

        Espresso.onView(withId(R.id.btnRegister)).perform(ViewActions.click())
        onView(withText(R.string.fullNameErrorMessage)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBack()

        Espresso.onView(withId(R.id.edtName))
            .perform(ViewActions.typeText(name), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.btnRegister)).perform(ViewActions.click())
        onView(withText(R.string.phoneErrorMessage)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBack()
        Espresso.onView(withId(R.id.edtName))
            .perform(ViewActions.clearText(), ViewActions.closeSoftKeyboard())

        Espresso.onView(withId(R.id.edtName))
            .perform(ViewActions.typeText(name), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.edtPhone))
            .perform(ViewActions.typeText(phone), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.btnRegister)).perform(ViewActions.click())
        onView(withText(R.string.passwordErrorMessage)).check(ViewAssertions.matches(isDisplayed()))
        Espresso.pressBack()
    }

    @Test
    @Throws(Exception::class)
    fun testAlreadyRegisterAccount() {
        val name = "Kyaw Khine"
        val phone = "09423696548"
        val password = "Admin@123"
        Espresso.onView(withId(R.id.edtName))
            .perform(ViewActions.typeText(name), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.edtPhone))
            .perform(ViewActions.typeText(phone), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.edtPassword))
            .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.btnRegister)).perform(ViewActions.click())
        Thread.sleep(3000)
        onView(withText(R.string.alreadyRegisterPhoneMessage)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    @Throws(Exception::class)
    fun testRegisterNewAccount() {
        val name = "Kyaw Khine"
        val phone = "09423644440"
        val password = "Admin@123"

        val expectedGreetingName = "Hi , $name"
        var expectedGreetingMessage = "We will call to your number : $phone"
        Espresso.onView(withId(R.id.edtName))
            .perform(ViewActions.typeText(name), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.edtPhone))
            .perform(ViewActions.typeText(phone), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.edtPassword))
            .perform(ViewActions.typeText(password), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.btnRegister)).perform(ViewActions.click())
        Thread.sleep(3000)

//        onView(withText(R.string.registerSuccessMessage)).check(ViewAssertions.matches(isDisplayed()))//this was used for showing only success alert message

        onView(withId(R.id.tvGreetingName)).check(ViewAssertions.matches(withText(expectedGreetingName)))
        onView(withId(R.id.tvGreetingMessage)).check(ViewAssertions.matches(withText(expectedGreetingMessage)))
    }
}