package ru.otus.erinary.hw07.springdatalibrary.service;

import ru.otus.erinary.hw07.springdatalibrary.model.Author;
import ru.otus.erinary.hw07.springdatalibrary.model.Book;
import ru.otus.erinary.hw07.springdatalibrary.model.Comment;
import ru.otus.erinary.hw07.springdatalibrary.model.Genre;

import java.util.List;

public interface LibraryService {

    List<Author> getAuthors();

    Author getAuthorByName(String name);

    void deleteAuthor(Long id);

    List<Genre> getGenres();

    Genre getGenreByName(String name);

    void deleteGenre(Long id);

    List<Book> getBooks();

    Book getBookById(Long id);

    Book saveBook(Long id, String title, int year, String authorName, String genreName);

    void deleteBook(Long id);

    List<Comment> getBookComments(Long bookId);

    Long saveComment(String text, String user, Long bookId);

    void deleteComment(Long id);
}
