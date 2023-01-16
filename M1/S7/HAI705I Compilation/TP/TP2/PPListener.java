// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PPParser}.
 */
public interface PPListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PPParser#entry}.
	 * @param ctx the parse tree
	 */
	void enterEntry(PPParser.EntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#entry}.
	 * @param ctx the parse tree
	 */
	void exitEntry(PPParser.EntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#genRule}.
	 * @param ctx the parse tree
	 */
	void enterGenRule(PPParser.GenRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#genRule}.
	 * @param ctx the parse tree
	 */
	void exitGenRule(PPParser.GenRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#procFunExpr}.
	 * @param ctx the parse tree
	 */
	void enterProcFunExpr(PPParser.ProcFunExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#procFunExpr}.
	 * @param ctx the parse tree
	 */
	void exitProcFunExpr(PPParser.ProcFunExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#typeVar}.
	 * @param ctx the parse tree
	 */
	void enterTypeVar(PPParser.TypeVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#typeVar}.
	 * @param ctx the parse tree
	 */
	void exitTypeVar(PPParser.TypeVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#newArrayExpr}.
	 * @param ctx the parse tree
	 */
	void enterNewArrayExpr(PPParser.NewArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#newArrayExpr}.
	 * @param ctx the parse tree
	 */
	void exitNewArrayExpr(PPParser.NewArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#getArrayExpr}.
	 * @param ctx the parse tree
	 */
	void enterGetArrayExpr(PPParser.GetArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#getArrayExpr}.
	 * @param ctx the parse tree
	 */
	void exitGetArrayExpr(PPParser.GetArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#setArrayExpr}.
	 * @param ctx the parse tree
	 */
	void enterSetArrayExpr(PPParser.SetArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#setArrayExpr}.
	 * @param ctx the parse tree
	 */
	void exitSetArrayExpr(PPParser.SetArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#allocExpr}.
	 * @param ctx the parse tree
	 */
	void enterAllocExpr(PPParser.AllocExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#allocExpr}.
	 * @param ctx the parse tree
	 */
	void exitAllocExpr(PPParser.AllocExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(PPParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(PPParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#assignExpr}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(PPParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#assignExpr}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(PPParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#condExpr}.
	 * @param ctx the parse tree
	 */
	void enterCondExpr(PPParser.CondExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#condExpr}.
	 * @param ctx the parse tree
	 */
	void exitCondExpr(PPParser.CondExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#whileExpr}.
	 * @param ctx the parse tree
	 */
	void enterWhileExpr(PPParser.WhileExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#whileExpr}.
	 * @param ctx the parse tree
	 */
	void exitWhileExpr(PPParser.WhileExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#procExpr}.
	 * @param ctx the parse tree
	 */
	void enterProcExpr(PPParser.ProcExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#procExpr}.
	 * @param ctx the parse tree
	 */
	void exitProcExpr(PPParser.ProcExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#argExpr}.
	 * @param ctx the parse tree
	 */
	void enterArgExpr(PPParser.ArgExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#argExpr}.
	 * @param ctx the parse tree
	 */
	void exitArgExpr(PPParser.ArgExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#varName}.
	 * @param ctx the parse tree
	 */
	void enterVarName(PPParser.VarNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#varName}.
	 * @param ctx the parse tree
	 */
	void exitVarName(PPParser.VarNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(PPParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(PPParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#calle}.
	 * @param ctx the parse tree
	 */
	void enterCalle(PPParser.CalleContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#calle}.
	 * @param ctx the parse tree
	 */
	void exitCalle(PPParser.CalleContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#genInstr}.
	 * @param ctx the parse tree
	 */
	void enterGenInstr(PPParser.GenInstrContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#genInstr}.
	 * @param ctx the parse tree
	 */
	void exitGenInstr(PPParser.GenInstrContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#genExpr}.
	 * @param ctx the parse tree
	 */
	void enterGenExpr(PPParser.GenExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#genExpr}.
	 * @param ctx the parse tree
	 */
	void exitGenExpr(PPParser.GenExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppplus}.
	 * @param ctx the parse tree
	 */
	void enterPpplus(PPParser.PpplusContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppplus}.
	 * @param ctx the parse tree
	 */
	void exitPpplus(PPParser.PpplusContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppmin}.
	 * @param ctx the parse tree
	 */
	void enterPpmin(PPParser.PpminContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppmin}.
	 * @param ctx the parse tree
	 */
	void exitPpmin(PPParser.PpminContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppmul}.
	 * @param ctx the parse tree
	 */
	void enterPpmul(PPParser.PpmulContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppmul}.
	 * @param ctx the parse tree
	 */
	void exitPpmul(PPParser.PpmulContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppdiv}.
	 * @param ctx the parse tree
	 */
	void enterPpdiv(PPParser.PpdivContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppdiv}.
	 * @param ctx the parse tree
	 */
	void exitPpdiv(PPParser.PpdivContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppinf}.
	 * @param ctx the parse tree
	 */
	void enterPpinf(PPParser.PpinfContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppinf}.
	 * @param ctx the parse tree
	 */
	void exitPpinf(PPParser.PpinfContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppinfeg}.
	 * @param ctx the parse tree
	 */
	void enterPpinfeg(PPParser.PpinfegContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppinfeg}.
	 * @param ctx the parse tree
	 */
	void exitPpinfeg(PPParser.PpinfegContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppsup}.
	 * @param ctx the parse tree
	 */
	void enterPpsup(PPParser.PpsupContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppsup}.
	 * @param ctx the parse tree
	 */
	void exitPpsup(PPParser.PpsupContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppsupeg}.
	 * @param ctx the parse tree
	 */
	void enterPpsupeg(PPParser.PpsupegContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppsupeg}.
	 * @param ctx the parse tree
	 */
	void exitPpsupeg(PPParser.PpsupegContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppeg}.
	 * @param ctx the parse tree
	 */
	void enterPpeg(PPParser.PpegContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppeg}.
	 * @param ctx the parse tree
	 */
	void exitPpeg(PPParser.PpegContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppdiff}.
	 * @param ctx the parse tree
	 */
	void enterPpdiff(PPParser.PpdiffContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppdiff}.
	 * @param ctx the parse tree
	 */
	void exitPpdiff(PPParser.PpdiffContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppand}.
	 * @param ctx the parse tree
	 */
	void enterPpand(PPParser.PpandContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppand}.
	 * @param ctx the parse tree
	 */
	void exitPpand(PPParser.PpandContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppor}.
	 * @param ctx the parse tree
	 */
	void enterPpor(PPParser.PporContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppor}.
	 * @param ctx the parse tree
	 */
	void exitPpor(PPParser.PporContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppinv}.
	 * @param ctx the parse tree
	 */
	void enterPpinv(PPParser.PpinvContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppinv}.
	 * @param ctx the parse tree
	 */
	void exitPpinv(PPParser.PpinvContext ctx);
	/**
	 * Enter a parse tree produced by {@link PPParser#ppnot}.
	 * @param ctx the parse tree
	 */
	void enterPpnot(PPParser.PpnotContext ctx);
	/**
	 * Exit a parse tree produced by {@link PPParser#ppnot}.
	 * @param ctx the parse tree
	 */
	void exitPpnot(PPParser.PpnotContext ctx);
}