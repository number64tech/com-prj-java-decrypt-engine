package net.number64.open.decryptengine;

import java.util.*;

import net.number64.open.decryptengine.decoding.DecryptionFailedException;
import net.number64.open.decryptengine.input.FileDigester;
import net.number64.open.decryptengine.input.IDigester;
import static org.junit.Assert.*;

public abstract class WorkerTestCommon implements IDigester {
    private List<String> resultDecodedLines;

    private List<String> decodedLinesForTest;
    private HashMap<Character, Character> handMaidEnigma = new HashMap<>();
    private HashMap<Character, Character> reverseEnigma = new HashMap<>();

    public WorkerTestCommon() {
        addEncryptionSetting("a","z");
        addEncryptionSetting("b","x");
        addEncryptionSetting("c","w");
        addEncryptionSetting("d","y");
        addEncryptionSetting("e","v");
        addEncryptionSetting("f","u");
        addEncryptionSetting("g","t");
        addEncryptionSetting("h","s");
        addEncryptionSetting("i","r");
        addEncryptionSetting("j","q");
        addEncryptionSetting("k","p");
        addEncryptionSetting("l","o");
        addEncryptionSetting("m","n");
        addEncryptionSetting("n","m");
        addEncryptionSetting("o","l");
        addEncryptionSetting("p","k");
        addEncryptionSetting("q","j");
        addEncryptionSetting("r","i");
        addEncryptionSetting("s","h");
        addEncryptionSetting("t","g");
        addEncryptionSetting("u","f");
        addEncryptionSetting("v","e");
        addEncryptionSetting("w","d");
        addEncryptionSetting("x","c");
        addEncryptionSetting("y","b");
        addEncryptionSetting("z","a");
    }

    @Override
    public void digest() throws DecryptionFailedException { /*dummy*/ }

    @Override
    public void setDecodedFileLines(List<String> decodedFileLines) {
        resultDecodedLines = decodedFileLines;
    }

    protected void addEncryptionSetting(String plain, String encoded) {
        handMaidEnigma.put(encoded.charAt(0), plain.charAt(0));
        reverseEnigma.put(plain.charAt(0), encoded.charAt(0));
    }

    protected void setDecodedLinesForTest(List<String> decodedLines) {
        decodedLinesForTest = decodedLines;
    }

    @Override
    public List<String> getDictionaryWords() {
        return FileDigester.splitIntoWordsAndApplyFilter(decodedLinesForTest);
    }

    @Override
    public List<String> getEncodedWords() {
        return FileDigester.splitIntoWordsAndApplyFilter(getEncodedFileLines());
    }

    @Override
    public List<String> getEncodedFileLines() {
        return convert(reverseEnigma, decodedLinesForTest);
    }

    public void validate() {
        List<String> expectedDecodedLines =
                convert(handMaidEnigma, getEncodedFileLines());
        if (resultDecodedLines.size() != expectedDecodedLines.size()) {
            fail();
        }
        assertArrayEquals(expectedDecodedLines.toArray(), resultDecodedLines.toArray());
    }

    private List<String> convert(Map<Character, Character> mockEnigma, List<String> baseLines) {
        List<String> convertedLines = new ArrayList<>();
        for (String line : baseLines) {
            StringBuilder builder = new StringBuilder();
            char[] charArray = line.toCharArray();
            for (char aChar : charArray) {
                if (mockEnigma.containsKey(aChar)) {
                    builder.append(mockEnigma.get(aChar));
                } else {
                    builder.append(aChar);
                }
            }
            convertedLines.add(builder.toString());
        }
        return convertedLines;
    }
}
