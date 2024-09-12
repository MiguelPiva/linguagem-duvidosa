package com.gthc.duvidosacompiler.core.ast;

import java.util.HashMap;
import java.util.List;

import com.gthc.duvidosacompiler.types.Types;
import com.gthc.duvidosacompiler.types.Var;

public class Program {
    private String name;
    private HashMap<String, Var> symbolTable;  // Tabela de símbolos
    private List<Command> commandList;         // Lista de comandos

    // Getters e setters
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
        StringBuilder warnings = new StringBuilder();
        str.append("import java.util.Scanner;\n\n");
        str.append("public class " + name + " {\n");
        str.append("\tpublic static void main (String[] args[]) {\n");
        str.append("\t\tScanner _scTrx = new Scanner(System.in);\n");

        // Declaração de variáveis
        for (String varId : symbolTable.keySet()) {
            Var var = symbolTable.get(varId);
            if (var.getType() == Types.numero_inte) {
                str.append("\t\tInteger ");
            } else if (var.getType() == Types.numero_flut) {
                str.append("\t\tDouble ");
            } else if (var.getType() == Types.seq_caracteres) {
                str.append("\t\tString ");
            }
            str.append(var.getId() + ";\n");

            if (!var.isUsed()) {
                warnings.append("Aviso: A variável '").append(var.getId()).append("' foi declarada, mas não está sendo utilizada.\n");
            }
        }

        for (Command cmd : commandList) {
            str.append("\t\t" + cmd.generateTarget());
        }

        str.append("\t}\n");
        str.append("}");

        if (warnings.length() > 0) {
            str.append("\n// Warnings:\n").append(warnings.toString());
        }

        return str.toString();
    }
    
    public String generateTargetCSharp() {
        StringBuilder str = new StringBuilder();
        StringBuilder warnings = new StringBuilder();
        str.append("using System;\n\n");
        str.append("public class " + name + " {\n");
        str.append("\tpublic static void Main (string[] args) {\n");

        // Declaração de variáveis
        for (String varId : symbolTable.keySet()) {
            Var var = symbolTable.get(varId);
            if (var.getType() == Types.numero_inte) {
                str.append("\t\tint ");
            } else if (var.getType() == Types.numero_flut) {
                str.append("\t\tdouble ");
            } else if (var.getType() == Types.seq_caracteres) {
                str.append("\t\tstring ");
            }
            str.append(var.getId() + ";\n");

            if (!var.isUsed()) {
                warnings.append("Aviso: A variável '").append(var.getId()).append("' foi declarada, mas não está sendo utilizada.\n");
            }
        }

        for (Command cmd : commandList) {
            str.append("\t\t" + cmd.generateTargetCSharp());
        }

        str.append("\t}\n");
        str.append("}");

        if (warnings.length() > 0) {
            str.append("\n// Warnings:\n").append(warnings.toString());
        }

        return str.toString();
    }

}
