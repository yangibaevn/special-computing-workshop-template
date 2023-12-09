package ru.spbu.apcyb.svp.tasks.task4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
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
            logger.log(Level.INFO,"Time spent on calculation with single thread: " + singleThreadTime + " ms");

            // Многопоточный режим
            Instant startMulti = Instant.now();
            List<Double> resultMulti = computeTangents(values, 4);
            Instant endMulti = Instant.now();
            writeResultInFile(multiOutputPath, resultMulti);
            long multiThreadTime = Duration.between(startMulti, endMulti).toMillis();
            logger.log(Level.INFO,"Time spent on calculation with multiple threads: " + multiThreadTime + " ms");

            // Сравнение производительности
            double speedup = (double) singleThreadTime / multiThreadTime;

            logger.log(Level.INFO,"Multi-threading speedup: " + String.format("%.2f", speedup) + " times");

        } catch (InvalidPathException e) {
            throw new IllegalArgumentException("Invalid path: " + e.getMessage());

        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Exception occurred", e);
        } catch (InterruptedException e) {
            logger.log(Level.WARNING, "Interrupted!", e);

            Thread.currentThread().interrupt();
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
            Future<List<Double>> futures = executorService.submit(
                    () -> values.parallelStream().map(Math::tan).toList());

            return futures.get();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred during computation", e);
            throw new InterruptedException("Computation interrupted");
        } finally {
            executorService.shutdownNow();
        }
    }
}