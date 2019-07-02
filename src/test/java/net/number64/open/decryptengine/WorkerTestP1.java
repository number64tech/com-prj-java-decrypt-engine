package net.number64.open.decryptengine;

import net.number64.open.decryptengine.decoding.DecryptionFailedException;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.Assert.fail;

public class WorkerTestP1 {

    private final Logger Log = LoggerFactory.getLogger(WorkerTestP1.class.getSimpleName());

    @Test
    public void testWork_pattern1_3sizes_3words() throws DecryptionFailedException {
        Log.info("[START] testWork_pattern1_3sizes_3words");

        WorkerTestP1.Pattern1Simple testDigester = new WorkerTestP1.Pattern1Simple();
        Worker testWorker = new Worker(testDigester);
        testWorker.work();
        testDigester.validate();
        Log.info("[END]");
    }

    private class Pattern1Simple extends WorkerTestCommon {

        public Pattern1Simple() {
            super();
            setDecodedLinesForTest(
                Arrays.asList(
                    "a bc def",
                    "def bc a",
                    "bc"
                )
            );
        }
    }
}
