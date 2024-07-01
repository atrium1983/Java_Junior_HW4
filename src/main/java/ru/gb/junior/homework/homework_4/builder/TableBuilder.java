package ru.gb.junior.homework.homework_4.builder;

import ru.gb.junior.homework.homework_4.entity.Post;
import ru.gb.junior.homework.homework_4.entity.PostComment;
import ru.gb.junior.homework.homework_4.entity.User;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TableBuilder {
    private List<Post> posts;
    private List<PostComment> comments;
    private List<User> users;
    String[] postText = {"Всё о кошках", "Жесть, смотреть до конца", "Адов ад",
            "Как похудеть и не умереть", "Современное искусство и другие ужасы",
            "Современная современность", "Традиционные традиции",
            "Сборник ответов на вопросы", "Исторические истории",
            "Художественное художество"};
    String[] commentText = {"Мне очень понравилась Ваша статья","Никогда бы не подумал","Статья проходная","Ну норм, спасибо!",
            "Ну как-то не очень", "Хрень хреновей сложно предстваить", "Отстойный отстой!", "С Вами там вообще всё в порядке?"};
    String[] userName = {"Андрей", "Максим", "Ольга", "Виктория", "Егор"};

    public TableBuilder() {
        posts = new ArrayList<>();
        comments = new ArrayList<>();
        users = new ArrayList<>();
        generateUsers();
        generatePosts();
        generateComments();
    }

    public void generatePosts(){
        long currId = 1L;
        for (String elem : postText) {
            posts.add(new Post(currId, elem, getRandDate(), users.get(getRandInt(0, users.size()))));
            currId++;
        }
    }

    public void generateComments(){
        long currId = 1L;
        for (Post elem : posts) {
            for (int i = 0; i < getRandInt(1, 5); i++) {
                comments.add(new PostComment(currId, commentText[getRandInt(0, commentText.length)],
                        elem, getRandDate(), users.get(getRandInt(0, users.size()))));
                currId++;
            }
        }
    }

    public void generateUsers(){
        long currId = 1L;
        for (String elem : userName) {
            users.add(new User(currId, elem));
            currId++;
        }
    }

    public int getRandInt(int start, int end){
        return ThreadLocalRandom.current().nextInt(start, end);
    }

    public Date getRandDate(){
        Random random =new Random();
        long min = 1704067200000L;
        long max = 1735689600000L;
        return new Date(random.nextLong(min, max));
    }

    public Date getCurrentDate(){
        return new Date();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<PostComment> getComments() {
        return comments;
    }

    public void setComments(List<PostComment> comments) {
        this.comments = comments;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
