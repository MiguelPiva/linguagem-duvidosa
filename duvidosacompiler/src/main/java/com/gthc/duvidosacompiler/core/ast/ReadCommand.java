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
        }
        else if (var.getType() == Types.numero_flut) {
            return var.getId() + " = _scTrx.nextDouble();\n";
        }
        else if (var.getType() == Types.seq_caracteres) {
            return var.getId() + " = _scTrx.nextLine();\n";
        }
        else if (var.getType() == Types.booleano) {
            return var.getId() + " = _scTrx.nextBoolean();\n";
        }
        else {
            return "";
        }
    }
}
