package turntabl.io.excon_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import turntabl.io.excon_2.taskQueue.Exchange;
import turntabl.io.excon_2.taskQueue.MakeOrder;

@SpringBootApplication
public class ExchangeConnectivity2Application {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeConnectivity2Application.class, args);

		Exchange exchange = new Exchange();

		Thread exchangeThread = new Thread(exchange);
		exchangeThread.start();

		MakeOrder makeOrder = new MakeOrder();
		Thread makeOrderThread = new Thread(makeOrder);
		makeOrderThread.start();

	}

}
