package com.example.kotlindemo.controllers

import com.example.kotlindemo.models.Article
import com.example.kotlindemo.respositories.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid
import javax.xml.ws.Response


@RestController
@RequestMapping("/api")
class ArticleController(private val articleRepository: ArticleRepository) {
    @GetMapping("/articles")
    fun getAllArticles(): List<Article> = articleRepository.findAll()

    @PostMapping("/articles")
    fun createNewArticle(@Valid @RequestBody article: Article): Article = articleRepository.save(article)

    @GetMapping("/articles/{id}")
    fun getArticleById(@PathVariable(value = "id") articleId: Long): ResponseEntity<Article> {
        return articleRepository.findById(articleId).map { article ->
            ResponseEntity.ok(article)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/articles/{id}")
    fun updateArticleById(@PathVariable(value = "id") articleId: Long,
                          @Valid @RequestBody newArticle: Article): ResponseEntity<Article> {
        return articleRepository.findById(articleId).map { existinArticle ->
            val updatedArticle: Article = existinArticle.copy(title = newArticle.title, content = newArticle.content)
            ResponseEntity.ok().body(articleRepository.save(updatedArticle))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/articles/{id}")
    fun deleteArticleById(@PathVariable(value = "id") articleId: Long): ResponseEntity<Void> {
        return articleRepository.findById(articleId).map { article ->
            articleRepository.delete(article)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }


}