package com.codebrains.todoapi.entities

import javax.persistence.*

@Table(name = "todo")
@Entity
class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    var id: Long? = null

    var title: String? = null
    var completed: Boolean? = false
}