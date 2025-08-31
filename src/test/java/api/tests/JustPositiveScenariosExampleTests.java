package api.tests;

import api.endpoins.postEndpoints;
import api.payload.Post;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JustPositiveScenariosExampleTests {

    Faker faker;
    Post postPayload;

    @BeforeClass
    public void setupdata() {
        faker = new Faker();
        postPayload = new Post();
        postPayload.setTitle(faker.lorem().sentence(6));
        postPayload.setBody(faker.lorem().paragraph(4));      // 4 cümlelik body
        postPayload.setUserId(faker.number().numberBetween(1, 10));
    }

    @Test(priority = 1)
    public void testCreatePost() {
        Response response = postEndpoints.createPost(postPayload);
        response.then().log().all()
                .assertThat()
                .contentType(ContentType.JSON);;
        Assert.assertEquals(response.getStatusCode(), 201);

        Assert.assertEquals(response.jsonPath().getString("title"), postPayload.getTitle());
        Assert.assertEquals(response.jsonPath().getString("body"), postPayload.getBody());
        Assert.assertEquals(response.jsonPath().getInt("userId"), postPayload.getUserId());

        int createdId = response.jsonPath().getInt("id");
        System.out.println("Created Post ID: " + createdId);
        Assert.assertTrue(createdId > 0);
    }

    @Test(priority = 2)
    public void testGetSinglePost() {
        Response response = postEndpoints.getSinglePost(postPayload.getUserId());
        response.then().log().all()
                .assertThat()
                .contentType(ContentType.JSON);;
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testDeletePost() {
        Response response = postEndpoints.getSinglePost(postPayload.getUserId());
        response.then().log().all()
                .assertThat()
                .contentType(ContentType.JSON);;
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testGetAllPosts() {
        Response response = postEndpoints.getPosts();
        response.then().log().all()
                .assertThat()
                .contentType(ContentType.JSON);
                Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 1)
    public void testUpdatePost() {
        Response response = postEndpoints.updatePost(postPayload, postPayload.getUserId());
        response.then().log().all()
                .assertThat()
                .contentType(ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 200);
    }


}
