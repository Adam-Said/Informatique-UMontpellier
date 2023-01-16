package calculator.ws;

import javax.jws.WebService;

@WebService(endpointInterface="calculator.ws.IMathsWS")
public class MathsWSImpl implements IMathsWS {

	public int add(int a, int b) {
		return a + b;
	}

	public int substract(int a, int b) {
		return a - b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}

	public int divide(int a, int b) {
		return a / b;
	}

}
