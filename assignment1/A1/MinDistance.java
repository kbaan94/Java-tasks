public class MinDistance {
    public static int counting(byte target, byte[] numbers) {
        int count = 0;
        for (byte number : numbers) {
            if (number == target) {
                ++count;
            }
        }
        return count;
    }
    public static int distance(byte target, byte[] numbers) {
        /*
         * purpose: determine the minimal distance between any two instances of target
         * in the numbers array input : target is the number we are looking for numbers
         * is an array of numbers output : the method returns o) the minimal distance
         * between any two instances of target in the numbers array, if there are at
         * least two instances of target present, or o) -2 if target is not in the
         * numbers array, or o) -1 if target appears only once in the numbers array
         */
        int count = counting(target, numbers);
        switch (count) {
            case 0:
                return -2;
            case 1:
                return -1;
            default:
                boolean flag = false;
                int res = 0, max = 100000, ans = 0;
                for (byte number : numbers) {
                    if (!flag && (number == target)) {
                        flag = true;
                        continue;
                    }
                    if (flag && number != target) {
                        ++res;
                    } else if (flag && number == target) {
                        if (res < max) {
                            max = res;
                            ans = res;
                        }
                        res = 0;
                    }
                }
                return ans;
        }
    }
    public static void main(String[] args) {
        byte[] S = {2, 8, 1, 3, 7, 1, -3, 2, 8, 8, 7, 1, 7, 2};
        byte[] A = {9, 2, 4, 5, 4, 2, 5, 5, 4, 8, -7, 9, 2, 9};
        byte[] N = {5, 3, 7, 2, 3, 4, 4, 7, 3, -1, 3, 4, 7, 5};

        System.out.println("First case:");
        System.out.println(distance((byte) 1, S));
        System.out.println(distance((byte) 8, S));
        System.out.println(distance((byte) 7, S));
        System.out.println(distance((byte) -3, S));
        System.out.println(distance((byte) 22, S));
        System.out.println(distance((byte) 2, S));

        System.out.println();
        System.out.println("Second Case");
        System.out.println(distance((byte) 9, A));
        System.out.println(distance((byte) 5, A));
        System.out.println(distance((byte) 4, A));
        System.out.println(distance((byte) -7, A));
        System.out.println(distance((byte) 16, A));
        System.out.println(distance((byte) 2, A));

        System.out.println();
        System.out.println("Original case:");
        System.out.println(distance((byte) 4, N));
        System.out.println(distance((byte) 7, N));
        System.out.println(distance((byte) 3, N));
        System.out.println(distance((byte) 2, N));
        System.out.println(distance((byte) 13, N));

    }
}