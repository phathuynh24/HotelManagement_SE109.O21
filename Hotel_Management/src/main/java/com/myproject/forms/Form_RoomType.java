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

public class Form_RoomType extends javax.swing.JPanel {

    public Form_RoomType() {
        initComponents();
        init();
        loadTypeRoomDataFromMongoDB();
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
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        typeRoomTable = new GoodsTable();

        addButton.setText("Thêm loại phòng");
        addButton.setBackground(new java.awt.Color(28, 181, 224));
        addButton.setForeground(new java.awt.Color(255, 255, 255));

        editButton.setText("Sửa loại phòng");
        editButton.setBackground(new java.awt.Color(28, 181, 224));
        editButton.setForeground(new java.awt.Color(255, 255, 255));

        deleteButton.setText("Xóa loại phòng");
        deleteButton.setBackground(new java.awt.Color(28, 181, 224));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));

        typeRoomTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã loại phòng", "Tên loại phòng", "Đơn giá", "Mô tả"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        jScrollPane1.setViewportView(typeRoomTable);

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
                showAddTypeRoomFrame();
            }
        });

        editButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                showEditTypeRoomFrame();
            }
        });

        deleteButton.addActionListener(e -> deleteSelectedTypeRoom());

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTypeRoom(searchField.getText());
            }
        });
    }

    private void searchTypeRoom(String searchText) {
        DefaultTableModel model = (DefaultTableModel) typeRoomTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        typeRoomTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 0, 1));
    }

    private void showAddTypeRoomFrame() {
        JFrame addTypeRoomFrame = new JFrame("Thêm loại phòng");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        codeField = new javax.swing.JTextField(20);
        nameField = new javax.swing.JTextField(20);
        descriptionField = new javax.swing.JTextField(20);
        priceField = new javax.swing.JTextField(20);

        codeField.setEditable(false);
        codeField.setEnabled(false);
        codeField.setText(generateRandomCode());

        javax.swing.JButton saveButton = new javax.swing.JButton("Lưu");
        javax.swing.JButton cancelButton = new javax.swing.JButton("Hủy");

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        saveButton.addActionListener(e -> {
            saveTypeRoom();
            addTypeRoomFrame.dispose();
        });

        cancelButton.addActionListener(e -> addTypeRoomFrame.dispose());

        addTypeRoomFrame.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        addTypeRoomFrame.add(new JLabel("Mã loại phòng:"), gbc);

        gbc.gridx = 1;
        addTypeRoomFrame.add(codeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addTypeRoomFrame.add(new JLabel("Tên loại phòng:"), gbc);

        gbc.gridx = 1;
        addTypeRoomFrame.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        addTypeRoomFrame.add(new JLabel("Đơn giá:"), gbc);

        gbc.gridx = 1;
        addTypeRoomFrame.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        addTypeRoomFrame.add(new JLabel("Mô tả:"), gbc);

        gbc.gridx = 1;
        addTypeRoomFrame.add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        addTypeRoomFrame.add(buttonPanel, gbc);

        addTypeRoomFrame.pack();
        addTypeRoomFrame.setLocationRelativeTo(this);
        addTypeRoomFrame.setVisible(true);
    }

    private void showEditTypeRoomFrame() {
        int selectedRow = typeRoomTable.getSelectedRow();
        if (selectedRow != -1) {
            JFrame editTypeRoomFrame = new JFrame("Sửa loại phòng");
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            codeField = new javax.swing.JTextField(20);
            nameField = new javax.swing.JTextField(20);
            descriptionField = new javax.swing.JTextField(20);
            priceField = new javax.swing.JTextField(20);

            codeField.setEditable(false);
            codeField.setEnabled(false);
            codeField.setText((String) typeRoomTable.getValueAt(selectedRow, 0));

            nameField.setText((String) typeRoomTable.getValueAt(selectedRow, 1));
            priceField.setText(String.valueOf(typeRoomTable.getValueAt(selectedRow, 2)));
            descriptionField.setText((String) typeRoomTable.getValueAt(selectedRow, 3));

            javax.swing.JButton saveButton = new javax.swing.JButton("Lưu");
            javax.swing.JButton cancelButton = new javax.swing.JButton("Hủy");

            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);

            saveButton.addActionListener(e -> {
                updateTypeRoom(selectedRow);
                editTypeRoomFrame.dispose();
            });

            cancelButton.addActionListener(e -> editTypeRoomFrame.dispose());

            editTypeRoomFrame.setLayout(new java.awt.GridBagLayout());
            java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
            gbc.insets = new java.awt.Insets(5, 5, 5, 5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            editTypeRoomFrame.add(new JLabel("Mã loại phòng:"), gbc);

            gbc.gridx = 1;
            editTypeRoomFrame.add(codeField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            editTypeRoomFrame.add(new JLabel("Tên loại phòng:"), gbc);

            gbc.gridx = 1;
            editTypeRoomFrame.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            editTypeRoomFrame.add(new JLabel("Đơn giá:"), gbc);

            gbc.gridx = 1;
            editTypeRoomFrame.add(priceField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            editTypeRoomFrame.add(new JLabel("Mô tả:"), gbc);

            gbc.gridx = 1;
            editTypeRoomFrame.add(descriptionField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.anchor = java.awt.GridBagConstraints.CENTER;
            editTypeRoomFrame.add(buttonPanel, gbc);

            editTypeRoomFrame.pack();
            editTypeRoomFrame.setLocationRelativeTo(this);
            editTypeRoomFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void saveTypeRoom() {
        String code = codeField.getText();
        String name = nameField.getText(); 
        double price = Double.parseDouble(priceField.getText());
        String description = descriptionField.getText();

        addTypeRoomToMongoDB(code, name, price, description);
        loadTypeRoomDataFromMongoDB();
    }

    private void updateTypeRoom(int selectedRow) {
        String code = codeField.getText();
        String name = nameField.getText(); 
        double price = Double.parseDouble(priceField.getText());
        String description = descriptionField.getText();

        updateTypeRoomInMongoDB(code, name, price, description);
        loadTypeRoomDataFromMongoDB();
    }

    private void addTypeRoomToMongoDB(String code, String name, double price, String description) {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            MongoCollection<Document> collection = database.getCollection("RoomType");

            Document document = new Document();
            document.put("code", code);
            document.put("name", name);
            document.put("price", price);
            document.put("description", description);

            collection.insertOne(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTypeRoomInMongoDB(String code, String name, double price, String description) {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            MongoCollection<Document> collection = database.getCollection("RoomType");

            Document updatedDocument = new Document();
            updatedDocument.put("name", name);
            updatedDocument.put("price", price);
            updatedDocument.put("description", description);

            collection.updateOne(new Document("code", code), new Document("$set", updatedDocument));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteSelectedTypeRoom() {
        int selectedRow = typeRoomTable.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa dịch vụ này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteTypeRoomFromDB(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteTypeRoomFromDB(int selectedRow) {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            MongoCollection<Document> collection = database.getCollection("RoomType");
            String code = (String) typeRoomTable.getValueAt(selectedRow, 0);
            collection.deleteOne(new Document("code", code));

            DefaultTableModel model = (DefaultTableModel) typeRoomTable.getModel();
            model.removeRow(selectedRow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTypeRoomDataFromMongoDB() {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            MongoCollection<Document> collection = database.getCollection("RoomType");

            model = (DefaultTableModel) typeRoomTable.getModel();
            model.setRowCount(0);

            for (Document doc : collection.find()) {
                String code = doc.getString("code");
                String name = doc.getString("name");
                double price = doc.getDouble("price");
                String description = doc.getString("description");

                model.addRow(new Object[]{code, name, price, description});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateRandomCode() {
        Random rand = new Random();
        int randomNum = rand.nextInt(900) + 100;
        return "RT" + randomNum;
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton addButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchField;
    private GoodsTable typeRoomTable;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JTextField priceField;
    private javax.swing.JTextField codeField;
    private DefaultTableModel model;
}
