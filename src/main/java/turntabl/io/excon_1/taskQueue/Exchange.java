package turntabl.io.excon_1.taskQueue;


import org.springframework.web.reactive.function.client.WebClient;
import redis.clients.jedis.Jedis;
import turntabl.io.excon_1.Utility;
import turntabl.io.excon_1.module.OrderBookRequest;
import turntabl.io.excon_1.module.PendingOrder;


public class Exchange implements Runnable {
    Jedis jedis = new Jedis();
    @Override
    public void run() {
        while (true){
            String data = jedis.rpop("exchange1-orderrequest");
            if(data == null) continue;
            System.out.println(data);
            OrderBookRequest orderBookRequest = Utility.convertToObject(data,OrderBookRequest.class);
//					WebClient /orderbook/{product}/{side}'


            PendingOrder[] response = null;
            String product = orderBookRequest.getProduct();

            String side = orderBookRequest.getSide().toLowerCase();

            if (side.equals("sell")){
                response = WebClient.builder()
                        .baseUrl("https://exchange.matraining.com")
                        .build()
                        .get()
                        .uri("/orderbook"+"/"+product+"/"+"buy")
                        .retrieve()
                        .bodyToMono(PendingOrder[].class)
                        .block();



            }else {
                response = WebClient.builder()
                        .baseUrl("https://exchange.matraining.com")
                        .build()
                        .get()
                        .uri("/orderbook"+"/"+product+"/"+"sell")
                        .retrieve()
                        .bodyToMono(PendingOrder[].class)
                        .block();


            }

            String result = Utility.convertToString(response);


            PendingOrder[] pendingOrders = Utility.convertToObject(result,PendingOrder[].class);

            if(pendingOrders == null)
                System.out.println("Pending orders null");


            //						CONVERT TICKERS HERE
//					Arrays.stream(pendingOrders).forEach(pendingOrder -> pendingOrder.setExchange("exchange1"));

            for (PendingOrder pendingOrder : pendingOrders) {
                pendingOrder.setExchange("exchange1");
            }

            jedis.lpush(orderBookRequest.getId() + "orderbook",Utility.convertToString(pendingOrders));
//					jedis.lpush(orderBookRequest.id + "orderbook",Utility.convertToString(pendingOrders));
        }
    }
}
