import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        int oddCount = 0;
        int evenCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("mydata.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split("\\s+");
                for (String number : numbers) {
                    try {
                        int value = Integer.parseInt(number);
                        if (value % 2 == 0) {
                            evenCount++;
                        } else {
                            oddCount++;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Odd numbers count: " + oddCount);
        System.out.println("Even numbers count: " + evenCount);
    }
}