package net.number64.open.decryptengine;

import net.number64.open.decryptengine.decoding.DecryptionFailedException;
import net.number64.open.decryptengine.input.FileDigester;

public class Main {

    public static void main(String[] args) throws DecryptionFailedException {
        // check args
        validateArgs(args);
        // call worker
        callWorkerWithFormalDigester(args);
    }

    private static void validateArgs(String[] args) throws DecryptionFailedException {
        if (args.length == 0) {
            System.out.println("Usage: main.jar <encodedFilePath> <dictionaryFilePath>");
            throw new DecryptionFailedException();
        } else if (args.length != 2) {
            throw new DecryptionFailedException("Parameter error. Two arguments are required.");
        }
    }

    private static void callWorkerWithFormalDigester(String[] twoArgs) throws DecryptionFailedException {
        // with formal digester
        Worker worker = new Worker(new FileDigester(twoArgs[0], twoArgs[1]));
        worker.work();
    }
}
