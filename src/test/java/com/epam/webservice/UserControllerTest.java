package com.epam.webservice;

import com.epam.webservice.dto.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserControllerTest extends BaseTest {
    private final String USERS = "/users";
    private Response response;

    @BeforeTest
    public void setup() {
        response = RestAssured.get(USERS);
    }

    @Test
    public void verifyStatusCodeTest() {
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200, "Expected status code is 200");
    }

    @Test
    public void verifyResponseHeaderTest() {
        String contentType = response.getHeader("Content-Type");
        assertTrue(response.getHeaders().hasHeaderWithName("Content-Type"), "Content-Type header is missing");
        assertEquals(contentType, "application/json; charset=utf-8", "Unexpected Content-Type");
    }

    @Test
    public void verifyResponseBody() {
        ResponseBody<?> responseBody = response.getBody();
        User[] users = responseBody.as(User[].class);
        Assert.assertEquals(users.length, 10);
    }
}
