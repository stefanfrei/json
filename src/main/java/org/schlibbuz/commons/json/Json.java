/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Json {

    private final static Logger w = LogManager.getLogger(Json.class);

    private static final byte TRANSFER_BUFFER_SIZE = 8;
    private static final char Q_S = '\'';
    private static final char Q_D = '\"';
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final File f;
    private final Map<String, String> m;


    private int index;


    private Json(String fileName) throws FileNotFoundException {
        f = new File("src/test/resources/simple.json");
        m = buildMap();
        index = 0;
    }

    private Json(File f) throws FileNotFoundException {
        this.f = f;
        m = buildMap();
    }

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

    public static Json fromFile(String fileName) throws FileNotFoundException {
        return new Json(fileName);
    }

    public static Json fromFile(File file) throws FileNotFoundException {
        return new Json(file);
    }



    int findDelim() {
        return 0;
    }

    public List<String> select(String q) {
        return new LinkedList<>();
    }

    public String selectFirst(String q) {
        return "blaa";
    }

}
