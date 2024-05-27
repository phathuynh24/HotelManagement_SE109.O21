package com.myproject.forms;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.myproject.swings.GoodsTable;
import com.myproject.swings.SearchText;
import java.awt.FlowLayout;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.bson.Document;

public class Form_Service extends javax.swing.JPanel {

    public Form_Service() {
        initComponents();
        init();
        loadServiceDataFromMongoDB();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 36)); 
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
    }

    public void init() {
        searchField = new SearchText();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton(); // New Edit Button
        jScrollPane1 = new javax.swing.JScrollPane();
        serviceTable = new GoodsTable();

        addButton.setText("Thêm dịch vụ");
        addButton.setBackground(new java.awt.Color(28, 181, 224));
        addButton.setForeground(new java.awt.Color(255, 255, 255));

        deleteButton.setText("Xóa dịch vụ");
        deleteButton.setBackground(new java.awt.Color(28, 181, 224));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        
        editButton.setText("Sửa dịch vụ");
        editButton.setBackground(new java.awt.Color(28, 181, 224));
        editButton.setForeground(new java.awt.Color(255, 255, 255));

        serviceTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã dịch vụ", "Tên dịch vụ", "Đơn vị tính", "Đơn giá"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        jScrollPane1.setViewportView(serviceTable);

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
                                                .addComponent(editButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(deleteButton)
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
                                        .addComponent(editButton)
                                        .addComponent(deleteButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                .addContainerGap())
        );
        addButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showAddServiceFrame();
            }
        });

        deleteButton.addActionListener(e -> deleteSelectedService());

        editButton.addActionListener(e -> showEditServiceFrame());

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchService(searchField.getText());
            }
        });
    }

    private void searchService(String searchText) {
        DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        serviceTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 0, 1)); // Tìm kiếm theo mã dịch vụ (cột 0) và tên dịch vụ (cột 1)
    }

    private void showAddServiceFrame() {
        JFrame addServiceFrame = new JFrame("Thêm dịch vụ mới");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        codeField = new javax.swing.JTextField(20);
        nameField = new javax.swing.JTextField(20);
        unitField = new javax.swing.JTextField(20);
        priceField = new javax.swing.JTextField(20);

        codeField.setEditable(false);
        codeField.setEnabled(false);
        codeField.setText(generateRandomCode());

        javax.swing.JButton saveButton = new javax.swing.JButton("Lưu");
        javax.swing.JButton cancelButton = new javax.swing.JButton("Hủy");

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        saveButton.addActionListener(e -> {
            saveService();
            addServiceFrame.dispose();
        });

        cancelButton.addActionListener(e -> addServiceFrame.dispose());

        addServiceFrame.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        addServiceFrame.add(new JLabel("Mã dịch vụ:"), gbc);

        gbc.gridx = 1;
        addServiceFrame.add(codeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addServiceFrame.add(new JLabel("Tên dịch vụ:"), gbc);

        gbc.gridx = 1;
        addServiceFrame.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        addServiceFrame.add(new JLabel("Đơn vị tính:"), gbc);

        gbc.gridx = 1;
        addServiceFrame.add(unitField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        addServiceFrame.add(new JLabel("Đơn giá:"), gbc);

        gbc.gridx = 1;
        addServiceFrame.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        addServiceFrame.add(buttonPanel, gbc);

        addServiceFrame.pack();
        addServiceFrame.setLocationRelativeTo(this);
        addServiceFrame.setVisible(true);
    }

    private void showEditServiceFrame() {
        int selectedRow = serviceTable.getSelectedRow();
        if (selectedRow != -1) {
            JFrame editServiceFrame = new JFrame("Sửa dịch vụ");
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            codeField = new javax.swing.JTextField(20);
            nameField = new javax.swing.JTextField(20);
            unitField = new javax.swing.JTextField(20);
            priceField = new javax.swing.JTextField(20);

            codeField.setEditable(false);
            codeField.setEnabled(false);
            codeField.setText((String) serviceTable.getValueAt(selectedRow, 0));

            nameField.setText((String) serviceTable.getValueAt(selectedRow, 1));
            unitField.setText((String) serviceTable.getValueAt(selectedRow, 2));
            priceField.setText(serviceTable.getValueAt(selectedRow, 3).toString());

            javax.swing.JButton saveButton = new javax.swing.JButton("Lưu");
            javax.swing.JButton cancelButton = new javax.swing.JButton("Hủy");

            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);

            saveButton.addActionListener(e -> {
                updateService(selectedRow);
                editServiceFrame.dispose();
            });

            cancelButton.addActionListener(e -> editServiceFrame.dispose());

            editServiceFrame.setLayout(new java.awt.GridBagLayout());
            java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
            gbc.insets = new java.awt.Insets(5, 5, 5, 5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            editServiceFrame.add(new JLabel("Mã dịch vụ:"), gbc);

            gbc.gridx = 1;
            editServiceFrame.add(codeField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            editServiceFrame.add(new JLabel("Tên dịch vụ:"), gbc);

            gbc.gridx = 1;
            editServiceFrame.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            editServiceFrame.add(new JLabel("Đơn vị tính:"), gbc);

            gbc.gridx = 1;
            editServiceFrame.add(unitField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            editServiceFrame.add(new JLabel("Đơn giá:"), gbc);

            gbc.gridx = 1;
            editServiceFrame.add(priceField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.anchor = java.awt.GridBagConstraints.CENTER;
            editServiceFrame.add(buttonPanel, gbc);

            editServiceFrame.pack();
            editServiceFrame.setLocationRelativeTo(this);
            editServiceFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void saveService() {
        String code = codeField.getText();
        String name = nameField.getText();
        String unit = unitField.getText();
        double price = Double.parseDouble(priceField.getText());

        addServiceToMongoDB(code, name, unit, price);
        loadServiceDataFromMongoDB();
    }

    private void updateService(int selectedRow) {
        String code = codeField.getText();
        String name = nameField.getText();
        String unit = unitField.getText();
        double price = Double.parseDouble(priceField.getText());

        updateServiceInMongoDB(code, name, unit, price);
        loadServiceDataFromMongoDB();
    }

    private void addServiceToMongoDB(String code, String name, String unit, double price) {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            MongoCollection<Document> collection = database.getCollection("Service");

            Document document = new Document();
            document.put("code", code);
            document.put("name", name);
            document.put("unit", unit);
            document.put("price", price);

            collection.insertOne(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateServiceInMongoDB(String code, String name, String unit, double price) {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            MongoCollection<Document> collection = database.getCollection("Service");

            Document updatedDocument = new Document();
            updatedDocument.put("name", name);
            updatedDocument.put("unit", unit);
            updatedDocument.put("price", price);

            Document updateQuery = new Document("$set", updatedDocument);

            collection.updateOne(new Document("code", code), updateQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteSelectedService() {
        int selectedRow = serviceTable.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa dịch vụ này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteServiceFromDB(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteServiceFromDB(int selectedRow) {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            MongoCollection<Document> collection = database.getCollection("Service");

            String code = (String) serviceTable.getValueAt(selectedRow, 0);
            collection.deleteOne(new Document("code", code));

            DefaultTableModel model = (DefaultTableModel) serviceTable.getModel();
            model.removeRow(selectedRow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadServiceDataFromMongoDB() {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            MongoCollection<Document> collection = database.getCollection("Service");

            model = (DefaultTableModel) serviceTable.getModel();
            model.setRowCount(0);

            for (Document doc : collection.find()) {
                String code = doc.getString("code");
                String name = doc.getString("name");
                String unit = doc.getString("unit");
                double price = doc.getDouble("price");

                model.addRow(new Object[]{code, name, unit, price});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateRandomCode() {
        Random rand = new Random();
        int randomNum = rand.nextInt(900) + 100;
        return "DV" + randomNum;
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton; // New Edit Button
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchField;
    private GoodsTable serviceTable;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField unitField;
    private javax.swing.JTextField priceField;
    private javax.swing.JTextField codeField;
    private DefaultTableModel model;
}
