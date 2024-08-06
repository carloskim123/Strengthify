import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // Define regex patterns for validation
        String lowercasePattern = ".*[a-z].*";
        String uppercasePattern = ".*[A-Z].*";
        String numberPattern = ".*\\d.*";
        String specialCharPattern = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>?/].*";

        // Create Pattern objects
        Pattern lowerPattern = Pattern.compile(lowercasePattern);
        Pattern upperPattern = Pattern.compile(uppercasePattern);
        Pattern numPattern = Pattern.compile(numberPattern);
        Pattern specialPattern = Pattern.compile(specialCharPattern);

        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        String password = "";
        boolean isValid = false;

        while (!isValid) {
            // Prompt user for password input
            System.out.print("Enter your desired password: ");
            password = scanner.nextLine();

            // Validate the password
            boolean containsLowercase = lowerPattern.matcher(password).matches();
            boolean containsUppercase = upperPattern.matcher(password).matches();
            boolean containsNumbers = numPattern.matcher(password).matches();
            boolean containsSpecialChar = specialPattern.matcher(password).matches();
            boolean containsAtLeastEightChars = password.length() >= 8;

            // Check if the password meets all criteria
            isValid = containsLowercase && containsUppercase && containsNumbers && containsSpecialChar && containsAtLeastEightChars;

            if (!isValid) {
                System.out.println("Password does not meet the criteria. Please try again.");
                System.out.println("Requirements:");
                System.out.println("1. Minimum length of 8 characters.");
                System.out.println("2. At least one lowercase letter.");
                System.out.println("3. At least one uppercase letter.");
                System.out.println("4. At least one numeric digit.");
                System.out.println("5. At least one special character.");
            }
        }

        // Calculate number of criteria met
        int criteriaMet = 0;
        if (lowerPattern.matcher(password).matches()) criteriaMet++;
        if (upperPattern.matcher(password).matches()) criteriaMet++;
        if (numPattern.matcher(password).matches()) criteriaMet++;
        if (specialPattern.matcher(password).matches()) criteriaMet++;
        if (password.length() >= 8) criteriaMet++;

        // Determine password strength
        String strength;
        switch (criteriaMet) {
            case 5:
                strength = "Very Strong";
                break;
            case 4:
                strength = "Strong";
                break;
            case 3:
                strength = "Medium";
                break;
            default:
                strength = "Weak";
                break;
        }

        // Output results
        System.out.println("Password validation results:");
        System.out.println("Contains at least 8 characters: " + (password.length() >= 8));
        System.out.println("Contains at least one lowercase letter: " + lowerPattern.matcher(password).matches());
        System.out.println("Contains at least one uppercase letter: " + upperPattern.matcher(password).matches());
        System.out.println("Contains at least one numeric digit: " + numPattern.matcher(password).matches());
        System.out.println("Contains at least one special character: " + specialPattern.matcher(password).matches());
        System.out.println("Password strength: " + strength);

        scanner.close();
    }
}
