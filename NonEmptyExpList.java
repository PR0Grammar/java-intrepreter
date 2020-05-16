import java.util.HashMap;

public class NonEmptyExpList extends ExpList{
    Exp e;
    ExpList el;

    NonEmptyExpList(Exp e, ExpList el){
        this.e = e;
        this.el = el;        
    }
    
    Exp firstExp(){
		return e;
	}
	
	Exp secondExp(){
		return ((NonEmptyExpList)el).firstExp();
	}

	ExpList tailExpList(){
		return el;
    }
    
    static boolean equalPairVal(PairVal p1, PairVal p2){
        return equalVal(p1.first, p2.first) && equalVal(p1.second, p2.second);
    }

    static boolean equalVal(Val e1, Val e2){
        Class e1Class = e1.getClass();
		Class e2Class = e2.getClass();
		
		if ( e1Class == NilVal.class && e2Class == NilVal.class )
			return true;

		if ( e1Class == PairVal.class && e2Class == PairVal.class )
			return equalPairVal((PairVal)e1, (PairVal)e2);

		if (e1.isNumber() && e2.isNumber()){
			if ( e1Class == IntVal.class && e2Class == IntVal.class )
				return ((IntVal)e1).val == ((IntVal)e2).val;
			else
				return e1.floatVal() == e2.floatVal();
		}

		if ( e1Class == BoolVal.class && e2Class == BoolVal.class )
			return ((BoolVal)e1).val == ((BoolVal)e2).val;

		return false;
    }

    private boolean checkNumber(Val e1, Val e2, String op){
        if(e1 == null || e2 == null){
            return false;
        }

        if(!e1.isNumber()){
            System.out.println("Error: " + op + " operator cannot be applied to " + e1);
            return false;
        }
        return true;
    }

    private boolean checkBool(Val e1, Val e2, String op){
        if(e1 == null || e2 == null){
            return false;
        }
        if(! (e1 instanceof BoolVal)){
            System.out.println( "Error: " + op + " operator cannot be applied to " + e1);
			return false; 
        }
        return true;
    }

    private boolean checkComp(Val e1, String op){
        if(e1 == null){
            return false;
        }
        if(!(e1.isNumber()) && op != "="){
            System.out.println("Error: " + op + " operator cannot be applied to " + e1);
            return false;
        }
        return true;
    }

    private Val opEval(Val e1, Val e2, String op){
        Class e1Class = e1.getClass();
        Class e2Class = e2.getClass();

        if(e1Class == IntVal.class && e2Class == IntVal.class){
            switch(op){
                case("+"):
                    ((IntVal) e1).val = ((IntVal) e1).val + ((IntVal) e2).val;
                    return e1;
                case("-"):
                    ((IntVal) e1).val = ((IntVal) e1).val - ((IntVal) e2).val;
                    return e1;
                case("*"):
                    ((IntVal) e1).val = ((IntVal) e1).val * ((IntVal) e2).val;
                    return e1;
               default:
                    ((IntVal) e1).val = ((IntVal) e1).val / ((IntVal) e2).val;
                    return e1;
            }
        }
        else if(e1Class == IntVal.class && e2Class == FloatVal.class){
            switch(op){
                case("+"):
                    ((FloatVal) e1).val = ((IntVal) e1).val + ((FloatVal) e2).val;
                    return e1;
                case("-"):
                    ((FloatVal) e1).val = ((IntVal) e1).val - ((FloatVal) e2).val;
                    return e1;
                case("*"):
                    ((FloatVal) e1).val = ((IntVal) e1).val * ((FloatVal) e2).val;
                    return e1;
               default:
                    ((FloatVal) e1).val = ((IntVal) e1).val / ((FloatVal) e2).val;
                    return e1;
            }
        }
        else if(e1Class == FloatVal.class && e2Class == IntVal.class){
            switch(op){
                case("+"):
                    ((FloatVal) e1).val = ((FloatVal) e1).val + ((IntVal) e2).val;
                    return e1;
                case("-"):
                    ((FloatVal) e1).val = ((FloatVal) e1).val - ((IntVal) e2).val;
                    return e1;
                case("*"):
                    ((FloatVal) e1).val = ((FloatVal) e1).val * ((IntVal) e2).val;
                    return e1;
               default:
                    ((FloatVal) e1).val = ((FloatVal) e1).val / ((IntVal) e2).val;
                    return e1;
            }
        }
        else{
            switch(op){
                case("+"):
                    ((FloatVal) e1).val = ((FloatVal) e1).val + ((FloatVal) e2).val;
                    return e1;
                case("-"):
                    ((FloatVal) e1).val = ((FloatVal) e1).val - ((FloatVal) e2).val;
                    return e1;
                case("*"):
                    ((FloatVal) e1).val = ((FloatVal) e1).val * ((FloatVal) e2).val;
                    return e1;
               default:
                    ((FloatVal) e1).val = ((FloatVal) e1).val / ((FloatVal) e2).val;
                    return e1;
            }
        }
    }
    private Val compEval(HashMap<String, Val> state, String op){
        Exp head = e;
        ExpList tail = el;
        boolean b = true;

        Val e1 = head.Eval(state);
        
        while(tail instanceof NonEmptyExpList){
            Val e2 = ((NonEmptyExpList)tail).firstExp().Eval(state);
            
            if (e2 == null)
				return null;
			if (!e2.isNumber() && op != "="){
				System.out.println( "Error: "+ op +" operator cannot be applied to " + e2 );
				return null;
            }
            
            boolean c;
            if(e1.getClass() == IntVal.class && e2.getClass() == IntVal.class){
                switch(op){
                    case("<"):
                        c = ((IntVal) e1).val < ((IntVal) e2).val;
                        break;
                    case("<="):
                        c = ((IntVal) e1).val <= ((IntVal) e2).val;
                        break;
                    case(">"):
                        c = ((IntVal) e1).val > ((IntVal) e2).val;
                        break;
                    case(">="):
                        c = ((IntVal) e1).val >= ((IntVal) e2).val;
                        break;
                    default:
                        c = equalVal(e1, e2);
                }
            }
            else{
                switch(op){
                    case("<"):
                        c = e1.floatVal() < e2.floatVal();
                        break;
                    case("<="):
                        c = e1.floatVal() <= e2.floatVal();
                        break;
                    case(">"):
                        c = e1.floatVal() > e2.floatVal();
                        break;
                    case(">="):
                        c = e1.floatVal() >= e2.floatVal();
                        break;
                    default:
                        c = equalVal(e1, e2); 
                }
            }
            b = b && c;
            e1 = e2;
            tail = ((NonEmptyExpList) tail).tailExpList();
        }

        return b ? new BoolVal(true) : new BoolVal(false);
    }
    Val addEval(HashMap<String,Val> state){
        Val e1 = e.Eval(state);
        Val e2 = el.addEval(state);
        
        if(!checkNumber(e1, e2, "+")){
            return null;
        }

        return opEval(e1, e2, "+");
    }

