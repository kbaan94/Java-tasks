import java.util.*;
public class prob {
    public static void main(String[] args){
        int[][] array;
        array = new int[10][10]; //10x10 2D-array
        Random gen;
        gen = new Random();
        for(int j = 9; j >= 0; j--){
            for(int i = 9; i >= 0; i--){
                int randBool;
                randBool = gen.nextInt(2);
                array[j][i] = randBool;
                System.out.print(randBool == 1 ? "O" : ".");
            }  //Keeping the sequences in columns and rows
            System.out.println();
        }
        int maxHorizontal; // for the horizontal sequence
        maxHorizontal = 0;
        for(int j = 9; j >= 0; j--){
            int v2;
            v2 = 1;
            for(int i = 8; i >= 0; i--){
                int v1 = 1;
                for (int r = 8; r >= i; r--){
                    if(array[j][r] == array[j][r+1] & array[j][r] == 1) v1 = v1 + 1;
                    else break;
                }
                v2 = Math.max(v2,v1);
            }
            maxHorizontal = Math.max(maxHorizontal,v2);
        }
        System.out.println("The longest horizontal sequence is " + maxHorizontal);

        int maxVertical = 0; // for the vertical sequence
        for(int j = 0; j<10; j++) {
            int v2 = 1;
            for(int i = 0; i<9; i++) {
                int v1 = 1;
                for (int n = i; n<9;n++) {
                    if (!(array[n][j] == array[n+1][j] & array[n][j] == 1)) {
                        break;
                    } else v1 = v1 + 1;
                }
                v2 = Math.max(v2,v1);
            }
            maxVertical = Math.max(maxVertical,v2);
        }
        System.out.println("The longest vertical sequence is " + maxVertical);
        System.out.println();
    }
}