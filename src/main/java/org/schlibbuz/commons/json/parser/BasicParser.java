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

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;
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


    private BasicParser(Stream<String> data) {
        super(data);
        jat = JsonAreaType.OBJ_START;
    }

    static BasicParser of(Stream<String> data) {
        return new BasicParser(data);
    }

    @Override
    public Map<String, String> buildJsonMap() {
        data.forEach(this::printzz);
        return Collections.emptyMap();
    }

    public void printzz(String x) {
        w.trace(x);
    }
}
