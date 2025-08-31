package api.tests;

import api.endpoins.postEndpoints;
import api.payload.Post;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdatePostTest extends BaseTest {

    @Test(priority = 1)
    public void testUpdatePost() {
        try {
            int existingId = 1;
            Post updated = new Post();
            updated.setUserId(postPayload.getUserId());
            updated.setTitle("Updated: " + faker.lorem().sentence(5));
            updated.setBody("Updated: " + faker.lorem().paragraph(2));

            Response response = postEndpoints.updatePost(updated, existingId);

            response.then().log().all()
                    .assertThat()
                    .contentType(ContentType.JSON);

            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals(response.jsonPath().getInt("id"), existingId);
            Assert.assertEquals(response.jsonPath().getInt("userId"), updated.getUserId());
            Assert.assertEquals(response.jsonPath().getString("title"), updated.getTitle());
            Assert.assertEquals(response.jsonPath().getString("body"), updated.getBody());
        }catch (Exception e) {
            System.err.println(" Exception during testUpdatePost: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testUpdatePostInvalidId() {
        int invalidId = 0;
        Post updated = new Post();
        updated.setUserId(1);
        updated.setTitle("Update with invalid id");
        updated.setBody("Body");

        Response response = postEndpoints.updatePost(updated, invalidId);
        response.then().log().all();


        Assert.assertTrue(
                response.getStatusCode() == 200
                        || response.getStatusCode() == 400
                        || response.getStatusCode() == 404
                        || response.getStatusCode() == 500
        );

        String contentType = response.getContentType();
        Assert.assertTrue(contentType.contains("json") || contentType.contains("html"));
    }

    @Test(priority = 3)
    public void testUpdatePostEmptyFields() {
        int id = 1;
        Post updated = new Post();
        updated.setUserId(1);
        updated.setTitle("");
        updated.setBody("");

        Response response = postEndpoints.updatePost(updated, id);
        response.then().log().all()
                .assertThat()
                .contentType(ContentType.JSON);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "");
        Assert.assertEquals(response.jsonPath().getString("body"), "");
    }
}
