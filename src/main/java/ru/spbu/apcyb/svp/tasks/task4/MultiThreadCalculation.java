package ru.spbu.apcyb.svp.tasks.task4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MultiThreadCalculation {

    private static final Logger logger = Logger.getLogger(MultiThreadCalculation.class.getName());

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Incorrect amount of arguments, check specification of program.");
        }

        try {
            Path inputPath = Path.of(args[0]);
            Path singleOutputPath = Path.of(args[1]);
            Path multiOutputPath = Path.of(args[2]);

            List<Double> values = readValuesFromFile(inputPath);

            // Однопоточный режим
            Instant startSingle = Instant.now();
            List<Double> resultSingle = computeTangents(values, 1);
            Instant endSingle = Instant.now();
            writeResultInFile(singleOutputPath, resultSingle);
            long singleThreadTime = Duration.between(startSingle, endSingle).toMillis();
            System.out.printf("Time spent on calculation with single thread: %d ms%n", singleThreadTime);

            // Многопоточный режим
            Instant startMulti = Instant.now();
            List<Double> resultMulti = computeTangents(values, 4);
            Instant endMulti = Instant.now();
            writeResultInFile(multiOutputPath, resultMulti);
            long multiThreadTime = Duration.between(startMulti, endMulti).toMillis();
            System.out.printf("Time spent on calculation with multiple threads: %d ms%n", multiThreadTime);

            // Сравнение производительности
            double speedup = (double) singleThreadTime / multiThreadTime;
            System.out.printf("Multi-threading speedup: %.2f times%n", speedup);

        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Invalid path: " + e.getMessage());

        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Exception occurred", e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private static List<Double> readValuesFromFile(Path inputPath) throws IOException {
        return Files.readAllLines(inputPath)
                .stream()
                .map(Double::valueOf)
                .toList();
    }

    private static void writeResultInFile(Path outputPath, List<Double> result) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            writer.write("Processed " + result.size() + " numbers.\n");
            for (Double x : result) {
                writer.write(x + "\n");
            }
        }
    }

    public static List<Double> computeTangents(List<Double> values, int threadCount) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        try {
            int partitionSize = values.size() / threadCount;
            List<Future<List<Double>>> futures = new ArrayList<>();

            for (int i = 0; i < threadCount; i++) {
                int from = i * partitionSize;
                int to = (i == threadCount - 1) ? values.size() : (i + 1) * partitionSize;
                List<Double> sublist = values.subList(from, to);

                Future<List<Double>> future = executorService.submit(() -> calculateTangents(sublist));
                futures.add(future);
            }

            List<Double> result = new ArrayList<>();
            for (Future<List<Double>> future : futures) {
                result.addAll(future.get());
            }
            return result;

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred during computation", e);
            throw new InterruptedException("Computation interrupted");
        } finally {
            executorService.shutdownNow();
        }
    }

    private static List<Double> calculateTangents(List<Double> values) {
        List<Double> result = new ArrayList<>();
        for (Double x : values) {
            result.add(Math.tan(x));
        }
        return result;
    }
}
