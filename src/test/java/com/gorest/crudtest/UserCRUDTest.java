package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
        static String name = TestUtils.getRandomValue() + "Anki";
        static String email = TestUtils.getRandomValue() + "akmk@gmail.com";
        static String gender = "Female";
        static String status = "Active";


        @Test
        public void verifyUserCreatedSuccessfully() {

            UserPojo userPojo = new UserPojo();
            userPojo.setName(name);
            userPojo.setEmail(email);
            userPojo.setGender(gender);
            userPojo.setStatus(status);

            Response response = given().log().all()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer 10efcc0eda0dc46c3d8b3d415af61bad8412b32398edede555b02a9ab8860eb4")
                    .when()
                    .body(userPojo)
                    .post("https://gorest.co.in/public/v2/users");
            response.then().log().all().statusCode(201);
            response.prettyPrint();
        }

        @Test
        public void verifyNewUserInfo() {

            Response response = given()
                    .header("Content-Type","application/json")
                    .header("Authorization","Bearer 10efcc0eda0dc46c3d8b3d415af61bad8412b32398edede555b02a9ab8860eb4")
                    .queryParams("id", 5970247)
                    .when()
                    .get("/posts");
            response.then().statusCode(200);
            response.prettyPrint();
        }

        @Test
        public void verifyUserUpdateSuccessfully() {

            UserPojo userPojo = new UserPojo();
            String email = TestUtils.getRandomValue() + "kakadasha1234@gmail.com";
            userPojo.setEmail(email);

            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer 10efcc0eda0dc46c3d8b3d415af61bad8412b32398edede555b02a9ab8860eb4")
                    .pathParam("id", 5970247)
                    .when()
                    .body(userPojo)
                    .when().patch("/public/v2/users/{id}");
            response.prettyPrint();

        }

        @Test
        public void verifyUserDeleteSuccessfully() {
            UserPojo userPojo = new UserPojo();
            Response response = given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer 10efcc0eda0dc46c3d8b3d415af61bad8412b32398edede555b02a9ab8860eb4")
                    .pathParam("id", 5970247)
                    .when()
                    .body(userPojo)
                    .when().delete("/public/v2/users/{id}");
            response.prettyPrint();
        }
    }
