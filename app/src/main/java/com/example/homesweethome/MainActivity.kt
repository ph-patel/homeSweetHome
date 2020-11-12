package com.example.homesweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.google.firebase.firestore.FirebaseFirestore




class MainActivity : AppCompatActivity() {

    var signOutButton: Button = findViewById(R.id.buttonLogout)
    val db = FirebaseFirestore.getInstance()
    //private var adapter: rentalPropertyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        addProperty.setOnClickListener {
//            var intent = Intent(applicationContext, RentalActivity::class.java)
//            startActivity(intent)
//        }

        signOutButton.setOnClickListener {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        // ...
                    }
        }
    }
}