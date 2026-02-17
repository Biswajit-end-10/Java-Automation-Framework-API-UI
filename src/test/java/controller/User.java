package controller;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import setup.Setup;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static utils.Utils.setEnviromentVariable;

public class User extends Setup {

    public User() throws IOException {
        intitconfig();
    }

    // 🔴 Negative Login Validation
    public void doLoginWithInvalid(String email, String password) throws ConfigurationException, IOException {

        RestAssured.baseURI = prop.getProperty("baseUrl");

        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setPassword(password);

        Response res =
                given()
                        .contentType("application/json")
                        .body(userModel)
                .when()
                        .post("/user/login")
                .then()
                        .assertThat()
                        .statusCode(404)
                        .time(lessThan(2000L))
                        .body("message", containsString("User"))
                        .extract()
                        .response();

        JsonPath jsonObj = res.jsonPath();
        String message = jsonObj.get("message");

        Assert.assertTrue(message.contains("User not found"));
    }

    // 🟢 Successful Login Validation (Improved)
    public void doLogin(String email, String password) throws ConfigurationException, IOException {

        RestAssured.baseURI = prop.getProperty("baseUrl");

        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setPassword(password);

        Response res =
                given()
                        .contentType("application/json")
                        .body(userModel)
                .when()
                        .post("/user/login")
                .then()
                        .assertThat()
                        .statusCode(200)
                        .time(lessThan(2000L))
                        .body("message", notNullValue())
                        .body("token", notNullValue())
                        .extract()
                        .response();

        JsonPath jsonObj = res.jsonPath();
        String token = jsonObj.get("token");
        String message = jsonObj.get("message");

        // Save token for further requests
        setEnviromentVariable("token", token);

        Assert.assertTrue(message.contains("Login successfully"));
    }

    // 🟢 User Creation (Existing Method)
    public Response userCreate(String name, String email, String password,
                               String phone_number, String nid,
                               String role, String token)
            throws ConfigurationException, InterruptedException {

        Thread.sleep(2000);

        UserModel registerModel =
                new UserModel(name, email, password, phone_number, nid, role);

        RestAssured.baseURI = prop.getProperty("baseUrl");

        return given()
                .contentType("application/json")
                .header("Authorization", token)
                .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                .body(registerModel)
                .when()
                .post("user/create")
                .then()
                .assertThat()
                .time(lessThan(2000L))
                .extract()
                .response();
    }

    // 🔴 New Negative User Creation (Invalid Token)
    public Response createUserWithInvalidToken(String name, String email,
                                               String password, String phone_number,
                                               String nid, String role)
            throws ConfigurationException {

        UserModel registerModel =
                new UserModel(name, email, password, phone_number, nid, role);

        RestAssured.baseURI = prop.getProperty("baseUrl");

        return given()
                .contentType("application/json")
                .header("Authorization", "InvalidToken")
                .header("X-AUTH-SECRET-KEY", prop.getProperty("partnerKey"))
                .body(registerModel)
                .when()
                .post("user/create")
                .then()
                .assertThat()
                .statusCode(401)
                .time(lessThan(2000L))
                .extract()
                .response();
    }
}
