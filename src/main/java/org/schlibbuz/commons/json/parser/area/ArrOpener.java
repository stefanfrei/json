/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.commons.json.parser.area;

import java.util.regex.Pattern;
import org.schlibbuz.commons.json.parser.JsonParserException;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class ArrOpener extends AbstractArea {

    private static final Pattern validator = Pattern.compile("[ \n\t]*\\[[ \n\t]*\"", Pattern.MULTILINE);
    @Override
    public void validate(String area) throws JsonParserException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int findCheckpoint(String area) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonArea traverse() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
