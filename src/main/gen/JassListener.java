// Generated from E:/work/wc3libs/src/main/antlr\Jass.g4 by ANTLR 4.7

	package net.moonlightflower.wc3libs.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JassParser}.
 */
public interface JassListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JassParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(JassParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(JassParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#globalsBlock}.
	 * @param ctx the parse tree
	 */
	void enterGlobalsBlock(JassParser.GlobalsBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#globalsBlock}.
	 * @param ctx the parse tree
	 */
	void exitGlobalsBlock(JassParser.GlobalsBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#varName}.
	 * @param ctx the parse tree
	 */
	void enterVarName(JassParser.VarNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#varName}.
	 * @param ctx the parse tree
	 */
	void exitVarName(JassParser.VarNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#funcName}.
	 * @param ctx the parse tree
	 */
	void enterFuncName(JassParser.FuncNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#funcName}.
	 * @param ctx the parse tree
	 */
	void exitFuncName(JassParser.FuncNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#globalDec}.
	 * @param ctx the parse tree
	 */
	void enterGlobalDec(JassParser.GlobalDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#globalDec}.
	 * @param ctx the parse tree
	 */
	void exitGlobalDec(JassParser.GlobalDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#surroundedExpr}.
	 * @param ctx the parse tree
	 */
	void enterSurroundedExpr(JassParser.SurroundedExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#surroundedExpr}.
	 * @param ctx the parse tree
	 */
	void exitSurroundedExpr(JassParser.SurroundedExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(JassParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(JassParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#funcExpr}.
	 * @param ctx the parse tree
	 */
	void enterFuncExpr(JassParser.FuncExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#funcExpr}.
	 * @param ctx the parse tree
	 */
	void exitFuncExpr(JassParser.FuncExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#arg_list}.
	 * @param ctx the parse tree
	 */
	void enterArg_list(JassParser.Arg_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#arg_list}.
	 * @param ctx the parse tree
	 */
	void exitArg_list(JassParser.Arg_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#arrayRead}.
	 * @param ctx the parse tree
	 */
	void enterArrayRead(JassParser.ArrayReadContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#arrayRead}.
	 * @param ctx the parse tree
	 */
	void exitArrayRead(JassParser.ArrayReadContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#funcRef}.
	 * @param ctx the parse tree
	 */
	void enterFuncRef(JassParser.FuncRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#funcRef}.
	 * @param ctx the parse tree
	 */
	void exitFuncRef(JassParser.FuncRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(JassParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(JassParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#localVarDec}.
	 * @param ctx the parse tree
	 */
	void enterLocalVarDec(JassParser.LocalVarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#localVarDec}.
	 * @param ctx the parse tree
	 */
	void exitLocalVarDec(JassParser.LocalVarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#localVarDec_list}.
	 * @param ctx the parse tree
	 */
	void enterLocalVarDec_list(JassParser.LocalVarDec_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#localVarDec_list}.
	 * @param ctx the parse tree
	 */
	void exitLocalVarDec_list(JassParser.LocalVarDec_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#statement2}.
	 * @param ctx the parse tree
	 */
	void enterStatement2(JassParser.Statement2Context ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#statement2}.
	 * @param ctx the parse tree
	 */
	void exitStatement2(JassParser.Statement2Context ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(JassParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(JassParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#statement_list}.
	 * @param ctx the parse tree
	 */
	void enterStatement_list(JassParser.Statement_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#statement_list}.
	 * @param ctx the parse tree
	 */
	void exitStatement_list(JassParser.Statement_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#callFunc}.
	 * @param ctx the parse tree
	 */
	void enterCallFunc(JassParser.CallFuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#callFunc}.
	 * @param ctx the parse tree
	 */
	void exitCallFunc(JassParser.CallFuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#setVar}.
	 * @param ctx the parse tree
	 */
	void enterSetVar(JassParser.SetVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#setVar}.
	 * @param ctx the parse tree
	 */
	void exitSetVar(JassParser.SetVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(JassParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(JassParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#selection2}.
	 * @param ctx the parse tree
	 */
	void enterSelection2(JassParser.Selection2Context ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#selection2}.
	 * @param ctx the parse tree
	 */
	void exitSelection2(JassParser.Selection2Context ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#selection}.
	 * @param ctx the parse tree
	 */
	void enterSelection(JassParser.SelectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#selection}.
	 * @param ctx the parse tree
	 */
	void exitSelection(JassParser.SelectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(JassParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(JassParser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#exitwhen}.
	 * @param ctx the parse tree
	 */
	void enterExitwhen(JassParser.ExitwhenContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#exitwhen}.
	 * @param ctx the parse tree
	 */
	void exitExitwhen(JassParser.ExitwhenContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#loopBody}.
	 * @param ctx the parse tree
	 */
	void enterLoopBody(JassParser.LoopBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#loopBody}.
	 * @param ctx the parse tree
	 */
	void exitLoopBody(JassParser.LoopBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#loopBodyLine}.
	 * @param ctx the parse tree
	 */
	void enterLoopBodyLine(JassParser.LoopBodyLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#loopBodyLine}.
	 * @param ctx the parse tree
	 */
	void exitLoopBodyLine(JassParser.LoopBodyLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#rule_return}.
	 * @param ctx the parse tree
	 */
	void enterRule_return(JassParser.Rule_returnContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#rule_return}.
	 * @param ctx the parse tree
	 */
	void exitRule_return(JassParser.Rule_returnContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(JassParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(JassParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#funcDec}.
	 * @param ctx the parse tree
	 */
	void enterFuncDec(JassParser.FuncDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#funcDec}.
	 * @param ctx the parse tree
	 */
	void exitFuncDec(JassParser.FuncDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(JassParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(JassParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#funcReturnType}.
	 * @param ctx the parse tree
	 */
	void enterFuncReturnType(JassParser.FuncReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#funcReturnType}.
	 * @param ctx the parse tree
	 */
	void exitFuncReturnType(JassParser.FuncReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#funcParam_list}.
	 * @param ctx the parse tree
	 */
	void enterFuncParam_list(JassParser.FuncParam_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#funcParam_list}.
	 * @param ctx the parse tree
	 */
	void exitFuncParam_list(JassParser.FuncParam_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#funcParam}.
	 * @param ctx the parse tree
	 */
	void enterFuncParam(JassParser.FuncParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#funcParam}.
	 * @param ctx the parse tree
	 */
	void exitFuncParam(JassParser.FuncParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#funcBody}.
	 * @param ctx the parse tree
	 */
	void enterFuncBody(JassParser.FuncBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#funcBody}.
	 * @param ctx the parse tree
	 */
	void exitFuncBody(JassParser.FuncBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#typeDec}.
	 * @param ctx the parse tree
	 */
	void enterTypeDec(JassParser.TypeDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#typeDec}.
	 * @param ctx the parse tree
	 */
	void exitTypeDec(JassParser.TypeDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link JassParser#nativeDec}.
	 * @param ctx the parse tree
	 */
	void enterNativeDec(JassParser.NativeDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link JassParser#nativeDec}.
	 * @param ctx the parse tree
	 */
	void exitNativeDec(JassParser.NativeDecContext ctx);
}