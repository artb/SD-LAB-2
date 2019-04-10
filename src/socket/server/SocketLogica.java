package socket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class SocketLogica {


    private final Socket socket;

    public SocketLogica(Socket socket) {
        this.socket = socket;
    }

    public void taxas() throws IOException {
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        try {
            double Q0 = in.readDouble();
            double QF = in.readDouble();
            int t = in.readInt();
            if (QF < 0 || Q0 < 0) {
                System.out.println("Erro 500 - Invalid Number");
                out.writeDouble(-1);
            } else {
                double razao = (QF/Q0);
                double resultado = Math.pow(razao,1.0/((double)t)) -1;
                System.out.println("Taxas calculadas: " + resultado);
                out.writeDouble(resultado);
            }
        } catch(Exception e) {
            out.writeDouble(-1);
            System.out.println("Erro 500 - Unexpected");
        }
        in.close();
        out.close();
        socket.close();

    }

    public void mostraMoeda() throws IOException{
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    }

    public void moeda(Vector<String> moedas) throws IOException{
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        try{
            double cotacao = in.readDouble();
            String nome = in.readUTF();
            if(cotacao <=0){
                System.out.println("Erro 501 - Invalid Number");
                out.writeUTF("Moeda com valor negativo. Refazer operacao");
            }else {
                String resultado = nome + " cotado em: R$ " + Double.toString(cotacao);
                moedas.add(resultado);
                String unicaMoeda = "";
                for(int i=0;i < moedas.size();i++){
                    unicaMoeda = unicaMoeda + "\n" + moedas.get(i);

                }
                out.writeUTF(unicaMoeda);

            }
        }catch(Exception e) {
        out.writeDouble(-1);
        System.out.println("Erro 500 - Unexpected");
    }

        in.close();
        out.close();
        socket.close();

    }

    public void editaMoeda(Vector<String> moedas)throws IOException{

    }
}