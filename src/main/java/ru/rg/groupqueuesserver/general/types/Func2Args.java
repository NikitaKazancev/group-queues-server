package ru.rg.groupqueuesserver.general.types;

public interface Func2Args<A1, A2, R> {
    R apply(A1 arg1, A2 arg2);
}
