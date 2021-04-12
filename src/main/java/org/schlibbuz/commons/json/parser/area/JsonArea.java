/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.commons.json.parser.area;

import org.schlibbuz.commons.json.parser.JsonParserException;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public interface JsonArea {
    void validate(String area) throws JsonParserException;
    int findCheckpoint(String area);
    JsonArea traverse() throws JsonParserException;
}
