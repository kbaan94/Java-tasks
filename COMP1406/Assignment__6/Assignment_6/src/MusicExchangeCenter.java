import javafx.util.Pair;
import java.util.*;

public class MusicExchangeCenter {
    private ArrayList<User> users;
    private HashMap<String, Float> royalties;
    private ArrayList<Song> downloadedSongs;

    public MusicExchangeCenter() {
        users = new ArrayList<User>();
        royalties = new HashMap<String, Float>();
        downloadedSongs = new ArrayList<Song>();
    }

    public HashMap<String, Float> getRoyalties() { return royalties; }
    public ArrayList<Song> getDownloadedSongs() { return downloadedSongs; }
    public ArrayList<User> onlineUsers(){

        ArrayList<User> online = new ArrayList<User>();

        for (User user : users) {
            if (user.isOnline()) {
                online.add(user);
            }
        }
        return online;
    }

    public ArrayList<Song> allAvailableSongs() {
        ArrayList<Song> availableSongs = new ArrayList<Song>();
        for (User user : onlineUsers()) {
            availableSongs.addAll(user.getSongList());
        }
        return availableSongs;
    }

    public String toString() {
        return "Music Exchange Center " + "(" + onlineUsers().size() + " clients on line, " + allAvailableSongs().size() + " songs available)";
    }

    public User userWithName(String s) {
        for (User user : users) {
            if (user.getUserName().equals(s)) {
                return user;
            }
        }
        return null;
    }

    public void registerUser(User x) {
        if (userWithName(x.getUserName()) == null) {
            users.add(x);
        }
    }

    public ArrayList<Song> availableSongsByArtist(String artist) {
        ArrayList<Song> availableSongs = new ArrayList<Song>();

        for (Song song : allAvailableSongs()) {
            if (song.getArtist().equals(artist)) {
                availableSongs.add(song);
            }
        }
        return availableSongs;
    }

    public Song getSong(String title, String ownerName) {
        for (User user : onlineUsers()) {
            if (user.getUserName().equals(ownerName)) {
                Song found;
                found = user.songWithTitle(title);
                if (!(found == null)) {
                    downloadedSongs.add(found);
                    return found;
                }
            }
        }
        return null;
    }

    public TreeSet<Song> uniqueDownloads() {
        TreeSet<Song> sorted;
        sorted = new TreeSet<Song>(new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        sorted.addAll(downloadedSongs);
        return sorted;
    }

    public ArrayList<Pair<Integer, Song>> songsByPopularity() {
        ArrayList<Pair<Integer, Song>> popular = new ArrayList<Pair<Integer, Song>>();

        for (Song song : uniqueDownloads()) {
            popular.add(new Pair<Integer, Song>((int)(royalties.get(song.getTitle())/0.25), song));
        }

        Collections.sort(popular, new Comparator<Pair<Integer, Song>>() {
            public int compare(Pair<Integer, Song> p1, Pair<Integer, Song> p2) {
                return -(p1.getKey() - p2.getKey());
            }
        });
        return popular;
    }

    public void displayRoyalties() {
        HashMap<String, Float> artistRoyalties = new HashMap<String, Float>();

        for (Song song : downloadedSongs) {
            if (!artistRoyalties.containsKey(song.getArtist())) {
                artistRoyalties.put(song.getArtist(), 0.25f);
            }
            else {
                artistRoyalties.put(song.getArtist(), artistRoyalties.get(song.getArtist()) + 0.25f);
            }
        }
        System.out.println("Amount Artist");
        System.out.println("---------------");

        for (HashMap.Entry<String, Float> entry : artistRoyalties.entrySet()) {
            System.out.println(String.format("$%.2f" + "   " + entry.getKey(), entry.getValue()));
        }
    }
}