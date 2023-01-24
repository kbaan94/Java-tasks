public class Theatre { //Second class (Theatre)
    int seatsSold; //the movie's total seats sold
    int capacity; //the movie theatres capacity
    Movie nowPlaying; //the movie that is being played
    public Theatre(int theatreCap) { //create constructor to initialize the theater's capacity
        capacity = theatreCap;
    }

    public String isFull() { // we will check if the theater is full and return T or F
        if (seatsSold < capacity) return "false";
        else return "true";
    }

}
