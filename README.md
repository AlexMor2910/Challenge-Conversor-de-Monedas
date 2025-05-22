# Currency Converter

This is a simple Java console application that allows you to convert amounts between different currencies using the **ExchangeRate-API**.

---

## Features

* Convert **USD to ARS** (Argentine Peso) and vice-versa.
* Convert **USD to BRL** (Brazilian Real) and vice-versa.
* Convert **USD to COP** (Colombian Peso) and vice-versa.
* Convert any custom currency pair by providing their 3-letter currency codes (e.g., EUR, JPY, GBP).
* Handles invalid input and API errors gracefully.

---

## Getting Started

### Prerequisites

* **Java Development Kit (JDK) 11 or higher** installed on your system.
* An **API Key from ExchangeRate-API.com**. You can get a free API key by signing up on their website.

### Installation

1.  **Clone or download** this repository to your local machine.
2.  **Open the project** in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse, VS Code).
3.  **Add the Gson library** to your project's dependencies. If you're using Maven or Gradle, you can add the following to your `pom.xml` (Maven) or `build.gradle` (Gradle) file:

    **Maven:**
    ```xml
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
    </dependency>
    ```

    **Gradle:**
    ```gradle
    implementation 'com.google.code.gson:gson:2.10.1'
    ```
    If you're not using a build tool, you'll need to download the Gson JAR file and add it to your project's classpath manually.

4.  **Replace `"YOUR_API_KEY_HERE"`** in the `CurrencyConverter.java` file with your actual API key from ExchangeRate-API.com:

    ```java
    private static final String API_KEY = "YOUR_ACTUAL_API_KEY_HERE";
    ```

### Running the Application

1.  **Compile the `CurrencyConverter.java`** file.
2.  **Run the `main` method** in the `CurrencyConverter` class.

    You can typically do this directly from your IDE. If running from the command line, navigate to your project's `src` directory (or wherever your `.java` file is located) and execute:

    ```bash
    javac CurrencyConverter.java
    java CurrencyConverter
    ```

---

## How to Use

Once you run the application, you'll see a menu of options:

Welcome to the Currency Converter!

--- Currency Conversion Menu ---

1. Convert USD to ARS (Argentine Peso)

2. Convert ARS to USD (US Dollar)

3. Convert USD to BRL (Brazilian Real)

4. Convert BRL to USD (US Dollar)

5. Convert USD to COP (Colombian Peso)

6. Convert COP to USD (US Dollar)

7. Convert another currency pair (Requires knowing currency codes like 'USD', 'EUR', 'JPY')

8. Exit 

Enter your option:

1.  **Choose an option** by entering the corresponding number.
2.  For conversion options, you'll be prompted to **enter the amount** you wish to convert.
3.  For option `7`, you'll first be asked to **enter the base currency code** (e.g., `USD`) and then the **target currency code** (e.g., `EUR`).
4.  The application will then display the converted amount.
5.  You can continue performing conversions until you choose option `8` to exit.

---

## API Key

This application relies on the ExchangeRate-API.com for exchange rate data. It's crucial to obtain your own **free API key** and insert it into the `API_KEY` variable in the `CurrencyConverter.java` file. Without a valid API key, the application will not be able to fetch exchange rates.
