// Generated from E:/work/wc3libs/src/main/antlr\Jass.g4 by ANTLR 4.7

	package net.moonlightflower.wc3libs.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JassParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JassVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JassParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(JassParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#globalsBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalsBlock(JassParser.GlobalsBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#varName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarName(JassParser.VarNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#funcName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncName(JassParser.FuncNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#globalDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalDec(JassParser.GlobalDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#surroundedExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSurroundedExpr(JassParser.SurroundedExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(JassParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#funcExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncExpr(JassParser.FuncExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#arg_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg_list(JassParser.Arg_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#arrayRead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayRead(JassParser.ArrayReadContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#funcRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncRef(JassParser.FuncRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(JassParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#localVarDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVarDec(JassParser.LocalVarDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#localVarDec_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVarDec_list(JassParser.LocalVarDec_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#statement2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement2(JassParser.Statement2Context ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(JassParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#statement_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement_list(JassParser.Statement_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#callFunc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallFunc(JassParser.CallFuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#setVar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetVar(JassParser.SetVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(JassParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#selection2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelection2(JassParser.Selection2Context ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#selection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelection(JassParser.SelectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(JassParser.LoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#exitwhen}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExitwhen(JassParser.ExitwhenContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#loopBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopBody(JassParser.LoopBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#loopBodyLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopBodyLine(JassParser.LoopBodyLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#rule_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRule_return(JassParser.Rule_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(JassParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#funcDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDec(JassParser.FuncDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#func}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc(JassParser.FuncContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#funcReturnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncReturnType(JassParser.FuncReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#funcParam_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncParam_list(JassParser.FuncParam_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#funcParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncParam(JassParser.FuncParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#funcBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncBody(JassParser.FuncBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#typeDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDec(JassParser.TypeDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link JassParser#nativeDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNativeDec(JassParser.NativeDecContext ctx);
}