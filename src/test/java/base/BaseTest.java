package base;

import com.github.javafaker.Faker;
import config_Requirements.ConfigLoader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.API_Utilities.RequestBuilder;


import java.util.HashMap;

public abstract class BaseTest {

    protected static ConfigLoader configLoader;

    protected static JsonPath repJP;
    protected static Response response;
    protected static HashMap map;

    protected static RequestBuilder builder;

    protected static String requestBody;
    public static Faker faker;

    public BaseTest() {
        builder = new RequestBuilder();
        map = new HashMap<>();
        configLoader = new ConfigLoader();
        faker = new Faker(); // Faker nesnesi initialize edildi.
    }
}
