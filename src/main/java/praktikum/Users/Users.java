package praktikum.Users;
import lombok.Getter;
import lombok.Setter;
import java.util.concurrent.ThreadLocalRandom;

public class Users {
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String name;

    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static Users random() {
        int suffix = ThreadLocalRandom.current().nextInt(100, 100_000);
        return new Users("Jack" + suffix + "@ya.ru", "P@ssw0rd123", "Sparrow");
    }
}
