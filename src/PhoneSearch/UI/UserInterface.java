package PhoneSearch.UI;

import java.util.Scanner;
import PhoneSearch.StoredInfo.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class UserInterface {

    private Scanner reader;
    private PhoneBook phoneBook;
    private AddressBook addressBook;

    public UserInterface(Scanner reader) {
        this.reader = reader;
        this.phoneBook = new PhoneBook();
        this.addressBook = new AddressBook();
    }

    public void start() {

        menu();
        try {
            selection();
        } catch (UnsupportedOperationException e) {
            System.out.println("Selection not valid yet");
        }
    }

    private void menu() {
        System.out.println("phone search");
        System.out.println("available operations:");
        System.out.println(" 1 add a number");
        System.out.println(" 2 search for a number");
        System.out.println(" 3 search for a person by phone number");
        System.out.println(" 4 add an address");
        System.out.println(" 5 search for personal information");
        System.out.println(" 6 delete personal information");
        System.out.println(" 7 filtered listing");
        System.out.println(" x quit ");
    }

    private void selection() throws UnsupportedOperationException {
        String selection = "";

        while (!selection.equals("x")) {
            System.out.print("\ncommand: ");
            selection = reader.nextLine();
            if (selection.equals("1")) {
                addNumber();
            } else if (selection.equals("2")) {
                searchByName();
            } else if (selection.equals("3")) {
                searchByNumber();
            } else if (selection.equals("4")) {
                addAddress();
            } else if (selection.equals("5")) {
                searchForPersonalInfo(inputName());
            } else if (selection.equals("6")) {
                deletePersonalInfo();
            } else if (selection.equals("7")) {
                fileredListing();
            }
        }
    }

    private void addNumber() {
        System.out.print("whose number: ");
        String name = reader.nextLine();
        System.out.print("number: ");
        String number = reader.nextLine();
        phoneBook.addNumber(name, number);
    }

    private void searchByName() {
        System.out.print("whose number: ");
        String name = reader.nextLine();
        try {
            System.out.println(phoneBook.searchByName(name));
        } catch (IllegalArgumentException e) {
            System.out.println("name not found");
        }
    }

    private void searchByNumber() {
        System.out.print("number: ");
        String number = reader.nextLine();
        System.out.println(phoneBook.searchByNumber(number));
    }

    private void addAddress() {
        System.out.print("whose address: ");
        String name = reader.nextLine();
        System.out.print("street: ");
        String street = reader.nextLine();
        System.out.print("city: ");
        String city = reader.nextLine();
        String address = street + " " + city;
        addressBook.addAddress(name, address);
    }

    private void searchForPersonalInfo(String name) {
        if (phoneBook.isNull(name) && addressBook.isNull(name)) {
            System.out.println("not found");
        } else {

            try {
                System.out.println("address: " + addressBook.getAddress(name));
            } catch (IllegalArgumentException e) {
                System.out.println("address unknown");
            }

            try {
                System.out.println("phone numbers: \n" + phoneBook.searchByName(name));
            } catch (IllegalArgumentException e) {
                System.out.println("phone number not found");
            }
        }
    }

    private void deletePersonalInfo() {
        System.out.print("whose information: ");
        String name = reader.nextLine();
        phoneBook.removeNumbers(name);
        addressBook.removeAddress(name);
    }

    private void fileredListing() {
        System.out.print("keyword (if empty, all listed): ");
        String search = reader.nextLine();

        if (search.isEmpty()) {
            ifFIlteredIsEmpty();
        } else {
            returnFilteredSearch(search);
        }
    }

    private void ifFIlteredIsEmpty() {
        Set<String> names = new TreeSet<String>();
        names.addAll(phoneBook.getNames());
        names.addAll(addressBook.getNames());

        for (String name : names) {
            System.out.println(name);
            searchForPersonalInfo(name);
            System.out.println("");
        }
    }

    private void returnFilteredSearch(String search) {
        Set<String> names = new TreeSet<String>();
        names.addAll(addressBook.searchAddressBook(search));
        names.addAll(phoneBook.searchPhoneBook(search));

        if (names.isEmpty()) {
            System.out.println("not found");
        } else {
            for (String name : names) {
                System.out.println(name);
                searchForPersonalInfo(name);
                System.out.println("");
            }
        }
    }

    private String inputName() {
        System.out.println("whose information: ");
        return reader.nextLine();
    }
}
