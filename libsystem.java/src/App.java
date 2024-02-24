import java.util.ArrayList;
import java.util.List;

interface LibraryItem {
    void borrowItem();
    void returnItem();
    boolean isBorrowed();
}

class Book implements LibraryItem {
    private String name;
    String title;
    String author;
    int publicationYear;
    private boolean borrowed;

    public Book(String name, String title, String author, int publicationYear) {
        this.name = name;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!isBorrowed()) {
            borrowed = true;
            System.out.println("Book '" + title + "' by " + author + "  borrowed by " + name + ".");
        } else {
            System.out.println("Book '" + title + "' is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (isBorrowed()) {
            borrowed = false;
            System.out.println("Book '" + title + "' by " + author + " has been returned by " + name + ".");
        } else {
            System.out.println("Book '" + title + "' is not borrowed.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }
}

class DVD implements LibraryItem {
    private String name;
    String title;
    String director;
    int runningTime;
    private boolean borrowed;

    public DVD(String name, String title, String director, int runningTime) {
        this.name = name;
        this.title = title;
        this.director = director;
        this.borrowed = false;
    }

    @Override
    public void borrowItem() {
        if (!isBorrowed()) {
            borrowed = true;
            System.out.println("DVD '" + title + "' directed by " + director + " borrowed by " + name + ".");
        } else {
            System.out.println("DVD '" + title + "' is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (isBorrowed()) {
            borrowed = false;
            System.out.println("DVD '" + title + "' directed by " + director + " has been returned by " + name + ".");
        } else {
            System.out.println("DVD '" + title + "' is not borrowed.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return borrowed;
    }
}

abstract class LibraryUser {
    List<LibraryItem> borrowedItems;

    public LibraryUser() {
        borrowedItems = new ArrayList<>();
    }

    abstract void printItemsBorrowed();

    void borrowItem(LibraryItem item) {
        if (!item.isBorrowed()) {
            item.borrowItem();
            borrowedItems.add(item);
        } else {
            System.out.println("Item is already borrowed.");
        }
    }

    void returnItem(LibraryItem item) {
        if (item.isBorrowed()) {
            item.returnItem();
            borrowedItems.remove(item);
        } else {
            System.out.println("Item is not borrowed.");
        }
    }
}

class Student extends LibraryUser {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    void printItemsBorrowed() {
        System.out.println("Student: " + name);
        System.out.println("Borrowed items:");
        for (LibraryItem item : borrowedItems) {
            if (item instanceof Book) {
                Book book = (Book) item;
                System.out.println("Book: " + book.title + " by " + book.author);
            } else if (item instanceof DVD) {
                DVD dvd = (DVD) item;
                System.out.println("DVD: " + dvd.title + " directed by " + dvd.director);
            }
        }
    }
}

class Teacher extends LibraryUser {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    void printItemsBorrowed() {
        System.out.println("Teacher: " + name);
        System.out.println("Borrowed items:");
        for (LibraryItem item : borrowedItems) {
            if (item instanceof Book) {
                Book book = (Book) item;
                System.out.println("Book: " + book.title + " by " + book.author);
            } else if (item instanceof DVD) {
                DVD dvd = (DVD) item;
                System.out.println("DVD: " + dvd.title + " directed by " + dvd.director);
            }
        }
    }
}

public class App {
    public static void main(String[] args) {
        Book book1 = new Book("Brett", "Java Programming", "John Smith", 2020);
        DVD dvd1 = new DVD("Rafi", "The Matrix", "Wachowski Brothers", 136);

        Student student = new Student("Brett");
        Teacher teacher = new Teacher("Rafi");

        student.borrowItem(book1);
        teacher.borrowItem(dvd1);

        System.out.println(); 

        student.printItemsBorrowed();
        teacher.printItemsBorrowed();

        System.out.println(); 

        student.returnItem(book1);
        teacher.returnItem(dvd1);
    }
}
