package praktikum.Users;

import net.datafaker.Faker;

public class RandomUsers {
    private static final Faker faker = new Faker();
    public static final String USER_EMAIL = faker.internet().emailAddress();
    public static final String USER_PASSWORD = faker.internet().password(6, 10);
    public static final String USER_NAME = faker.name().firstName();

    public RandomUsers(String email, String password, String name) {

    }
}
