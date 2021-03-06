package ru.otus.erinary.hw08.library.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;

    @Field(name = "text")
    private String text;

    @Field(name = "user")
    private String user;

    @Field(name = "date")
    private ZonedDateTime date;

    @DBRef(lazy = true)
    @Field(name = "book")
    private Book book;

    public Comment() {
        this.id = UUID.randomUUID().toString();
    }

    public Comment(final String text, final String user, final Book book) {
        this.id = UUID.randomUUID().toString();
        this.text = text;
        this.user = user;
        this.date = ZonedDateTime.now();
        this.book = book;
    }
}
