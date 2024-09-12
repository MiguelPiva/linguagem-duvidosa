package com.gthc.duvidosacompiler.apicompiler;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.gthc.duvidosacompiler.core.DuvidosaLexer;
import com.gthc.duvidosacompiler.core.DuvidosaParser;
import com.gthc.duvidosacompiler.core.ast.Program;
import com.gthc.duvidosacompiler.core.exceptions.DuvidosaSemanticException;

public class CAPI {
    public String compile(String duvidosaText) throws DuvidosaSemanticException {
        DuvidosaLexer lexer;
        DuvidosaParser parser;

        // Crio um analisador léxico a partir da leitura de uma arquivo de entrada
        lexer = new DuvidosaLexer(CharStreams.fromString(duvidosaText));

        // A partir do analisador léxico, crio um fluxo de tokens
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        // Crio o parser a partir do fluxo de tokens
        parser = new DuvidosaParser(tokenStream);

        // Utilizando as funções
        parser.programa();

        Program program = parser.getProgram();
        StringBuilder sb = new StringBuilder();
        sb.append(program.generateTargetC());
        return sb.toString();
    }
}
