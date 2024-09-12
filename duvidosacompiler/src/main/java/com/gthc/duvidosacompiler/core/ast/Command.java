package com.gthc.duvidosacompiler.core.ast;

public abstract class Command {
    public abstract String generateTarget();
    public abstract String generateTargetCSharp();
    public abstract String generateTargetRust();
}
