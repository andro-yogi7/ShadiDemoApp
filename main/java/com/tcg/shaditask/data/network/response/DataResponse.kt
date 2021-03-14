package com.tcg.shaditask.data.network.response

data class DataResponse(
    var results: List<Results>? = null,
    var info: Info? = null
)

data class Info (
    val seed : String? = null,
    val results : Int? = null,
    val page : Int? = null,
    val version : String? = null
)

data class Results (
    var gender : String,
    val name : Name,
    val location : Location,
    val email : String,
    val login : Login,
    val dob : Dob,
    val registered : Registered,
    val phone : String,
    val cell : String,
    val id : Id,
    val picture : Picture,
    val nat : String
)

data class Name (

    val title : String,
    val first : String,
    val last : String
)

data class Location (

    val street : Street,
    val city : String,
    val state : String,
    val country : String,
    val postcode : String,
    val coordinates : Coordinates,
    val timezone : Timezone
)

data class Login (

    val uuid : String,
    val username : String,
    val password : String,
    val salt : String,
    val md5 : String,
    val sha1 : String,
    val sha256 : String
)

data class Dob (

    val date : String,
    val age : Int
)

data class Registered (

    val date : String,
    val age : Int
)

data class Id (

    val name : String,
    val value : String
)

data class Picture (

    val large : String,
    val medium : String,
    val thumbnail : String
)

data class Street (

    val number : Int,
    val name : String
)

data class Coordinates (

    val latitude : String,
    val longitude : String
)

data class Timezone (

    val offset : String,
    val description : String
)