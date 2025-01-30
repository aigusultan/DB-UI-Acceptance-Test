package API;

import com.github.javafaker.Faker;

import java.util.*;

public class FakerClass {
    public Map<String, String> generateRandomUser() {
        Faker faker = new Faker();

        String name = faker.name().fullName();
        String email = faker.bothify(name.replaceAll("\\s+", "") + "@gmail.com");
        String gender = faker.options().option("Male", "Female");

        List<String> statuses = Arrays.asList("active", "inactive");
        String status = statuses.get(faker.random().nextInt(statuses.size()));

        Map<String, String> user = new LinkedHashMap<>();
        user.put("name", name);
        user.put("email", email);
        user.put("gender", gender);
        user.put("status", status);

        return user;
    }
}
