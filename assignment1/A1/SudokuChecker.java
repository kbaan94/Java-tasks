
public class SudokuChecker{
    /** sample valid game */
    public static byte[][] example1 = new byte[][]{
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,4},
            {2,8,7,4,1,9,6,3,5},
            {3,4,5,2,8,6,1,7,9}};
    /** sample invalid game */
    public static byte[][] example2 = new byte[][]{
            {5,3,4,6,7,8,9,1,2},
            {6,7,2,1,9,5,3,4,8},
            {1,9,8,3,4,2,5,6,7},
            {8,5,9,7,6,1,4,2,3},
            {4,2,6,8,5,3,7,9,1},
            {7,1,3,9,2,4,8,5,6},
            {9,6,1,5,3,7,2,8,3},
            {2,8,7,4,1,9,6,2,6},
            {3,4,5,2,8,6,1,8,8}};

    public static boolean checkRow(int row, byte[][] grid){
        boolean[] arr;
        arr = new boolean[grid[row].length];
        for(int i = arr.length - 1; i >= 0; i--) arr[i] = false;
        for(int i = grid[row].length - 1; i >= 0; i--) arr[grid[row][i] - 1] = true;
        for (boolean anArr : arr) if (!anArr) return false;
        return true;
    }

    public static boolean checkColumn(int col, byte[][] grid){
        boolean[] arr;
        arr = new boolean[grid[col].length];
        for(int i = arr.length - 1; i >= 0; i--) arr[i] = false;
        for(int i = grid[col].length - 1; i >= 0; i--) arr[grid[i][col] - 1] = true;
        for (boolean anArr : arr) {
            if (!anArr) return false;
        }
        return true;
    }

    public static boolean checkSubregion(int region, byte[][] grid){
        boolean[] arr = new boolean[grid.length];
        for(int i = arr.length - 1; i >= 0; i--) arr[i] = false;
        for(int i = 0;i<Math.sqrt(grid.length);i++){
            for(int j = 0;j<Math.sqrt(grid.length);j++){
                arr[grid[i + ((region * (int) Math.sqrt(grid.length)) % grid.length)][j +
                        ((region * (int) Math.sqrt(grid.length)) % grid.length)]-1] = true;
            }
        }
        for(int i = arr.length - 1; i >= 0; i--){
            if(!arr[i]) return false;
        }
        return true;
    }
    public static boolean check(byte[][] grid){
        for(int row=0; row<9; row+=1){
            if( !checkRow(row, grid) ) return false;
        }
        for(int col=0; col<9; col+=1){
            if( !checkColumn(col, grid) ) return false;
        }
        for(int subRegion=0; subRegion<9; subRegion+=1){
            if( !checkSubregion(subRegion, grid) ) return false;
        }
        return true;
    }
    public static void main(String[] args){
        System.out.print("example1 | expected output is true | actual output is ");
        System.out.println(check(example1));

        System.out.print("example2 | expected output is false | actual output is ");
        System.out.println(check(example2));

    }

}