package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Ilya", "Kyzmin", (byte) 20);
        userService.saveUser("Adndrey", "Semenov", (byte) 25);
        userService.saveUser("Vova", "Ivanov", (byte) 17);
        userService.saveUser("Valeryi", "Zmishenko", (byte) 54);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
