package io.compiler.core.ast;

public class WriteCommand extends Command {
    private String content;

    public WriteCommand() {
    }

    public WriteCommand(String content) {
        this.content = content;
    }

    @Override
    public String generateTarget() {
        return "System.out.println("+content+");\n";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
}
