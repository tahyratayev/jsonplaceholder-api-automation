package api.tests;

import api.endpoins.Routes;
import api.endpoins.postEndpoints;
import api.payload.Post;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreatePostTest extends BaseTest {

    @Test(priority = 1)
    public void testCreatePost() {
        try {
            Response response = postEndpoints.createPost(postPayload);
            response.then().log().all()
                    .assertThat()
                    .contentType(ContentType.JSON);
            ;
            Assert.assertEquals(response.getStatusCode(), 201);

            Assert.assertEquals(response.jsonPath().getString("title"), postPayload.getTitle());
            Assert.assertEquals(response.jsonPath().getString("body"), postPayload.getBody());
            Assert.assertEquals(response.jsonPath().getInt("userId"), postPayload.getUserId());

            int createdId = response.jsonPath().getInt("id");
            System.out.println("Created Post ID: " + createdId);
            Assert.assertTrue(createdId > 0);
        }catch (Exception e) {
            System.err.println("Exception during testCreatePost: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testCreatePostMissingTitle() {
        Post invalidPost = new Post();
        invalidPost.setUserId(1);
        invalidPost.setBody("Body without title");

        Response response = postEndpoints.createPost(invalidPost);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertNull(response.jsonPath().getString("title"));
    }

    @Test(priority = 3)
    public void testCreatePostEmptyFields() {
        Post invalidPost = new Post();
        invalidPost.setUserId(1);
        invalidPost.setTitle("");
        invalidPost.setBody("");

        Response response = postEndpoints.createPost(invalidPost);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("title"), "");
        Assert.assertEquals(response.jsonPath().getString("body"), "");
    }

    @Test(priority = 4)
    public void testCreatePostWrongContentType() {
        String jsonBody = "{ \"title\": \"Invalid\", \"body\": \"Bad header\", \"userId\": 1 }";

        Response response = given()
                .contentType("text/plain") // JSON değil
                .body(jsonBody)
                .when()
                .post(Routes.post_url);

        response.then().log().all();

        Assert.assertTrue(
                response.getStatusCode() == 201
                        || response.getStatusCode() == 400
                        || response.getStatusCode() == 415
        );
    }


    @Test(priority = 5)
    public void testCreatePostMalformedJson() {
        String brokenJson = "{ \"title\": \"oops\" "; // kapanmıyor

        Response response = given()
                .contentType(ContentType.JSON)
                .body(brokenJson)
                .when()
                .post(Routes.post_url);

        response.then().log().all();

        Assert.assertTrue(
                response.getStatusCode() == 400
                        || response.getStatusCode() == 500
                        || response.getStatusCode() == 201
        );
    }

}
