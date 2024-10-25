package grpc.client;

import grpc.client.client.HelloService.Response;
import grpc.client.client.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final HelloService helloService;

    @GetMapping("sayHello")
    public Mono<Response<String>> getName(@RequestParam String name) {
        return helloService.sayHello(name);
    }
}