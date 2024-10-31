package org.something.lox;

import java.util.List;

class LoxFunction implements LoxCallable {
    private final Stmt.Fun declaration;
    private final Environment closure;

    LoxFunction(Stmt.Fun declaration, Environment closure) {
        this.declaration = declaration;
        this.closure = closure;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> args) {
        Environment env = new Environment(closure);
        for (int i = 0; i < declaration.params.size(); i++) {
            env.define(declaration.params.get(i).lexeme, args.get(i));
        }

        try {
            interpreter.executeBlock(declaration.body, env);
        } catch (Return r) {
            return r.value;
        }
        return null;
    }

    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }
}
