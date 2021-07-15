package com.kkk.androiduiunittesting.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kkk.androiduiunittesting.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    companion object{
        const val IE_NAME = "IE_NAME"
        const val IE_PHONE_NUMBER = "IE_PHONE_NUMBER"

        fun getIntent(context: Context,name:String,phone:String): Intent {
            val intent = Intent(context,HomeActivity::class.java)
            intent.putExtra(IE_NAME,name)
            intent.putExtra(IE_PHONE_NUMBER,phone)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val fullName = intent.getStringExtra(IE_NAME).toString()
        val phoneNumber = intent.getStringExtra(IE_PHONE_NUMBER).toString()
        val greetingName = "Hi , $fullName"
        val greetingMessage = "We will call to your number : 09$phoneNumber"
        tvGreetingName.text = greetingName
        tvGreetingMessage.text = greetingMessage
    }
}