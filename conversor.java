import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CurrencyConverter {

    // IMPORTANT: Replace with your actual API Key from ExchangeRate-API.com
    private static final String API_KEY = "YOUR_API_KEY_HERE";
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson();
        int option;

        System.out.println("Welcome to the Currency Converter!");

        do {
            System.out.println("\n--- Currency Conversion Menu ---");
            System.out.println("1. Convert USD to ARS (Argentine Peso)");
            System.out.println("2. Convert ARS to USD (US Dollar)");
            System.out.println("3. Convert USD to BRL (Brazilian Real)");
            System.out.println("4. Convert BRL to USD (US Dollar)");
            System.out.println("5. Convert USD to COP (Colombian Peso)");
            System.out.println("6. Convert COP to USD (US Dollar)");
            System.out.println("7. Convert another currency pair (Requires knowing currency codes like 'USD', 'EUR', 'JPY')");
            System.out.println("8. Exit");
            System.out.print("Enter your option: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (option) {
                    case 1:
                        performConversion(scanner, gson, "USD", "ARS");
                        break;
                    case 2:
                        performConversion(scanner, gson, "ARS", "USD");
                        break;
                    case 3:
                        performConversion(scanner, gson, "USD", "BRL");
                        break;
                    case 4:
                        performConversion(scanner, gson, "BRL", "USD");
                        break;
                    case 5:
                        performConversion(scanner, gson, "USD", "COP");
                        break;
                    case 6:
                        performConversion(scanner, gson, "COP", "USD");
                        break;
                    case 7:
                        System.out.print("Enter the base currency code (e.g., USD): ");
                        String baseCurrency = scanner.nextLine().toUpperCase();
                        System.out.print("Enter the target currency code (e.g., EUR): ");
                        String targetCurrency = scanner.nextLine().toUpperCase();
                        performConversion(scanner, gson, baseCurrency, targetCurrency);
                        break;
                    case 8:
                        System.out.println("Thank you for using the Currency Converter. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input from the scanner
                option = 0; // Set option to 0 to continue the loop
            }

        } while (option != 8);

        scanner.close();
    }

    private static void performConversion(Scanner scanner, Gson gson, String baseCurrency, String targetCurrency) {
        System.out.print("Enter the amount to convert from " + baseCurrency + " to " + targetCurrency + ": ");
        try {
            double amountToConvert = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            double exchangeRate = getExchangeRate(gson, baseCurrency, targetCurrency);

            if (exchangeRate > 0) {
                double convertedAmount = amountToConvert * exchangeRate;
                System.out.printf("%.2f %s is equal to %.2f %s%n", amountToConvert, baseCurrency, convertedAmount, targetCurrency);
            } else {
                System.out.println("Could not retrieve exchange rate. Please check currency codes and your API key.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private static double getExchangeRate(Gson gson, String baseCurrency, String targetCurrency) {
        HttpClient client = HttpClient.newHttpClient();
        String url = API_BASE_URL + API_KEY + "/pair/" + baseCurrency + "/" + targetCurrency;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
                String result = jsonResponse.get("result").getAsString();

                if ("success".equals(result)) {
                    return jsonResponse.get("conversion_rate").getAsDouble();
                } else {
                    String errorType = jsonResponse.has("error-type") ? jsonResponse.get("error-type").getAsString() : "unknown";
                    System.err.println("API Error: " + errorType);
                    return -1.0; // Indicate error
                }
            } else {
                System.err.println("HTTP Error: " + response.statusCode() + " - " + response.body());
                return -1.0; // Indicate error
            }
        } catch (Exception e) {
            System.err.println("Error fetching exchange rate: " + e.getMessage());
            return -1.0; // Indicate error
        }
    }
}