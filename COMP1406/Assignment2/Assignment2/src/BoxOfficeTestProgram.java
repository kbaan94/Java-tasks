public class BoxOfficeTestProgram {
    public static void main(String args[]) {
        // Create a box office with two theatres, each with a capacity of 5 seats
        BoxOffice box = new BoxOffice(5, 5);
        // Open up a couple of new movies at the box office
        System.out.println("Theatre A opens movie: Justice League");
        box.openMovie("Justice League", box.theatreA);
        System.out.println("Theatre B opens movie: Geostorm");
        box.openMovie("Geostorm", box.theatreB);
        // Now create some patrons
        Patron p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12;
        p1 = new Patron(15);
        p2 = new Patron(26);
        p3 = new Patron(7);
        p4 = new Patron(72);
        p5 = new Patron(65);
        p6 = new Patron(11);
        p7 = new Patron(19);
        p8 = new Patron(17);
        p9 = new Patron(12);
        p10 = new Patron(14);
        p11 = new Patron(13);
        p12 = new Patron(16);
// ... continued on the next page ... //
        // Sell some tickets to the patrons
        System.out.println("Patron 1 buys ticket for Justice League");
        box.sellTicket(p1, "Justice League");
        System.out.println("Patrons 2,3 and 4 buy tickets for Geostorm");
        box.sellTicket(p2, "Geostorm");
        box.sellTicket(p3, "Geostorm");
        box.sellTicket(p4, "Geostorm");
        System.out.println("Geostorm seats remaining: " +
                (box.theatreB.capacity - box.theatreB.seatsSold));
        System.out.println("\nPatron 5 buys ticket for The Giggling Giraffe");
        box.sellTicket(p5, "The Giggling Giraffe");
        System.out.println("\nPatrons 6 and 9 buy tickets to see Geostorm");
        box.sellTicket(p6, "Geostorm");
        box.sellTicket(p9, "Geostorm");
        System.out.println("Patrons 7,8,10 buy tickets for Justice League");
        box.sellTicket(p7, "Justice League");
        box.sellTicket(p8, "Justice League");
        box.sellTicket(p10, "Justice League");
        System.out.println("Patron 11 tries to buy tickets for Geostorm");
        box.sellTicket(p11, "Geostorm");
        System.out.println("Geostorm sold out ? " + box.theatreB.isFull());
        System.out.println("\nPatrons 2, 6 and 10 return tickets");
        box.returnTicket(p2);
        box.returnTicket(p6);
        box.returnTicket(p10);
        System.out.println("\nPatron 10 tries to return a ticket again");
        box.returnTicket(p10);
        System.out.println("Geostorm sold out ? " + box.theatreB.isFull());
        System.out.println("Geostorm seats remaining: " +
                (box.theatreB.capacity - box.theatreB.seatsSold));
        System.out.println("\nPatrons 11 and 12 buy tickets for Geostorm");
        box.sellTicket(p11, "Geostorm");
        box.sellTicket(p12, "Geostorm");
        System.out.println("\nBest movie title: " + box.bestMovie().title);
        System.out.println("Best movie earnings: " + box.bestMovie().earnings);
        System.out.println("\nTheatre A opens movie: Despicable Me 3");
        box.openMovie("Despicable Me 3", box.theatreA);
        System.out.println("Patrons 1,2,7,8,12 buy tickets for Despicable Me 3");
        box.sellTicket(p1, "Despicable Me 3");
        box.sellTicket(p2, "Despicable Me 3");
        box.sellTicket(p7, "Despicable Me 3");
        box.sellTicket(p8, "Despicable Me 3");
        box.sellTicket(p12, "Despicable Me 3");
        System.out.println("\nBest movie title: " + box.bestMovie().title);
        System.out.println("Best movie earnings: " + box.bestMovie().earnings);
    }
}