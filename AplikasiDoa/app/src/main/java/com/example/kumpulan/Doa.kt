package com.example.kumpulan

import android.os.Parcel
import android.os.Parcelable

data class Doa(
    val no: Int? = null,
    var judulDoa: String? = null,
    val bArab: String? = null,
    val bIndo: String? = null,
    var tanggal: String? = null,
    var waktu: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(no)
        parcel.writeString(judulDoa)
        parcel.writeString(bArab)
        parcel.writeString(bIndo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Doa> {
        override fun createFromParcel(parcel: Parcel): Doa {
            return Doa(parcel)
        }

        override fun newArray(size: Int): Array<Doa?> {
            return arrayOfNulls(size)
        }
    }
}