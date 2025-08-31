package api.tests;

import api.endpoins.postEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetSinglePostTest extends  BaseTest{

    @Test(priority = 1)
    public void testGetSinglePostValidId() {
        try {
            int existingId = 1;

            Response response = postEndpoints.getSinglePost(existingId);
            response.then().log().all()
                    .assertThat()
                    .contentType(ContentType.JSON);

            Assert.assertEquals(response.getStatusCode(), 200);
            Assert.assertEquals(response.jsonPath().getInt("id"), existingId);
            Assert.assertNotNull(response.jsonPath().getString("title"));
            Assert.assertNotNull(response.jsonPath().getString("body"));
        } catch (Exception e) {
            System.err.println(" Exception during testGetSinglePostValidId: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testGetSinglePostInvalidId() {
        int invalidId = 0;

        Response response = postEndpoints.getSinglePost(invalidId);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(priority = 3)
    public void testGetSinglePostNonExistingId() {
        int nonExistingId = 9999;

        Response response = postEndpoints.getSinglePost(nonExistingId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
