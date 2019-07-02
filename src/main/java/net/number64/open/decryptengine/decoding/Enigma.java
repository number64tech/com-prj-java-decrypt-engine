package net.number64.open.decryptengine.decoding;

import java.util.HashMap;

public class Enigma extends HashMap<Character, Character> {
    public Enigma() {
        super();
    }

    public char[] getValueOrDefault(int codePoint) {
        char[] result = Character.toChars(codePoint);
        if (result.length > 1) {
            return result;
        }
        if (containsKey(result[0])) {
            return new char[]{get(result[0])};
        } else {
            return result;
        }
    }

    public Enigma getDeepCopy() {
        Enigma copiedEnigma = new Enigma();
        keySet().
                forEach(key -> copiedEnigma.put(key, get(key)));
        return copiedEnigma;
    }
}
