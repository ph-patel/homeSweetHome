package com.example.homesweethome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.core.view.get
import com.firebase.ui.auth.AuthUI
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

        findViewById<Button>(R.id.buttonSubmit).setOnClickListener {
            var db = FirebaseFirestore.getInstance().collection("rentalProperty")

            if ((!TextUtils.isEmpty(findViewById<EditText>(R.id.editTextLocation).text)) && (!TextUtils.isEmpty(
                    findViewById<EditText>(R.id.editTextNumberDecimal).text)
                )) {
                val id = db.document().id
                val rental= Rental()
                rental.rentalPerMonth = findViewById<EditText>(R.id.editTextNumberDecimal).text.toString().toFloat()
                rental.propertyLocation = findViewById<EditText>(R.id.editTextLocation).text.toString()
                rental.like = findViewById<CheckBox>(R.id.checkBox).isChecked
                rental.propertyType = findViewById<Spinner>(R.id.propertyTypeSpinner).getSelectedItem().toString();
                db.document(id).set(rental);

                findViewById<EditText>(R.id.editTextNumberDecimal).setText(0)
                findViewById<EditText>(R.id.editTextLocation).setText("")
                
            }
        }
    }
}