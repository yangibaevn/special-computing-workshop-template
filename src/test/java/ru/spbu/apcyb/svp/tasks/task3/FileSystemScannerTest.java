package ru.spbu.apcyb.svp.tasks.task3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

class FileSystemScannerTest {

    @Test
    void emptyDirTest() throws IOException {
        Path directoryPath = Path.of("src/test/resources/task3/emptyDir");
        Path expectedPath = Path.of("src/test/resources/task3/expected/empty_dir_expected.txt");
        Path outputPath = Path.of("src/test/resources/task3/output/empty_dir_actual.txt");

        List<String> fileList = FileSystemScanner.listFiles(directoryPath);
        FileSystemScanner.writeToFile(outputPath, fileList);

        assertTrue(Files.exists(expectedPath));
        assertEquals(Files.readAllLines(expectedPath), Files.readAllLines(outputPath));
    }

    @Test
    void wSubdirTest() throws IOException {
        Path directoryPath = Path.of("src/test/resources/task3/folder2");
        Path expectedPath = Path.of("src/test/resources/task3/expected/folder2_expected.txt");
        Path outputPath = Path.of("src/test/resources/task3/output/folder2_actual.txt");

        List<String> fileList = FileSystemScanner.listFiles(directoryPath);
        FileSystemScanner.writeToFile(outputPath, fileList);

        assertTrue(Files.exists(expectedPath));
        assertEquals(Files.readAllLines(expectedPath), Files.readAllLines(outputPath));
    }

    @Test
    void woSubdirTest() throws IOException {
        Path directoryPath = Path.of("src/test/resources/task3/folder1");
        Path expectedPath = Path.of("src/test/resources/task3/expected/folder1_expected.txt");
        Path outputPath = Path.of("src/test/resources/task3/output/folder1_actual.txt");

        List<String> fileList = FileSystemScanner.listFiles(directoryPath);
        FileSystemScanner.writeToFile(outputPath, fileList);

        assertTrue(Files.exists(expectedPath));
        assertEquals(Files.readAllLines(expectedPath), Files.readAllLines(outputPath));
    }

    @Test
    void invalidPathTest() {
        Path directoryPath = Path.of("invalid_Directory");
        assertThrows(IOException.class, () -> FileSystemScanner.listFiles(directoryPath));
    }
}
