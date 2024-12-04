package com.example.bookmanager.controller;

import com.example.bookmanager.model.Book;
import com.example.bookmanager.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name= "Book", description = "Book management APIs")
@RestController
@RequestMapping("/api/books") 
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Endpoint para listar todos os livros
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // Endpoint para criar um novo livro
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    // Endpoint para atualizar um livro
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.getBookById(id)
                .map(existingBook -> {
                    book.setId(id);
                    return ResponseEntity.ok(bookService.saveBook(book));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para excluir um livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}