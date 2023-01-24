public class BoxOffice {
    Movie bestMovie; // movie with highest demand
    Theatre theatreA, theatreB; // theater 1 and 2 to keep track of sales

    public BoxOffice(int capA, int capB) { // create constructor for both theaters capacity
        theatreA = new Theatre(capA);
        theatreB = new Theatre(capB);
    }

    public void openMovie(String watchMovie, Theatre m) { //create function for movie depending on theathre
        // if-else statements if the bestmovie has not been assigned a movie
        if (!(bestMovie != null)) {
            m.nowPlaying = new Movie(watchMovie);
            switch (m.seatsSold = 0) {
            }
            bestMovie = m.nowPlaying;
        } else { //make sure theater 'm' has not been active
            if (!(m.nowPlaying != null)) {
                m.nowPlaying = new Movie(watchMovie);
                switch (m.seatsSold = 0) {
                }
            } else {
                if (!(bestMovie.earnings >= m.nowPlaying.earnings)) {
                    bestMovie = m.nowPlaying;
                }
                m.nowPlaying = new Movie(watchMovie);
                switch (m.seatsSold = 0) {
                }
            }
        }
    }
    //Ticket Processing
    public void sellTicket(Patron t1, String str) { //ticket the patron will receive for their purchased movie
        double cost; //initiating cost per patron for ticket based on age
        if (!(t1.patronAge >= 12))
            cost = 6.25;
        else {
            if (!(t1.patronAge < 65)) {
                cost = 5.75;
            } else {
                cost = 12.50;
            }
        }
        if (str.equals(theatreA.nowPlaying.title)) { //update details of Theater A
            if (!(theatreA.seatsSold >= theatreA.capacity)) {
                theatreA.seatsSold += 1;
                theatreA.nowPlaying.earnings = theatreA.nowPlaying.earnings + cost;
                t1.yourTicket = new Ticket(theatreA);
            } else {
                System.out.println("Movie is sold out"); //if the movie is sold out
            }
        } else if (str.equals(theatreB.nowPlaying.title)) {  //update details of Theater B
            if (!(theatreB.seatsSold >= theatreB.capacity)) {
                theatreB.seatsSold += 1;
                theatreB.nowPlaying.earnings = theatreB.nowPlaying.earnings + cost;
                t1.yourTicket = new Ticket(theatreB);
            } else { //if the movie is sold out
                System.out.println("Movie is sold out");
            }
        } else { //if the movie is not playing
            System.out.println("Movie is not currently playing");
        }
    }
    //create function that will return ticket of movie by patron
    public void returnTicket(Patron t2) {
        //find ticket price and calculate based on age if the ticket does exist
        if (!(t2.yourTicket == null)) {
            double price;
            if (!(t2.patronAge >= 12)) {
                price = 6.25;
            } else {
                if (!(t2.patronAge < 65))
                    price = 5.75;
                else {
                    price = 12.50;
                }
            }
            //update details, and if ticket does not exist, then display that it does not
            t2.yourTicket.ticForTheatre.seatsSold--;
            t2.yourTicket.ticForTheatre.nowPlaying.earnings = t2.yourTicket.ticForTheatre.nowPlaying.earnings - price;
            t2.yourTicket = null;
        } else { //if ticket does not exist
            System.out.println("Patron does not have a ticket");
        }
    }
    public Movie bestMovie() { //create function that will return the best movie in general
        // if statements will compare earnings of movies and return best earning one
        if (!(theatreA.nowPlaying.earnings <= bestMovie.earnings))
            bestMovie = theatreA.nowPlaying;
            else {
            if (!(theatreB.nowPlaying.earnings <= bestMovie.earnings)) {
                bestMovie = theatreB.nowPlaying;
            }
        }
        return bestMovie;
    }
}
