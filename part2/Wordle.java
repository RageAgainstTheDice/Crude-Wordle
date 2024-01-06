package part2;
import java.util.*;
import static part2.Dictionary.*;
public class Wordle {
    public static final String HIT = "H";
    public static final String MISS = "m";
    public static final String NEVER = "_";
    public static final String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public static String alphabetize(String test) {
        StringBuilder builder = new StringBuilder();
        for (int i =0; i < ALPHABET.length; i++) {
            if (test.contains(ALPHABET[i])) {builder.append(ALPHABET[i]).append(" ");}
        }
        return builder.toString();
    } // end of the alphabetize method
    public static void main(String[] args) {
        int randIndex = (int) (Math.random() * (LONG_LIST.length - 1));
        String secret = LONG_LIST[randIndex];
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Do you want to see the secret? ");
        String response = keyboard.next().toLowerCase();
        if (response.startsWith("y")) {System.out.println(secret);}
        System.out.println();
        boolean incorrect = true;
        String history = "";
        StringBuilder mustUse = new StringBuilder(), mustNotUse = new StringBuilder();
        for (int round = 1; round <= 6; round++) {
            StringBuilder feedback = new StringBuilder(), mayUse = new StringBuilder();
            System.out.printf("Round %d. Enter guess: ", round);
            String guess = keyboard.next().trim().toUpperCase();
            if (guess.equals(secret)) {
                incorrect = false;
                System.out.println("You've got it right.");
                break;
            } else if (guess.length() != 5) {
                System.out.println("Your guess must be exactly 5 characters in length.\n");
                round--;
                continue;
            } else if (!Arrays.asList(LONG_LIST).contains(guess)) {
                System.out.println("There is no such word.\n");
                round--;
                continue;
            } else {
                for (int i = 0; i <= 4; i++) {
                    if (guess.charAt(i) == secret.charAt(i)) {
                        feedback.append(HIT);
                    } else if (secret.contains(guess.substring(i, i + 1))) {
                        feedback.append(MISS);
                    } else {
                        feedback.append(NEVER);
                    } // end of the conditional chain determining if the character is a hit, miss, or neither
                } // end of the loop constructing the hit-miss string(s)
            } // end of the conditional chain determining whether the input is correct or not
            for (int i = 0; i < ALPHABET.length; i++) {
                if (mustUse.toString().contains(ALPHABET[i]) || mustNotUse.toString().contains(ALPHABET[i])) {
                    continue;
                }
                if (guess.contains(ALPHABET[i]) && secret.contains(ALPHABET[i])) {
                    mustUse.append(ALPHABET[i]).append(" ");
                } else if (guess.contains(ALPHABET[i]) && !secret.contains(ALPHABET[i])) {
                    mustNotUse.append(ALPHABET[i]).append(" ");
                } else {
                    mayUse.append(ALPHABET[i]).append(" ");
                } // end of the conditional chain determining where the letter goes
            } // end of the loop determining what letters must, may, or must not be used (per round)
            history += guess + ": " + feedback + "\n";
            System.out.printf("%s--------%nMUST USE: %s%nMAY USE: %s%nMUST NOT USE: %s%n%n", history,
                    alphabetize(mustUse.toString()), alphabetize(mayUse.toString()),
                    alphabetize(mustNotUse.toString()));
        } // end of the loop controlling the rounds
        if (incorrect) {System.out.println("secret was " + secret);}
    } // end of main method
} // end of class
