package praktikum.Users;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static praktikum.EnvConfig.*;

public class UserClient {
    @Step("Create new user")
    public Response register(Users user) {
        return given()
                .baseUri(URL_USER)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(URL_USER_REGISTER);
    }

    @Step("User login")
    public Response login(Users user) {
        return given()
                .baseUri(URL_USER)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(URL_USER_LOGIN);
    }

    @Step("User delete")
    public void delete(String accessToken) {
        given()
                .baseUri(URL_USER)
                .header("Authorization", accessToken)
                .delete(USER_ENDPOINT);
    }
}
