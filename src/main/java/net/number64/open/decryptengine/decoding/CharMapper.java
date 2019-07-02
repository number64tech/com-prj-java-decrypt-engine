package net.number64.open.decryptengine.decoding;

import net.number64.open.decryptengine.cluster.CharPair;
import net.number64.open.decryptengine.cluster.WordCluster;

import java.util.List;
import java.util.Map;

public class CharMapper {
    private List<WordCluster> wordClusterList;
    private Enigma defaultEnigma;

    public CharMapper(List<WordCluster> wordClusterList) {
        this.wordClusterList = wordClusterList;
        defaultEnigma = new Enigma();
    }

    public Decoder generateDecoder() throws DecryptionFailedException {

        Enigma enigma = mapEncodedCharsToDictionaryChars(0, defaultEnigma);

        if (enigma == null) {
            throw new DecryptionFailedException("Failed to map all characters.");
        }

        return new Decoder(enigma);
    }

    // reclusive!
    private Enigma mapEncodedCharsToDictionaryChars(int targetDepth, Enigma parentalEnigma) throws DecryptionFailedException {
        Enigma result = null;
        // for safety
        if (wordClusterList.size() == targetDepth) {
            throw new RuntimeException();
        }
        //
        for (List<CharPair> charPairsList : wordClusterList.get(targetDepth).getCharPairsList()) {

            if (! checkContradiction(parentalEnigma, charPairsList)) {
                continue;
            }

            Enigma copiedEnigma = parentalEnigma.getDeepCopy();
            charPairsList.
                    forEach(charPairs -> copiedEnigma.put(charPairs.getEncChar(), charPairs.getDicChar()));

            Enigma tempResult;
            if (targetDepth < wordClusterList.size() - 1) {
                tempResult = mapEncodedCharsToDictionaryChars(targetDepth + 1, copiedEnigma);
            } else {
                tempResult = copiedEnigma;
            }

            if (result != null && tempResult != null) {
                throw new DecryptionFailedException("Got duplicated enigma engines.");
            }

            if (tempResult != null) {
                result = tempResult;
            }
        }

        return result;
    }

    private boolean checkContradiction(Map<Character, Character> parentalEnigma, List<CharPair> charPairsList) {
        for (CharPair charPair : charPairsList) {
            if (parentalEnigma.containsKey(charPair.getEncChar())) {
                if (! parentalEnigma.get(charPair.getEncChar()).equals(charPair.getDicChar())) {
                    return false;
                }
            }
        }
        return true;
    }
}
