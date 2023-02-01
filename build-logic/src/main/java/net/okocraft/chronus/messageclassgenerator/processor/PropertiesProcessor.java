package net.okocraft.chronus.messageclassgenerator.processor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class PropertiesProcessor {

    public static Map<String, String> load(Path filepath) {
        var filename = filepath.getFileName();

        if (filename == null || !filename.toString().endsWith(".properties")) {
            return Collections.emptyMap();
        }

        var properties = new Properties();

        try (var reader = Files.newBufferedReader(filepath)) {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return PropertiesProcessor.process(properties);
    }

    public static Map<String, String> process(Properties properties) {
        var result = new HashMap<String, String>(properties.size(), 2.0f);

        for (var entry : properties.entrySet()) {
            if (entry.getKey() instanceof String key && entry.getValue() instanceof String value) {
                result.put(key, value);
            } else {
                throw new IllegalStateException("key or value is not String (key: " + entry.getKey() + " value: " + entry.getValue());
            }
        }

        return result;
    }
}
