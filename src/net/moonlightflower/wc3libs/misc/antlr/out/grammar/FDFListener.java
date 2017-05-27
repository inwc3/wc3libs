// Generated from grammar\FDF.g4 by ANTLR 4.5.3

	package net.moonlightflower.wc3libs.misc.antlr.out.grammar;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FDFParser}.
 */
public interface FDFListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FDFParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(FDFParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link FDFParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(FDFParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link FDFParser#stringList}.
	 * @param ctx the parse tree
	 */
	void enterStringList(FDFParser.StringListContext ctx);
	/**
	 * Exit a parse tree produced by {@link FDFParser#stringList}.
	 * @param ctx the parse tree
	 */
	void exitStringList(FDFParser.StringListContext ctx);
	/**
	 * Enter a parse tree produced by {@link FDFParser#stringListContent}.
	 * @param ctx the parse tree
	 */
	void enterStringListContent(FDFParser.StringListContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link FDFParser#stringListContent}.
	 * @param ctx the parse tree
	 */
	void exitStringListContent(FDFParser.StringListContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link FDFParser#stringAssign}.
	 * @param ctx the parse tree
	 */
	void enterStringAssign(FDFParser.StringAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link FDFParser#stringAssign}.
	 * @param ctx the parse tree
	 */
	void exitStringAssign(FDFParser.StringAssignContext ctx);
}