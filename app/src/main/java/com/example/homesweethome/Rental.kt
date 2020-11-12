package com.example.homesweethome

class Rental {

    var like:Boolean?= null;
    var rentalPerMonth:Float? = null;
    var propertyLocation:String? = null;
    var propertyType:String? = null;

    constructor()
    constructor(like:Boolean, propertyLocation:String, propertyType:String, rentalPerMonth: Float){
        this.like = like
        this.propertyLocation = propertyLocation
        this.rentalPerMonth = rentalPerMonth
        this.propertyType = propertyType
    }


}