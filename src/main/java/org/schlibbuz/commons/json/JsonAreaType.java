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

import java.util.function.Predicate;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public enum JsonAreaType {

    OBJ_START("obj-opener", (c) -> " {\t\n".indexOf((char)c) > -1, "\""),
    NAME("obj-opener", (c) -> "\"".indexOf((char)c) == -1, ":"), // negative lookup
    SEPARATOR("obj-opener", (c) -> "\"".indexOf((char)c) == -1, ":"), // negative lookup
    ARR_START("obj-opener", (c) -> " [\t\n".indexOf((char)c) > -1, "\""),
    ARR_END("obj-opener", (c) -> " ]\t\n".indexOf((char)c) > -1, "\""),
    LITERAL("obj-opener", (c) -> " ]\t\n".indexOf((char)c) > -1, "\""),
    OBJ_END("obj-opener", (c) -> " }\t\n".indexOf((char)c) > -1, "");

    private final String name;
    private final Predicate p;
    private final String checkPoints;


    JsonAreaType(String name, Predicate p, String checkPoints) {
        this.name = name;
        this.p = p;
        this.checkPoints = checkPoints;
    }

    boolean isCharValid(char c) {
        return p.test(c);
    }

    boolean isCheckPoint(char c) {
        return checkPoints.indexOf(c) > -1;
    }

    @Override public String toString() { return name; }
}
