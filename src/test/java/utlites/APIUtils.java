package utlites;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIUtils {

    public static void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    public static RequestSpecification getRequestSpecification() {
        return RestAssured.given().header("Content-Type", "application/json");
    }

    public static String buildProductJson(String title, String price, String description) {
        return "{\"title\":\"" + title + "\",\"price\":" + price + ",\"description\":\"" + description + "\"}";
    }

    public static String buildPatchJson(String field, String value) {
        if (field.equals("price")) {
            return "{\"" + field + "\":" + value + "}";
        } else {
            return "{\"" + field + "\":\"" + value + "\"}";
        }
    }
}
