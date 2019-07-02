package net.number64.open.decryptengine.decoding;

import net.number64.open.decryptengine.input.IDigester;

import java.util.ArrayList;
import java.util.List;

public class Decoder {

    Enigma enigma;
    List<String> decodedFileLines = new ArrayList<>();

    public Decoder(Enigma enigma) {
        this.enigma = enigma;
    }

    public List<String> getDecodedFileLines() {
        return decodedFileLines;
    }

    public void decodeAndOutput(IDigester digester) {
        System.out.println("---- Decoded File Lines: ");
        List<String> encodedFileLines = digester.getEncodedFileLines();
        for (String line : encodedFileLines) {
            StringBuilder charLine = new StringBuilder();
            line.chars().
                    forEach(codePoint -> charLine.append(enigma.getValueOrDefault(codePoint)));
            decodedFileLines.add(charLine.toString());
        }
        decodedFileLines.stream().
                forEach(System.out::println);
        digester.setDecodedFileLines(decodedFileLines);
    }
}
