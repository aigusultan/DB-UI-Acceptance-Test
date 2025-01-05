package co.wedevx.digitalbank.automation.ui.utils;

import java.text.SimpleDateFormat;
import java.util.*;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

public class MockData {

    private FakeValuesService fakeValueService = new FakeValuesService(new Locale("en-US"), new RandomService());

    public Map<String, String> generateRandomNameAndEmail() {
        String name = fakeValueService.bothify(new Faker().name().firstName());
        String email = fakeValueService.bothify(name + "####@gmail.com");

        //bothify() replaces any question marks (?) or number symbols (#) in a string with random
        // letters or numbers, respectively. It's used to generate randomized strings based on
        // a pattern you provide.

        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("email", email);

        return data;
    }

    public String generateRandomSsn() {
        String ssn = String.format("%09d", new Random().nextInt(100000000));
        // .format works in the same way as System.out.printf();
        return ssn;
    }

    public Map<String, String> generateRandomUser() {
        Faker faker = new Faker();

        List<String> validTitles = List.of("Mr.", "Mrs.", "Ms.");

        Random random = new Random();

        String title = validTitles.get(random.nextInt(validTitles.size()));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        String gender = faker.options().option("M", "F");

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dob = dateFormat.format(faker.date().birthday());

        String ssn = faker.idNumber().ssnValid();

        String email = faker.bothify(firstName + lastName + "####@gmail.com");;
        String password = generateSecurePassword(faker);
        String confirmPassword = password;
        String address = faker.address().streetAddress();
        String locality = faker.address().city();
        String region = faker.address().stateAbbr();
        String postalCode = String.format("%05d", random.nextInt(10000));//will give you a 5-digit zip code
        //String postalCode = faker.address().zipCode();
        String country = faker.address().countryCode();
        String homePhone = faker.phoneNumber().cellPhone();
        String mobilePhone = faker.phoneNumber().cellPhone();
        String workPhone = faker.phoneNumber().phoneNumber();


        Map<String, String> user = new HashMap<>();
        user.put("Title", title);
        user.put("First Name", firstName);
        user.put("Last Name", lastName);
        user.put("Gender", gender);
        user.put("Date of Birth", dob);
        user.put("SSN", ssn);
        user.put("Email", email);
        user.put("Password", password);
        user.put("Confirm Password", confirmPassword);
        user.put("Address", address);
        user.put("Locality", locality);
        user.put("Region", region);
        user.put("Postal Code", postalCode);
        user.put("Country", country);
        user.put("Home Phone", homePhone);
        user.put("Mobile Phone", mobilePhone);
        user.put("Work Phone", workPhone);

        return user;
    }

    @Override
    public String toString() {
        Map<String, String> user = generateRandomUser();
        StringBuilder sb = new StringBuilder();

        user.forEach((key, value) -> sb.append(key).append(": ").append(value).append("\n"));

        return sb.toString();

    }

    private static String generateSecurePassword(Faker faker) {

        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

        Random random = new Random();

        // Ensure at least one character from each pool
        char oneUpper = upperCase.charAt(random.nextInt(upperCase.length()));
        char oneLower = lowerCase.charAt(random.nextInt(lowerCase.length()));
        char oneNumber = numbers.charAt(random.nextInt(numbers.length()));
        char oneSymbol = symbols.charAt(random.nextInt(symbols.length()));

        // Fill the rest of the password with random characters
        String allCharacters = upperCase + lowerCase + numbers + symbols;
        StringBuilder passwordBuilder = new StringBuilder();

        // Add mandatory characters
        passwordBuilder.append(oneUpper)
                .append(oneLower)
                .append(oneNumber)
                .append(oneSymbol);

        // Add remaining characters to reach the minimum length (e.g., 8)
        int remainingLength = 8 - passwordBuilder.length();
        for (int i = 0; i < remainingLength; i++) {
            passwordBuilder.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        // Shuffle the characters to ensure randomness
        return shuffleString(passwordBuilder.toString());
    }

    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        Random random = new Random();
        for (int i = characters.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = characters[i];
            characters[i] = characters[index];
            characters[index] = temp;
        }
        return new String(characters);
    }

    public static void main(String[] args) {
        MockData mockData = new MockData();
        Map <String, String> data = mockData.generateRandomNameAndEmail();
        System.out.println(data.get("name"));
        System.out.println(data.get("email"));

        System.out.println(mockData.generateRandomSsn());

        MockData generator = new MockData();
        System.out.println(generator.toString());
    }
}
