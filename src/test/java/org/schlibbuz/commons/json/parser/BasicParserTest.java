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
import static org.testng.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class BasicParserTest {

    private static final String RES_PREFIX = "src/test/resources/";


    @DataProvider(
        name = "buildJsonMap"
    )
    public static Object[][] buildJsonMap() {
        var mapOne = new HashMap<String, String>();
        mapOne.put("name", "value");

        var mapTwo = new HashMap<String, String>();
        mapTwo.put("name", "value");

        var mapThree = new HashMap<String, String>();
        mapThree.put("name", "value");

        return new Object[][] {
            {new File(RES_PREFIX + "simple.json"), mapOne},
            {new File(RES_PREFIX + "simple.json"), mapTwo},
            {new File(RES_PREFIX + "simple.json"), mapThree},
        };
    }
    @Test(dataProvider = "buildJsonMap")
    public void buildJsonMap(File f, Map<String, String> exp) throws IOException {
        var i = BasicParser.of(f);
        assertEquals(i.buildJsonMap(), exp);
    }

    @DataProvider(
        name = "getName"
    )
    public static Object[][] getName() {
        return new Object[][] {
            {"\"name\"", "name"},
            {"\"ναμε\"", "ναμε"},
            {"\"name\\\"withescape\"", "name\\\"withescape"},
        };
    }
    @Test(dataProvider = "getName")
    public void getName(String name, String exp) throws IOException {
        var p = BasicParser.of(null);
        assertEquals(p.read(name, AreaType.NAME), exp);
    }

    @DataProvider(
        name = "getColon"
    )
    public static Object[][] getColon() {
        return new Object[][] {
            {":", 1},
            {"  :  ", 5},
            {"\t:\n\t\t\n", 6},
            {":\n\t\t\n\n", 6},
            {"  :\r\n  ", 7},
        };
    }
    @Test(dataProvider = "getColon")
    public void getColon(String delim, int expLength) throws IOException {
        var p = BasicParser.of(null);
        assertEquals(p.read(delim, AreaType.COLON).length(), expLength);
    }

    @DataProvider(
        name = "getString"
    )
    public static Object[][] getString() {
        return new Object[][] {
            {"\"abc\"", "abc"},
            {"\"abc\\ndef\"", "abc\\ndef"},
        };
    }
    @Test(dataProvider = "getString")
    public void getString(String value, String exp) throws IOException {
        var p = BasicParser.of(null);
        assertEquals(p.read(value, AreaType.STRING), exp);
    }

    @DataProvider(
        name = "getBool"
    )
    public static Object[][] getBool() {
        return new Object[][] {
            {"true", "true"},
            {"false", "false"},
        };
    }
    @Test(dataProvider = "getBool")
    public void getBool(String value, String exp) throws IOException {
        var p = BasicParser.of(null);
        assertEquals(p.read(value, AreaType.BOOL), exp);
    }

    @DataProvider(
        name = "getInteger"
    )
    public static Object[][] getInteger() {
        return new Object[][] {
            {"1234", "1234"},
            {"12345", "12345"},
        };
    }
    @Test(dataProvider = "getInteger")
    public void getInteger(String value, String exp) throws IOException {
        var p = BasicParser.of(null);
        assertEquals(p.read(value, AreaType.INTEGER), exp);
    }
}
