package ru.darek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryUserService implements UserService {
    class User {
        private String login;
        private String password;
        private String username;
        private String role;
        private boolean ban;

        public User(String login, String password, String username) {
            this(login,password,username,"user");
        }
        public User(String login, String password, String username,String role) {
            this.login = login;
            this.password = password;
            this.username = username;
            this.role = role;
            this.ban = false;
        }
    }

    private List<User> users;

    public InMemoryUserService() {
        this.users = new ArrayList<>(Arrays.asList(
                new User("login1", "pass1", "user1"),
                new User("login2", "pass2", "user2"),
                new User("login3", "pass3", "user3"),
                new User("login4", "pass4", "user4","admin")
        ));
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.login.equals(login) && u.password.equals(password)) {
                return u.username;
            }
        }
        return null;
    }
    @Override
    public boolean getIsAdminByUsername(String username){
        for (User u : users) {
            if (u.username.equals(username) && u.role.equals("admin")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void createNewUser(String login, String password, String username) {
        users.add(new User(login, password, username));
    }

    @Override
    public boolean isLoginAlreadyExist(String login) {
        for (User u : users) {
            if (u.login.equals(login)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isUsernameAlreadyExist(String username) {
        for (User u : users) {
            if (u.username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setBanByUsername(String username,boolean banUnban) {
        for (User u : users) {
            if (u.username.equals(username)) {
                u.ban = true;
            }
        }
    }

    @Override
    public boolean isBanByUsername(String username) {
        for (User u : users) {
            if (u.username.equals(username)) {
               return u.ban;
            }
        }
        return false;
    }

    @Override
    public void setNewUsername(String currentUserName, String newUsername) {
        for (User u : users) {
            if (u.username.equals(currentUserName)) {
                u.username = newUsername;
                return;
            }
        }
    }
}