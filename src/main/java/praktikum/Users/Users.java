package praktikum.Users;
import lombok.Data;
import java.util.concurrent.ThreadLocalRandom;

@Data
public class Users {
    private String email;
    private String password;
    private String name;

    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
