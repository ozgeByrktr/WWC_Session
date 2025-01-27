package utilities.API_Utilities;

import base.BaseTest;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;

import static hooks.HooksAPI.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class API_Methods extends BaseTest {
    public static String fullPath;

    public static void pathParam(String rawPaths) {
        String[] paths = rawPaths.split("/");

        System.out.println(Arrays.toString(paths));

        StringBuilder tempPath = new StringBuilder("/{");


        for (int i = 0; i < paths.length; i++) {

            String key = "pp" + i;
            String value = paths[i].trim();

            spec.pathParam(key, value);

            tempPath.append(key + "}/{");

        }
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));

        fullPath = tempPath.toString();
        System.out.println("fullPath = " + fullPath);
    }

    public static Response sendRequest(String httpMethod, Object requestBody) {

        switch (httpMethod.toUpperCase()) {
            case "GET":
                response = given()
                        .spec(spec)
                        .when()
                        .get(fullPath);
                break;
            case "POST":
                response = given()
                        .spec(spec)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(requestBody)
                        .post(fullPath);
                break;
            case "PATCH":
                response = given()
                        .spec(spec)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(requestBody)
                        .patch(fullPath);
                break;
            case "DELETE":
                response = given()
                        .spec(spec)
                        .contentType(ContentType.JSON)
                        .when()
                        .body(requestBody)
                        .delete(fullPath);
                break;
        }

        if (response != null) {
            response.prettyPrint();
        }

        return response;
    }


    public static String tryCatchRequest(String httpMethod, Object requestBody) {
        String exceptionMesaj = null;
        try {
            switch (httpMethod.toUpperCase()) {
                case "GET":
                    response = given()
                            .spec(spec)
                            .when()
                            .get(fullPath);
                    break;
                case "DELETE":
                    response = given()
                            .spec(spec)
                            .contentType(ContentType.JSON)
                            .when()
                            .body(requestBody)
                            .delete(fullPath);
                    break;
                case "PATCH":
                    response = given()
                            .spec(spec)
                            .contentType(ContentType.JSON)
                            .when()
                            .body(requestBody)
                            .patch(fullPath);
                    break;
            }
        } catch (Exception e) {
            exceptionMesaj = e.getMessage();
        }
        System.out.println("Exception Mesaj : " + exceptionMesaj);
        return exceptionMesaj;
    }

    public static void statusCodeAssert(int statusCode) {
        response.then()
                .assertThat()
                .statusCode(statusCode);
    }

    public static void assertBody(String path, String value) {
        response.then()
                .assertThat()
                .body(path, equalTo(value));
    }

    public static int idCreate() {
        int id;
        if (addedId == 0) {
            id = (int) faker.number().randomNumber(10, true);
        } else {
            id = addedId;
        }

        return id;
    }

    public static void assertPathParam(String reponseId, String key) {
        repJP = response.jsonPath();

        int idValue = repJP.getInt(reponseId);

        int idRequest = (int) builder.getParameter(key);

        assertEquals(idRequest, idValue);
    }

    public static void verification(String pp1, String pp2, String dataKey, String path, Object value) {
        repJP = response.jsonPath();

        int idValue = repJP.getInt(dataKey);

        System.out.println(dataKey + " : " + idValue);

        spec = new RequestSpecBuilder().setBaseUri(configLoader.getApiConfig("base_url")).build();
        spec.pathParams("pp1", pp1, "pp2", pp2);

        JSONObject reqBody = new JSONObject();
        reqBody.put("id", idValue);

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                //.header("Authorization", "Bearer " + Authentication.generateToken())
                .when()
                .body(reqBody.toString())
                .get("/{pp1}/{pp2}");

        response.then()
                .assertThat()
                .body(path, equalTo(value));
    }

    public static int addedId(String pp2, String folder, String idKey) {

        spec = new RequestSpecBuilder().setBaseUri(configLoader.getApiConfig("base_url")).build();
        spec.pathParams("pp1", "api", "pp2", pp2);
        TestData testData = new TestData();
        HashMap<String, Object> requestBody = testData.requestBody(folder);

        response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                //.header("Authorization", "Bearer " + Authentication.generateToken())
                .when()
                .body(requestBody)
                .post("/{pp1}/{pp2}");

        map = response.as(HashMap.class);

        int id = (int) map.get(idKey);

        System.out.println(idKey + " : " + id);

        return id;
    }

    public static int addedId;

    @Before(order = 1)
    public void beforePatchOrDelete(Scenario scenario) {
        if (scenario.getSourceTagNames().contains("@Patch") || scenario.getSourceTagNames().contains("@Delete")) {
            String scenarioName = scenario.getName();

            String pp2 = null;
            String folder = null;
            String idKey = null;

            if (scenarioName.contains("VISITORSPURPOSE")) {
                pp2 = "visitorsPurposeAdd";
                folder = "visitorsPurpose";
                idKey = "addId";
            } else if (scenarioName.contains("VISITOR")) {
                pp2 = "visitorsAdd";
                folder = "visitor";
                idKey = "addId";
            }

            // ID oluşturma
            addedId = API_Methods.addedId(pp2, folder, idKey);
        }
    }


}
