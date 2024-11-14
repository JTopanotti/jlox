package org.something.lox;

import java.util.List;
import java.util.Map;

public class LoxClass implements LoxCallable {
    final String name;
    private final Map<String, LoxFunction> methods;

    LoxClass(String name, Map<String, LoxFunction> methods) {
        this.name = name;
        this.methods = methods;
    }

    LoxFunction findMethod(String name) {
        if (methods.containsKey(name)) return methods.get(name);
        return null;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> args) {
        LoxInstance instance = new LoxInstance(this);
        LoxFunction init = findMethod("init");
        if (init != null) {
            init.bind(instance).call(interpreter, args);
        }
        return instance;
    }

    @Override
    public int arity() {
        LoxFunction init = findMethod("init");
        if (init == null) return 0;
        return init.arity();
    }

    @Override
    public String toString() {
        return name;
    }
}
