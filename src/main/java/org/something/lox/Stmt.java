package org.something.lox;

import java.util.List;

abstract class Stmt{
    interface Visitor<R> {
        R visitBlockStmt(Block stmt);
        R visitExpressionStmt(Expression stmt);
        R visitClassStmt(Class stmt);
        R visitFunStmt(Fun stmt);
        R visitIfStmt(If stmt);
        R visitPrintStmt(Print stmt);
        R visitVarStmt(Var stmt);
        R visitWhileStmt(While stmt);
        R visitReturnStmt(Return stmt);
        R visitBreakStmt(Break stmt);
    }
    static class Block extends Stmt {
        Block (List<Stmt> statements) {
            this.statements = statements;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBlockStmt(this);
        }

        final List<Stmt> statements;
    }
    static class Expression extends Stmt {
        Expression (Expr expression) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitExpressionStmt(this);
        }

        final Expr expression;
    }
    static class Class extends Stmt {
        Class (Token name, Expr.Var superclass, List<Stmt.Fun> methods) {
            this.name = name;
            this.superclass = superclass;
            this.methods = methods;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitClassStmt(this);
        }

        final Token name;
        final Expr.Var superclass;
        final List<Stmt.Fun> methods;
    }
    static class Fun extends Stmt {
        Fun (Token name, Expr.Fun function) {
            this.name = name;
            this.function = function;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitFunStmt(this);
        }

        final Token name;
        final Expr.Fun function;
    }
    static class If extends Stmt {
        If (Expr condition, Stmt thenBranch, Stmt elseBranch) {
            this.condition = condition;
            this.thenBranch = thenBranch;
            this.elseBranch = elseBranch;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitIfStmt(this);
        }

        final Expr condition;
        final Stmt thenBranch;
        final Stmt elseBranch;
    }
    static class Print extends Stmt {
        Print (Expr expression) {
            this.expression = expression;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitPrintStmt(this);
        }

        final Expr expression;
    }
    static class Var extends Stmt {
        Var (Token name, Expr initializer) {
            this.name = name;
            this.initializer = initializer;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitVarStmt(this);
        }

        final Token name;
        final Expr initializer;
    }
    static class While extends Stmt {
        While (Expr condition, Stmt body) {
            this.condition = condition;
            this.body = body;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitWhileStmt(this);
        }

        final Expr condition;
        final Stmt body;
    }
    static class Return extends Stmt {
        Return (Token keyword, Expr value) {
            this.keyword = keyword;
            this.value = value;
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitReturnStmt(this);
        }

        final Token keyword;
        final Expr value;
    }
    static class Break extends Stmt {
        Break () {
        }

        @Override
        <R> R accept(Visitor<R> visitor) {
            return visitor.visitBreakStmt(this);
        }

    }

    abstract <R> R accept(Visitor<R> visitor);
}
