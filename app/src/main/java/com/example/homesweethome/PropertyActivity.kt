package com.example.homesweethome

import android.R.attr.phoneNumber
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class PropertyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)
        val id = intent.getStringExtra("id");
        var location = intent.getStringExtra("location")
        var rent =  intent.getStringExtra("rent")
        var phoneNumber = intent.getStringExtra("phone")
        findViewById<TextView>(R.id.textViewLocation).text = location
        findViewById<TextView>(R.id.textViewRentalPrice).text = rent
        findViewById<TextView>(R.id.textViewPropertyType).text = intent.getStringExtra("type")
        findViewById<CheckBox>(R.id.checkBoxLocation).isChecked = intent.getStringExtra("like").toBoolean()

        //Calling Button Reference from stackoverflow
        //https://stackoverflow.com/questions/32654580/android-studio-calling-button
        findViewById<FloatingActionButton>(R.id.buttonCall).setOnClickListener{
            startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)))
        }


    }


}