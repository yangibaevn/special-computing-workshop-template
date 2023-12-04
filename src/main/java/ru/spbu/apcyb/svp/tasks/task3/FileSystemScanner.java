package ru.spbu.apcyb.svp.tasks.task3;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileSystemScanner {

    /**
     * MAIN FUNCTION.
     * @param args args[0] = sourceDirectoryPath, args[1] = outputFilePath.
     */

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: java FileSystemScanner <source_directory> <output_file>");
        }

        String sourceDirectoryPath = args[0];
        String outputFilePath = args[1];

        Path sourceDirectory = Paths.get(sourceDirectoryPath);
        Path outputPath = Paths.get(outputFilePath);

        try {
            if (!Files.exists(sourceDirectory) || !Files.isDirectory(sourceDirectory)) {
                throw new IllegalArgumentException("Source directory does not exist or is not a directory");
            }
            if (Files.exists(outputPath) && Files.isDirectory(outputPath)) {
                throw new FileAlreadyExistsException("Output path points to an existing directory");
            }

            List<String> fileList = listFiles(sourceDirectory);
            writeToFile(outputPath, fileList);
            System.out.println("File list successfully written to " + outputFilePath);
        } catch (IllegalArgumentException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Creates list of paths of folders and files.

     * @param sourceDirectory directory
     * @return list of sub dirs.
     * @throws IOException NoSuchFileException.
     */

    public static List<String> listFiles(Path sourceDirectory) throws IOException {
        List<String> fileList = new ArrayList<>();
        Files.walkFileTree(sourceDirectory, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                Path relativePath = sourceDirectory.relativize(file);
                fileList.add(relativePath.toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                Path relativePath = sourceDirectory.relativize(dir);
                fileList.add(relativePath.toString() + "/");
                return FileVisitResult.CONTINUE;
            }
        });
        return fileList;
    }

    /**
     * Write paths to output file.

     * @param outputPath Path to write
     * @param fileList List of paths to write
     * @throws IOException NoSuchFileException
     */

    public static void writeToFile(Path outputPath, List<String> fileList) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            for (String entry : fileList) {
                writer.write(entry);
                writer.newLine();
            }
        }
    }
}
