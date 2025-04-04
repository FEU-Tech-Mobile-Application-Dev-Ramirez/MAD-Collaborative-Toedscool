package com.example.mad_collaborative.Domain

import java.io.Serializable

data class ItemDomain(
    var title: String = "",
    var address: String = "",
    var description: String = "",
    var pic: String = "",
    var duration: String = "",
    var timeTour: String = "",
    var dateTour: String = "",
    var tourGuideName: String = "",
    var tourGuidePhone: String = "",
    var tourGuidePic: String = "",
    var price: Int = 0,
    var bed: Int = 0,
    var distance: String = "",
    var score: Double = 0.0
) : Serializable