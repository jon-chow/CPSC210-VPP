package model.configurables;

import java.util.ArrayList;

public class ChanceGenerator {
    public boolean randomResult(double accuracy) {
        return (Math.random() <= accuracy);
    }

    public boolean rareRandomResult(int iterations, double accuracy, int requiredTrues) {
        ArrayList<Boolean> truthValues = new ArrayList<>();

        for (int i = 0; i < iterations; i++) {
            truthValues.add(randomResult(accuracy));
        }

        return (countTrues(truthValues) >= requiredTrues);
    }

    public int countTrues(ArrayList<Boolean> truthValues) {
        int counterTrues = 0;
        for (boolean value : truthValues) {
            if (value) {
                counterTrues++;
            }
        }
        return counterTrues;
    }
}
