package com.example.homesweethome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_rental.view.*


class MainActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    private var adapter: propertyRentalAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set our recyclerview to use LinearLayour
        rentalPropertyRecyclerView.layoutManager = LinearLayoutManager(this)
        val query = db.collection("rentalProperty").orderBy("propertyLocation", Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<Rental>().setQuery(query, Rental::class.java).build()
        adapter = propertyRentalAdapter(options)
        rentalPropertyRecyclerView.adapter = adapter
        adapter!!.startListening()

        findViewById<Button>(R.id.buttonLogout).setOnClickListener {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        // ...
                        var intent = Intent(applicationContext, RegisterActivity::class.java)
                        startActivity(intent)
                    }
        }

        findViewById<Button>(R.id.buttotAddProperty).setOnClickListener{
            var intent = Intent(applicationContext, RentalActivity::class.java)
            startActivity(intent)
        }
        var user = FirebaseAuth.getInstance().currentUser

        if(user == null){
            var intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    // create inner classes needed to bind the data to the recyclerview
    private inner class propertyRentalViewHolder internal constructor(private val view: View) : RecyclerView.ViewHolder(view) {}

    private inner class propertyRentalAdapter internal constructor(options: FirestoreRecyclerOptions<Rental>) :
            FirestoreRecyclerAdapter<Rental, propertyRentalViewHolder>(options) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): propertyRentalViewHolder {

            // inflate the item_restaurant.xml layout template to populate the recyclerview
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rental, parent, false)
            return propertyRentalViewHolder(view)
        }

        override fun onBindViewHolder(
                holder: propertyRentalViewHolder,
                position: Int,
                model: Rental
        ) {
            holder.itemView.textViewRentalLocation.text = model.propertyLocation
            holder.itemView.textViewPrice.text ="$ " + model.rentalPerMonth.toString()
            holder.itemView.textViewType.text = model.propertyType

            holder.itemView.setOnClickListener {
                val intent = Intent(applicationContext, PropertyActivity::class.java)
                intent.putExtra("id", model.id)
                intent.putExtra("location",model.propertyLocation)
                intent.putExtra("phone",model.phoneNumber)
                intent.putExtra("type",model.propertyType)
                intent.putExtra("rent",model.rentalPerMonth.toString())
                intent.putExtra("like", model.like.toString())
                startActivity(intent)
            }
        }
    }

}