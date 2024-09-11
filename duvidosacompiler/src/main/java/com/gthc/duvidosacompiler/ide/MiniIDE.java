package com.gthc.duvidosacompiler.ide;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;

public class MiniIDE extends Application {

    // Definir as palavras reservadas da sua linguagem
    private static final String[] KEYWORDS = new String[] {
            "inicioprog", "fimprog", "declare", "numero_inte", "numero_flut", "seq_caracteres",
            "se", "entao", "senao", "es", "enquanto", "execute", "pronto"
    };

    // Padrão de regex para identificar as palavras reservadas
    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    private static final Pattern PATTERN = Pattern.compile(KEYWORD_PATTERN);

    @Override
    public void start(Stage primaryStage) {
        // Cria a área de código e adiciona números de linha
        CodeArea codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        // Aplicar o highlight quando o texto mudar
        codeArea.textProperty().addListener((obs, oldText, newText) -> applyHighlighting(codeArea, newText));

        // Configurar a cena com a área de código
        Scene scene = new Scene(codeArea, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/highlight.css").toExternalForm()); // Carregar o CSS

        // Configurar o stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mini IDE com Highlight");
        primaryStage.show();
    }

    // Método para aplicar o highlight nas palavras reservadas
    private void applyHighlighting(CodeArea codeArea, String text) {
        codeArea.clearStyle(0, text.length());
        Matcher matcher = PATTERN.matcher(text);

        while (matcher.find()) {
            codeArea.setStyle(matcher.start(), matcher.end(), Collections.singletonList("keyword"));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
