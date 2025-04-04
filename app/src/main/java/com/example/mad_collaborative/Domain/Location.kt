package com.example.mad_collaborative.Domain

data class Location(
    var id: Int = 0,
    var loc: String = ""
) {
    override fun toString(): String {
        return loc
    }
}