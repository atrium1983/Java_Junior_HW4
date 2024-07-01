package ru.gb.junior.homework.homework_4.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post_comment")
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "text")
    private String text;
    @Column(name = "date")
    private Date timeStamp;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PostComment() {
    }

    public PostComment(String text, Post post, Date date, User user) {
        this.text = text;
        this.post = post;
        timeStamp = date;
        this.user = user;
    }
    public PostComment(long id, String text, Post post, Date date, User user) {
        this.id = id;
        this.text = text;
        this.post = post;
        timeStamp = date;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Комментарий =>" +
                "id: " + id +
                "| Текст: '" + text + '\'' +
                "| Время: " + timeStamp +
                "| Инфо о публикации: " + post +
                "| Инфо о пользователе: =" + user;
    }
}
