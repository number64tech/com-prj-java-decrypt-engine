package net.number64.open.decryptengine.cluster;

import net.number64.open.decryptengine.decoding.DecryptionFailedException;

import java.util.*;
import java.util.stream.Collectors;

public class Aggregator {

    private List<String> encodedWords;
    private List<String> dictionaryWords;

    public Aggregator(List<String> encodedWords, List<String> dictionaryWords) {
        this.encodedWords = encodedWords;
        this.dictionaryWords = dictionaryWords;
    }

    public List<WordCluster> getWordClusterList() throws DecryptionFailedException {

        Set<Integer> wordLengthSet = getLengthListOfDictionaryWords();

        List<WordCluster> wordClusterList = aggregateSameSizeWordsToCluster(wordLengthSet);

        makeAllOptionsOfWordPair(wordClusterList);

        divideWordPairsToCharPairs(wordClusterList);

        return wordClusterList;
    }

    private Set<Integer> getLengthListOfDictionaryWords() {
        return dictionaryWords.stream()
                .map(String::length)
                .collect(Collectors.toSet());
    }

    private List<WordCluster> aggregateSameSizeWordsToCluster(Set<Integer> wordLengthSet)
            throws DecryptionFailedException {
        List<WordCluster> clusters = new ArrayList<>();

        for (Integer wordLength : wordLengthSet) {
            WordCluster cluster = new WordCluster(wordLength);
            cluster.setEncodedWords(
                    encodedWords.stream()
                            .filter(word -> word.length() == wordLength)
                            .collect(Collectors.toList())
            );
            cluster.setDictionaryWords(
                    dictionaryWords.stream()
                            .filter(word -> word.length() == wordLength)
                            .collect(Collectors.toList())
            );
            cluster.validateEqualityOfWordsSize();
            clusters.add(cluster);
        }

        return clusters.stream()
                .sorted(Comparator.comparing(WordCluster::getWordLength))
                .collect(Collectors.toList());
    }

    private void makeAllOptionsOfWordPair(List<WordCluster> wordClusterList) {
        for (WordCluster cluster : wordClusterList) {
            List<String> encodedList = cluster.getEncodedWords();
            List<String> dictionaryList = cluster.getDictionaryWords();
            for (String encodedWord : encodedList) {
                for (String dictionaryWord : dictionaryList) {
                    cluster.addWordPair(new WordPair(encodedWord, dictionaryWord));
                }
            }
        }
    }

    private void divideWordPairsToCharPairs(List<WordCluster> wordClusterList) {
        for (WordCluster cluster : wordClusterList) {
            List<WordPair> wordPairList = cluster.getWordPairList();
            for (WordPair wordPair : wordPairList) {
                Character[] encodedCharArray = wordPair.getEncodedCharArray();
                Character[] dictionaryCharArray = wordPair.getDictionaryCharArray();
                List<CharPair> charPairs = new ArrayList<>();
                for (int index = 0 ; index < encodedCharArray.length ; index++) {
                    charPairs.add(new CharPair(encodedCharArray[index], dictionaryCharArray[index]));
                }
                cluster.addCharPairs(charPairs);
            }
        }
    }
}
