package block.projects.enigma;
import java.util.Arrays;

public class Enigma {
    public static void main(String[] args) {
//        Enigma enigma = new Enigma();
//        enigma.getPositions("4,4,4|2,16,20|Lorem Ipsum is simply dummy text of the printing and typesetting industry Lorem Ipsum has been the industrys standard dummy text ever since the fifteen hundrededs when an unknown printer took a galley of type and scrambled it to make a type specimen book It has survived not only five centuries but also the leap into electronic typesetting remaining essentially unchanged");
    }

    public String getPositions(String input) {
        // gets starting input and turns into string array split by |
        String[] start = input.split("\\|");

        // gets the starting rotors as a string array
        String[] startingRotorString = (start[0].split(","));
        int[] startingRotors = new int[3];

        // turns that starting rotor string array into an int array
        for (int i = 0; i < 3; i++) {
            startingRotors[i] = Integer.parseInt(startingRotorString[i]);
        }

        // gets the starting shift as a string array
        String[] startingShiftString = (start[1].split(","));
        int[] startingShift = new int[3];

        // turns starting shift string array into int array
        for (int i = 0; i < 3; i++) {
            startingShift[i] = (Integer.parseInt(startingShiftString[i]));
        }

        // gets starting word and turns it to uppercase
        String word = start[2].toUpperCase();

        // creates new  string array where each letter of the word will be a element in array
        String[] startingWord = new String[start[2].length()];

        // creates an array  that is each letter in the starting word
        for (int i = 0; i < startingWord.length; i++) {
            startingWord[i] = word.substring(i, i + 1);
        }

        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // creates a new int array with the length of the start word
        int[] startingIndexes = new int[startingWord.length];

        // sets up an integer array of the starting loccation of eah letter
        for (int i = 0; i < startingWord.length; i++) {
            startingIndexes[i] = alpha.indexOf(startingWord[i]);
        }

        // create rotor and reflector
        Rotor rotor = new Rotor();
        Reflector reflector = new Reflector();

        int[] currentShift = {startingShift[0], startingShift[1], startingShift[2]};
        int[] rotationCount = {0, 0, 0};

        String output = "";

        // loop through each letter in the word
        for (int i = 0; i < startingIndexes.length; i++) {

            // if the current letter is a space add that space to the final word
            if (startingIndexes[i] == -1) {
                output += (" ");
                continue;
            }

                    // rotate before encrypting each letter except the first
                    if (i > 0) {
                        currentShift[2] = (currentShift[2] + 1) % 26;
                        rotationCount[2]++;
                        if (currentShift[2] < 0) {
                            currentShift[2] = 25;
                        }

                        // middle rotor rotates every 26 right rotor rotations
                        if (rotationCount[2] % 26 == 0) {
                            currentShift[1] = (currentShift[1] + 1) % 26;
                            rotationCount[1]++;
                            if (currentShift[1] < 0) {
                                currentShift[1] = 25;
                            }

                            // left rotor rotates every 26 middle rotor rotations
                            if (rotationCount[1] % 26 == 0) {
                                currentShift[0] = (currentShift[0] + 1) % 26;
                                rotationCount[0]++;
                                if (currentShift[0] < 0) {
                                    currentShift[0] = 25;
                                }
                            }
                        }
                    }

                    // currentIndex starts as the index of the current letter
                    int currentIndex = startingIndexes[i];
//                    System.out.println("starting letter index: " + currentIndex + " letter:" + alpha.charAt(currentIndex));

                    // send current index forward through leftmost rotor
                    currentIndex = rotor.getForward(startingRotors[0], currentShift[0], currentIndex);
//                    System.out.println("after rotor 1: " + currentIndex + " letter:" + alpha.charAt(currentIndex));

                    // send current index forward through middle rotor
                    currentIndex = rotor.getForward(startingRotors[1], currentShift[1], currentIndex);
//                    System.out.println("after rotor 2: " + currentIndex + " letter:" + alpha.charAt(currentIndex));

                    // send current index forward through rightmost rotor
                    currentIndex = rotor.getForward(startingRotors[2], currentShift[2], currentIndex);
//                    System.out.println("after rotor 3: " + currentIndex + " letter:" + alpha.charAt(currentIndex));

                    // send current index through reflector
                    currentIndex = reflector.reflect(currentIndex);
//                    System.out.println("after reflector: " + currentIndex + " letter:" + alpha.charAt(currentIndex));

                    // send current index backward through rightmost rotor
                    currentIndex = rotor.getBackward(startingRotors[2], currentShift[2], currentIndex);
//                    System.out.println("after backward rotor 3: " + currentIndex + " letter:" + alpha.charAt(currentIndex));

                    // send current index backward through middle rotor
                    currentIndex = rotor.getBackward(startingRotors[1], currentShift[1], currentIndex);
//                    System.out.println("after backward rotor 2: " + currentIndex + " letter:" + alpha.charAt(currentIndex));

                    // send current index backward through leftmost rotor
                    currentIndex = rotor.getBackward(startingRotors[0], currentShift[0], currentIndex);
//                    System.out.println("after backward rotor 1: " + currentIndex + " letter:" + alpha.charAt(currentIndex));

//                    System.out.println(" letter: " + alpha.substring(currentIndex, currentIndex + 1));
                    output += (alpha.substring(currentIndex, currentIndex + 1));
                }
        System.out.println(output);
        return output;
            }
        }



