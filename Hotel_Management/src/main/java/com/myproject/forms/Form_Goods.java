/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myproject.forms;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.myproject.swings.GoodsForm;
import com.myproject.swings.GoodsTable;
import com.myproject.swings.HintTextField;
import com.myproject.swings.SearchText;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import org.bson.Document;

public class Form_Goods extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    public Form_Goods() {
        initComponents();
        init();
        loadGoodsDataFromMongoDB();
    }

    /**
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(106, 106, 106));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(125, 125, 125))
        );
    }// </editor-fold>//GEN-END:initComponents
     public void init() {
        searchField = new SearchText();
        addButton = new javax.swing.JButton();
        importButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        goodsTable = new GoodsTable();

        addButton.setText("Thêm hàng hóa");
        addButton.setBackground(new java.awt.Color(28, 181, 224));
        addButton.setForeground(new java.awt.Color(255, 255, 255));

        importButton.setText("Nhập hàng");
        importButton.setBackground(new java.awt.Color(28, 181, 224));
        importButton.setForeground(new java.awt.Color(255, 255, 255));

        deleteButton.setText("Xóa hàng hóa");
        deleteButton.setBackground(new java.awt.Color(28, 181, 224));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));

        goodsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã hàng hóa", "Tên hàng hóa", "Tồn kho", "Đơn vị tính", "Đơn giá nhập", "Đơn giá bán"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        jScrollPane1.setViewportView(goodsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(addButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(deleteButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(importButton)
                                                .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addButton)
                                        .addComponent(importButton)
                                        .addComponent(deleteButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                .addContainerGap())
        );
        addButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showAddProductFrame();
            }
        });
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int selectedRow = goodsTable.getSelectedRow();
                if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
                    int confirm = javax.swing.JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa hàng này?", "Xác nhận xóa", javax.swing.JOptionPane.YES_NO_OPTION);
                    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                        deleteSelectedData(selectedRow);
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.", "Thông báo", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        importButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importGoods();
            }
        });

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchGoods(searchField.getText());
            }
        });
        // Phương thức searchGoods để thực hiện tìm kiếm
    }// </editor-fold>

    private void searchGoods(String searchText) {
        DefaultTableModel model = (DefaultTableModel) goodsTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        goodsTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 0)); // Tìm kiếm theo mã hàng hóa (cột 0)
    }

    private void showAddProductFrame() {
        // Create a new JFrame for the add product form
        JFrame addProductFrame = new JFrame("Thêm sản phẩm mới");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Create components for the frame
        sellPriceField = new HintTextField("Nhập đơn giá bán", 20);
        importPriceField = new HintTextField("Nhập đơn giá nhập", 20);
        quantityField = new HintTextField("Nhập số lượng", 20);
        nameField = new HintTextField("Nhập tên hàng hóa", 20);
        unitField = new HintTextField("Nhập đơn vị tính", 20);
        codeField = new HintTextField("Mã hàng hóa", 20);
        descriptionArea = new javax.swing.JTextArea(4, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        codeField.setEditable(false);
        codeField.setEnabled(false);
        codeField.setText(generateRandomCode());

        //Set fillter to text field
        DocumentFilter numberFilter = new NumberFilter();

        ((AbstractDocument) sellPriceField.getDocument()).setDocumentFilter(numberFilter);
        ((AbstractDocument) importPriceField.getDocument()).setDocumentFilter(numberFilter);
        ((AbstractDocument) quantityField.getDocument()).setDocumentFilter(numberFilter);

        // Create buttons for the frame
        javax.swing.JButton saveButton = new javax.swing.JButton("Lưu");
        javax.swing.JButton cancelButton = new javax.swing.JButton("Hủy");

        //Set up hint text        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        // Add action listeners for buttons
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                saveProduct();
                addProductFrame.dispose(); // Đóng cửa sổ sau khi lưu
            }
        });
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                addProductFrame.dispose(); // Đóng cửa sổ nếu hủy
            }
        });

        // Layout for the frame
        addProductFrame.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(5, 5, 5, 5); // Thiết lập khoảng cách giữa các thành phần

        gbc.anchor = java.awt.GridBagConstraints.WEST; // Căn trái cho tất cả các label

        gbc.gridx = 0;
        gbc.gridy = 0;
        addProductFrame.add(new JLabel("Mã hàng hóa:"), gbc);

        gbc.gridx = 1;
        addProductFrame.add(codeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addProductFrame.add(new JLabel("Tên hàng hóa:"), gbc);

        gbc.gridx = 1;
        addProductFrame.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        addProductFrame.add(new JLabel("Đơn giá nhập:"), gbc);

        gbc.gridx = 1;
        addProductFrame.add(importPriceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        addProductFrame.add(new JLabel("Đơn giá bán:"), gbc);

        gbc.gridx = 1;
        addProductFrame.add(sellPriceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        addProductFrame.add(new JLabel("Số lượng ban đầu:"), gbc);

        gbc.gridx = 1;
        addProductFrame.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        addProductFrame.add(new JLabel("Đơn vị tính:"), gbc);

        gbc.gridx = 1;
        addProductFrame.add(unitField, gbc);

        // Thêm nút "Lưu" và "Hủy"
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2; // Đặt gridwidth bằng 2 để nút chiếm cả hai cột
        gbc.anchor = java.awt.GridBagConstraints.CENTER; // Đặt anchor để căn giữa
        addProductFrame.add(buttonPanel, gbc);

        // Set frame size and make it visible
        addProductFrame.pack();
        addProductFrame.setLocationRelativeTo(this);
        addProductFrame.setVisible(true);
    }

    private void saveProduct() {
        // Lấy thông tin từ các trường và thêm vào MongoDB
        String name = nameField.getText();
        double importPrice = Double.parseDouble(importPriceField.getText());
        double sellPrice = Double.parseDouble(sellPriceField.getText());
        String code = codeField.getText();
        String unit = unitField.getText();
        int quantity = Integer.parseInt(quantityField.getText());

        // Thêm sản phẩm vào MongoDB (sử dụng phương thức addGoodsToMongoDB() đã được định nghĩa trong phần trước)
        addGoodsToMongoDB(code, name, importPrice, sellPrice, unit, quantity);
        loadGoodsDataFromMongoDB();
    }

    private void importGoods() {
        ImportCommodityWindow importForm = new ImportCommodityWindow();
        importForm.setVisible(true);
    }

    private void addGoodsToMongoDB(String code, String name, double importPrice, double sellPrice, String unit, int quantity) {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Chọn hoặc tạo database
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");

            // Chọn hoặc tạo collection
            MongoCollection<Document> collection = database.getCollection("Goods");

            // Tạo một đối tượng Document để lưu dữ liệu
            Document document = new Document();
            document.put("code", code);
            document.put("name", name);
            document.put("quantity", quantity);
            document.put("unit", unit);
            document.put("importPrice", importPrice);
            document.put("sellPrice", sellPrice);
            // Thêm document vào collection
            collection.insertOne(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateRandomCode() {
        // Generate a random 4-digit number
        Random rand = new Random();
        int randomNum = rand.nextInt(900) + 100; // Generates number between 1000 and 9999
        return "HH" + randomNum;
    }

    private void deleteSelectedData(int selectedRow) {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Chọn hoặc tạo database
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");

            // Chọn hoặc tạo collection
            MongoCollection<Document> collection = database.getCollection("Goods");

            // Lấy mã hàng hóa từ hàng được chọn trong bảng
            String code = (String) goodsTable.getValueAt(selectedRow, 0);

            // Xóa hàng được chọn từ MongoDB
            collection.deleteOne(new Document("code", code));

            // Xóa hàng được chọn từ bảng
            DefaultTableModel model = (DefaultTableModel) goodsTable.getModel();
            model.removeRow(selectedRow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGoodsDataFromMongoDB() {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Chọn hoặc tạo database
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");

            // Chọn hoặc tạo collection
            MongoCollection<Document> collection = database.getCollection("Goods");

            // Xóa dữ liệu cũ trong bảng
            model = (DefaultTableModel) goodsTable.getModel();
            model.setRowCount(0); // Xóa tất cả các hàng trong bảng
            DecimalFormat currencyFormat = new DecimalFormat("#,###");
            // Duyệt qua các document và thêm chúng vào bảng
            for (Document doc : collection.find()) {
                String code = doc.getString("code");
                String name = doc.getString("name");
                String description = doc.getString("description");
                String importPrice = currencyFormat.format(doc.getDouble("importPrice"));
                String sellPrice = currencyFormat.format(doc.getDouble("sellPrice"));
                String unit = doc.getString("unit");
                int quantity = doc.getInteger("quantity");

                // Thêm dữ liệu vào hàng mới trong bảng
                model.addRow(new Object[]{code, name, quantity, unit, sellPrice, importPrice, sellPrice});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class NumberFilter extends DocumentFilter {

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (isNumeric(string)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (isNumeric(text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        private boolean isNumeric(String text) {
            return text.matches("\\d*"); // Chỉ cho phép nhập các ký tự số
        }
    }

    public class ImportCommodityWindow extends JFrame {

        public ImportCommodityWindow() {
            setTitle("Import Commodity");
            //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 400);
            setLocationRelativeTo(null);

            // Panel chính chứa hai bảng và các nút chức năng
            JPanel mainPanel = new JPanel(new BorderLayout());

            // Tạo bảng "Danh Sách Hàng Hóa Nhập Vào"
            String[] columnNames1 = {"#", "Mã hàng hóa", "Tên hàng hóa", "Số lượng", "Đơn vị"};
            Object[][] data1 = {
                {1, "HH101", "Cocacola", 20, "Chai"},
                {2, "HH104", "Bánh ngọt", 20, "Cái"},
                {3, "HH103", "Pepsi", 40, "Chai"}
            };
            JTable table1 = new JTable(new DefaultTableModel(data1, columnNames1));
            JScrollPane scrollPane1 = new JScrollPane(table1);
            JPanel panel1 = new JPanel(new BorderLayout());
            panel1.setBorder(BorderFactory.createTitledBorder("DANH SÁCH HÀNG HÓA NHẬP VÀO"));
            panel1.add(scrollPane1, BorderLayout.CENTER);

            // Tạo bảng "Danh Sách Hàng Hóa Trong Kho"
            String[] columnNames2 = {"Mã", "Tên", "Tồn kho", "Đơn vị", "Chọn"};
            Object[][] data2 = {
                {"HH101", "Cocacola", 157, "Chai", Boolean.TRUE},
                {"HH102", "Nước suối", 73, "Chai", Boolean.FALSE},
                {"HH103", "Pepsi", 50, "Chai", Boolean.TRUE},
                {"HH104", "Bánh ngọt", 20, "Cái", Boolean.TRUE}
            };
            JTable table2 = new JTable(new DefaultTableModel(data2, columnNames2) {
                @Override
                public Class<?> getColumnClass(int column) {
                    switch (column) {
                        case 2:
                            return Integer.class;
                        case 4:
                            return Boolean.class;
                        default:
                            return String.class;
                    }
                }
            });
            JScrollPane scrollPane2 = new JScrollPane(table2);
            JPanel panel2 = new JPanel(new BorderLayout());
            panel2.setBorder(BorderFactory.createTitledBorder("DANH SÁCH HÀNG HÓA TRONG KHO"));
            panel2.add(scrollPane2, BorderLayout.CENTER);

            // Thêm hai bảng vào panel chính
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel1, panel2);
            splitPane.setResizeWeight(0.5);
            mainPanel.add(splitPane, BorderLayout.CENTER);

            // Tạo các nút chức năng
            JPanel buttonPanel = new JPanel();
            JButton cancelButton = new JButton("Hủy");
            JButton importButton = new JButton("Nhập hàng");

            // Thêm hành động cho nút "Hủy"
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });

            // Thêm hành động cho nút "Nhập hàng"
            importButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Thêm logic nhập hàng vào đây
                    JOptionPane.showMessageDialog(null, "Hàng hóa đã được nhập vào kho!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            buttonPanel.add(cancelButton);
            buttonPanel.add(importButton);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            // Thêm panel chính vào cửa sổ
            add(mainPanel);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
    private JButton addButton;
    private JButton importButton;
    private JButton deleteButton;
    private JScrollPane jScrollPane1;
    private JTextField searchField;
    private GoodsTable goodsTable;
    private HintTextField nameField;
    private JTextArea descriptionArea;
    private HintTextField sellPriceField;
    private HintTextField quantityField;
    private HintTextField importPriceField;
    private HintTextField codeField;
    private HintTextField unitField;
    private DefaultTableModel model;
    private GoodsForm goodsForm;
    // End of variables declaration                   

}
