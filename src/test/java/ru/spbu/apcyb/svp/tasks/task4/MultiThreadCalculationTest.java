package ru.spbu.apcyb.svp.tasks.task4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class MultiThreadCalculationTest {

    @Test
    void performanceTest() {
        System.out.println("\nPerformance Test");
        System.out.println("Calculation of 1 tan(x):");
        String oneInput = "src/test/resources/task4/in/numbers{1}.txt";
        String oneSingleOut = "src/test/resources/task4/out/results{1}_S.txt";
        String oneMultiOut = "src/test/resources/task4/out/results{1}_M.txt";

        MultiThreadCalculation.main(new String[]{oneInput, oneSingleOut, oneMultiOut});

        System.out.println("\nCalculation of 100 tan(x):");
        String hundredInput = "src/test/resources/task4/in/numbers{100}.txt";
        String hundredSingleOut = "src/test/resources/task4/out/results{100}_S.txt";
        String hundredMultiOut = "src/test/resources/task4/out/results{100}_M.txt";

        MultiThreadCalculation.main(new String[]{hundredInput, hundredSingleOut, hundredMultiOut});

        System.out.println("\nCalculation of 1000000 tan(x):");
        String millionInput = "src/test/resources/task4/in/numbers{1000000}.txt";
        String millionSingleOut = "src/test/resources/task4/out/results{1000000}_S.txt";
        String millionMultiOut = "src/test/resources/task4/out/results{1000000}_M.txt";

        MultiThreadCalculation.main(new String[]{millionInput, millionSingleOut, millionMultiOut});

        assertTrue(true);
    }

    @Test
    void comparisonFilesTest() throws IOException {
        System.out.println("\nComparison Test");
        String hundredInput = "src/test/resources/task4/in/numbers{100}.txt";
        String hundredSingleOut = "src/test/resources/task4/out/results{100}_S.txt";
        String hundredMultiOut = "src/test/resources/task4/out/results{100}_M.txt";

        MultiThreadCalculation.main(new String[]{hundredInput, hundredSingleOut, hundredMultiOut});

        Path singleThreadResult = Path.of(hundredSingleOut);
        Path multiThreadResult = Path.of(hundredMultiOut);
        assertEquals(-1, Files.mismatch(singleThreadResult, multiThreadResult));
    }
}