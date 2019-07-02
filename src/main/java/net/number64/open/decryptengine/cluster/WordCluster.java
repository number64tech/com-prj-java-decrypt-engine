package net.number64.open.decryptengine.cluster;

import net.number64.open.decryptengine.decoding.DecryptionFailedException;

import java.util.ArrayList;
import java.util.List;

public class WordCluster {
    private int wordLength;
    private List<String> encodedWords;
    private List<String> dictionaryWords;
    private List<WordPair> wordPairList = new ArrayList<>();
    private List<List<CharPair>> charPairsList = new ArrayList<>();

    public WordCluster(int wordLength) {
        this.wordLength = wordLength;
    }

    public int getWordLength() {
        return wordLength;
    }

    public void setEncodedWords(List<String> encodedWords) {
        this.encodedWords = encodedWords;
    }

    public void setDictionaryWords(List<String> dictionaryWords) {
        this.dictionaryWords = dictionaryWords;
    }

    public void validateEqualityOfWordsSize() throws DecryptionFailedException {
        if (encodedWords.size() != dictionaryWords.size()) {
            throw new DecryptionFailedException("Invalid word count: wordLength=" +
                    wordLength + " enc=" + encodedWords.size() + " dic=" + dictionaryWords.size());
        }
    }

    public List<String> getEncodedWords() {
        return encodedWords;
    }

    public List<String> getDictionaryWords() {
        return dictionaryWords;
    }

    public void addWordPair(WordPair pair) {
        wordPairList.add(pair);
    }

    public List<WordPair> getWordPairList() {
        return wordPairList;
    }

    public void addCharPairs(List<CharPair> charPairs) {
        charPairsList.add(charPairs);
    }

    public List<List<CharPair>> getCharPairsList() {
        return charPairsList;
    }

}
