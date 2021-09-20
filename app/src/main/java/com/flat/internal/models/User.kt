package com.flat.internal.models



class User {
    var Firstname: String? = null
    var Lastname: String? = null
    var Phonenumber : String? = null

    constructor(Firstname:String, Lastname:String, Phonenumber:String) {
        this.Firstname = Firstname
        this.Lastname = Lastname
        this.Phonenumber = Phonenumber
    }

    constructor() {

    }
}