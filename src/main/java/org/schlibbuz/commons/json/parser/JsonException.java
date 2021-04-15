/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.commons.json.parser;

import java.io.IOException;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class JsonException extends IOException {

    public JsonException() { super(); }
    public JsonException(String message) {
        super(message);
    }
}
