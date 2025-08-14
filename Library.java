import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    ArrayList<Book> books = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    void addBook() {
        System.out.print("Enter the book ID : ");
        int Id = sc.nextInt();
        sc.nextLine();
        for(Book b:books) {
            if(b.id == Id) {
                System.out.println("This ID registered already");
                return;
            }
        }
        System.out.print("Enter book title : ");
        String title = sc.nextLine();
        System.out.print("Enter author name : ");
        String author = sc.nextLine();

        books.add(new Book(Id, title, author));
    }

    void viewBook() {
        if(books.isEmpty()) {
            System.out.println("No books available.");
        }else {
            System.out.println("List of books : ");
            for(Book b : books) {
                b.Display();
            }
        }
    }

    void deleteBook() {
        System.out.print("Enter the book ID : ");
        int id = sc.nextInt();

        boolean removed = books.removeIf(b -> b.id == id);

        if(removed) {
            saveBooksToFile("books.txt");
            System.out.println("Book with ID " + id + " deleted successfully");
        }else {
            System.out.println("Book not found");
        }
    }
    void issuedBook(String username) {
        System.out.print("Enter Book ID for Issue : ");
        int id = sc.nextInt();
        for(Book b:books) {
            if(b.id == id) {
                if(!b.isIssued) {
                    b.isIssued = true;
                    b.issuedTo = username;
                    System.out.println("Book issued Successfully.");
                }else {
                    System.out.println("Book already issued to " + b.issuedTo);
                }
                return;
            }
        }
        System.out.print("Book not found.");
    }

    void returnBook() {
        System.out.print("Enter Book ID for Return : ");
        int id = sc.nextInt();
        for(Book b:books) {
            if(b.id == id) {
                if(b.isIssued) {
                    b.isIssued = false;
                    b.issuedTo = null;
                    System.out.println("Book return Successfully.");
                }else {
                    System.out.println("Book was not issued.");
                }
                return;
            }
        }
        System.out.print("Book not found.");
    }

    void saveBooksToFile(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            for(Book b:books) {
                fw.write(b.id+","+b.title+","+b.author+","+b.isIssued+","+(b.issuedTo == null ? "null" : b.issuedTo)+"\n");
            }
            fw.close();
            System.out.println("Book save successfully");
        }catch(Exception e) {
            System.out.println("Error of saving book : "+e.getMessage());
        }
    }

    void loadBooksFromFile(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while((line = br.readLine())!= null) {
                String[] parts = line.split(",");
                if(parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    boolean isIssued = Boolean.parseBoolean(parts[3]);
                    String issuedTo = parts[4].equals(null) ? "null" : parts[4];

                    Book b = new Book(id, title, author);
                    b.isIssued = isIssued;
                    b.issuedTo = issuedTo;
                    books.add(b);
                }
            }
            br.close();
            System.out.println("Books load from "+filename);
        }catch(FileNotFoundException e) {
            System.out.println("File not found");
        }catch(Exception e) {
            System.out.println("Error loading books "+e.getMessage());
        }
    }

}
