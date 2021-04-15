/* MIT License
 *
 * Copyright (c) 2021 Stefan Frei
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.schlibbuz.commons.json.parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class BasicParser extends AbstractParser {

    /**
     * logger
     */
    private final static Logger w = LogManager.getLogger(BasicParser.class);
    private static final int RECURSION_LIMIT = 20;
    private static final Pattern NUM_END_P = Pattern.compile("[,\\s\\]\\}]");

    private int depth;
    private int offset;


    private BasicParser(File f) {
        super(f);
        depth = 0;
        offset = 0;
    }

    static BasicParser of(File f) {
        return new BasicParser(f);
    }


    void extract(String json) throws JsonException {
        read(json, AreaType.OBJ_LEFT);
        if (++depth > RECURSION_LIMIT)
            throw new JsonParserException(
                    "recursion limit " + depth + " breached"
            );
        do {
            String key = read(json, AreaType.NAME);
            read(json, AreaType.COLON);
            AreaType next = identifyNext(json);
            if (next == AreaType.OBJ_LEFT) {
                extract(json); //recurse
            } else if (next == AreaType.ARR_LEFT) {
                read(json, AreaType.ARR_LEFT);
                int arrIndex = 0;
                do {
                    put(
                            key + "." + String.valueOf(arrIndex++),
                            read(json, identifyNext(json))
                    );
                } while(read(json, AreaType.COMMA) != null);
                read(json, AreaType.ARR_RIGHT);
            } else {
                put(key, read(json, next));
            }
        } while(read(json, AreaType.COMMA) != null);
        read(json, AreaType.OBJ_RIGHT);
        depth--;
    }

    String read(String json, AreaType t) throws JsonParserException {
        Matcher m = t.getPattern().matcher(json);
        if (!m.find(offset)) {
            if (t == AreaType.COMMA) return null; // comma is optional
            throw new JsonParserException(
                    "expected " + t.toString() + " not found"
            );
        }
        if (offset != m.start()) {
            if (t == AreaType.COMMA) return null; // comma is optional
            throw new JsonParserException(
                    "illegal match position: not on offset"
            );
        }
        offset += m.group().length();
        if (m.groupCount() > 0) return m.group(1);
        return m.group();
    }

    AreaType identifyNext(String json) throws JsonParserException {
        if (json.charAt(offset) == '{') return AreaType.OBJ_LEFT;
        if (json.charAt(offset) == '[') return AreaType.ARR_LEFT;
        if (json.charAt(offset) == '\"') return AreaType.STRING;
        if (json.charAt(offset) == 'f' || json.charAt(offset) == 't')
            return AreaType.BOOL; // F and T are not valid JSON
        Matcher numericEnd = NUM_END_P.matcher(json);
        if (!numericEnd.find(offset)) throw new JsonParserException("doh");
        String numeric = json.substring(offset, numericEnd.start());
        try {
            Integer.parseInt(numeric);
            return AreaType.INTEGER;
        } catch(NumberFormatException e) {
            try {
                Double.parseDouble(numeric);
                return AreaType.DOUBLE;
            } catch(NumberFormatException eSub) {
                throw new JsonParserException(
                        "failed on numeric -> " +
                        numeric + ": -> " + eSub.getMessage()
                );
            }
        }
    }

    void put(String key, String value) throws JsonException {
        if (map.putIfAbsent(key, value) != null)
            throw new JsonException("duplicate key -> " + key);
    }

    @Override
    public Map<String, String> buildJsonMap() throws IOException {
        String json = FileUtils.readFileToString(f, DEFAULT_CHARSET);
        extract(json);
        return map;
    }
}
