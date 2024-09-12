package com.gthc.duvidosacompiler.core.ast;

public class WriteCommand extends Command {
    private String id;
    private String content;

    public WriteCommand() {
    }

    public WriteCommand(String content) {
        this.content = content;
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
        return "printf(" + content + ");\n";
    }

    @Override
    public String generateTargetRust() {
        return "println!(\"{}\"," + rustTypeTreatment(content) + ");\n";
    }

    private String rustTypeTreatment(String expression) {
        if (expression.startsWith("\"") && expression.endsWith("\"")) {
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