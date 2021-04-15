/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.schlibbuz.commons.json.parser;

import java.util.regex.Pattern;

/**
 *
 * @author Stefan Frei <stefan.a.frei@gmail.com>
 */
public enum AreaType {
    OBJ_LEFT(Pattern.compile("\\s*\\{\\s*")),
    NAME(Pattern.compile("\"(.*?(?<!\\\\))\"")),
    COLON(Pattern.compile("\\s*:\\s*")),
    COMMA(Pattern.compile("\\s*,\\s*")),
    BOOL(Pattern.compile("(?:true|false)")),
    STRING(Pattern.compile("\"(.*?(?<!\\\\))\"")),
    INTEGER(Pattern.compile("[-]?[\\d]{1,19}")),
    DOUBLE(Pattern.compile("[-]?[\\d\\.]{3,19}")),
    EXP(Pattern.compile("[-]?[\\d\\.eE]{3,19}")),
    ARR_LEFT(Pattern.compile("\\s*\\[\\s*")),
    ARR_RIGHT(Pattern.compile("\\s*\\]\\s*")),
    OBJ_RIGHT(Pattern.compile("\\s*\\}\\s*"));

    private final Pattern P;

    AreaType(Pattern p) {
        this.P = p;
    }

    Pattern getPattern() {
        return this.P;
    }
}
