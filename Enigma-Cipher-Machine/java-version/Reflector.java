package block.projects.enigma;

public class Reflector {
    String[] alpha = {"A", "B" , "C", "D", "E", "F" , "G" , "H", "I", "J" , "K" , "L", "M" , "N" , "O" , "P" , "Q", "R", "S", "T","U", "V","X","Y","z"};
    int[] reflectorNums = {8,2,13,-2,3,11,6,-3,-8,4,4,11,-6,-4,-4,-13,-11,8,5,2,4,-2,-11,-5,-4,-8};

    public int reflect(int input) {
        // same as rotor but no position shift
        return (input + reflectorNums[input] + 26) % 26;
    }
}
