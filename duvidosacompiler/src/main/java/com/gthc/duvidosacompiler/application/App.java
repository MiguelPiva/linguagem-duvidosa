package com.gthc.duvidosacompiler.application;

import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStreams;

import com.gthc.duvidosacompiler.core.DuvidosaLexer;
import com.gthc.duvidosacompiler.core.DuvidosaParser;
import com.gthc.duvidosacompiler.core.ast.Program;


public class App {
    public static void compile() {
        try {
            DuvidosaLexer lexer;
            DuvidosaParser parser;
            String path = "/home/arizona/Desktop/Project/linguagem-duvidosa/duvidosacompiler/src/main/java/com/gthc/duvidosacompiler/application/"; // Colocar o caminho do arquivo de entrada aqui
            String inputFile = "input.in";

            // Crio um analisador léxico a partir da leitura de um arquivo de entrada
            lexer = new DuvidosaLexer(CharStreams.fromFileName(path + inputFile));

            // A partir do analisador léxico, crio um fluxo de tokens
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            // Crio o parser a partir do fluxo de tokens
            parser = new DuvidosaParser(tokenStream);


            System.out.println("Iniciando compilador de linguagem Duvidosa");
            parser.programa();
            parser.showAllVars();
            System.out.println("Finalizando compilador de linguagem Duvidosa");

            Program program = parser.getProgram();
            try {
                File f = new File(program.getName() + ".java");
                FileWriter fr = new FileWriter(f);
                PrintWriter pr = new PrintWriter(fr);
                pr.println(program.generateTarget());
                pr.close();
            } 
            catch (IOException e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }

        }
        catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
