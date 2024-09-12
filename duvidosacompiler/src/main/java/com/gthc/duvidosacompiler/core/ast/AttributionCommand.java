package com.gthc.duvidosacompiler.core.ast;

import com.gthc.duvidosacompiler.types.*;


public class AttributionCommand extends Command {
    private String varName;
    private String content;
    private Types type;


    public AttributionCommand() {
    }

    public AttributionCommand(Var var) {
        this.varName = var.getId();
        this.type = var.getType();
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Types getType() {
        return type;
    }

    public void setContent(Var var) {
        this.content = var.getId();
    }

    @Override
    public String generateTarget() {
        StringBuilder str = new StringBuilder();
        str.append(this.getVarName() + " = " + this.getContent() + ";\n");
        return str.toString();
    }

    @Override
    public String generateTargetCSharp() {
        StringBuilder str = new StringBuilder();
        str.append(this.getVarName() + " = " + this.getContent() + ";\n");
        return str.toString();
    }

    @Override
    public String generateTargetRust() {
        StringBuilder str = new StringBuilder();
        if (this.getType() == Types.numero_inte) {
            str.append(this.getVarName() + " = (" + rustTypeTreatment(this.getContent()) + ") as i32;\n");
        }
        else if (this.getType() == Types.numero_flut) {
            str.append(this.getVarName() + " = (" + rustTypeTreatment(this.getContent()) + ") as f32;\n");
        }
        else if (this.getType() == Types.seq_caracteres) {
            str.append(this.getVarName() + " = " + this.getContent() + ";\n");
        }
        return str.toString();
    }

    private String rustTypeTreatment(String expression) {
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
