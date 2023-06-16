package com.example.inventory

class Product {
    var image: Int
    var name: String
    var description: String
    var price: String

    constructor(image: Int, name: String, description: String, price: String) {
        this.image = image
        this.name = name
        this.description = description
        this.price = price
    }
}