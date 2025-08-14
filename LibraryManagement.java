import java.util.Scanner;

public class LibraryManagement {
    public static void main(String[]args) {
        Library library = new Library();
        library.loadBooksFromFile("books.txt");
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.print("Enter Username : ");
        String username = sc.nextLine();

        System.out.print("Enter Password : ");
        String password = sc.nextLine();

        boolean isAdmin = false;
        if(username.equals("admin") && password.equals("123")) {
            isAdmin = true;
            System.out.println("Welcome Admin");
        }else if(username.equals("dhiraj") && password.equals("123")) {
            System.out.println("Welcome User1");
        }else {
            System.out.println("Invalid username & password");
            return ;
        }

        do {
            System.out.println("Library management menu");
            if(isAdmin) {
                System.out.println("1. Add Book");
                System.out.println("2. Delete Book");
                System.out.println("3. View Book");
                System.out.println("4. Issued Book");
                System.out.println("5. Return Book");
                System.out.println("6. Exit Menu");
                System.out.print("Enter your choice(1-7):");
            }else {
                System.out.println("1. View Book");
                System.out.println("2. Issued Book");
                System.out.println("3. Return Book");
                System.out.println("4. Exit Menu");
                System.out.print("Enter your choice(1-4):");
            }

            choice = sc.nextInt();

            System.out.println("");
            if(isAdmin) {
                switch(choice) {
                    case 1 -> {
                        library.addBook();
                        library.saveBooksToFile("books.txt");
                    }
                    case 2 -> {
                        library.deleteBook();
                        library.saveBooksToFile("books.txt");
                    }
                    case 3 -> library.viewBook();
                    case 4 -> {
                        library.issuedBook("admin");
                        library.saveBooksToFile("books.txt");
                    }
                    case 5 -> {
                        library.returnBook();
                        library.saveBooksToFile("books.txt");
                    }
                    case 6 -> System.out.println("Thank you for exiting");
                    default -> System.out.println("Invalid choice.");
                }
            }else {
                switch(choice) {
                    case 1 -> library.viewBook();
                    case 2 -> {
                        library.issuedBook(username);
                        library.saveBooksToFile("books.txt");
                    }
                    case 3 -> {
                        library.returnBook();
                        library.saveBooksToFile("books.txt");
                    }
                    case 4 -> {System.out.println("Exiting... Thank you!");}
                    default -> System.out.println("Invalid choice");
                }
            }

        }while((isAdmin && choice != 6) || (!isAdmin && choice != 4));
    }
}
