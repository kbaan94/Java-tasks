import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String userName;
    private boolean online;
    private ArrayList<Song> songList;
    public User() { this(""); }
    public User(String u) {
        userName = u;
        online = false;
        songList = new ArrayList<Song>();
    }
    public String getUserName() { return userName; }
    public boolean isOnline() { return online; }
    public ArrayList<Song> getSongList() { return songList; }

    public void addSong(Song s) {
        s.setOwner(this);
        songList.add(s);
    }

    public int totalSongTime() {
        int total = 0;

        for (Song s : songList) {
            total += s.getDuration();
        }
        return total;
    }

    public void register(MusicExchangeCenter m) {
        m.registerUser(this);
    }

    public void logon(MusicExchangeCenter m) {
        if (!(m.userWithName(this.getUserName()) == null)) {
            this.online = true;
        }
    }

    public void logoff(MusicExchangeCenter m) {
        if (!(m.userWithName(this.getUserName()) == null)) {
            this.online = false;
        }
    }

    public String toString() {
        String s;
        s = "" + userName + ": " + songList.size() + " songs (";
        if (!online) {
            s += "not ";
        }
        return s + "online)";
    }

    public ArrayList<String> requestCompleteSonglist(MusicExchangeCenter m) {
        ArrayList<Song> availableSongs;
        availableSongs = m.allAvailableSongs();
        ArrayList<String> songList;
        songList = new ArrayList<String>();
        songList.add(String.format("    %-30s%-18s%-7s%-5s\n", "TITLE", "ARTIST", "TIME", "OWNER"));

        int i = 0;
        while (i < availableSongs.size()) {
            songList.add(String.format("%2d. %-30s%-17s%2d:%02d   %-15s", i+1,
                    availableSongs.get(i).getTitle(), availableSongs.get(i).getArtist(),
                    availableSongs.get(i).getMinutes(), availableSongs.get(i).getSeconds(),
                    availableSongs.get(i).getOwner().getUserName()));
            i++;
        }
        return songList;
    }

    public ArrayList<String> requestSonglistByArtist(MusicExchangeCenter m, String artist) {
        ArrayList<Song> availableSongs = m.availableSongsByArtist(artist);
        ArrayList<String> songList = new ArrayList<String>();
        songList.add(String.format("    %-30s%-18s%-7s%-5s\n", "TITLE", "ARTIST", "TIME", "OWNER"));
        int i = 0;
        while (i < availableSongs.size()) {
            songList.add(String.format("%2d. %-30s%-17s%2d:%02d   %-15s", i+1,
                    availableSongs.get(i).getTitle(), availableSongs.get(i).getArtist(),
                    availableSongs.get(i).getMinutes(), availableSongs.get(i).getSeconds(),
                    availableSongs.get(i).getOwner().getUserName()));
            i++;
        }
        return songList;
    }

    public Song songWithTitle(String title) {
        for (Song song : songList) {
            if (song.getTitle().equals(title)) {
                return song;
            }
        }
        return null;
    }

    public void downloadSong(MusicExchangeCenter m, String title, String owner) {
        Song foundSong;
        foundSong = m.getSong(title, owner);
        if (!(foundSong == null)) {
            addSong(new Song(foundSong.getTitle(),foundSong.getArtist(),foundSong.getMinutes(), foundSong.getSeconds()));
            if (m.getRoyalties().containsKey(foundSong.getTitle())) {
                m.getRoyalties().put(foundSong.getTitle(), m.getRoyalties().get(foundSong.getTitle()) + 0.25f);
            }
            else {
                m.getRoyalties().put(foundSong.getTitle(), 0.25f);
            }
        }
    }

    // Various Users for test purposes
    public static User DiscoStew() {
        User discoStew = new User("Disco Stew");
        discoStew.addSong(new Song("Hey Jude", "The Beatles", 4, 35));
        discoStew.addSong(new Song("Barbie Girl", "Aqua", 3, 54));
        discoStew.addSong(new Song("Only You Can Rock Me", "UFO", 4, 59));
        discoStew.addSong(new Song("Paper Soup Cats", "Jaw", 4, 18));
        return discoStew;
    }
    public static User SleepingSam() {
        User sleepingSam = new User("Sleeping Sam");
        sleepingSam.addSong(new Song("Meadows", "Sleepfest", 7, 15));
        sleepingSam.addSong(new Song("Calm is Good", "Waterfall", 6, 22));
        return sleepingSam;
    }
    public static User RonnieRocker() {
        User ronnieRocker = new User("Ronnie Rocker");
        ronnieRocker.addSong(new Song("Rock is Cool", "Yeah", 4, 17));
        ronnieRocker.addSong(new Song("My Girl is Mean to Me", "Can't Stand Up", 3, 29));
        ronnieRocker.addSong(new Song("Only You Can Rock Me", "UFO", 4, 52));
        ronnieRocker.addSong(new Song("We're Not Gonna Take It", "Twisted Sister", 3, 9));
        return ronnieRocker;
    }
    public static User CountryCandy() {
        User countryCandy = new User("Country Candy");
        countryCandy.addSong(new Song("If I Had a Hammer", "Long Road", 4, 15));
        countryCandy.addSong(new Song("My Man is a 4x4 Driver", "Ms. Lonely", 3, 7));
        countryCandy.addSong(new Song("This Song is for Johnny", "Lone Wolf", 4, 22));
        return countryCandy;
    }
    public static User PeterPunk() {
        User peterPunk = new User("Peter Punk");
        peterPunk.addSong(new Song("Bite My Arms Off", "Jaw", 4, 12));
        peterPunk.addSong(new Song("Where's My Sweater", "The Knitters", 3, 41));
        peterPunk.addSong(new Song("Is that My Toenail ?", "Clip", 4, 47));
        peterPunk.addSong(new Song("Anvil Headache", "Clip", 4, 34));
        peterPunk.addSong(new Song("My Hair is on Fire", "Jaw", 3, 55));
        return peterPunk;
    }
}