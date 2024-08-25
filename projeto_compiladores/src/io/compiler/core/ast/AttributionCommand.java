package io.compiler.core.ast;

public class AttributionCommand extends Command {
    private String varName;
    private String content;


    public AttributionCommand() {
    }

    public AttributionCommand(String varName) {
        this.varName = varName;
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

    @Override
    public String generateTarget() {
        StringBuilder str = new StringBuilder();
        str.append(this.getVarName() + " = " + this.getContent() + ";\n");
        return str.toString();
    }

}
