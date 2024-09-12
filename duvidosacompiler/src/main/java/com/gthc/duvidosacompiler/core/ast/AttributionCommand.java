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
}
