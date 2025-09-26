package com.moroz.__field_mask.entity;

public final class FieldMask {
    public boolean id;
    public boolean name;
    public boolean entityType;
    public boolean balance;
    public boolean active;

    public FieldMask(boolean id, boolean name, boolean entityType, boolean balance, boolean active) {
        this.id = id;
        this.name = name;
        this.entityType = entityType;
        this.balance = balance;
        this.active = active;
    }
}
