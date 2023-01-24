import javafx.geometry.Point2D;

public class PartImage {
    private boolean[][] pixels;
    private boolean[][] visited;
    private int rows;
    private int cols;
    public int border = 0;
    public int runamount = 0;

    public PartImage(int r, int c) {
        rows = r;
        cols = c;
        visited = new boolean[r][c];
        pixels = new boolean[r][c];
    }

    public PartImage(int rw, int cl, byte[][] data) {
        this(rw, cl);
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (data[r][c] == 1)
                    pixels[r][c] = true;
                else
                    pixels[r][c] = false;
            }
        }
    }

    private void expandFrom(int r, int c) {
        try {
            boolean bool = pixels[r][c];
            if (bool) {
                visited[r][c] = true;
                pixels[r][c] = false;
                expandFrom(r + 1, c);
                expandFrom(r - 1, c);
                expandFrom(r, c + 1);
                expandFrom(r, c - 1);
            } else if (!visited[r][c]) border += 1;
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
            this.border++;
        }
    }

    private int perimeterOf(int r, int c) {
        return 0;
    }

    public int perimeter() {
        Point2D p = findStart();
        expandFrom((int) p.getX(), (int) p.getY());
        return border;
    }


    public boolean isBroken() {
        Point2D p = findStart();
        expandFrom((int) p.getX(), (int) p.getY());
        return (partSize() != 0);
    }


    public void print() {
        String string = "";
        for (boolean[] pixcols : pixels) {
            for (int i = 0; i < pixcols.length; i++) {
                boolean pixrows = pixcols[i];
                if (pixrows) string += "*";
                else string += "-";
            }
            string += "\n";
        }
        System.out.printf(string);
    }

    public Point2D findStart() {
        int y;
        for (y = 0; y < cols; y++) {
            int x;
            for (x = 0; x < rows; x++) {
                if (pixels[y][x]) return new Point2D(x, y);
            }
        }
        return null;
    }

    public int partSize() {
        int count = 0;
        for (int i = 0; i < pixels.length; i++) {
            boolean[] pixcols = pixels[i];
            for (boolean pixrows : pixcols) {
                if (pixrows) count++;
            }
        }
        return count;
    }

    public static PartImage exampleA() {
        byte[][] pix = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        return new PartImage(10, 10, pix);
    }

    public static PartImage exampleB() {
        byte[][] pix = {{1, 0, 1, 0, 1, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 1, 1, 1, 1},
                {0, 1, 0, 1, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 1, 0, 0, 0}};
        return new PartImage(10, 10, pix);
    }

    public static PartImage exampleC() {
        byte[][] pix = {{1, 1, 1, 0, 0, 0, 1, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 1, 0, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 0, 1, 1, 1, 1, 1},
                {0, 0, 1, 0, 0, 0, 1, 1, 0, 0}};
        return new PartImage(10, 10, pix);
    }

    public static PartImage exampleD() {
        byte[][] pix = {{1, 0, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                {1, 1, 0, 0, 0, 1, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0, 0}};
        return new PartImage(10, 10, pix);
    }
}