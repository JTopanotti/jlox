package org.something.lox;

import java.util.List;

class LoxFunction implements LoxCallable {
    private final String name;
    private final Expr.Fun declaration;
    private final Environment closure;
    private final boolean isInitializer;

    LoxFunction(String name, Expr.Fun declaration, Environment closure, boolean isInitializer) {
        this.name = name;
        this.declaration = declaration;
        this.closure = closure;
        this.isInitializer = isInitializer;
    }

    LoxFunction bind(LoxInstance instance) {
        Environment env = new Environment(closure);
        env.define("this", instance);
        return new LoxFunction(name, declaration, env, isInitializer);
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> args) {
        Environment env = new Environment(closure);
        for (int i = 0; i < declaration.parameters.size(); i++) {
            env.define(declaration.parameters.get(i).lexeme, args.get(i));
        }

        try {
            interpreter.executeBlock(declaration.body, env);
        } catch (Return r) {
            if (isInitializer) return closure.getAt(0, "this");

            return r.value;
        }

        if (isInitializer) return closure.getAt(0, "this");
        return null;
    }

    @Override
    public int arity() {
        return declaration.parameters.size();
    }

    @Override
    public String toString() {
        if (name == null) return "<fn>";
        return "<fn " + name + ">";
    }
}
