package com.example.homesweethome

import com.firebase.ui.auth.data.model.PhoneNumber

class Rental {

    var id:String ?= null
    var like:Boolean?= null;
    var rentalPerMonth:Float? = null;
    var propertyLocation:String? = null;
    var propertyType:String? = null;
    var phoneNumber:String ?= null

    constructor()
    constructor(id:String, phoneNumber: String,like:Boolean, propertyLocation:String, propertyType:String, rentalPerMonth: Float){
        this.id = id
        this.like = like
        this.propertyLocation = propertyLocation
        this.rentalPerMonth = rentalPerMonth
        this.propertyType = propertyType
        this.phoneNumber = phoneNumber
    }


}