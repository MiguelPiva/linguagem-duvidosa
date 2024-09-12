grammar Duvidosa;

@header {
    import java.util.ArrayList;
    import java.util.Stack;
    import java.util.HashMap;
    import com.gthc.duvidosacompiler.types.*;
    import com.gthc.duvidosacompiler.core.exceptions.*;
    import com.gthc.duvidosacompiler.core.ast.*;
}


@members {
    private ArrayList<Var> varList = new ArrayList<Var>();
    private HashMap<String, Var> symbolTable = new HashMap<String, Var>();
    private ArrayList<Var> currentDeclaration = new ArrayList<Var>();
    private Types currentType;
    private Types leftType = null, rightType = null;
    private Program program = new Program();
    private Stack<ArrayList<Command>> stack = new Stack<ArrayList<Command>>();
    private String strExpr = "";
    private IfCommand currentIfCommand;
    private WhileCommand currentWhileCommand;


    public void updateType() {
        for (Var var : currentDeclaration) {
            var.setType(currentType);
            varList.add(var);
        }
    }

    public void showAllVars() {
        for (Var var : varList) {
            System.out.println(var);
        }
    }

    public void saveVar(Var var) {
        varList.add(var);
        symbolTable.put(var.getId(), var);
     }

    public boolean isDeclared(String id) {
        return symbolTable.get(id) != null;
    }

    public Program getProgram() {
        return program;
    }
}


programa    : 'inicioprog' 
                ID { 
                    program.setName(_input.LT(-1).getText()); 
                    stack.push(new ArrayList<Command>());
                    }
                instrucoes* 
                'fimprog' 
                { 
                    program.setSymbolTable(symbolTable);
                    program.setCommandList(stack.pop()); 
                }
            ;


instrucoes  : ( declaravar | espr | comando )
            ;


declaravar  : 'declare' { currentDeclaration.clear(); }
            ( 'numero_inte' { currentType = Types.numero_inte; }
            | 'numero_flut' { currentType = Types.numero_flut; }
            | 'seq_caracteres' { currentType = Types.seq_caracteres; })
            DOISP
            ID { saveVar(new Var(_input.LT(-1).getText(), currentType)); }
            ( VIRG ID 
                { saveVar(new Var(_input.LT(-1).getText(), currentType)); }
            )*
            PVIRG
            ;


espr        : termo { strExpr += _input.LT(-1).getText(); } esprl
            ;


termo       : ID {
                if (!isDeclared(_input.LT(-1).getText())) {
                    throw new DuvidosaSemanticException("Variável não declarada: " + _input.LT(-1).getText());
                }
                Var var = symbolTable.get(_input.LT(-1).getText());
                var.markUsed(); // Marca a variável como usada
                if (!var.isInitialized()) {
                    throw new DuvidosaSemanticException("Variável não inicializada: " + _input.LT(-1).getText());
                }
                if (rightType == null) {
                    rightType = var.getType();
                } else {
                    if (var.getType().getValue() > rightType.getValue()) {
                        rightType = var.getType();
                    }
                }
            }
            | NUM { if (rightType == null) {
                        rightType = Types.numero_inte;
                    } else {
                        if (rightType.getValue() < Types.numero_inte.getValue()) {
                            rightType = Types.numero_inte;
                        }
                    }
                }
            | TEXTO { if (rightType == null) {
                        rightType = Types.seq_caracteres;
                    } else {
                        if (rightType.getValue() < Types.seq_caracteres.getValue()) {
                            rightType = Types.seq_caracteres;
                        }
                    }
                }
            ;


esprl       : ( OP { strExpr += _input.LT(-1).getText(); } 
              termo { strExpr += _input.LT(-1).getText(); } 
              )*
            ;


comando     : cmdAtribu
            | cmdLeia
            | cmdEscreva
            | cmdSe
            | cmdEnquanto
            ;


