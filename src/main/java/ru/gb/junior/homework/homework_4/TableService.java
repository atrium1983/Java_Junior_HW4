package ru.gb.junior.homework.homework_4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.gb.junior.homework.homework_4.entity.Post;
import ru.gb.junior.homework.homework_4.entity.PostComment;
import ru.gb.junior.homework.homework_4.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TableService {

    public static <T> void createTable(List<T> elements, SessionFactory sessionFactory){
        try(Session session = sessionFactory.openSession()){
            for (T elem : elements) {
                session.beginTransaction();
                session.save(elem);
                session.getTransaction().commit();
            }
        }
    }

    public <T> void insertIntoTable(T elem, SessionFactory sessionFactory){
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(elem);
            session.getTransaction().commit();
        }
    }

    public <T> T readFromTable(Class<T> className, long id, SessionFactory sessionFactory){
        try(Session session = sessionFactory.openSession()){
            return session.find(className, id);
        }
    }

    public <T> void updateTable(T elem, SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.merge(elem);
            session.getTransaction().commit();
        }
    }

    public <T> void deleteFromTable(T elem, SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.remove(elem);
            session.getTransaction().commit();
        }
    }

    public void printPostComments(long id, SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()) {
            Post post = session.find(Post.class, id);
            for (PostComment comment : post.getComments()) {
                System.out.println(comment);
            }
        }
    }

    public void printPostsByUserId(long id, SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()) {
            User user = session.find(User.class, id);
            for (Post post : user.getPosts()) {
                System.out.println(post);
            }
        }
    }

    public void printCommentsByUserId(long id, SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()) {
            User user = session.find(User.class, id);
            for (PostComment comment : user.getComments()) {
                System.out.println(comment);
            }
        }
    }

    public Set<User> getUsersByUserId(long id, SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()) {
            Set<User> usersList = new HashSet<>();
            User user = session.find(User.class, id);
            for (PostComment comment : user.getComments()) {
                usersList.add(comment.getPost().getUser());
            }
            return usersList;
        }
    }
}
