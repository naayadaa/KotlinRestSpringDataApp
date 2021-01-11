package com.naayadaa.example.rest

import com.naayadaa.example.persistence.repository.AuthorRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthorController(
    private val authorRepository: AuthorRepository
) {
    @GetMapping("/author", produces = ["application/json"])
    @Transactional
    suspend fun getAll(): ResponseEntity<*> {
        val author = authorRepository.findById(1L).get()
//        val posts = author.posts

        return ResponseEntity(author, HttpStatus.OK)
    }
}
