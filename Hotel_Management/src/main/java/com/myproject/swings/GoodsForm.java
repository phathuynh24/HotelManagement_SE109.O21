
package com.myproject.swings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TienPhat
 */
public class GoodsForm extends JFrame{
    DefaultTableModel inputTableModel;
    DefaultTableModel goodTableModel;
    public GoodsForm (){
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Phân quyền user");
        setSize(800, 400);
        setLocationRelativeTo(null);

        String[] leftColumnNames = {"Mã hàng hóa", "Tên hàng hóa", "Số lượng", "Đơn vị"};
        inputTableModel = new DefaultTableModel(null, leftColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable leftTable = new JTable(inputTableModel);


        String[] rightColumnNames = {"Mã", "Tên", "Tồn Kho", "Đơn vị", "Ch"};
        goodTableModel = new DefaultTableModel(null, rightColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };
        JTable rightTable = new JTable(goodTableModel);

        leftTable.getSelectionModel().addListSelectionListener(e -> {
           
        });
        leftTable.setPreferredScrollableViewportSize(new Dimension(400, 300));
        rightTable.setPreferredScrollableViewportSize(new Dimension(400, 300));

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JScrollPane(leftTable), BorderLayout.CENTER);
        leftPanel.setBorder(BorderFactory.createTitledBorder("Danh sách tài khoản"));

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JScrollPane(rightTable), BorderLayout.CENTER);
        rightPanel.setBorder(BorderFactory.createTitledBorder("Danh sách quyền hạn"));

        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(leftPanel, gbc);

        gbc.gridx = 1;
        add(rightPanel, gbc);

        // Xử lý sự kiện đóng cửa sổ
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                
                super.windowClosing(e);
            }
        });
    }
}
