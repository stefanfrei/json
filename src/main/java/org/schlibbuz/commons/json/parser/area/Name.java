/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.commons.json.parser.area;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.schlibbuz.commons.json.parser.JsonParserException;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public class Name extends AbstractArea {

    private static final Pattern V = Pattern.compile("\\\".*?\\\"[ \\n\\t]*:[ \\n\\t]*[tf0-9\\{\\[\\\"]", Pattern.CASE_INSENSITIVE);
    private char traverseIndicator;


    @Override
    public void validate(String area) throws JsonParserException {
        Matcher m = V.matcher(area);
        if (!m.find()) throw new JsonParserException("omg");
        String match = m.group();
        traverseIndicator = match.charAt(match.length()-1);
    }

    @Override
    public int findCheckpoint(String area) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JsonArea traverse() throws JsonParserException {
        if (traverseIndicator == '{') return new ObjOpener();
        if (traverseIndicator == '[') return new ArrOpener();
        if (traverseIndicator == '"') return new Literal();
        if (traverseIndicator == 't' || traverseIndicator == 'f') return new Bool();
        try {
            Integer.parseInt(String.valueOf(traverseIndicator));
            return new Numeric();
        } catch(NumberFormatException e) {
            throw new JsonParserException("fuck");
        }

    }

}
