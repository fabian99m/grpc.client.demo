package grpc.client.client;

import grpc.server.proto.MyServiceGrpc.MyServiceFutureStub;
import grpc.server.proto.ReactorMyServiceGrpc;
import grpc.server.proto.ReactorMyServiceGrpc.ReactorMyServiceStub;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean("grpcHelloService")
    public ReactorMyServiceStub reactorMyServiceStub(
            @GrpcClient("hello") MyServiceFutureStub serviceFutureStub
    ) {
        return ReactorMyServiceGrpc.newReactorStub(serviceFutureStub.getChannel());
    }

    @Bean
    public HelloService helloService(
            @Qualifier("grpcHelloService") ReactorMyServiceStub serviceStub
    ) {
        return new HelloService(serviceStub);
    }
}