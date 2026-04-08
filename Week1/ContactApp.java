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

public class ContactApp {

    private Map<Integer, Contact> phoneMap = new HashMap<>();
    private Map<String, Contact> nameMap = new HashMap<>();

    // ADD CONTACT
    public void addContact(String name, int number, String surname) {
        Contact contact = new Contact(name, number, surname);
        String fullName = (name + " " + surname).toLowerCase();

        nameMap.put(fullName, contact);
        phoneMap.put(number, contact);
    }

    public Contact searchByName(String name) {
        return nameMap.get(name);
    }

    public Contact searchByNumber(int number) {
        return phoneMap.get(number);
    }

    public Optional<Contact> searchByNameSafe(String name) {
        return Optional.ofNullable(nameMap.get(name.toLowerCase().trim()));
    }

    public Optional<Contact> searchByNumberSafe(int number) {
        return Optional.ofNullable(phoneMap.get(number));
    }

    public void sortContacts() {
        List<Contact> list = new ArrayList<>(phoneMap.values());

        list.sort(
                Comparator.comparing(Contact::getSurname, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contact::getName, String.CASE_INSENSITIVE_ORDER)
                        .thenComparingInt(Contact::getNumber)
        );

        System.out.println("\nSorted Contacts:");
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        ContactApp app = new ContactApp();
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n1. Add contact");
            System.out.println("2. Search by name (old)");
            System.out.println("3. Search by number (old)");
            System.out.println("4. Search by name ");
            System.out.println("5. Search by number");
            System.out.println("6. Sort contacts");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine(); 

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
                    String nameOld = sc.nextLine();

                    Contact c1 = app.searchByName(nameOld);
                    System.out.println(c1 != null ? c1 : "Contact not found!");
                    break;

                case 3:
                    System.out.print("Enter number: ");
                    int numOld = sc.nextInt();
                    sc.nextLine();

                    Contact c2 = app.searchByNumber(numOld);
                    System.out.println(c2 != null ? c2 : "Contact not found!");
                    break;

                case 4:
                    System.out.print("Enter full name: ");
                    String nameSafe = sc.nextLine();

                    app.searchByNameSafe(nameSafe)
                            .ifPresentOrElse(
                                    System.out::println,
                                    () -> System.out.println("Contact not found!")
                            );
                    break;

                case 5:
                    System.out.print("Enter number: ");
                    int numSafe = sc.nextInt();
                    sc.nextLine();

                    app.searchByNumberSafe(numSafe)
                            .ifPresentOrElse(
                                    System.out::println,
                                    () -> System.out.println("Contact not found!")
                            );
                    break;

                case 6:
                    app.sortContacts();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 7);

        sc.close();
    }
}