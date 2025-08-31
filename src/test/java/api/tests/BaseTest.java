package api.tests;

import api.payload.Post;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static Faker faker;
    protected Post postPayload;

    @BeforeClass
    public void setupBaseData() {
        faker = new Faker();

        postPayload = new Post();
        postPayload.setTitle(faker.lorem().sentence(6));
        postPayload.setBody(faker.lorem().paragraph(4));
        postPayload.setUserId(faker.number().numberBetween(1, 10));
    }
}
