package api.utils;

import com.github.javafaker.Faker;

public class FakerUtils {
    private static final Faker faker = new Faker();

    public static Faker getFaker() {
        return faker;
    }
}
