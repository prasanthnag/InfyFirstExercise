package com.infy.infypoc.model

import android.os.Parcel
import android.os.Parcelable

data class CountryDetails(
    var title: String? = "",
    var description: String? = "",
    var imageRef: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(imageRef)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryDetails> {
        override fun createFromParcel(parcel: Parcel): CountryDetails {
            return CountryDetails(parcel)
        }

        override fun newArray(size: Int): Array<CountryDetails?> {
            return arrayOfNulls(size)
        }
    }
}