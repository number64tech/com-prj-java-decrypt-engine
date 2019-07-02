package net.number64.open.decryptengine.cluster;

public class CharPair {
    private Character encChar;
    private Character dicChar;
    public CharPair(Character encChar, Character dicChar) {
        this.encChar = encChar;
        this.dicChar = dicChar;
    }

    public Character getEncChar() {
        return encChar;
    }

    public Character getDicChar() {
        return dicChar;
    }
}
