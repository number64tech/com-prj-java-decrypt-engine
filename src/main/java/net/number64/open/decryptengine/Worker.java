package net.number64.open.decryptengine;

import net.number64.open.decryptengine.cluster.Aggregator;
import net.number64.open.decryptengine.cluster.WordCluster;
import net.number64.open.decryptengine.decoding.CharMapper;
import net.number64.open.decryptengine.decoding.Decoder;
import net.number64.open.decryptengine.decoding.DecryptionFailedException;
import net.number64.open.decryptengine.input.IDigester;

import java.util.List;

public class Worker {

    private IDigester digester;

    public Worker(IDigester digester) {
        this.digester = digester;
    }

    public void work() throws DecryptionFailedException {
        // call digester.execute
        digester.digest();
        // debug
        System.out.println("---- Encoded File Lines:");
        digester.getEncodedFileLines().forEach(System.out::println);
        System.out.println("---- Dictionary File Lines:");
        System.out.println(String.join(", ", digester.getDictionaryWords()));

        //
        List<String> encodedWords = digester.getEncodedWords();
        List<String> dictionaryWords = digester.getDictionaryWords();

        // clustering...
        Aggregator aggregator = new Aggregator(encodedWords, dictionaryWords);
        List<WordCluster> wordClusterList = aggregator.getWordClusterList();

        //
        CharMapper charMapper = new CharMapper(wordClusterList);

        //
        Decoder decoder = charMapper.generateDecoder();
        decoder.decodeAndOutput(digester);
    }
}
