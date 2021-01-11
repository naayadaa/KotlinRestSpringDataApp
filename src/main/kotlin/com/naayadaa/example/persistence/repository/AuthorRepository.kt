package com.naayadaa.example.persistence.repository

import com.naayadaa.example.persistence.entity.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : JpaRepository<Author, Long>
