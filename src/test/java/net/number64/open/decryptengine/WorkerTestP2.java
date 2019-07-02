package net.number64.open.decryptengine;

import net.number64.open.decryptengine.decoding.DecryptionFailedException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class WorkerTestP2 {

    private final Logger Log = LoggerFactory.getLogger(WorkerTestP2.class.getSimpleName());

    @Test
    public void testWork_pattern2_5sizes_10words() throws DecryptionFailedException {
        Log.info("[START] testWork_pattern2_5sizes_10words");

        WorkerTestP2.Pattern2Simple testDigester = new WorkerTestP2.Pattern2Simple();
        Worker testWorker = new Worker(testDigester);
        testWorker.work();
        testDigester.validate();
        Log.info("[END]");
    }

    private class Pattern2Simple extends WorkerTestCommon {

        public Pattern2Simple() {
            super();
            setDecodedLinesForTest(
                Arrays.asList(
                    "a ax who know place",
                    "b ml for test there"
                )
            );
        }
    }
}
