package grpc.client.client;

import grpc.server.proto.HelloWorldRequest;
import grpc.server.proto.ReactorMyServiceGrpc.ReactorMyServiceStub;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HelloService {

    private final ReactorMyServiceStub client;

    public Mono<Response<String>> sayHello(String name) {
        var req = HelloWorldRequest.newBuilder().setName(name).build();
        return client.sayHello(req)
                .map(reply -> new Response<>(reply.getMessage(), "ok"))
                .onErrorResume(thr -> Mono.just(new Response<>(null, thr.getLocalizedMessage())));
    }

    public record Response<T>(T body, String message) {}
}