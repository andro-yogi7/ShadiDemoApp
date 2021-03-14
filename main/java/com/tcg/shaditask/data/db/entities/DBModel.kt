package com.tcg.shaditask.data.db.entities

import androidx.room.Entity

@Entity(primaryKeys = ["username"])
data class DBModel(
    var username: String,
    var gender: String,
    var firstName: String,
    var lastName: String,
    var city: String,
    var state: String,
    var country: String,
    var postcode: String,
    var email: String,
    var age: String,
    var cell: String,
    var largePicture: String,
    var mediumPicture: String,
    var thumbnail: String,
    var isSurveyDone: Boolean = false,
    var isAccepted: Boolean = false
) {
    //constructor(): this()
}