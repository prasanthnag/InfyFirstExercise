package com.infy.newspoc.model

import android.os.Parcel
import android.os.Parcelable

data class NewsDetails(
    var title: String? = "",
    var description: String? = "",
    var imageRef: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(imageRef)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsDetails> {
        override fun createFromParcel(parcel: Parcel): NewsDetails {
            return NewsDetails(parcel)
        }

        override fun newArray(size: Int): Array<NewsDetails?> {
            return arrayOfNulls(size)
        }
    }
}