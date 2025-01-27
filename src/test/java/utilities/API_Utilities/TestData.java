package utilities.API_Utilities;

import base.BaseTest;

import java.util.HashMap;

public class TestData extends BaseTest {
    HashMap<String, HashMap<String, Object>> reqBody = new HashMap<>();
    HashMap<String, Object> requestBody;

    public HashMap findingRequestBody() {

        requestBody = new HashMap<>();

        requestBody.put("name", "mouth sore");
        requestBody.put("description", "mouth sore is");
        requestBody.put("finding_category_id", 2);

        return requestBody;
    }

    public HashMap requestBody(String folder) {
        reqBody.put("finding", findingRequestBody());

        return reqBody.get(folder);
    }

}
