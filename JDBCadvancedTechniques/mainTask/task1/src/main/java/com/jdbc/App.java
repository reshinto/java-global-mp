package com.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

public class App {
    public static void main( String[] args ) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "socialmedia", "postgres", "password");

        try {
            Connection connection = dcm.getConnection();

            /* uncomment this to add seed data into database */
            // createDefaultData(connection);

            UsersDAO usersDAO = new UsersDAO(connection);
            Timestamp timestamp = Timestamp.valueOf("2025-03-12 21:30:55.888");
            List<UserLine> users = usersDAO.getUsersForConditions(70, timestamp, 300);
            users.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public static void createDefaultData(Connection connection) {
        for (int i=1; i<=100; i++) {
            generateDefaultUsersData(connection, "George"+i, "Washington"+i, "2020-12-01");
        }

        for (int i=1; i<=100; i++) {
            int totalFriends = generateRandomNum(1, 100, 0);
            for (int j=1; j<=totalFriends; j++) {
                generateDefaultFriendshipsData(connection, i, j);
            }
        }

        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        for (int i=0; i<500; i++) {
            int userId = generateRandomNum(1, 100, 0);
            generateDefaultPostsData(connection, userId, text);
        }

        Timestamp timestamp2 = Timestamp.valueOf("2022-04-12 21:30:55.888");
        for (int userId=1; userId<=100; userId++) {
            for (int postId=1; postId<50; postId++) {
                generateDefaultLikesData(connection, postId, userId, timestamp2);
            }
        }
        Timestamp timestamp = Timestamp.valueOf("2025-03-12 21:30:55.888");
        for (int userId=1; userId<=100; userId++) {
            int maxPostIds = generateRandomNum(50, 500, 0);
            for (int postId=50; postId<=maxPostIds; postId++) {
                generateDefaultLikesData(connection, postId, userId, timestamp);
            }
        }
    }

    public static int generateRandomNum(int low, int high, int value) {
        Random r = new Random();
        int result = r.nextInt(high - low) + low;
        if (result == value) {
            return generateRandomNum(low, high, result);
        }
        return result;
    }

    public static void generateDefaultUsersData(Connection connection, String firstName, String lastName, String dateOfBirthInput) {
        UsersDAO usersDAO = new UsersDAO(connection);
        Date dateOfBirth = Date.valueOf(dateOfBirthInput);

        Users user = new Users();
        user.setName(firstName);
        user.setSurname(lastName);
        user.setDateOfBirth(dateOfBirth);

        usersDAO.create(user);
        System.out.println(user);
    }

    public static void generateDefaultFriendshipsData(Connection connection, long firstUserId, long secondUserId) {
        FriendshipsDAO friendshipsDAO = new FriendshipsDAO(connection);

        Friendships friendship = new Friendships();
        friendship.setFirstUserId(firstUserId);
        friendship.setSecondUserId(secondUserId);

        friendshipsDAO.create(friendship);
        System.out.println(friendship);
    }

    public static void generateDefaultPostsData(Connection connection, long userId, String text) {
        PostsDAO postsDAO = new PostsDAO(connection);
        Timestamp sqlTimestamp = Timestamp.valueOf("2025-03-12 21:30:55.888");

        Posts post = new Posts();
        post.setUserId(userId);
        post.setText(text);
        post.setCreatedDatetime(sqlTimestamp);
        post.setUpdatedDatetime(sqlTimestamp);

        postsDAO.create(post);
        System.out.println(post);
    }

    public static void generateDefaultLikesData(Connection connection, long postId, long userId, Timestamp timestamp) {
        LikesDAO likesDAO = new LikesDAO(connection);

        Likes like = new Likes();
        like.setPostId(postId);
        like.setUserId(userId);
        like.setCreatedDatetime(timestamp);
        like.setUpdatedDatetime(timestamp);

        likesDAO.create(like);
        System.out.println(like);
    }
}
