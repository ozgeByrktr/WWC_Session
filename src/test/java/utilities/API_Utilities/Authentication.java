package utilities.API_Utilities;

import base.BaseTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import static hooks.HooksAPI.spec;
import static io.restassured.RestAssured.given;

public class Authentication extends BaseTest {
    public static String generateToken(String user) {

        JSONObject reqBody = null;

        spec = new RequestSpecBuilder().setBaseUri(configLoader.getApiConfig("base_url")).build();

        switch (user) {
            case "admin":
                spec.pathParams("pp1", "api", "pp2", "getToken");
                reqBody = new JSONObject();
                reqBody.put("email", configLoader.getApiConfig("adminEmail"));
                reqBody.put("password", configLoader.getApiConfig("adminPassword"));
                break;
            case "teacher":
                spec.pathParams("pp1", "api", "pp2", "getToken");
                reqBody = new JSONObject();
                reqBody.put("email", configLoader.getApiConfig("teacherEmail"));
                reqBody.put("password", configLoader.getApiConfig("teacherPassword"));
                break;
            case "student":
                spec.pathParams("pp1", "apistudent", "pp2", "getToken");
                reqBody = new JSONObject();
                reqBody.put("email", configLoader.getApiConfig("studentEmail"));
                reqBody.put("password", configLoader.getApiConfig("studentPassword"));
                break;
        }

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .when()
                .body(reqBody.toString())
                .post("/{pp1}/{pp2}");


        JsonPath repJP = response.jsonPath();

        String token = repJP.getString("token");

        return token;
    }
}
