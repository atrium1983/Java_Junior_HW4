# Домашнее задание 4

Используя hibernate, создать таблицы:
1. Post (публикация) (id, title)
2. PostComment (комментарий к публикации) (id, text, post_id)

Написать стандартные CRUD-методы: создание, загрузка, удаление.
Доп. задания:
1. * В сущностях post и postComment добавить поля timestamp с датами.
2. * Создать таблицу users(id, name) и в сущностях post и postComment добавить ссылку на юзера.
3. * Реализовать методы:
3.1 Загрузить все комментарии публикации
3.2 Загрузить все публикации по идентификатору юзера
3.3 ** Загрузить все комментарии по идентификатору юзера
3.4 **** По идентификатору юзера загрузить юзеров, под чьими публикациями он оставлял комменты.
// userId -> List<User>

Замечание:
1. Можно использовать ЛЮБУЮ базу данных (например, h2)
2. Если запутаетесь, приходите в группу в телеграме или пишите мне @inchestnov в личку.