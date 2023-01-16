package calculator.ws;

import javax.xml.ws.Endpoint;

public class MathsWSPublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/maths", new MathsWSImpl());
		System.err.println("Server ready!");
	}

}
