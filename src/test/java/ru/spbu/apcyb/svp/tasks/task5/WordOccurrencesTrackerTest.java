package ru.spbu.apcyb.svp.tasks.task5;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WordOccurrencesTrackerTest {

    @Test
    void shortFileTest() throws IOException {
        String source = "src/test/resources/task5/shortFile.txt";
        String outputDir = "src/test/resources/task5/testSF";
        Path outputDirPath = Paths.get(outputDir);

        if (!Files.exists(outputDirPath)) {
            Files.createDirectories(outputDirPath);
        }

        Map<String, Long> words = WordOccurrencesTracker.countWords(source);
        WordOccurrencesTracker.writeToFile(words, outputDir);
        WordOccurrencesTracker.writeWordCount(words, outputDir);

        Path expectedPath = Paths.get("src/test/resources/task5/shortFile_expected.txt");
        Path actualPath = Paths.get("src/test/resources/task5/counts.txt");

        List<String> expectedLines = Files.readAllLines(expectedPath);
        List<String> actualLines = Files.readAllLines(actualPath);

        assertEquals(expectedLines.size(), actualLines.size(), "Files have different line counts");

        for (int i = 0; i < expectedLines.size(); i++) {
            assertEquals(expectedLines.get(i), actualLines.get(i), "File content mismatch at line " + (i + 1));
        }
    }

    @Test
    void hugeFileTest() throws IOException {
        String source = "src/test/resources/task5/hugeFile.txt";
        String outputDir = "src/test/resources/task5/testHF";
        Path outputDirPath = Paths.get(outputDir);

        if (!Files.exists(outputDirPath)) {
            Files.createDirectories(outputDirPath);
        }

        Map<String, Long> map = WordOccurrencesTracker.countWords(source);
        WordOccurrencesTracker.writeToFile(map, outputDir);
        WordOccurrencesTracker.writeWordCount(map, outputDir);

        try (Stream<String> fileStream = Files.lines(Paths.get(outputDir + "/counts.txt"))) {
            long lines = fileStream.count();
            assertEquals(lines + 1, Files.list(outputDirPath).filter(Files::isRegularFile).count(),
                    "File count mismatch");
        }

        // Удаление созданной директории после выполнения теста
        Files.walk(outputDirPath)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }
}
