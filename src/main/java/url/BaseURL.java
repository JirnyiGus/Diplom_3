package url;

import constant.Constant;
import io.restassured.RestAssured;

public class BaseURL {
    protected static void setUp(){
        RestAssured.baseURI = Constant.BURGER_URL;
    }
}
