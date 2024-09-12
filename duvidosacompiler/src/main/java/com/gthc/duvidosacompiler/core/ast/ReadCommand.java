package com.gthc.duvidosacompiler.core.ast;

import com.gthc.duvidosacompiler.types.Types;
import com.gthc.duvidosacompiler.types.Var;

public class ReadCommand extends Command {
    public Var var;

    public ReadCommand() {
    }

    public ReadCommand(Var var) {
        this.var = var;
    }

    public Var getVar() {
        return var;
    }

    public void setVar(Var var) {
        this.var = var;
    }

    @Override
    public String generateTarget() {
        if (var.getType() == Types.numero_inte) {
            return var.getId() + " = _scTrx.nextInt();\n";
        } else if (var.getType() == Types.numero_flut) {
            return var.getId() + " = _scTrx.nextDouble();\n";
        } else if (var.getType() == Types.seq_caracteres) {
            return var.getId() + " = _scTrx.nextLine();\n";
        } else {
            return "";
        }
    }

    @Override
    public String generateTargetCSharp() {
        if (var.getType() == Types.numero_inte) {
            return var.getId() + " = Convert.ToInt32(Console.ReadLine());\n";
        } else if (var.getType() == Types.numero_flut) {
            return var.getId() + " = Convert.ToDouble(Console.ReadLine());\n";
        } else if (var.getType() == Types.seq_caracteres) {
            return var.getId() + " = Console.ReadLine();\n";
        } else {
            return "";
        }
    }

    @Override
    public String generateTargetRust() {
        StringBuilder str = new StringBuilder();
        str.append("io::stdin().read_line(&mut aux);\n");
        if (var.getType() == Types.numero_inte || var.getType() == Types.numero_flut) {
            str.append("\t" + var.getId() + " = aux.trim().parse().expect(\"Erro ao converter\");\n");
        } else if (var.getType() == Types.seq_caracteres) {
            str.append("\t" + var.getId() + " = aux.trim().to_string();\n");
        }
        str.append("\taux.clear();\n");
        return str.toString();
    }

    @Override
    public String generateTargetC() {
        if (var.getType() == Types.numero_inte) {
            return "scanf(\"%ld\", &" + var.getId() + " );\n";
        } else if (var.getType() == Types.numero_flut) {
            return "scanf(\"%lf\", &" + var.getId() + " );\n";
        } else if (var.getType() == Types.seq_caracteres) {
            return "scanf(\"%s\", " + var.getId() + " );\n";
        } else {
            return "";
        }
    }

}