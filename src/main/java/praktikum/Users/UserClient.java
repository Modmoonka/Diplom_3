package praktikum.Users;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static praktikum.EnvConfig.*;
import static praktikum.Users.UserResponce.*;

public class UserClient {
    @Step("Create new user")
    public Response register(Users user) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(URL_USER_REGISTER);
    }

    @Step("User login")
    public Response login(Users user) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(URL_USER_LOGIN);
    }

    @Step("User delete")
    public static void delete(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .when()
                .delete(URL_DELETE_USER);
    }

    public static String getAccessToken(Users user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user) // передаем объект
                .when()
                .post(URL_USER_LOGIN)
                .then()
                .extract()
                .path("accessToken");
    }
}
