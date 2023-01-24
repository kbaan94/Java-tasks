// Import to use Random generator and Scanner for parsing primitive types and strings
import java.util.Scanner;
import java.util.Random;

public class QuizProgram {
    // We will get the percentage with this Variable
    static int correctAns;
    static {
        correctAns = 0;
    }
    public static void main(String[] args) {
        int count; // Setting the count to 0
        count = 0;
        // now we will create 10 questions for the quiz
        generateQuestion();
        count++;
        while (count != 10) {
            generateQuestion();
            count++;
        }
        System.out.printf("You scored %d%% in the quiz%n", correctAns*10);;
    }
    public static void generateQuestion() { // now to generate the 10 questions and make sure they are the right answer
        Scanner r = new Scanner(System.in);
        int result;
        result = 0;
        Random randomGenerator = new Random();
        int n1 = randomGenerator.nextInt(99);
        // n1 for range of 0 to 99
        int n2 = n1==0?0:randomGenerator.nextInt(99)%n1;
        // n2 for range in 0 to n1
        int operator = randomGenerator.nextInt(2);
        // Generate a random operator (+ or -)
        if (operator != 0) {
            System.out.printf("What is the output to %d - %d = ", n1, n2);
            result = r.nextInt();
            if (result != n1-n2) {
                System.out.println("Sorry the correct answer is "+(n1-n2));
            } else {
                correctAns++;
                System.out.println("You are correct!");
            }
        } else {
            System.out.print("What is the result to "+n1+" + "+n2+ " = ");
            result = r.nextInt();
            if (result != n1+n2) {
                System.out.println("Sorry the correct answer is "+(n1+n2));
            } else {
                correctAns++;
                System.out.println("You are correct!");
            }
        }
    }
}


