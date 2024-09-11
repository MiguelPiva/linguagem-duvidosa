package com.gthc.duvidosacompiler.core.ast;

import java.util.HashMap;
import java.util.List;

import com.gthc.duvidosacompiler.types.Types;
import com.gthc.duvidosacompiler.types.Var;

public class Program {
    private String name;
    private HashMap<String, Var> symbolTable;
    private List<Command> commandList;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public HashMap<String, Var> getSymbolTable() {
        return symbolTable;
    }
    public void setSymbolTable(HashMap<String, Var> symbolTable) {
        this.symbolTable = symbolTable;
    }
    public List<Command> getCommandList() {
        return commandList;
    }
    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

    public String generateTarget() {
        StringBuilder str = new StringBuilder();
        str.append("import java.util.Scanner;\n\n");
        str.append("public class "+name+" {\n");
        str.append("\tpublic static void main (String[] args[]) {\n");
        str.append("\t\tScanner _scTrx = new Scanner(System.in);\n");
        for (String varId: symbolTable.keySet()) {
            Var var = symbolTable.get(varId);
            if (var.getType() == Types.numero_inte) {
                str.append("\t\tInteger ");
            }
            else if (var.getType() == Types.numero_flut) {
                str.append("\t\tDouble ");
            }
            else if (var.getType() == Types.seq_caracteres) {
                str.append("\t\tString ");
            }
            else if (var.getType() == Types.booleano) {
                str.append("\t\tBooleano ");
            }
            str.append(var.getId() + ";\n");
        }
        for (Command cmd : commandList) {
            str.append("\t\t" + cmd.generateTarget());
        }

        str.append("\t}\n");
        str.append("}");
        return str.toString();
    }
}
