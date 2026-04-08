import java.util.*;

class Contact {
    private String name;
    private int number;
    private String surname;

    public Contact(String name, int number, String surname) {
        this.name = name;
        this.number = number;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", number=" + number + ", surname=" + surname + "]";
    }
}

public class LemdaUse {
 
    private final Map<Integer, Contact> phoneMap = new HashMap<>();

    // Add contact
    public void addContact(String name, int number, String surname) {
        phoneMap.put(number, new Contact(name, number, surname));
    }

    // Lambda search by name
    public Optional<Contact> searchByName(String name) {
        String search = name.toLowerCase().trim();

        return phoneMap.values()
                .stream()
                .filter(c -> (c.getName() + " " + c.getSurname())
                        .toLowerCase()
                        .equals(search))
                .findFirst();
    }

    //  Lambda search by number
    public Optional<Contact> searchByNumber(int number) {
        return phoneMap.values()
                .stream()
                .filter(c -> c.getNumber() == number)
                .findFirst();
    }

    // Sorting
    public void sortContacts() {
        phoneMap.values()
                .stream()
                .sorted(
                        Comparator.comparing(Contact::getSurname, String.CASE_INSENSITIVE_ORDER)
                                .thenComparing(Contact::getName, String.CASE_INSENSITIVE_ORDER)
                                .thenComparingInt(Contact::getNumber)
                )
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        LemdaUse app = new LemdaUse();
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n1. Add contact");
            System.out.println("2. Search by name");
            System.out.println("3. Search by number");
            System.out.println("4. Sort contacts");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter number: ");
                    int number = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter surname: ");
                    String surname = sc.nextLine();

                    app.addContact(name, number, surname);
                    System.out.println("Contact added successfully");
                    break;

                case 2:
                    System.out.print("Enter full name: ");
                    String input = sc.nextLine();

                    Contact result1 = app.searchByName(input).orElse(null);

                    if (result1 != null) {
                        System.out.println(result1);
                    } else {
                        System.out.println("Contact not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter number: ");
                    int num = sc.nextInt();
                    sc.nextLine();

                    Contact result2 = app.searchByNumber(num).orElse(null);

                    if (result2 != null) {
                        System.out.println(result2);
                    } else {
                        System.out.println("Contact not found!");
                    }
                    break;

                case 4:
                    app.sortContacts();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}