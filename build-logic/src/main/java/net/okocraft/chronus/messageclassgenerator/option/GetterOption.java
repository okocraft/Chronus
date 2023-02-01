package net.okocraft.chronus.messageclassgenerator.option;

public class GetterOption {

    public Type getterType;

    public String methodName;

    public String fieldName;

    public enum Type {
        GETTER_METHOD,
        STATIC_CONSTANT,
        NONE
    }
}
