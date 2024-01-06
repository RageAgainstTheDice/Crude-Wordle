package part1;
import java.util.Scanner;
public class Wordle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter secret: ");
        String secret = input.next();
        secret = secret.trim().toUpperCase();
        boolean incorrect = true;
        String history = "";
        for (int round = 1; round <= 6; round++) {
            String feedback = "";
            System.out.printf("Round %d. Enter guess: ", round);
            String guess = input.next();
            guess = guess.trim().toUpperCase();
            if (guess.equals(secret)) {
                incorrect = false;
                System.out.println("You've got it right.");
                break;
            } else if (guess.length() != 5) {
                System.out.println("Invalid guess.");
                round--;
                continue;
            } else {
                for (int i = 0; i <= 4; i++) {
                    if (guess.charAt(i) == secret.charAt(i)) {
                        feedback += "H";
                    } else if (secret.contains(guess.substring(i, i + 1))) {
                        feedback += "m";
                    } else {
                        feedback += "_";
                    } // end of the if-else statement determining if the character is a hit, miss, or neither
                } // end of the loop constructing the hit-miss string(s)
            } // end of the if-else statement determining whether the input is correct or not
            history += guess + ": " + feedback + "\n";
            System.out.println(history);
        } // end of the loop controlling the rounds
        if (incorrect) {System.out.println("secret was " + secret);}
    } // end of the main method
} // end of the class
