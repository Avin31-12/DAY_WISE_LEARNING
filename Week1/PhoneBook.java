import java.util.*;
class Contact{
     private String name;
     private int number;
     private String surename;
     
    public Contact(String name, int number, String surename) {
        this.name = name;
        this.number = number;
        this.surename = surename;
    }
   
    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getSurename() {
        return surename;
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", number=" + number + ", surename=" + surename + "]";
    }
   
}
//search by name
public class PhoneBook {
   private Map<Integer, Contact>  Phonebook = new HashMap<>();
   private Map<String ,Contact> Contacts = new HashMap<>();
    public  void addContact( String name ,int number,String surename){
         Contact contact = new Contact(name, number, surename);
         String fullname = name + " " + surename;
         Contacts.put(fullname, contact);
         Phonebook.put(number,contact);
    
     }
     public Contact searchByName(String name){
        return Contacts.get(name);
     }
   public Contact searchBynumber(int number){
        return Phonebook.get(number);
     }
         public void sortBySurname() {
    List<Contact> list = new ArrayList<>(Phonebook.values());

    Collections.sort(list, new Comparator<Contact>() {
        public int compare(Contact c1, Contact c2) {
            return c1.getSurename().compareToIgnoreCase(c2.getSurename());
        }
    });

    System.out.println("\nContacts sorted by surname:");
    for (Contact c : list) {
        System.out.println(c);
    }
    }
     
     public static void main(String[] args) {
        PhoneBook phonebook = new PhoneBook();
        Scanner sc = new Scanner(System.in);
        
        int choice;

        do {
            System.out.println("\n1. Add contact");
            System.out.println("2. Search By name");
            System.out.println("3. Search by number");
            System.out.print("4. Sorted By surename: ");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Your name: ");
                    String name = sc.next();
                    System.out.print("Enter number: ");
                    int number   = sc.nextInt();
                    System.out.print("Enter your surename: ");
                    String surname = sc.next();
                    phonebook.addContact(name, number, surname);
                    System.out.println("Contact added successfully");
                    break;

                case 2:
                    System.out.print("Enter full name: ");
                    sc.nextLine();
                    String searchName = sc.nextLine();
                    Contact foundByName = phonebook.searchByName(searchName);
                        if (foundByName!=null) {
                         System.out.println(foundByName);
                        } else {
                         System.out.println("Contact not found!");
                       }
                     break;
   

                case 3:
                    System.out.print("Enter number: ");
                    int Number = sc.nextInt();

                    Contact foundByNumber = phonebook.searchBynumber(Number);

                    if (foundByNumber != null) {
                        System.out.println(foundByNumber);
                    } else {
                        System.out.println("Contact not found!");
                    }
                    break;
                case 4:
                     phonebook.sortBySurname();
                        break;
                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}
