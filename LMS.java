class Library{
    class Book {
        String title;
        String author;
        String genre;
        int bookId;
        boolean isAvailable;
        Book previousBook;
        Book nextBook;

        Book(String title, String author, String genre, int BookId, boolean isAvailable) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.bookId = BookId;
            this.isAvailable = isAvailable;
            previousBook = nextBook = null;
        }
    }

        private Book head =null;
        private Book tail = null;
        int count =0;

        public void addAtTheBeginning(String title, String author, String genre, int BookId, boolean isAvailable){
            Book newBook = new Book(title, author, genre, BookId, isAvailable);
            if(head==null){
                head = tail = newBook;
                return;
            }
            newBook.nextBook = head;
            head.previousBook = newBook;
            head = newBook;
            count+=1;
        }

        public void add(String title, String author, String genre, int BookId, boolean isAvailable){
            Book newBook = new Book(title, author, genre, BookId, isAvailable);
            if(head==null){
                head = tail = newBook;
                return;
            }
            newBook.previousBook = tail;
            tail.nextBook = newBook;
            tail = newBook;

            count++;
        }

        public void addBookAtPosition(String title, String author, String genre, int bookID, boolean isAvailable, int position) {
            if (position <= 1) {
                addAtTheBeginning(title, author, genre, bookID, isAvailable);
                return;
            }
            Book newBook = new Book(title,author,genre,bookID, isAvailable);

            Book currentBook = head;

            int start = 1;

            while(currentBook!=null && start<position-1){
                currentBook = currentBook.nextBook;
                start++;
            }

            if(currentBook==null && currentBook.nextBook== null){
                add(title,author,genre,bookID, isAvailable);
                return;
            }

            newBook.nextBook  = currentBook.nextBook;
            newBook.previousBook = currentBook;
            if(currentBook.nextBook!=null){
                  currentBook.nextBook.previousBook = newBook;
            }
            currentBook.nextBook = newBook;
            count++;
        }

        // Remove book by Book ID
        public void removeBook(int bookID) {
            if (head == null) {
                System.out.println("No books available.");
                return;
            }

            Book current = head;

            // Search for the book
            while (current != null) {
                if (current.bookId == bookID) {
                    break;
                }
                current = current.nextBook;
            }

            // If book not found
            if (current == null) {
                System.out.println("Book with ID " + bookID + " not found.");
                return;
            }

            // Case 1: Removing head node
            if (current == head) {
                head = head.nextBook;
                if (head != null) {
                    head.previousBook = null;
                } else {
                    tail = null;
                }
            }
            // Case 2: Removing tail node
            else if (current == tail) {
                tail = tail.previousBook;
                if (tail != null) {
                    tail.nextBook = null;
                } else {
                    head = null;
                }
            }
            // Case 3: Removing a middle node
            else {
                current.previousBook.nextBook = current.nextBook;
                current.nextBook.previousBook = current.previousBook;
            }

            count--;
            System.out.println("Book with ID " + bookID + " removed.");
        }

        // Search book by Title or Author
        public void searchBook(String title, String author) {
            Book current = head;
            boolean found = false;
            while (current != null) {
                if ((title != null && current.title.equalsIgnoreCase(title)) ||
                        (author != null && current.author.equalsIgnoreCase(author))) {
                    System.out.println("Book ID: " + current.bookId + " - " + current.title + " by " + current.author + " (" + current.genre + ")");
                    found = true;
                }
                current = current.nextBook;
            }
            if (!found) {
                System.out.println("No matching books found.");
            }
        }

        // Update book availability status
        public void updateAvailability(int bookID, boolean isAvailable) {
            Book current = head;
            while (current != null) {
                if (current.bookId == bookID) {
                    current.isAvailable = isAvailable;
                    System.out.println("Availability updated for Book ID " + bookID + ": " + (isAvailable ? "Available" : "Not Available"));
                    return;
                }
                current = current.nextBook;
            }
            System.out.println("Book with ID " + bookID + " not found.");
        }

        // Display books in reverse order
        public void displayBooksReverse() {
            Book current = tail;
            if (current == null) {
                System.out.println("No books available.");
                return;
            }
            System.out.println("Library Books (Reverse Order):");
            while (current != null) {
                System.out.println(current.bookId + " - " + current.title + " by " + current.author + " (" + current.genre + ") - " + (current.isAvailable ? "Available" : "Not Available"));
                current = current.previousBook;
            }
        }

        // Display books in forward order
        public void displayBooks() {
            Book current = head;
            if (current == null) {
                System.out.println("No books available.");
                return;
            }
            System.out.println("Library Books (Forward Order):");
            while (current != null) {
                System.out.println(current.bookId+ " - " + current.title + " by " + current.author + " (" + current.genre + ") - " + (current.isAvailable ? "Available" : "Not Available"));
                current = current.nextBook;
            }
        }

        public int countTotalBooks(){
            return count;
        }
    }

public class LMS {
    public static void main(String[] args) {
        Library library = new Library();
        library.add("Harry Potter and the Sorcerer’s Stone", "J.K. Rowling", "Fantasy", 101, true);
        library.addAtTheBeginning("The Hobbit", "J.R.R. Tolkien", "Fantasy", 102, true);
        library.add("To Kill a Mockingbird", "Harper Lee", "Fiction", 103, false);
        library.addBookAtPosition("1984", "George Orwell", "Dystopian", 104, true, 2);

        // Display books
        library.displayBooks();

        System.out.println();
        // Search books
        library.searchBook("The Hobbit", null);
        library.searchBook(null, "J.K. Rowling");

        // Update availability
        library.updateAvailability(103, true);
        System.out.println();

        // Remove a book
        library.removeBook(102);

        // Display books in reverse order
        library.displayBooksReverse();

        // Count total books
        System.out.println("Total Books in Library: " + library.countTotalBooks());
    }
}
