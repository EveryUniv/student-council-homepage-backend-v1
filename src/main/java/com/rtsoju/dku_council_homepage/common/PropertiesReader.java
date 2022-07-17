package com.rtsoju.dku_council_homepage.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 프로퍼티 파일을 파싱해서 Map으로 제공하는 클래스. 테스트할 때 @Value 대신 사용할 수 있다.
 */
public class PropertiesReader {
    public static final PropertiesReader INSTANCE;
    private final HashMap<String, String> properties = new HashMap<>();

    static {
        try {
            INSTANCE = new PropertiesReader("/application.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PropertiesReader(String path) throws IOException {
        read(path);
    }

    /**
     * Properties 파일을 파싱해서 내부 맵에 저장.
     */
    public void read(String path) throws IOException {
        properties.clear();

        try (InputStream is = PropertiesReader.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new IOException("Can't find that file in jar: " + path);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String buffer;
                while ((buffer = br.readLine()) != null) {
                    if (buffer.startsWith("#") || buffer.indexOf('=') == -1) {
                        continue;
                    }
                    String[] keyValue = buffer.split("=", 2);
                    properties.put(keyValue[0], keyValue[1]);
                }
            }
        }
    }

    public String getValue(String key) {
        return properties.get(key);
    }

    public Map<String, String> getProperties() {
        return properties;
    }
}
