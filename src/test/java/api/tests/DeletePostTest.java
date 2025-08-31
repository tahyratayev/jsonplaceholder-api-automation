package api.tests;

import api.endpoins.postEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeletePostTest extends BaseTest {

    @Test(priority = 1)
    public void testDeletePostValidId() {
        try {
            int existingId = 1;

            Response response = postEndpoints.deletePost(existingId);

            response.then().log().all()
                    .assertThat()
                    .contentType(ContentType.JSON);
             int statusCode= response.getStatusCode();
            Assert.assertTrue( statusCode== 200 || statusCode == 204,
                    " Unexpected status code: " + statusCode + " | Body: " + response.asString());
        }catch (Exception e) {
            System.err.println(" Exception during testDeletePostValidId: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testDeletePostInvalidId() {
        int invalidId = 0;

        Response response = postEndpoints.deletePost(invalidId);

        response.then().log().all();

        Assert.assertTrue(
                response.getStatusCode() == 200 ||
                        response.getStatusCode() == 404
        );
    }

    @Test(priority = 3)
    public void testDeletePostNonExistingId() {
        int nonExistingId = 9999;

        Response response = postEndpoints.deletePost(nonExistingId);

        response.then().log().all();

        Assert.assertTrue(
                response.getStatusCode() == 200 ||
                        response.getStatusCode() == 404
        );
    }
}

