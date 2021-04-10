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
package org.schlibbuz.commons.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * write json map from json-file
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Json {

    /**
     * logger
     */
    private final static Logger w = LogManager.getLogger(Json.class);

    /**
     * small buffer for testing
     */
    private static final byte TRANSFER_BUFFER_SIZE = 8;
    /**
     * single quote
     */
    private static final char Q_S = '\'';
    /**
     * double quote
     */
    private static final char Q_D = '\"';
    /**
     * predefined charset UTF-8
     */
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    /**
     * the file resource to use
     */
    private final File f;
    /**
     * the resulting json map
     */
    private final Map<String, String> m;
    /**
     * whatever this is it needs to be here
     */
    private int index;


    /**
     * Constructor
     * @param String fileName - path to json-file
     * @throws FileNotFoundException
     */
    private Json(String fileName) throws FileNotFoundException {
        f = new File("src/test/resources/simple.json");
        m = buildMap();
        index = 0;
    }

    /**
     * Constructor
     * @param File f - json-file
     * @throws FileNotFoundException
     */
    private Json(File f) throws FileNotFoundException {
        this.f = f;
        m = buildMap();
    }

    /**
     *
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static Json fromFile(String fileName) throws FileNotFoundException {
        return new Json(fileName);
    }

    /**
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static Json fromFile(File file) throws FileNotFoundException {
        return new Json(file);
    }

    /**
     * build a HashMap from json data file
     * @return a map
     * @throws FileNotFoundException
     */
    Map<String, String> buildMap() throws FileNotFoundException {
        if (!f.exists()) throw new FileNotFoundException("File is not there");
        if (!f.canRead()) throw new FileNotFoundException("File is not readable");
        try(
                FileInputStream is = new FileInputStream(f);
                BufferedReader br = new BufferedReader(new InputStreamReader(is, UTF_8), TRANSFER_BUFFER_SIZE);
        ) {
            char[] buffer = new char[TRANSFER_BUFFER_SIZE];
            StringBuffer s = new StringBuffer();
            while (br.read(buffer) > -1) s.append(buffer);

            w.trace("\n{}", s.toString());

            return new HashMap<String, String>();
        } catch(IOException e) {
            w.error("unexpected io-error -> {}", e);
        }
        return Collections.emptyMap();
    }

    /**
     * querySelector like query function to get a ordered List of json values
     * @param q - query string
     * @return List - json values
     */
    public List<String> select(String q) {
        throw new UnsupportedOperationException("implement me");
    }

    /**
     * querySelector like query function to get a json value. first hit is returned
     * @param q - query string
     * @return String - json value
     */
    public String selectFirst(String q) {
        throw new UnsupportedOperationException("implement me");
    }
}
