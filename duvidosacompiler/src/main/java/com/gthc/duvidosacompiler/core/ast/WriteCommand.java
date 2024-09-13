package com.gthc.duvidosacompiler.core.ast;

import java.util.HashMap;

import com.gthc.duvidosacompiler.types.Types;
import com.gthc.duvidosacompiler.types.Var;


public class WriteCommand extends Command {
    private String content;
    private HashMap<String, Var> symbolTable;

    public WriteCommand() {
    }

    public WriteCommand(String content, HashMap<String, Var> symbolTable) {
        this.content = content;
        this.symbolTable = symbolTable;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String generateTarget() {
        return "System.out.println(" + content + ");\n";
    }

    @Override
    public String generateTargetCSharp() {
        return "Console.WriteLine(" + content + ");\n";
    }

    @Override
    public String generateTargetC() {
        return "printf(" + cTypeTreatment(content) + content + ");\nprintf(\"\\n\");\n";
    }

    public String cTypeTreatment(String content) {
        if (content.startsWith("\"") && content.endsWith("\"")) {
            return "\"%s\", ";
        }
        for (String varId : symbolTable.keySet()) {
            Var var = symbolTable.get(varId);
            if (content.equals(var.getId())) {
                if (var.getType() == Types.numero_inte) {
                    return "\"%d\", ";
                } else if (var.getType() == Types.numero_flut) {
                    return "\"%f\", ";
                } else if (var.getType() == Types.seq_caracteres) {
                    return "\"%s\", ";
                } else if (var.getType() == Types.booleano) {
                    return "\"%d\", ";
                }
            }
        }
        return "\"%s\", ";
    }

    @Override
    public String generateTargetRust() {
        return "println!(\"{}\"," + rustTypeTreatment(content) + ");\n";
    }

    private String rustTypeTreatment(String expression) {
        if (expression.startsWith("\"") && expression.endsWith("\"")) {
            return expression;
        }
        boolean containsOnlyLetters = expression.matches("[a-zA-Z\\s]+");
        if (containsOnlyLetters) {
            return expression;
        }
        boolean containsOnlyLettersAndLogicalOperators = expression.matches("[a-zA-Z\\s]*(\\|\\|\\s*[a-zA-Z\\s]*)+(&(\\s*[a-zA-Z\\s]*)+)?");
        if (containsOnlyLettersAndLogicalOperators) {
            return expression;
        }
        boolean containsFloat = expression.matches(".*\\d+\\.\\d+.*") || expression.matches(".*[a-zA-Z].*");
        if (!containsFloat) {
            return expression;
        }
        expression = expression.replaceAll("([+\\-*/()])", " $1 ");
        String[] tokens = expression.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String token : tokens) {
            if (rustIsNumeric(token)) {
                if (token.matches("\\d+")) {
                    result.append(token).append(".0 ");
                } else {
                    result.append(token).append(" ");
                }
            } else if (rustIsOperator(token)) {
                result.append(token).append(" ");
            } else if (!token.isEmpty()) {
                result.append("(").append(token).append(" as f32) ");
            }
        }
        return result.toString().trim();
    }

    private static boolean rustIsNumeric(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }

    private static boolean rustIsOperator(String str) {
        return str.matches("[+\\-*/()]");
    }
}