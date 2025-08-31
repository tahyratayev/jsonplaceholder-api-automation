package api.tests;

import api.endpoins.Routes;
import api.endpoins.postEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetAllPostsTest extends BaseTest {

    @Test(priority = 1)
    public void testGetAllPosts() {
        try {
            Response response = postEndpoints.getPosts();
            response.then().log().all()
                    .assertThat()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("size()", greaterThan(0));
            Assert.assertNotNull(response.jsonPath().getString("[0].title"));
            Assert.assertNotNull(response.jsonPath().getString("[0].body"));
            Assert.assertNotNull(response.jsonPath().getInt("[0].userId"));
        }catch (Exception e) {
            System.err.println("Exception during testGetAllPosts: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testGetAllPostsWrongEndpoint() {
        Response response = given()
                .when()
                .get("https://jsonplaceholder.typicode.com/postsss");

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(priority = 3)
    public void testGetAllPostsWrongAcceptHeader() {
        Response response = given()
                .accept("text/plain")
                .when()
                .get(Routes.post_url);

        response.then().log().all();

        Assert.assertTrue(
                response.getStatusCode() == 200 || response.getStatusCode() == 406
        );
    }
}
