// Adam SAID
// Jérémie Bentolila

grammar PP;

entry returns [PPProg e] :
    global = allocExpr
    (rul = procFunExpr)
    inst = genInstr {$e = new PPProg($global.var,$rul.value,$inst.instr);};

genRule returns [PPDef r] :
    var = varName
    '(' (arg = argExpr)*  ')' 
    (':' type = typeVar {$r = new PPFun($r.name,$r.args,$r.locals,$r.code,$type.t);} )?
    (local = allocExpr)?
    instr = genInstr {$r = new PPProc($var.text,$arg.args,$local.var,$instr.instr);};

procFunExpr returns [ArrayList<PPDef> value] @init{$value = new ArrayList<PPDef>();}:
    (
        def = genRule {$value.add($def.r);}  
    )*;

typeVar returns [Type t] : 
    'integer' {$t = new Int();}
    | 'boolean' {$t = new Bool();} 
    | 'array of' tab = typeVar {$t = new Array($tab.t);} ;

/*
funcExpr returns [PPFun f] : 
    var = varName {$f.var = $var.text;} 
    '(' (arg = argExpr)*  ')' 
    (':' type = typeVar {$f = new PPFun($var.text,$arg.args,null,$r.code,$type.t);} )?; 
*/
newArrayExpr : 'new' typeVar '[' genExpr ']';

getArrayExpr : varName '[' genExpr ']';

setArrayExpr returns [PPArraySet sa] :
    expr1 = genExpr
    '[' expr2 = genExpr ']' 
    ':=' expr3 = genExpr {$sa = new PPArraySet($expr1.expr,$expr2.expr,$expr3.expr);};

allocExpr returns [ArrayList<Pair<String,Type>> var] 
    @init{$var = new ArrayList<Pair<String,Type>>();} : 
    ('var' (v = pair {$var.add($v.p);})+);

pair returns [Pair<String,Type> p]:
    string = varName 
    ':' type = typeVar {$p = new Pair($string.var.name,$type.t);}; 

assignExpr returns [PPAssign ass] : 
    var = varName
    ':=' exp = genExpr {$ass = new PPAssign($var.var.name,$exp.expr);} ;

condExpr returns [PPCond c] : 
    'if' expr = genExpr 
    'then' instr1 = genInstr 
    'else' instr2 = genInstr {$c = new PPCond($expr.expr,$instr1.instr,$instr2.instr);};

whileExpr returns [PPWhile wh] : 
    'while' exp = genExpr 
    'do' inst = genInstr {$wh = new PPWhile($exp.expr,$inst.instr);};

procExpr returns [PPProcCall pr] @init{ArrayList<PPExpr> al = new ArrayList<PPExpr>();}: 
    cal = calle
    '(' (exp = genExpr {al.add($exp.expr);}
    (',' exps = genExpr {al.add($exps.expr);} )*)? ')' {$pr = new PPProcCall($cal.cal,al);};


argExpr returns [ArrayList<Pair<String,Type>> args] @init{$args = new ArrayList<Pair<String,Type>>();}:
    (string = varName
    ':' type = typeVar {$args.add(new Pair($string.var.name,$type.t));});

varName returns [PPVar var] : 
    name = Var {$var = new PPVar($name.text);};

constant returns [PPExpr co] : 
    c = Number {$co = new PPCte(Integer.valueOf($c.text));}  
    | False {$co = new PPFalse();}  
    | True {$co = new PPTrue();} ;  

calle returns [Callee cal] : Read {$cal = new Read();} 
    | Write {$cal = new Write();}
    | vn = varName {$cal = new User($vn.text);};

genInstr returns [PPInst instr]  :
    skip = Skip {$instr = new PPSkip();} 
    |   assign = assignExpr {$instr = $assign.ass;} 
    |   setarr = setArrayExpr {$instr = $setarr.sa;} 
    |   cond = condExpr {$instr = $cond.c;} 
    |   loop = whileExpr {$instr = $loop.wh;} 
    |   proc = procExpr {$instr = $proc.pr;} 
    |   instr1 = genInstr 
    ';' instr2 = genInstr {$instr = new PPSeq($instr2.instr, $instr1.instr);}
    ;


genExpr returns [PPExpr expr] :
    cons = constant {$expr = $cons.co;} 
    |   var = Var {$expr = new PPVar($var.text);} 
    |   ppinv exp = genExpr {$expr = new PPInv($exp.expr);} 
    |   ppnot exp = genExpr {$expr = new PPNot($exp.expr);} 
    |   exp1 = genExpr {$expr = $exp1.expr;} ppmin exp2 =  genExpr {$expr = new PPSub($expr, $exp2.expr);} 
    |   exp1 = genExpr {$expr = $exp1.expr;} ppplus exp2 =  genExpr {$expr = new PPAdd($expr, $exp2.expr);} 
    |   exp1 = genExpr {$expr = $exp1.expr;} ppmul exp2 =  genExpr {$expr = new PPMul($expr, $exp2.expr);}
    |   exp1 = genExpr {$expr = $exp1.expr;} ppdiv exp2 =  genExpr {$expr = new PPDiv($expr, $exp2.expr);}
    |   exp1 = genExpr {$expr = $exp1.expr;} ppinf exp2 =  genExpr {$expr = new PPLt($expr, $exp2.expr);}
    |   exp1 = genExpr {$expr = $exp1.expr;} ppinfeg exp2 =  genExpr {$expr = new PPLe($expr, $exp2.expr);}
    |   exp1 = genExpr {$expr = $exp1.expr;} ppsup exp2 =  genExpr {$expr = new PPGt($expr, $exp2.expr);}
    |   exp1 = genExpr {$expr = $exp1.expr;} ppsupeg exp2 =  genExpr {$expr = new PPGe($expr, $exp2.expr);}
    |   exp1 = genExpr {$expr = $exp1.expr;} ppeg exp2 =  genExpr {$expr = new PPEq($expr, $exp2.expr);}
    |   exp1 = genExpr {$expr = $exp1.expr;} ppdiff exp2 =  genExpr {$expr = new PPNe($expr, $exp2.expr);}
    |   exp1 = genExpr {$expr = $exp1.expr;} ppand exp2 =  genExpr {$expr = new PPAnd($expr, $exp2.expr);}
    |   exp1 = genExpr {$expr = $exp1.expr;} ppor exp2 =  genExpr {$expr = new PPOr($expr, $exp2.expr);}
    //|   proc = procExpr {$expr = $proc.pr;} 
    |   newArrayExpr
    |   getArrayExpr
    ;



ppplus : '+';
ppmin : '-';
ppmul : '*';
ppdiv : '/';
ppinf : '<';
ppinfeg : '<=';
ppsup : '>';
ppsupeg : '>=';
ppeg : '=';
ppdiff : '!=';
ppand : 'and';
ppor : 'or';
ppinv : '-';
ppnot : 'not';


Skip : 'skip';
Number : ('0'..'9')+ ;
fragment Char : [a-zA-Z_];
Read : 'read';
Write : 'write';
True : 'true';
False : 'false';
Var : Char (Char | Number)* ;
WS : [ \t\r\n]+ -> skip ;