package com.resta.core.address

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
data class City(
        var name: String,

        @OneToMany(mappedBy = "city", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        @JsonIgnore
        var aliases: MutableList<CityAlias>? = mutableListOf()
) : BaseEntity()
