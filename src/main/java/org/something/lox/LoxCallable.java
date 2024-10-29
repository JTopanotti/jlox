package org.something.lox;

import java.util.List;

interface LoxCallable {
    Object call(Interpreter interpreter, List<Object> args);
    int arity();
}
