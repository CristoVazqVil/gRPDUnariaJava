package grpholamundo.cliente;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import javax.swing.JOptionPane;

import com.proto.calculadora.Calculadora.CalculadoraRequest;
import com.proto.calculadora.Calculadora.CalculadoraResponse;
import com.proto.calculadora.CalculadoraServiceGrpc;

//Cristopher Vázquez Villa

public class ClienteCalculadora {
    public static void main(String[] args) {
        String servidor = "localhost";
        int puerto = 8080;

        ManagedChannel canal = ManagedChannelBuilder.forAddress(servidor, puerto)
                .usePlaintext()
                .build();

        CalculadoraServiceGrpc.CalculadoraServiceBlockingStub stub = CalculadoraServiceGrpc.newBlockingStub(canal);

        try {
            while (true) {
                String opt = JOptionPane.showInputDialog(
                        "Calcular\n" +
                                "Suma _______________(1)\n" +
                                "Resta ______________(2)\n" +
                                "Multiplicación ______(3)\n" +
                                "División ___________(4)\n\n" +
                                "Cancelar para salir");

                if (opt == null)
                    break;

                int a = Integer.parseInt(JOptionPane.showInputDialog("Ingrese a"));
                int b = Integer.parseInt(JOptionPane.showInputDialog("Ingrese b"));

                CalculadoraRequest request = CalculadoraRequest.newBuilder()
                        .setA(a)
                        .setB(b)
                        .build();

                        CalculadoraResponse response;

                switch (opt) {
                    case "1":
                        response = stub.sum(request);
                        JOptionPane.showMessageDialog(null, a + " + " + b + " = " + response.getResultado());
                        break;
                    case "2":
                        response = stub.res(request);
                        JOptionPane.showMessageDialog(null, a + " - " + b + " = " + response.getResultado());
                        break;
                    case "3":
                        response = stub.mul(request);
                        JOptionPane.showMessageDialog(null, a + " * " + b + " = " + response.getResultado());
                        break;
                    case "4":
                        response = stub.div(request);
                        JOptionPane.showMessageDialog(null, a + " / " + b + " = " + response.getResultado());
                        break;
                }
            }
        } catch (StatusRuntimeException e) {
            JOptionPane.showMessageDialog(null, "No se pudo conectar con el servidor:\n" + e.getStatus());
        } finally {
            canal.shutdown();
        }
    }
}
