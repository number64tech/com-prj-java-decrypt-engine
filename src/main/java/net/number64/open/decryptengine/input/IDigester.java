package net.number64.open.decryptengine.input;

import net.number64.open.decryptengine.decoding.DecryptionFailedException;

import java.util.List;

public interface IDigester {

    void digest() throws DecryptionFailedException;

    List<String> getEncodedWords();

    List<String> getDictionaryWords();

    List<String> getEncodedFileLines();

    void setDecodedFileLines(List<String> decodedFileLines);

}
