import javax.swing.plaf.ColorUIResource;

class MovieTheater{
    class Movie{
        Movie previousMovie;
        String title;
        String director;
        int yearOfRelease;
        double ratings;
        Movie nextMovie;

        Movie(String title, String director, int yearOfRelease, double ratings){
            this.title = title;
            this.director = director;
            this.yearOfRelease = yearOfRelease;
            this.ratings = ratings;
            previousMovie = nextMovie = null;
        }
    }

    private Movie head = null;
    private Movie tail = null;

    public void addAtBeginning(String title, String director, int yearOfRelease, double ratings){
        Movie newMovie = new Movie(title,director,yearOfRelease, ratings);
        if(head==null){
           tail =  head = newMovie;
            return;
        }
        newMovie.nextMovie = head;
        head.previousMovie = newMovie;
        head = newMovie;
    }

    public void addMovieAtEnd(String title, String director, int yearOfRelease, double ratings) {

        Movie newMovie = new Movie(title,director,yearOfRelease, ratings);
        if(head==null){
            head = tail = newMovie;
            return;
        }
        newMovie.previousMovie = tail;
        tail.nextMovie = newMovie;
        tail = newMovie;
    }

    public void removeMovie(String title) {
        if (head == null) {
            System.out.println("No movie exists.");
            return;
        }

        Movie currentMovie = head;

        // Search for the movie
        while (currentMovie != null) {
            if (currentMovie.title.equals(title)) {
                break;
            }
            currentMovie = currentMovie.nextMovie;
        }

        // If movie is not found
        if (currentMovie == null) {
            System.out.println("Movie '" + title + "' not found.");
            return;
        }

        // Case 1: Removing head node
        if (currentMovie == head) {
            head = head.nextMovie;
            if (head != null) {
                head.previousMovie = null;
            } else {
                tail = null; // List becomes empty
            }
        }
        // Case 2: Removing tail node
        else if (currentMovie == tail) {
            tail = tail.previousMovie;
            if (tail != null) {
                tail.nextMovie = null;
            } else {
                head = null; // List becomes empty
            }
        }
        // Case 3: Removing a middle node
        else {
            currentMovie.previousMovie.nextMovie = currentMovie.nextMovie;
            currentMovie.nextMovie.previousMovie = currentMovie.previousMovie;
        }

        System.out.println("Movie '" + title + "' removed.");
    }

    // Search for Movie
    public void searchMovie(String director, Double rating){
        Movie currentMove = head;
        boolean found = false;

        if(head==null){
            System.out.println("No movie exist");
            return;
        }

        while(currentMove!=null){
            if(currentMove.director.equals(director) && currentMove.ratings == rating){
                System.out.println(currentMove.title + " (" + currentMove.yearOfRelease + ") - Rating: " + currentMove.ratings);
                found = true;
            }
            currentMove = currentMove.nextMovie;
        }
        if (!found) {
            System.out.println("No matching movies found.");
        }
    }

    // update Rating
    public void updateRating(String title, double newRating) {
        Movie current = head;
        while (current != null) {
            if (current.title.equals(title)) {
                current.ratings = newRating;
                System.out.println("Updated rating for '" + title + "' to " + newRating);
                return;
            }
            current = current.nextMovie;
        }
        System.out.println("Movie '" + title + "' not found.");
    }

    public void displayMovies() {
        Movie current = head;
        if (current == null) {
            System.out.println("No movies available.");
            return;
        }
        System.out.println("Movies List (Forward):");
        while (current != null) {
            System.out.println(current.title + " (" + current.yearOfRelease + ") - " + current.director + " - Rating: " + current.ratings);
            current = current.nextMovie;
        }
    }

    public void displayMoviesReverse() {
        Movie current = tail;
        if (current == null) {
            System.out.println("No movies available.");
            return;
        }
        System.out.println("Movies List (Reverse):");
        while (current != null) {
            System.out.println(current.title + " (" + current.yearOfRelease + ") - " + current.director + " - Rating: " + current.ratings);
            current = current.previousMovie;
        }
    }
}
public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieTheater mms = new MovieTheater();

        mms.addMovieAtEnd("Harry Potter and the Sorcerer’s Stone", "Chris Columbus", 2001, 7.6);
        mms.addMovieAtEnd("Harry Potter and the Chamber of Secrets", "Chris Columbus", 2002, 7.4);
        mms.addMovieAtEnd("Harry Potter and the Prisoner of Azkaban", "Alfonso Cuarón", 2004, 7.9);
        mms.addMovieAtEnd("Harry Potter and the Goblet of Fire", "Mike Newell", 2005, 7.7);
        mms.addAtBeginning("Harry Potter and the Order of the Phoenix", "David Yates", 2007, 7.5);
        mms.addAtBeginning("Harry Potter and the Half-Blood Prince", "David Yates", 2009, 7.6);
        mms.addAtBeginning("Harry Potter and the Deathly Hallows – Part 1", "David Yates", 2010, 7.7);
        mms.addAtBeginning("Harry Potter and the Deathly Hallows – Part 2", "David Yates", 2011, 8.1);

        mms.displayMovies();
        System.out.println();
        mms.updateRating("Inception", 9.0);

        mms.updateRating("Harry Potter and the Deathly Hallows – Part 2", 8.0);

        mms.displayMovies();
        System.out.println();

        mms.displayMoviesReverse();
        System.out.println();
    }
}
