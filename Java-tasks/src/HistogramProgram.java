import java.util.*;

public class HistogramProgram{
    public static void main(String[] args) {
        Random r = new Random();
        int[]arr = new int[100]; //create array of 100 random values
        for(int j=0;j<100;j++) arr[j] = r.nextInt(10);
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>(); //mapping ints
        for(int j=0;j<100;j++){
            int val = arr[j];
            if (!hmap.containsKey(val)) {
                hmap.put(val, 1);
            } else {
                hmap.put(val, hmap.get(val) + 1);
            }
        }
        for(int i=0;i<10;i++){ // values from 0-9
            System.out.print(i+" |");
            for(int n=1;n<=hmap.get(i);n++) System.out.print("*");
            System.out.println();
        }
    }
}
