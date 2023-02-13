package com.example.amazon_adle

import android.os.Parcel
import android.os.Parcelable

class Product(
    private val id: Int,
    var title: String?,
    var imageResourceId: Int,
    var reviews: Int,
    var stars: Int,
    var price: Double,
    var discount: Double,
    var category: String?,
    var shippingToEcuador: Boolean
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeInt(imageResourceId)
        parcel.writeInt(reviews)
        parcel.writeInt(reviews)
        parcel.writeDouble(price)
        parcel.writeDouble(discount)
        parcel.writeString(category)
        parcel.writeByte(if (shippingToEcuador) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }

}