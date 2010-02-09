public class MultiDrawFunction extends AbstractDrawFunction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6178082773934931639L;
	private int funcType;

	public void setFuncType(int funcType) {
        this.funcType = funcType;
	}

	MultiDrawFunction(int index) {
		this.funcType = index;
//		drawFunction();
		System.out.println("Function created with " + funcType);
	}

	@Override
	double f(double x) {
        double value = 0;
		switch(funcType) {
		
			case 0:
				value = x * x;
				break;
			case 1:
				value = Math.sin(x);
				break;
			case 2:
				value = Math.cos(x);
				break;
			case 3:
				value = Math.tan(x);
				break;
			case 4:
				value = Math.pow(x, 3);
				break;

			default:
				value = x;
			
		}
        String out = String.format(" F(%f) = %f", x, value);
        out = "Func: " + funcType + out;
        //System.out.println("F(" )"Value is: " + value);
        System.out.println(out);
		return value;
	}
}
