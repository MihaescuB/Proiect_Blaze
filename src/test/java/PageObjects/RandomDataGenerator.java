package PageObjects;

import Tests.BaseTest;

import java.util.Random;

public class RandomDataGenerator extends BaseTest {

    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "aol.com", "icloud.com"};
        String[] names = {"john", "jane", "smith", "doe", "alex", "emily", "chris", "lisa", "mike", "sara"};
        String[] extensions = {"", "01", "02", "03", "04", "05"};

        Random rand = new Random();
        String name = getRandomElement(names, rand);
        String domain = getRandomElement(domains, rand);
        String extension = getRandomElement(extensions, rand);

        return name + rand.nextInt(100) + "@" + domain + extension;
    }

    // Method to generate a random name
    public static String generateRandomName() {
        String[] names = {"John", "Jane", "Smith", "Doe", "Alex", "Emily", "Chris", "Lisa", "Mike", "Sara"};
        Random rand = new Random();
        return getRandomElement(names, rand);
    }

    // Method to generate a random message
    public static String generateRandomMessage() {
        String[] messages = {
                "Hello, how are you?",
                "Just checking in.",
                "Hope you're having a great day!",
                "Remember to stay hydrated!",
                "Did you finish that task yet?",
                "Wishing you all the best!",
                "Let's catch up soon.",
                "Don't forget to take breaks!",
                "Looking forward to seeing you.",
                "Keep up the good work!"
        };
        Random rand = new Random();
        return getRandomElement(messages, rand);
    }

    // Method to get a random element from an array
    private static String getRandomElement(String[] array, Random rand) {
        return array[rand.nextInt(array.length)];
    }
}
