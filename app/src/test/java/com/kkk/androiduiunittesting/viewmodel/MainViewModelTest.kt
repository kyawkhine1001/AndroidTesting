package com.kkk.androiduiunittesting.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kkk.androiduiunittesting.di.testAppModule
import com.kkk.androiduiunittesting.model.networkRequest.RegisterRequest
import com.kkk.androiduiunittesting.model.uimodel.RegisterUIModel
import org.junit.*
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


class MainViewModelTest : KoinTest {
    private val viewModel: MainViewModel by inject()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var registerDataObserver: Observer<RegisterUIModel>

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin(testAppModule)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun testShowValidationMessageWhenMakeRegistration() {
        viewModel.registerUIData.observeForever(registerDataObserver)
        viewModel.makeRegistration("","","")

        val value = viewModel.registerUIData.value ?: error("No value for view myModel")

        Mockito.verify(registerDataObserver).onChanged(RegisterUIModel(error = value.error))
        Assert.assertEquals(value.error,"Full Name is required.")

    }

    @Test
    fun testAlreadyRegisterAccountWhenMakeRegistration() {
        viewModel.registerUIData.observeForever(registerDataObserver)
        viewModel.makeRegistration("Kyaw Khine","09423696548","1232321")

        val value = viewModel.registerUIData.value ?: error("No value for view myModel")

        Mockito.verify(registerDataObserver).onChanged(RegisterUIModel(error = value.error))
        Assert.assertEquals(value.error,"This number is already registered with an existing account. Please login with your number or register with a different one.")
    }

    @Test
    fun testRegisterNewAccountWhenMakeRegistration() {
        val name = "Kyaw Khine"
        viewModel.registerUIData.observeForever(registerDataObserver)
        viewModel.makeRegistration(name,"09423694400","1232321")

        val value = viewModel.registerUIData.value ?: error("No value for view myModel")

        Mockito.verify(registerDataObserver).onChanged(RegisterUIModel(data = value.data))
        Assert.assertEquals(value.data?.fullName,name)

    }
}