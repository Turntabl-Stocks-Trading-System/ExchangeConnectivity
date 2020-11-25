package turntabl.io.excon_1.taskQueue;


import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import redis.clients.jedis.Jedis;
import turntabl.io.excon_1.Utility;
import turntabl.io.excon_1.module.Order;


public class MakeOrder implements Runnable {
    Jedis jedis = new Jedis();
    String key = "463d8d68-7c01-4e5f-b083-2393a498e34f";
    WebClient webClient = WebClient.create("https://exchange.matraining.com");

    @Override
    public void run() {
        while (true){

            String data = jedis.rpop("makeorderexchange1");

            if(data == null) continue;

            System.out.println("exchange1");
            String order2 = Utility.convertToString(data);
            System.out.println(order2);

            Order order = Utility.convertToObject(data, Order.class);

            String orderId = webClient.post().uri("/"+key+"/order")
                    .body(Mono.just(order), Order.class)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            System.out.println("Order placed successfully, orderId: " +orderId);
            jedis.del("data");
        }
    }
}
