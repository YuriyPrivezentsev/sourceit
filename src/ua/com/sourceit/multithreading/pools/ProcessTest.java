package ua.com.sourceit.multithreading.pools;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by yuriy.privezentsev on 4/17/14.
 */
public class ProcessTest {
    public static void main(String[] args) throws IOException {
        List<String> argumentsList = new ArrayList<String>();

        String fileSeparator = System.getProperty("file.separator");
        StringBuilder parametersBuilder = new StringBuilder();
        parametersBuilder.append(System.getProperty("java.home")).
                append(fileSeparator).
                append("bin").
                append(fileSeparator).
                append("java");

        argumentsList.add(parametersBuilder.toString());
        argumentsList.add("-classpath");
        argumentsList.add(System.getProperty("java.class.path"));
        argumentsList.add("ua.com.sourceit.multithreading.pools.CallableTest");

        ProcessBuilder processBuilder = new ProcessBuilder(argumentsList.toArray(new String[argumentsList.size()]));
        processBuilder.start();
        System.out.println("Process Started");
    }

}
