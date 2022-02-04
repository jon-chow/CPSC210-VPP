package model.configurables;

import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {
//    public static boolean randomResult(double accuracy) {
//        return (Math.random() <= accuracy);
//    }

//    public static boolean rareRandomResult(int iterations, double accuracy, int requiredTrues) {
//        ArrayList<Boolean> truthValues = new ArrayList<>();
//
//        for (int i = 0; i < iterations; i++) {
//            truthValues.add(randomResult(accuracy));
//        }
//
//        return (countTrues(truthValues) >= requiredTrues);
//    }
//
//    // EFFECTS: returns a random number between low to high
//    public static int randomNumberBetween(int low, int high) {
//        Random r = new Random();
//
//        return r.nextInt(high - low) + low;
//    }

    // EFFECTS: returns a random number between 0 to high
    public static int randomNumberUpTo(int high) {
        Random r = new Random();

        return r.nextInt(high);
    }

//    // EFFECTS: return the number of trues in a list of boolean values
//    public static int countTrues(ArrayList<Boolean> truthValues) {
//        int counterTrues = 0;
//        for (boolean value : truthValues) {
//            if (value) {
//                counterTrues++;
//            }
//        }
//        return counterTrues;
//    }
}
