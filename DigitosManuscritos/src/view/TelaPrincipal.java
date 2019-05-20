package view;

import controller.ControllerRedeNeural;
import controller.MatrizController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TelaPrincipal extends JFrame {

    public static void main(String[] args) {

        TelaPrincipal tp = new TelaPrincipal();
        tp.setVisible(true);
    }

    private MatrizController controller = new MatrizController();
    private ControllerRedeNeural controllerRN = new ControllerRedeNeural();
    private Container container;
    private JPanel jpMatriz;
    private JPanel jpMenu;
    private JButton jbTreinarRede;
    private JButton jbLimpar;
    private JButton jbIdentificarNumero;
    private JTable jtbMatriz;
    private MatrizTableModel tableModel;

    public TelaPrincipal() {
        setSize(600, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setTitle("Identificador de Números Manuscritos");

        initComponents();
    }

    private void initComponents() {
        container = this.getContentPane();
        container.setLayout(new BorderLayout());

        jpMenu = new JPanel();
        jpMenu.setBorder(new TitledBorder("Menu"));
        jpMenu.setLayout(new FlowLayout());

        jbTreinarRede = new JButton("Treinar Rede");
        jbLimpar = new JButton("Limpar Matriz");
        jbIdentificarNumero = new JButton("Identificar número");

        jpMenu.add(jbTreinarRede);
        jpMenu.add(jbLimpar);
        jpMenu.add(jbIdentificarNumero);

        container.add(BorderLayout.NORTH, jpMenu);

        jpMatriz = new JPanel();
        jpMatriz.setBorder(new TitledBorder("Desenhe aqui"));
        jpMatriz.setLayout(new FlowLayout());

        tableModel = new MatrizTableModel();
        tableModel.setController(controller);

        jtbMatriz = new JTable(tableModel);
        jtbMatriz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        MatrizCellRenderer matrizCellRenderer = new MatrizCellRenderer();
        jtbMatriz.setDefaultRenderer(Object.class, matrizCellRenderer);

        jpMatriz.add(jtbMatriz);
        jtbMatriz.setRowHeight(25);

        controller.criarMatriz();
        tableModel.criarTabuleiro();

        container.add(BorderLayout.CENTER, jpMatriz);

        iniciaMatriz();
        setaTamanhoColunas();

        jbTreinarRede.addActionListener( e -> {
            controllerRN.montarEstruturaCrossValidation(5);
        });

        jbIdentificarNumero.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Numero identificado: 5");
//            String representacao = "";
//            boolean[][] matriz = controller.getMatriz();
//            for(boolean[] linha : matriz) {
//                for (boolean celula : linha)
//                    representacao = representacao + celula + " ";
//                representacao += "\n";
//            }
//            JOptionPane.showMessageDialog(null, representacao);
        });

        jbLimpar.addActionListener(e -> {
            controller.criarMatriz();
            iniciaMatriz();
            setaTamanhoColunas();
        });

        jtbMatriz.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int y = jtbMatriz.getSelectedRow();
                int x = jtbMatriz.getSelectedColumn();
                System.out.println(y + " " + x);

                boolean valorAtual = controller.matrizClicada(y, x);
                JLabel label = (JLabel) tableModel.getValueAt(y, x);
                if(valorAtual)
                    label.setBackground(new Color(0,0,0));
                else
                    label.setBackground(new Color(217,217,243));

                jtbMatriz.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    private void setaTamanhoColunas() {
        for(int i = 0; i < jtbMatriz.getColumnModel().getColumnCount(); i++) {
            jtbMatriz.getColumnModel().getColumn(i).setPreferredWidth(20);
        }
    }

    private void iniciaMatriz() {
        boolean[][] matriz = controller.getMatriz();
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[i].length; j++) {
                JLabel l = new JLabel();
                l.setOpaque(true);
                if(matriz[i][j])
                    l.setBackground(new Color(0,0,0));
                else
                    l.setBackground(new Color(217,217,243));

                jtbMatriz.add(l);
                tableModel.setValueAt(l, i, j);
            }
        }
    }


}
