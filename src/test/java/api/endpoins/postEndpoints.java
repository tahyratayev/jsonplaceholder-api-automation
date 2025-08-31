package api.endpoins;

import api.payload.Post;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class postEndpoints {
    public static Response createPost(Post payload) {

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);

        return response;
    }

    public static Response getSinglePost(int id) {

        Response response = given()
                .pathParam("id",id)
                .when()
                .get(Routes.get_url);

        return response;
    }

    public static Response getPosts() {

        Response response = given()
                .when()
                .get(Routes.post_url);

        return response;
    }
    public static Response updatePost(Post payload, int id) {

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id",id)
                .body(payload)
                .when()
                .put(Routes.update_url);

        return response;
    }
    public static Response deletePost(int id) {

        Response response = given()
                .pathParam("id",id)
                .when()
                .delete(Routes.delete_url);

        return response;
    }
}
