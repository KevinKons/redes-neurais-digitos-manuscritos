package view;

import controller.MatrizController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MatrizTableModel extends DefaultTableModel {

    private JLabel[][] celulas = new JLabel[16][16];
    private MatrizController controller;

    public void setController(MatrizController controller) {
        this.controller = controller;
    }

    @Override
    public int getRowCount() {
        if (controller != null) {
            return controller.getQntLinhas();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        if (controller != null) {
            return controller.getQntColunas();
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return celulas[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        celulas[rowIndex][columnIndex] = (JLabel) aValue;
        fireTableStructureChanged();
    }

    public void criarTabuleiro() {
        controller.criarMatriz();
        fireTableRowsInserted(getRowCount(), getRowCount());
    }
}
