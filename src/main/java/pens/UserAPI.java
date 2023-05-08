package pens;

import constant.Constant;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import serialization.User;
import url.BaseURL;

import static io.restassured.RestAssured.given;

public class UserAPI extends BaseURL {


    @Step("Удаление пользователя (DELETE /api/auth/user)")
    public static Response deleteUser(String accessToken) {
        setUp();
        return given()
                .header("Authorization", accessToken)
                .when()
                .delete(Constant.BURGER_API_USER_DELETE);
    }

    @Step("Авторизация пользователя (POST /api/auth/login)")
    public static  Response loginUser(User user) {
        setUp();
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(Constant.BURGER_API_USER_AUTH);
    }

    @Step("Создание пользователя POST /api/auth/register")
    public  static Response createUser(User user) {
        setUp();
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(Constant.BURGER_API_USER_CREATE);
    }
}
