package com.example.amazon_adle

class ProductsDataArrayList {
    companion object {
        val productsList: ArrayList<Product> = arrayListOf()

        init {
            productsList.add(
                Product(
                    1,
                    "Lenovo Laptop Ideapad 3 2022, pantalla táctil HD de 15.6 pulgadas, " +
                            "procesador Intel Core I3-1115G4 de 11va generación, 8GB de RAM " +
                            "DDR4, SSD PCIe NVMe de 256 GB, HDMI, cámara web, Wi-Fi 5, " +
                            "Bluetooth, Windows 11 Home, almendra",
                    R.drawable.laptop_lenovo_ideapad3,
                    832,
                    4,
                    385.99,
                    0.35,
                    "computadoras",
                    true)
            )

            productsList.add(
                Product(
                    1,
                    "Another Laptop Lenovo Ideapad 3",
                    R.drawable.laptop_lenovo_ideapad3,
                    91,
                    3,
                    745.99,
                    0.0,
                    "computadoras",
                    false)
            )
        }
    }
}