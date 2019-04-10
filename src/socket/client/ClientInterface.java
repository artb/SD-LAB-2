package socket.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static javax.swing.BoxLayout.X_AXIS;

public class ClientInterface extends JFrame{

    public ClientInterface() {
        Container panel = getContentPane();
        JLabel q0_label = new JLabel("Insira o nome da Moeda: ");
        final JTextField inputNome = new JTextField(15);
        JLabel qf_label = new JLabel("Insira o valor da Moeda: ");
        final JTextField inputCotacao = new JTextField(5);

        JButton ok = new JButton("Inserir");
        JButton janelaEditar = new JButton("Editar");
        final JTextArea area = new JTextArea(30, 30);

        panel.setLayout(new FlowLayout());
        panel.add(q0_label);
        panel.add(inputNome);
        panel.add(qf_label);
        panel.add(inputCotacao);
        panel.add(ok);
        panel.add(janelaEditar);
        panel.add(area);

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String s1 = inputNome.getText();
                    String s2 = inputCotacao.getText();
                    double cotacao = Double.valueOf(s2);


                    String moeda = new ClientSocket().CriaMoedaSemClasse(cotacao,s1);
                    System.out.println(moeda);
                    String texto = moeda;
                    area.setText(texto);

                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //AQUI EU VOU FAZER O BOTAO DE EDITAR QUE VAI ABRIR UMA NOVA JANELA PRA EDITAR UMA MOEDA
        janelaEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = new JFrame("Editar Moeda");
                Container panelEdit = frame2.getContentPane();
                JLabel num = new JLabel("Insira o numero da moeda: ");
                //PASSAR ESSE ID PRA EDITAR O VALOR DENTRO DO VECTOR
                final JTextField inputID = new JTextField(5);
                JLabel newVal = new JLabel("Insira o novo valor da Moeda: ");
                final JTextField inputNCotacao = new JTextField(5);
                JButton editarJanelaEditar = new JButton("Editar Moeda");
                JButton apagar = new JButton("Apagar Moeda");



                panelEdit.setLayout(new BoxLayout(panelEdit,BoxLayout.Y_AXIS));
                panelEdit.add(num);
                panelEdit.add(Box.createRigidArea(new Dimension(0, 20)));
                panelEdit.add(inputID);
                panelEdit.add(Box.createRigidArea(new Dimension(0, 20)));
                panelEdit.add(newVal);
                panelEdit.add(Box.createRigidArea(new Dimension(0, 20)));
                panelEdit.add(inputNCotacao);
                panelEdit.add(Box.createRigidArea(new Dimension(0, 20)));
                panelEdit.add(editarJanelaEditar);
                panelEdit.add(apagar);

                // Display the window.
                frame2.setLocation(500,500);
                frame2.pack();
                frame2.setVisible(true);
                frame2.toFront();

            }
        });

    }

    public static void main(String[] args) {

        ClientInterface frame  = new ClientInterface();
        Container content = frame.getContentPane();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        // Display the window.
        frame.setLocation(500,500);
        frame.pack();
        frame.setVisible(true);
        frame.toFront();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
