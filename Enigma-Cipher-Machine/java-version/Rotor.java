package block.projects.enigma;

public class Rotor {
    String[] alpha = {"A", "B" , "C", "D", "E", "F" , "G" , "H", "I", "J" , "K" , "L", "M" , "N" , "O" , "P" , "Q", "W", "R", "S", "T","U", "V","X","Y","Z"};
    int[] r1Nums = {7,-1,1,8,4,-3,-1,-6,4,8,3,-7,-3,-3,-8,6,9,5,2,-1,3,-7,-7,-4,0,-9};
    int[] r1NumsReverse = {1,5,3,-1,7,1,8,-7,-4,3,3,-8,-4,-3,7,7,9,-8,1,4,-2,-6,-5,-3,0,-9};
    int[] r2Nums = {8,4,-2,0,-3,2,-4,-3,5,-3,8,4,-1,1,-5,-5,8,4,-2,1,-1,4,1,-6,-2,-13};
    int[] r2NumsReverse = {2,3,4,0,3,-4,3,-2,-8,5,5,1,13,-5,-1,-4,2,6,-8,1,-1,-4,2,-1,-8,-4};
    int[] r3Nums = {8,0,-2,-1,1,-2,6,2,-1,2,5,2,-6,3,6,-11,-6,4,5,6,-1,3,-5,-1,-10,-7};
    int[] r3NumsReverse = {2,0,1,2,11,-1,7,1,-8,-2,6,-2,-6,-2,10,-5,-3,5,7,1,-6,-4,1,-5,-3,-6};
    int[] r4Nums = {7,9,-2,3,-3,4,-2,-2,3,-6,-8,-3,7,7,-1,3,-4,-2,3,-2,-4,-7,2,0,-2,0};
    int[] r4NumsReverse = {2,3,8,6,2,2,-3,-7,3,-4,-9,-3,4,1,7,2,4,2,-3,-7,-7,-3,2,0,-2,-0};

    // takes the rotor number its shift and the index of curent letter
    public int getForward(int rotorNum, int shift, int input) {

        // picks the correct array based on rotor number
        int[] nums;
        if (rotorNum == 1) {
            nums = r1Nums;
        } else if (rotorNum == 2) {
            nums = r2Nums;
        } else if (rotorNum == 3) {
            nums = r3Nums;
        } else {
            nums = r4Nums;
        }

        // adds shift to find new index in the shifted array
        // the mod 26 wraps num around if it goes past 26(because like 26 leters in alpha)
        int shiftedIndex = (input - shift) % 26;
        if(shiftedIndex < 0){
            shiftedIndex = (shiftedIndex + 26) % 26;
        }


        //takes the shifted index and applies the rotors offest to it
        // then i added 26 before doing the mod because if there is a negative number this will cause a wrong answer
        int output = (shiftedIndex + nums[shiftedIndex] + 26) % 26;

        // takes the shift back out so that we are back at the normal index for the next rotor
        output = (output + shift + 26) % 26;
        return output;
    }

    public int getBackward(int rotorNum, int shift, int input) {

        // pick the correct array based on rotor number
        int[] nums;
        if (rotorNum == 1) {
            nums = r1NumsReverse;
        } else if (rotorNum == 2) {
            nums = r2NumsReverse;
        } else if (rotorNum == 3) {
            nums = r3NumsReverse;
        } else {
            nums = r4NumsReverse;
        }

        // same as forward
        int shiftedIndex = (input - shift) % 26;
        if(shiftedIndex < 0){
            shiftedIndex = (shiftedIndex + 26) % 26;
        }

        // same as forward
        int output = (shiftedIndex + nums[shiftedIndex] + 26) % 26;

        // same as forward
        output = (output + shift + 26) % 26;

        return output;
    }
}
