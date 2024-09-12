package com.gthc.duvidosacompiler.core.ast;

import java.util.List;

public class WhileCommand extends Command {
    String expression;
    List<Command> commandList;

    public WhileCommand() {
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

    @Override
    public String generateTarget() {
        StringBuilder str = new StringBuilder();
        str.append("while (" + expression + ") {\n");
        for (Command cmd : commandList) {
            str.append("\t\t\t" + cmd.generateTarget());
        }
        str.append("\t\t}\n");
        return str.toString();
    }

    @Override
    public String generateTargetCSharp() {
        StringBuilder str = new StringBuilder();
        str.append("while (" + expression + ") {\n");
        for (Command cmd : commandList) {
            str.append("\t\t\t" + cmd.generateTargetCSharp());
        }
        str.append("\t\t}\n");
        return str.toString();
    }

    @Override
    public String generateTargetRust() {
        StringBuilder str = new StringBuilder();
        str.append("while " + expression + " {\n");
        for (Command cmd : commandList) {
            str.append("\t\t\t" + cmd.generateTargetRust());
        }
        str.append("\t\t}\n");
        return str.toString();
    }

    public String generateTargetC() {
        StringBuilder str = new StringBuilder();
        str.append("while (" + expression + ") {\n");
        for (Command cmd : commandList) {
            str.append("\t\t\t" + cmd.generateTargetC());
        }
        str.append("\t\t}\n");
        return str.toString();
    }
}
