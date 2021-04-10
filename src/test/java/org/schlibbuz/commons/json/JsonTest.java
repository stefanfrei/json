/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.commons.json;

import java.io.FileNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class JsonTest {

    private static final Logger w = LogManager.getLogger(JsonTest.class);

    private static final String JSON = "src/test/resources/simple.json";

    @Test
    public void blaa() throws FileNotFoundException {
        var i = Json.fromFile(JSON);
    }
}
