package org.something.lox;

public class AstPrinter implements Expr.Visitor<String> {

    public static void main(String[] args) {
        Expr expr = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(50)),
                new Token(TokenType.ASTERISK, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Literal(23.133)));
        System.out.println(new AstPrinter().print(expr));
    }
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitSequenceExpr(Expr.Sequence expr) {
        return "";
    }

    @Override
    public String visitAssignExpr(Expr.Assign expr) {
        return "";
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitLogicalExpr(Expr.Logical expr) {
        return "";
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        return "";
    }

    @Override
    public String visitCallExpr(Expr.Call expr) {
        return "";
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }
}
