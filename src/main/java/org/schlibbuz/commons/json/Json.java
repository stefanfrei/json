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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.schlibbuz.commons.json.parser.JsonParserFax;

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
     * the resulting json map
     */
    private final Map<String, String> m;


    /**
     * Constructor
     * @param String fileName - path to json-file
     * @throws IOException - in case the file is not found or is not readable
     */
    private Json(String fileName) throws IOException {
        m = JsonParserFax.createMapFrom(new File(fileName));
    }

    /**
     * Constructor
     * @param File f - json-file
     * @throws IOException - in case the file is not found or is not readable
     */
    private Json(File f) throws IOException {
        m = JsonParserFax.createMapFrom(f);
    }

    /**
     *
     * @param fileName
     * @return
     * @throws IOException - in case the file is not found or is not readable
     */
    public static Json of(String fileName) throws IOException {
        return new Json(fileName);
    }

    /**
     *
     * @param f - json-file
     * @return
     * @throws IOException - in case the file is not found or is not readable
     */
    public static Json of(File f) throws IOException {
        return new Json(f);
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
