package net.number64.open.decryptengine.input;

import net.number64.open.decryptengine.decoding.DecryptionFailedException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileDigester implements IDigester {

    private String encodedFilePathString;
    private String dictionaryFilePathString;

    private List<String> encodedFileLines;
    private List<String> dictionaryFileLines;

    private List<String> encodedWords;
    private List<String> dictionaryWords;

    public List<String> getDictionaryWords() {
        return dictionaryWords;
    }
    public List<String> getEncodedWords() {
        return encodedWords;
    }

    public FileDigester(String encodedFilePathString, String dictionaryFilePathString) {
        this.encodedFilePathString = encodedFilePathString;
        this.dictionaryFilePathString = dictionaryFilePathString;
    }

    @Override
    public List<String> getEncodedFileLines() {
        return encodedFileLines;
    }

    @Override
    public void digest() throws DecryptionFailedException {
        // input
        encodedFileLines = inputFile(encodedFilePathString);
        // input
        dictionaryFileLines = inputFile(dictionaryFilePathString);

        // apply filter and distinct it
        encodedWords = splitIntoWordsAndApplyFilter(encodedFileLines);
        // filter
        dictionaryWords = splitIntoWordsAndApplyFilter(dictionaryFileLines);
    }

    private List<String> inputFile(String pathString) throws DecryptionFailedException {
        try {
            Path filePath = Paths.get(pathString);
            return Files.readAllLines(filePath);
        } catch (IOException ioe) {
            throw new DecryptionFailedException("inputFile()", ioe);
        }
    }

    public static List<String> splitIntoWordsAndApplyFilter(List<String> lines) {
        List<String> split =
                lines.stream()
                    .map(line -> line.split(" "))
                    .map(Arrays::asList)
                    .collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);

        List<String> filtered =
                split.stream()
                    .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
                    .filter(word -> !word.equals(""))
                    .distinct()
                    .sorted(Comparator.comparing((String divided) -> divided.length())
                            .thenComparing(String::toString))
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        return filtered;
    }

    @Override
    public void setDecodedFileLines(List<String> decodedFileLines) { /* no-op */ }
}
