package grpholamundo.servidor;

import com.proto.calculadora.Calculadora.CalculadoraRequest;
import com.proto.calculadora.Calculadora.CalculadoraResponse;
import com.proto.calculadora.CalculadoraServiceGrpc;

import io.grpc.stub.StreamObserver;

//Cristopher Vázquez Villa

public class CalculadoraImpl extends CalculadoraServiceGrpc.CalculadoraServiceImplBase{
    @Override
    public void div(CalculadoraRequest request, StreamObserver<CalculadoraResponse> responseObserver) {
        double resultado = request.getA() / (double) request.getB();
        CalculadoraResponse response = CalculadoraResponse.newBuilder().setResultado(resultado).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void mul(CalculadoraRequest request, StreamObserver<CalculadoraResponse> responseObserver) {
        double resultado = request.getA() * request.getB();
        CalculadoraResponse response = CalculadoraResponse.newBuilder().setResultado(resultado).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void sum(CalculadoraRequest request, StreamObserver<CalculadoraResponse> responseObserver) {
        double resultado = request.getA() + request.getB();
        CalculadoraResponse response = CalculadoraResponse.newBuilder().setResultado(resultado).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void res(CalculadoraRequest request, StreamObserver<CalculadoraResponse> responseObserver) {
        double resultado = request.getA() - request.getB();
        CalculadoraResponse response = CalculadoraResponse.newBuilder().setResultado(resultado).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
