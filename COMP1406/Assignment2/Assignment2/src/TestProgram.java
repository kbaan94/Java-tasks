public class TestProgram { //Taken from Assignment2 Instructions to add "missing" code for program to run
    public static void main(String[] args) { //main function
        Movie m = new Movie("Despicable Me 3");
        System.out.println(m.title);
        System.out.println(m.earnings);

        Theatre theatre = new Theatre(3);
        System.out.println(theatre.capacity);
        System.out.println(theatre.seatsSold);
        theatre.nowPlaying = m;

        Patron mary = new Patron(15);
        System.out.println(mary.patronAge);
        System.out.println(mary.yourTicket);
        mary.yourTicket = new Ticket(theatre);
        System.out.println(mary.yourTicket.ticForTheatre.nowPlaying.title);
    }
}