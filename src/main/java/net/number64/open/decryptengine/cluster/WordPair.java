package net.number64.open.decryptengine.cluster;

public class WordPair {
    private Character[] encodedCharArray;
    private Character[] dictionaryCharArray;

    public WordPair(String encWord, String dicWord) {
        this.encodedCharArray = encWord.
                chars().
                mapToObj(c -> (char)c).
                toArray(Character[]::new);
        this.dictionaryCharArray = dicWord.
                chars().
                mapToObj(c -> (char)c).
                toArray(Character[]::new);
    }

    public Character[] getEncodedCharArray() {
        return encodedCharArray;
    }

    public Character[] getDictionaryCharArray() {
        return dictionaryCharArray;
    }
}
