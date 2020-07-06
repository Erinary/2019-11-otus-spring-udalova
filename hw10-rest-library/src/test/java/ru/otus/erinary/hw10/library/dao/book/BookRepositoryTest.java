package ru.otus.erinary.hw10.library.dao.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.erinary.hw10.library.model.Author;
import ru.otus.erinary.hw10.library.model.Book;
import ru.otus.erinary.hw10.library.model.Genre;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    void testSaveNew() {
        var books = repository.findAll();
        assertFalse(books.isEmpty());
        assertEquals(4, books.size());

        var book = new Book("newTitle", 2018,
                new Author(1L, "author1", null),
                new Genre(1L, "genre1", null));
        repository.save(book);
        assertNotEquals(0L, book.getId());

        books = repository.findAll();
        assertEquals(5, books.size());
        assertTrue(books.contains(book));
    }

    @Test
    void testSaveExisted() {
        var books = repository.findAll();
        assertEquals(4, books.size());

        var book = repository.findById(1L).orElseThrow();
        assertEquals("title1", book.getTitle());

        var newTitle = "newTitle";
        book.setTitle(newTitle);
        repository.save(book);

        var loadedBook = repository.findById(1L).orElseThrow();
        assertEquals(newTitle, loadedBook.getTitle());
    }

    @Test
    void testFindById() {
        var book = repository.findById(1L).orElseThrow();
        assertEquals("title1", book.getTitle());
        assertEquals(2020, book.getYear());

        assertNotNull(book.getAuthor());
        assertEquals("author1", book.getAuthor().getName());

        assertNotNull(book.getGenre());
        assertEquals("genre1", book.getGenre().getName());
    }

    @Test
    void testFindAll() {
        var books = repository.findAll();
        assertFalse(books.isEmpty());
        assertEquals(4, books.size());
        assertNotNull(books.get(0).getAuthor());
        assertNotNull(books.get(0).getGenre());

        var bookTitles = books.stream().map(Book::getTitle).collect(Collectors.toList());
        assertTrue(bookTitles.containsAll(List.of("title1", "title2", "title3")));

        var authorNames = books.stream().map(book -> book.getAuthor().getName()).collect(Collectors.toList());
        assertTrue(authorNames.containsAll(List.of("author1", "author2", "author3")));

        var genreNames = books.stream().map(book -> book.getGenre().getName()).collect(Collectors.toList());
        assertTrue(genreNames.containsAll(List.of("genre1", "genre2", "genre3")));
    }

    @Test
    void testFindAllByAuthorId() {
        var books = repository.findAllByAuthorId(1L);
        assertEquals(2, books.size());
        assertEquals("author1", books.get(0).getAuthor().getName());
        var bookTitles = books.stream().map(Book::getTitle).collect(Collectors.toList());
        assertTrue(bookTitles.containsAll(List.of("title1", "title4")));
    }

    @Test
    void testFindAllByGenreId() {
        var books = repository.findAllByGenreId(2L);
        assertEquals(2, books.size());
        assertEquals("genre2", books.get(0).getGenre().getName());
        var bookTitles = books.stream().map(Book::getTitle).collect(Collectors.toList());
        assertTrue(bookTitles.containsAll(List.of("title2", "title4")));
    }

    @Test
    void testDelete() {
        var books = repository.findAll();
        assertFalse(books.isEmpty());
        assertEquals(4, books.size());

        repository.deleteById(1L);
        books = repository.findAll();
        assertEquals(3, books.size());
        var bookIds = books.stream().map(Book::getId).collect(Collectors.toList());
        assertFalse(bookIds.contains(1L));
    }

}