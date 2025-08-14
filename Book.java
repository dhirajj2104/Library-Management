class Book {
    int id;
    String title;
    String author;
    boolean isIssued;

    Book(int id, String title, String author){
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }
    String issuedTo = null;

    void Display() {
        System.out.println("Id : "+id+", Title : "+title+", Author : "+author+", Issued : "+(isIssued ? "Yes, to "+ issuedTo : "No"));
    }
}
