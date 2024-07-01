package ru.gb.junior.homework.homework_4.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "date")
    private Date timeStamp;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostComment> comments;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {
    }

    public Post(String title, Date date, User user) {
        this.title = title;
        timeStamp = date;
        this.user = user;
    }

    public Post(long id, String title, Date date, User user) {
        this.id = id;
        this.title = title;
        timeStamp = date;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<PostComment> getComments() {
        return comments;
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Публикация => " +
                "id: " + id +
                "| Название: '" + title + '\'' +
                "| timeStamp: " + timeStamp +
                "| Инфо о пользовател: " + user;
    }
}