cmdAtribu   : ID {
                if (!isDeclared(_input.LT(-1).getText())) {
                    throw new DuvidosaSemanticException("Variável não declarada: " + _input.LT(-1).getText());
                }
                Var var = symbolTable.get(_input.LT(-1).getText());
                var.setInitialized(true);
                var.markUsed(); // Marca a variável como usada
                leftType = var.getType();
                AttributionCommand cmdAttrib = new AttributionCommand(var);
                strExpr = "";
              }
              OP_ATRIB
              espr {
                cmdAttrib.setContent(strExpr);
              }
              PVIRG {
                if (leftType.getValue() < rightType.getValue()) {
                    throw new DuvidosaSemanticException("Tipos incompatíveis: " + leftType + " e " + rightType);
                }
                stack.peek().add(cmdAttrib);
              }
            ;
            

cmdLeia     : 'leia'
                AB_PAREN
                ID {
                    if (!isDeclared(_input.LT(-1).getText())) {
                        throw new DuvidosaSemanticException("Variável não declarada: " + _input.LT(-1).getText());
                    }
                    Var var = symbolTable.get(_input.LT(-1).getText());
                    var.setInitialized(true);
                    var.markUsed(); // Marca a variável como usada
                    Command cmdRead = new ReadCommand(var);
                    stack.peek().add(cmdRead);
                }
                FE_PAREN
                PVIRG
            ;


cmdEscreva  : 'escreva'
                AB_PAREN { strExpr = ""; }
                ( termo { strExpr += _input.LT(-1).getText();} | espr ) 
                FE_PAREN 
                    {
                    Command cmdWrite = new WriteCommand(strExpr);
                    stack.peek().add(cmdWrite); 
                    } 
                PVIRG 
                { rightType = null; }
            ;


cmdSe       : 'se' { 
                    stack.push(new ArrayList<Command>()); 
                    strExpr = "";
                    currentIfCommand = new IfCommand();
                    }
                AB_PAREN 
                espr 
                OP_REL { strExpr += _input.LT(-1).getText(); }
                espr 
                FE_PAREN { currentIfCommand.setExpression(strExpr); }
                'entao' 
                comando+ { currentIfCommand.setTrueList(stack.pop()); }
                ('senao' { stack.push(new ArrayList<Command>()); }
                 comando+ { currentIfCommand.setFalseList(stack.pop()); } 
                 )? 
                'es' { stack.peek().add(currentIfCommand); }
            ;


cmdEnquanto : 'enquanto' { 
                    stack.push(new ArrayList<Command>()); 
                    strExpr = "";
                    currentWhileCommand = new WhileCommand();
                    }
                AB_PAREN
                espr
                OP_REL { strExpr += _input.LT(-1).getText(); }
                espr
                FE_PAREN { currentWhileCommand.setExpression(strExpr); }
                'execute'
                comando+ { currentWhileCommand.setCommandList(stack.pop()); }
                'pronto' { stack.peek().add(currentWhileCommand); }
            ;


OP          : '+' | '-' | '*' | '/'
            ;


OP_SOMA     : '+'
            ;


OP_SUB      : '-'
            ;


OP_MULT     : '*'
            ;


OP_DIV      : '/'
            ;


OP_ATRIB    : ':='
            ;


OP_REL      : '>' | '<' | '>=' | '<=' | '==' | '!='
            ;


ID          : [a-z] ( [a-z] | [A-Z] | [0-9] )*
            ;


NUM         : [0-9]+ ('.' [0-9]+ )?
            ;


VIRG        : ','
            ;


PVIRG       : ';'
            ;


DOISP       : ':'
            ;


ASPAS       : '"'
            ;


AB_PAREN    : '('
            ;


FE_PAREN    : ')'
            ;


TEXTO       : '"' ( [a-z] | [A-Z] | [0-9] | ',' | '.' | ' ' | '-' | '!' | '"' | '+' | '/' | '*' )* '"'
            ;


EB          : ( ' ' | '\n' | '\r' | '\t' ) -> skip
            ;
