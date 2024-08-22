package io.compiler.types;

public enum Types {
    numero_inte(1),
    numero_flut(2),
    seq_caracteres(3);

    private Integer value;

    private Types (Integer valueNumber) {
        this.value = valueNumber;
    }

    public Integer getValue() {
        return this.value;
    }
}
