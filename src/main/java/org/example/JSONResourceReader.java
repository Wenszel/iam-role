package org.example;

import java.io.InputStream;

public class JSONResourceReader extends JSONReader {

    @Override
    protected InputStream getInputStream(String url) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(url);
    }
}
