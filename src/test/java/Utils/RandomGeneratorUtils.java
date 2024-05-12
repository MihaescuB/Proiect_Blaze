package Utils;

import java.util.Random;

public class RandomGeneratorUtils {
    public static String generateEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "aol.com", "icloud.com"};
        String[] names = {"john", "jane", "smith", "doe", "alex", "emily", "chris", "lisa", "mike", "sara"};
        String[] extensions = {"", "01", "02", "03", "04", "05"};

        Random rand = new Random();

        String name = names[rand.nextInt(names.length)];
        String domain = domains[rand.nextInt(domains.length)];
        String extension = extensions[rand.nextInt(extensions.length)];

        return name + rand.nextInt(100) + "@" + domain + extension;
    }

    public static String generateName() {
        String[] names = {"John", "Jane", "Smith", "Doe", "Alex", "Emily", "Chris", "Lisa", "Mike", "Sara"};

        Random rand = new Random();

        return names[rand.nextInt(names.length)];
    }

    public static String generateMessage() {
        String[] messages = {
                "Hello, how are you?", "Just checking in.", "Hope you're having a great day!",
                "Remember to stay hydrated!", "Did you finish that task yet?",
                "Wishing you all the best!", "Let's catch up soon.", "Don't forget to take breaks!",
                "Looking forward to seeing you.", "Keep up the good work!"
        };

        Random rand = new Random();

        return messages[rand.nextInt(messages.length)];
    }

    public static String generateRandomUsername(int length) {
        String[] names = {"John", "Jane", "Smith", "Doe", "Alex", "Emily", "Chris", "Lisa", "Mike", "Sara"};
        Random rand = new Random();
        StringBuilder username = new StringBuilder();
        for (int i = 0; i < length; i++) {
            username.append(names[rand.nextInt(names.length)]);
        }
        return username.toString();
    }
}
