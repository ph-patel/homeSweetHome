package com.example.homesweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore

class RentalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rental)

        val arraySpinner = arrayOf(
            "Apartment", "Condo", "Co-Op", "Town home", "Cottage", "Farmhouse", "Tiny Home"
        )
        val s = findViewById<View>(R.id.propertyTypeSpinner) as Spinner
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, arraySpinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        s.adapter = adapter

        findViewById<Button>(R.id.buttonList).setOnClickListener {
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.buttonSubmit).setOnClickListener {
            var db = FirebaseFirestore.getInstance().collection("rentalProperty")

            if ((!TextUtils.isEmpty(findViewById<EditText>(R.id.editTextLocation).text)) && (!TextUtils.isEmpty(
                    findViewById<EditText>(R.id.editTextNumberDecimal).text)
                )) {
                val id = db.document().id
                val rental= Rental()
                rental.id = id;
                rental.rentalPerMonth = findViewById<EditText>(R.id.editTextNumberDecimal).text.toString().toFloat()
                rental.propertyLocation = findViewById<EditText>(R.id.editTextLocation).text.toString()
                rental.like = findViewById<CheckBox>(R.id.checkBox).isChecked
                rental.propertyType = findViewById<Spinner>(R.id.propertyTypeSpinner).getSelectedItem().toString();
                rental.phoneNumber = findViewById<EditText>(R.id.editTextPhone).text.toString();
                db.document(id).set(rental);

                findViewById<EditText>(R.id.editTextNumberDecimal).setText("")
                findViewById<EditText>(R.id.editTextLocation).setText("")


            }
        }
    }
}