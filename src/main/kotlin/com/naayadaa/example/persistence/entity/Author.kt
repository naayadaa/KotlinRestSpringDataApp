package com.naayadaa.example.persistence.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "authors")
class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null
    lateinit var uuid: String
    @Column(name = "full_name")
    lateinit var fullName: String

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
//    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    var posts: MutableCollection<Post> = mutableListOf()
}
