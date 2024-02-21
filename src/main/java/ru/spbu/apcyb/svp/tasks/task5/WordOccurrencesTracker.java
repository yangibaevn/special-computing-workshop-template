package ru.spbu.apcyb.svp.tasks.task5;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordOccurrencesTracker {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: <sourceFile> <outputDirectory>");
        }

        String sourceFile = args[0];
        String outputDirectory = args[1];

        Path sourcePath = Paths.get(sourceFile);
        if (!Files.exists(sourcePath) || !Files.isRegularFile(sourcePath)) {
            throw new IllegalArgumentException("Source file does not exist or is invalid.");
        }

        Map<String, Long> words = countWords(sourceFile);
        writeToFile(words, outputDirectory);
        writeWordCount(words, outputDirectory);
    }

    public static Map<String, Long> countWords(String fileName) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            ExecutorService executor = Executors.newFixedThreadPool(4);
            CompletableFuture<Map<String, Long>> future = CompletableFuture.supplyAsync(() ->
                            lines.parallel()
                                    .flatMap(line -> Arrays.stream(line.split("[^a-zA-ZЁёА-я0-9]")))
                                    .map(String::toLowerCase)
                                    .collect(Collectors.groupingByConcurrent(Function.identity(), Collectors.counting())),
                    executor);

            return future.join();
        }
    }

    public static void writeToFile(Map<String, Long> wordCounts, String outputDir) throws IOException {
        List<String> content = wordCounts.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue())
                .collect(Collectors.toList());
        Files.write(Paths.get(outputDir, "counts.txt"), content);
    }

    public static void writeWordCount(Map<String, Long> wordCounts, String outputDirectory) {
        wordCounts.forEach((word, count) -> {
            try {
                String content = word + " " + count;
                Files.write(Paths.get(outputDirectory, word + "_.txt"), content.getBytes());
            } catch (IOException e) {
                throw new CompletionException("Unable to write to file: " + word + "_.txt", e);
            }
        });
    }
}
