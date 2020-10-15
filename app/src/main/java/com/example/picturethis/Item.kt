package com.example.picturethis

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

//  Make the Model to be Parcelable
@Parcelize
data class Item constructor(var id: Int, var imageID: Int, var name: String, var date: Date, var type: String, var rating: Float) : Parcelable
