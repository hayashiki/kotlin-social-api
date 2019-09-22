package com.resta.core.address

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
data class CityAlias(
        var name: String,

        @ManyToOne
        @JsonIgnore
        var city: City
) : BaseEntity()