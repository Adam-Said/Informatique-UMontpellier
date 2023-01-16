package calculator.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IMathsWS {
	@WebMethod
	int add(int a, int b);
	
	@WebMethod
	int substract(int a, int b);
	
	@WebMethod
	int multiply(int a, int b);
	
	@WebMethod
	int divide(int a, int b);
}
