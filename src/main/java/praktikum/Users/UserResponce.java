package praktikum.Users;


import lombok.Data;

@Data
public class UserResponce {
    private boolean success;
    private Users user;
    private String accessToken;
    private String refreshToken;
    private String message;

    public UserResponce(boolean success, Users user, String accessToken, String refreshToken) {
        this.success = success;
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public UserResponce(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
