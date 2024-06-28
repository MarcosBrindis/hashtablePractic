import models.Business;
import models.HashtableC;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashtableC<String, Business> hashtable = new HashtableC<>();
        String line = "";
        String splitBy = ",";
        int id = 1;

        long startTime = System.nanoTime();
        try {
            BufferedReader br = new BufferedReader(new FileReader("bussines.csv"));
            while ((line = br.readLine()) != null) { // Returns a Boolean value
                String[] businessData = line.split(splitBy); // Use comma as separator
                Business business = new Business(businessData[0], businessData[1], businessData[2], businessData[3], businessData[4]);
                hashtable.put(business.getId(), business);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println("Time taken to insert all data: " + (endTime - startTime) + " nanoseconds");

        boolean flag = true;
        while (flag) {
            System.out.println("1) Print all");
            System.out.println("2) Business ID to search");
            System.out.println("3) Add a new business");
            System.out.println("4) End program");
            String response = scanner.nextLine();
            switch (response) {
                case "1":
                    //
                    long timeTaken = hashtable.printAll(new HashtableC.Callback<Business>() {
                        @Override
                        public void call(Business business) {
                            System.out.println(business);
                        }
                    });
                    System.out.println("Time taken to print all: " + timeTaken + " nanoseconds");

                    break;
                case "2":
                    System.out.print("Enter Business ID to search: ");
                    String searchId = scanner.nextLine();
                    startTime = System.nanoTime();

                    Business searchResult = hashtable.get(searchId, new Business(searchId, "", "", "", ""));
                    endTime = System.nanoTime();

                    if (searchResult != null) {
                        System.out.println("Found: " + searchResult);
                    } else {
                        System.out.println("Business not found.");
                    }
                    System.out.println("Time taken to search: " + (endTime - startTime) + " nanoseconds");
                    break;

                case "3":
                    System.out.print("Enter Business ID: ");
                    String newId = scanner.nextLine();

                    System.out.print("Enter Business Name: ");
                    String newName = scanner.nextLine();

                    System.out.print("Enter Business Address: ");
                    String newAddress = scanner.nextLine();

                    System.out.print("Enter Business City: ");
                    String newCity = scanner.nextLine();

                    System.out.print("Enter Business State: ");
                    String newState = scanner.nextLine();
                    Business newBusiness = new Business(newId, newName, newAddress, newCity, newState);

                    startTime = System.nanoTime();
                    hashtable.put(newBusiness.getId(), newBusiness);
                    endTime = System.nanoTime();

                    System.out.println("New business added: " + newBusiness);
                    System.out.println("Time taken to add new business: " + (endTime - startTime) + " nanoseconds");
                    break;

                case "4":
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }
}
