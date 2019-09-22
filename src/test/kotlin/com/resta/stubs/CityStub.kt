package com.resta.stubs

import com.resta.core.address.City
import com.resta.core.address.CityAlias

class CityStub {
    companion object {
        fun getCity(): City =
                City(
                        name = "test city",
                        aliases = mutableListOf(CityAlias(name = "tc", city = getCity()))
                )
    }
}
