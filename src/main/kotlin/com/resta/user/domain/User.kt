package com.resta.user.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.resta.auth.OAuth2UserInfo
import com.resta.event.domain.Event
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull

@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["email"])])
data class User(@Id @GeneratedValue var id: Long? = null) {

    @CreationTimestamp
    val createdAt: LocalDateTime? = null

    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null

    @Column(nullable = false)
    var name: String? = null

    @NotNull
    @Email
    @Column(nullable = false)
    @JsonIgnore
    var email: String? = null

    var imageUrl: String? = null


    @JsonIgnore
    var password: String? = null

    @NotNull
    @Enumerated(EnumType.STRING)
    var provider: AuthProvider? = null

    @JsonIgnore
    var providerId: String? = null

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    val roles: MutableSet<UserRole> = hashSetOf(UserRole.USER)

//    @ManyToMany(mappedBy = "users")
//    @JsonIgnore
//    val events: MutableSet<Event> = HashSet()

    constructor(email: String, password: String, name: String) : this() {
        this.email = email
        this.password = password
        this.name = name
        this.provider = AuthProvider.local
    }

    constructor(oAuth2UserInfo: OAuth2UserInfo, provider: AuthProvider) : this() {
        this.roles.add(UserRole.USER)

        this.name = oAuth2UserInfo.name
        this.email = oAuth2UserInfo.email
        this.imageUrl = oAuth2UserInfo.imageUrl
        this.provider = provider
        this.providerId = oAuth2UserInfo.id
    }
}
