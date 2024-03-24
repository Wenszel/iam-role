package org.example;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class JSONReader {

    public String readJSON(String url) throws JSONException {
        InputStream inputStream = getInputStream(url);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + url);
        }
        try {
            String InputString = readFromInputStream(inputStream);
            if (isJSONValid(InputString)) {
                return InputString;
            } else {
                throw new JSONException("Invalid JSON");
            }
        } catch (IOException e) {
            return null;
        }
    }

    protected InputStream getInputStream(String url) {
        try {
            File file = new File(url);
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public boolean isJSONValid(String JSON) {
        try {
            new JSONObject(JSON);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }
}