	Val subEval(HashMap<String,Val> state){
        Val e1 = e.Eval(state);
        Val e2 = el.subEval(state);

        if(!checkNumber(e1, e2, "-")){
            return null;
        }

        return opEval(e1, e2, "-");
    }

	Val mulEval(HashMap<String,Val> state){
        Val e1 = e.Eval(state);
        Val e2 = el.mulEval(state);

        if(!checkNumber(e1, e2, "*")){
            return null;
        }

        return opEval(e1, e2, "*");
    }

	Val divEval(HashMap<String,Val> state){
        Val e1 = e.Eval(state);
        Val e2 = el.divEval(state);

        if(!checkNumber(e1, e2, "/")){
            return null;
        }
        //Div by 0 check
        if(((IntVal) e2).val == 0 || ((FloatVal) e2).val == 0.0f){
            	System.out.println("Error: integer division by 0");
				return null;
        }
        return opEval(e1, e2, "/");
    }
    
	Val orEval(HashMap<String,Val> state){
        Val e1 = e.Eval(state);
        Val e2 = el.orEval(state);
        
        if(!checkBool(e1, e2, "or")){
            return null;
        }
        ((BoolVal) e1).val = ((BoolVal) e1).val || ((BoolVal) e2).val;
        return e1;
    }

	Val andEval(HashMap<String,Val> state){
        Val e1 = e.Eval(state);
        Val e2 = el.andEval(state);

        if(!checkBool(e1, e2, "and")){
            return null;
        }

        ((BoolVal) e1).val = ((BoolVal) e1).val && ((BoolVal) e2).val;
        return e1;
    }

	Val notEval(HashMap<String,Val> state){
        Val e1 = e.Eval(state);

        if(e1 == null){
            return null;
        }
        
        if(!(e1 instanceof BoolVal)){
            System.out.println( "Error: not operator cannot be applied to " + e1);
			return null;
        }

        ((BoolVal) e1).val = !((BoolVal) e1).val;
        return e1;
    }

	Val ltEval(HashMap<String,Val> state){
        Exp head = e;

        Val e1 = head.Eval(state);
        if(!checkComp(e1, "<")){
            return null;
        }
        return compEval(state, "<");
    }

	Val leEval(HashMap<String,Val> state){
        Exp head = e;

        Val e1 = head.Eval(state);
        if(!checkComp(e1, "<=")){
            return null;
        }

        return compEval(state, "<=");
    }

	Val gtEval(HashMap<String,Val> state){
        Exp head = e;

        Val e1 = head.Eval(state);
        if(!checkComp(e1, ">")){
            return null;
        }
        
        return compEval(state, ">");
    }

	Val geEval(HashMap<String,Val> state){
        Exp head = e;

        Val e1 = head.Eval(state);
        if(!checkComp(e1, ">=")){
            return null;
        }
        
        return compEval(state, ">=");
    }

    Val eqEval(HashMap<String,Val> state){
        Exp head = e;

        Val e1 = head.Eval(state);
        if(!checkComp(e1, "=")){
            return null;
        }
        
        return compEval(state, "=");
    }

	Val pairEval(HashMap<String,Val> state){
        System.out.println( "Error: pair operator missing arguments" );
		return null;
    }

	Val firstEval(HashMap<String,Val> state){
        System.out.println( "Error: pair operator missing arguments" );
		return null;
    }

	Val secondEval(HashMap<String,Val> state){
        System.out.println( "Error: pair operator missing arguments" );
		return null;
    } 
}
