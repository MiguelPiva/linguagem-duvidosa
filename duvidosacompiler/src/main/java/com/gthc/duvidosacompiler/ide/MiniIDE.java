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

    private static final String[] KEYWORDS = new String[] {
            "inicioprog", "fimprog", "declare", "numero_inte", "numero_flut", "seq_caracteres","booleano",
            "se", "entao", "senao", "es", "enquanto", "execute", "pronto", "leia", "escreva"
    };

    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";

    private static final String STRING_PATTERN = "\"([^\"]*)\"";

    private static final String NUMBER_PATTERN = "\\b\\d+(\\.\\d+)?\\b";

    private static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<NUMBER>" + NUMBER_PATTERN + ")"
    );

    @Override
    public void start(Stage primaryStage) {
        CodeArea codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        codeArea.textProperty().addListener((obs, oldText, newText) -> applyHighlighting(codeArea, newText));

        Scene scene = new Scene(codeArea, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/highlight.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mini IDE com Highlight");
        primaryStage.show();
    }

    private void applyHighlighting(CodeArea codeArea, String text) {
        codeArea.clearStyle(0, text.length());
        Matcher matcher = PATTERN.matcher(text);

        while (matcher.find()) {
            if (matcher.group("KEYWORD") != null) {
                codeArea.setStyle(matcher.start(), matcher.end(), Collections.singletonList("keyword"));
            } else if (matcher.group("STRING") != null) {
                codeArea.setStyle(matcher.start(), matcher.end(), Collections.singletonList("string"));
            } else if (matcher.group("NUMBER") != null) {
                codeArea.setStyle(matcher.start(), matcher.end(), Collections.singletonList("number"));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
