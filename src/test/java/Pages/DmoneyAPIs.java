package Pages;

import Config.UserModel;
import Utils.Utils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class DmoneyAPIs {

    public String baseUrl;

    public DmoneyAPIs(String baseUrl) {
        this.baseUrl = baseUrl;

    }

    public String callingLoginAPI(UserModel model) {
        RestAssured.baseURI = baseUrl;
        Response res = given().contentType("application/json")
                .body(model)
                .when()
                .post("/user/login")
                .then().assertThat()
                .statusCode(200)
                .extract().response();
        System.out.println(res.asString());

        JsonPath jsonPath = res.jsonPath();
        String Token = jsonPath.get("token");

        return Token;
    }

    public void createUser(String Token,String partnerKey, UserModel model) throws IOException, ParseException {
        RestAssured.baseURI = baseUrl;
        Response res = given().contentType("application/json")
                .header("Authorization", Token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .body(model)
                .when()
                .post("/user/create")
                .then().assertThat()
                .statusCode(201)
                .extract().response();
        System.out.println(res.asString());

        Utils.saveUserInfo(model);

    }

    public void deposit(String Token,String partnerKey, UserModel model) {
        RestAssured.baseURI = baseUrl;
        Response res = given().contentType("application/json")
                .header("Authorization", Token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .body(model)
                .when()
                .post("/transaction/deposit")
                .then().assertThat()
                .statusCode(201)
                .extract().response();
        System.out.println(res.asString());

    }

    public void withdraw(String Token, String partnerKey, UserModel model) {
        RestAssured.baseURI = baseUrl;
        Response res = given().contentType("application/json")
                .header("Authorization", Token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .body(model)
                .when()
                .post("/transaction/withdraw")
                .then()
                .assertThat().statusCode(201)
                .extract().response();


        System.out.println(res.asString());
    }


    public void checkBalance(String Token,String partnerKey, String agentPhoneNumber) {
        RestAssured.baseURI = baseUrl;
        Response res = given().contentType("application/json")
                .header("Authorization", Token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .when()
                .get("/transaction/balance/" + agentPhoneNumber);
        System.out.println(res.asString());

    }


    public void sendMoney(String Token, String partnerKey, UserModel model) {
        RestAssured.baseURI = baseUrl;
        Response res = given().contentType("application/json")
                .header("Authorization", Token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .body(model)
                .when()
                .post("/transaction/sendmoney")
                .then().assertThat()
                .statusCode(201)
                .extract().response();
        System.out.println(res.asString());
    }

    public void payment(String Token, String partnerKey,  UserModel model) {
        RestAssured.baseURI = baseUrl;
        Response res = given().contentType("application/json")
                .header("Authorization", Token)
                .header("X-AUTH-SECRET-KEY", partnerKey)
                .body(model)
                .when()
                .post("/transaction/payment")
                .then().assertThat()
                .statusCode(201)
                .extract().response();
        System.out.println(res.asString());
    }


}
