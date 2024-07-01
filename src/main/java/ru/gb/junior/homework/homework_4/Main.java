package ru.gb.junior.homework.homework_4;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.gb.junior.homework.homework_4.builder.TableBuilder;
import ru.gb.junior.homework.homework_4.entity.Post;
import ru.gb.junior.homework.homework_4.entity.PostComment;
import ru.gb.junior.homework.homework_4.entity.User;

public class Main {
    /**
     * Используя hibernate, создать таблицы:
     * 1. Post (публикация) (id, title)
     * 2. PostComment (комментарий к публикации) (id, text, post_id)
     *
     * Написать стандартные CRUD-методы: создание, загрузка, удаление.
     *
     * Доп. задания:
     * 1. * В сущностях post и postComment добавить поля timestamp с датами.
     * 2. * Создать таблицу users(id, name) и в сущностях post и postComment добавить ссылку на юзера.
     * 3. * Реализовать методы:
     * 3.1 Загрузить все комментарии публикации
     * 3.2 Загрузить все публикации по идентификатору юзера
     * 3.3 ** Загрузить все комментарии по идентификатору юзера
     * 3.4 **** По идентификатору юзера загрузить юзеров, под чьими публикациями он оставлял комменты.
     * // userId -> List<User>
     *
     *
     * Замечание:
     * 1. Можно использовать ЛЮБУЮ базу данных (например, h2)
     * 2. Если запутаетесь, приходите в группу в телеграме или пишите мне @inchestnov в личку.
     */
    public static void main(String[] args) {
        TableService service = new TableService();
        TableBuilder builder =new TableBuilder();

        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

//        Создание и заполнение таблиц users, post, post_comment
        service.createTable(builder.getUsers(), sessionFactory);
        service.createTable(builder.getPosts(),sessionFactory);
        service.createTable(builder.getComments(), sessionFactory);

//        Добавление новых данных в таблицы
        service.insertIntoTable(new Post("Post #55", builder.getCurrentDate(),
                builder.getUsers().get(2)), sessionFactory);
        service.insertIntoTable(new PostComment("Жесть!!!!", service.readFromTable(Post.class, 1L, sessionFactory),
                builder.getCurrentDate(), builder.getUsers().get(3)), sessionFactory);

//        Чтение данных из таблиц
        System.out.println(service.readFromTable(Post.class, 1L, sessionFactory));
        System.out.println(service.readFromTable(PostComment.class, 3L, sessionFactory));

//        Изменения в таблицах
        service.updateTable(new Post(3,"Post #100", builder.getCurrentDate(),
                builder.getUsers().get(1)), sessionFactory);
        service.updateTable(new PostComment(11L, "Супер жесть!", service.readFromTable(Post.class, 1L, sessionFactory),
                builder.getCurrentDate(), builder.getUsers().get(4)), sessionFactory);

//        Удаление строк из таблиц
        service.deleteFromTable(service.readFromTable(PostComment.class, 11L, sessionFactory), sessionFactory);
        service.deleteFromTable(service.readFromTable(Post.class, 5L, sessionFactory), sessionFactory);
//        Удаление пользователя с его публикациями и комментариями
        service.deleteFromTable(service.readFromTable(User.class, 2L, sessionFactory), sessionFactory);

//          Загрузить все комментарии публикации
        System.out.println("Комментарии к публикации с ID = 1:");
        service.printPostComments(1L, sessionFactory);

//        Загрузить все публикации по идентификатору юзера
        System.out.println("Публикации пользователя с ID = 1:");
        service.printPostsByUserId(1L, sessionFactory);

//        Загрузить все комментарии по идентификатору юзера
        System.out.println("Комментарии пользователя с ID = 1:");
        service.printCommentsByUserId(1L, sessionFactory);

//        По идентификатору юзера загрузить юзеров, под чьими публикациями он оставлял комменты
        System.out.println("Пользователи под чьими публикациями оставил комментарии пользователь с ID = 1:");
        System.out.println(service.getUsersByUserId(1L, sessionFactory));
    }
}
