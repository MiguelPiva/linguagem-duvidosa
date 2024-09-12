package com.gthc.duvidosacompiler.types;

public class Var {
    private String id;
    private Types type;
    private boolean initialized;
    private boolean used;

    public Var(String id, Types type) {
        this.id = id;
        this.type = type;
        this.initialized = false;
        this.used = false;
    }

    public String getId() {
        return id;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public boolean isUsed() {
        return used;
    }

    public void markUsed() {
        this.used = true;
    }

    @Override
    public String toString() {
        return "Var{id='" + id + "', type=" + type + ", initialized=" + initialized + ", used=" + used + '}';
    }
}
