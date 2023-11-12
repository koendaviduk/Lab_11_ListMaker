import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {

    private static ArrayList<String> itemList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            String choice = SafeInput.getRegExString(scanner, "Enter your choice", "[AaDdPpQq]");
            executeChoice(choice);
            if (choice.equalsIgnoreCase("Q") && !confirmQuit()) {
                continue;
            }
            if (choice.equalsIgnoreCase("Q")) {
                break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("A – Add an item to the list");
        System.out.println("D – Delete an item from the list");
        System.out.println("P – Print the list");
        System.out.println("Q – Quit");
    }

    private static void executeChoice(String choice) {
        switch (choice.toUpperCase()) {
            case "A":
                addItemToList();
                break;
            case "D":
                deleteItemFromList();
                break;
            case "P":
                printList();
                break;
            case "Q":
                // Quit option handled in the main loop
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addItemToList() {
        String newItem = SafeInput.getNonZeroLenString(scanner, "Enter the item to add");
        itemList.add(newItem);
        System.out.println("Item added successfully!");
    }

    private static void deleteItemFromList() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        System.out.println("Current List:");
        displayNumberedItems(itemList);

        int itemNumber = SafeInput.getRangedInt(scanner, "Enter the item number to delete", 1, itemList.size());

        itemList.remove(itemNumber - 1);
        System.out.println("Item deleted successfully!");
    }

    private static void printList() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("Current List:");
            displayNumberedItems(itemList);
        }
    }

    private static void displayNumberedItems(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    private static boolean confirmQuit() {
        return SafeInput.getYNConfirm(scanner, "Are you sure you want to quit?");
    }
}
