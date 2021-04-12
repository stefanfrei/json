/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.commons.json.parser;

import static org.schlibbuz.commons.json.parser.JsonParser.DEFAULT_BUFFER_SIZE;
import static org.schlibbuz.commons.json.parser.JsonParser.DEFAULT_CHARSET;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class JsonParserFax {

    /**
     * logger
     */
    private final static Logger w = LogManager.getLogger(JsonParserFax.class);


    private JsonParserFax() {}

    public static Map<String, String> createMapFrom(File f) throws IOException {
        try (
                FileInputStream stream = new FileInputStream(f);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(stream, DEFAULT_CHARSET)
                );
        ) {
            JsonParser instance;
            if (f.length() > (long)(10 * DEFAULT_BUFFER_SIZE)) {
                instance = new JsonObjectParser(reader.lines());
            } else {
                instance = new JsonBasicParser(reader.lines());
            }
            return instance.getJsonMap();
        } catch(IOException e) {
            // fatal!
            w.error("unexpected io-error -> {}", e);
            throw new IOException(e);
        }

    }
}
