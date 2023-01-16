package calculator.client.main;

import java.net.MalformedURLException;
import java.net.URL;

import calculator.client.IMathsWS;
import calculator.client.MathsWSImplService;

public class Main {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.dneonline.com/calculator.asmx?WSDL");
			MathsWSImplService serviceImpl = new MathsWSImplService(url);
			IMathsWS proxy = serviceImpl.getMathsWSImplPort();

			int a = 15, b = 3;
			System.out.println(a + " + " + b + " = " + proxy.add(a, b));
			System.out.println(a + " - " + b + " = " + proxy.substract(a, b));
			System.out.println(a + " * " + b + " = " + proxy.multiply(a, b));
			System.out.println(a + " / " + b + " = " + proxy.divide(a, b));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

}
