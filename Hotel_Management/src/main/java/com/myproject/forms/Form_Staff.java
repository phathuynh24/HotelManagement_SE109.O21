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

public class Form_Staff extends javax.swing.JPanel {

    public Form_Staff() {
        initComponents();
        init();
        loadStaffDataFromMongoDB();
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
        staffTable = new GoodsTable();

        addButton.setText("Thêm nhân viên");
        addButton.setBackground(new java.awt.Color(28, 181, 224));
        addButton.setForeground(new java.awt.Color(255, 255, 255));

        deleteButton.setText("Xóa nhân viên");
        deleteButton.setBackground(new java.awt.Color(28, 181, 224));
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        
        editButton.setText("Sửa nhân viên");
        editButton.setBackground(new java.awt.Color(28, 181, 224));
        editButton.setForeground(new java.awt.Color(255, 255, 255));

        staffTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã nhân viên", "Tên", "CCCD", "Số điện thoại", "Bộ phận", "Chức vụ", "Ngày sinh", "Giới tính"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        jScrollPane1.setViewportView(staffTable);

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
                showAddStaffFrame();
            }
        });

        deleteButton.addActionListener(e -> deleteSelectedStaff());

        editButton.addActionListener(e -> showEditStaffFrame());

        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchStaff(searchField.getText());
            }
        });
    }

    private void searchStaff(String searchText) {
        DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        staffTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 0, 1, 2, 3, 4, 5, 6, 7)); // Search across all columns
    }

    private void showAddStaffFrame() {
        JFrame addStaffFrame = new JFrame("Thêm nhân viên mới");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        codeField = new javax.swing.JTextField(20);
        nameField = new javax.swing.JTextField(20);
        cccdField = new javax.swing.JTextField(20);
        phoneField = new javax.swing.JTextField(20);
        departmentField = new javax.swing.JTextField(20);
        positionField = new javax.swing.JTextField(20);
        dobField = new javax.swing.JTextField(20);
        genderField = new javax.swing.JTextField(20);

        codeField.setEditable(false);
        codeField.setEnabled(false);
        codeField.setText(generateRandomCode());

        javax.swing.JButton saveButton = new javax.swing.JButton("Lưu");
        javax.swing.JButton cancelButton = new javax.swing.JButton("Hủy");

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        saveButton.addActionListener(e -> {
            saveStaff();
            addStaffFrame.dispose();
        });

        cancelButton.addActionListener(e -> addStaffFrame.dispose());

        addStaffFrame.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        addStaffFrame.add(new JLabel("Mã nhân viên:"), gbc);

        gbc.gridx = 1;
        addStaffFrame.add(codeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addStaffFrame.add(new JLabel("Tên:"), gbc);

        gbc.gridx = 1;
        addStaffFrame.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        addStaffFrame.add(new JLabel("CCCD:"), gbc);

        gbc.gridx = 1;
        addStaffFrame.add(cccdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        addStaffFrame.add(new JLabel("Số điện thoại:"), gbc);

        gbc.gridx = 1;
        addStaffFrame.add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        addStaffFrame.add(new JLabel("Bộ phận:"), gbc);

        gbc.gridx = 1;
        addStaffFrame.add(departmentField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        addStaffFrame.add(new JLabel("Chức vụ:"), gbc);

        gbc.gridx = 1;
        addStaffFrame.add(positionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        addStaffFrame.add(new JLabel("Ngày sinh:"), gbc);

        gbc.gridx = 1;
        addStaffFrame.add(dobField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        addStaffFrame.add(new JLabel("Giới tính:"), gbc);

        gbc.gridx = 1;
        addStaffFrame.add(genderField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        addStaffFrame.add(buttonPanel, gbc);

        addStaffFrame.pack();
        addStaffFrame.setLocationRelativeTo(this);
        addStaffFrame.setVisible(true);
    }

    private void showEditStaffFrame() {
        int selectedRow = staffTable.getSelectedRow();
        if (selectedRow != -1) {
            JFrame editStaffFrame = new JFrame("Sửa nhân viên");
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            codeField = new javax.swing.JTextField(20);
            nameField = new javax.swing.JTextField(20);
            cccdField = new javax.swing.JTextField(20);
            phoneField = new javax.swing.JTextField(20);
            departmentField = new javax.swing.JTextField(20);
            positionField = new javax.swing.JTextField(20);
            dobField = new javax.swing.JTextField(20);
            genderField = new javax.swing.JTextField(20);

            codeField.setEditable(false);
            codeField.setEnabled(false);
            codeField.setText((String) staffTable.getValueAt(selectedRow, 0));

            nameField.setText((String) staffTable.getValueAt(selectedRow, 1));
            cccdField.setText((String) staffTable.getValueAt(selectedRow, 2));
            phoneField.setText((String) staffTable.getValueAt(selectedRow, 3));
            departmentField.setText((String) staffTable.getValueAt(selectedRow, 4));
            positionField.setText((String) staffTable.getValueAt(selectedRow, 5));
            dobField.setText((String) staffTable.getValueAt(selectedRow, 6));
            genderField.setText((String) staffTable.getValueAt(selectedRow, 7));

            javax.swing.JButton saveButton = new javax.swing.JButton("Lưu");
            javax.swing.JButton cancelButton = new javax.swing.JButton("Hủy");

            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);

            saveButton.addActionListener(e -> {
                updateStaff(selectedRow);
                editStaffFrame.dispose();
            });

            cancelButton.addActionListener(e -> editStaffFrame.dispose());

            editStaffFrame.setLayout(new java.awt.GridBagLayout());
            java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
            gbc.insets = new java.awt.Insets(5, 5, 5, 5);

            gbc.gridx = 0;
            gbc.gridy = 0;
            editStaffFrame.add(new JLabel("Mã nhân viên:"), gbc);

            gbc.gridx = 1;
            editStaffFrame.add(codeField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            editStaffFrame.add(new JLabel("Tên:"), gbc);

            gbc.gridx = 1;
            editStaffFrame.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            editStaffFrame.add(new JLabel("CCCD:"), gbc);

            gbc.gridx = 1;
            editStaffFrame.add(cccdField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            editStaffFrame.add(new JLabel("Số điện thoại:"), gbc);

            gbc.gridx = 1;
            editStaffFrame.add(phoneField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            editStaffFrame.add(new JLabel("Bộ phận:"), gbc);

            gbc.gridx = 1;
            editStaffFrame.add(departmentField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            editStaffFrame.add(new JLabel("Chức vụ:"), gbc);

            gbc.gridx = 1;
            editStaffFrame.add(positionField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            editStaffFrame.add(new JLabel("Ngày sinh:"), gbc);

            gbc.gridx = 1;
            editStaffFrame.add(dobField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 7;
            editStaffFrame.add(new JLabel("Giới tính:"), gbc);

            gbc.gridx = 1;
            editStaffFrame.add(genderField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.gridwidth = 2;
            gbc.anchor = java.awt.GridBagConstraints.CENTER;
            editStaffFrame.add(buttonPanel, gbc);

            editStaffFrame.pack();
            editStaffFrame.setLocationRelativeTo(this);
            editStaffFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void saveStaff() {
        String code = codeField.getText();
        String name = nameField.getText();
        String cccd = cccdField.getText();
        String phone = phoneField.getText();
        String department = departmentField.getText();
        String position = positionField.getText();
        String dob = dobField.getText();
        String gender = genderField.getText();

        addStaffToMongoDB(code, name, cccd, phone, department, position, dob, gender);
        loadStaffDataFromMongoDB();
    }

    private void updateStaff(int selectedRow) {
        String code = codeField.getText();
        String name = nameField.getText();
        String cccd = cccdField.getText();
        String phone = phoneField.getText();
        String department = departmentField.getText();
        String position = positionField.getText();
        String dob = dobField.getText();
        String gender = genderField.getText();

        updateStaffInMongoDB(code, name, cccd, phone, department, position, dob, gender);
        loadStaffDataFromMongoDB();
    }

    private void addStaffToMongoDB(String code, String name, String cccd, String phone, String department, String position, String dob, String gender) {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            MongoCollection<Document> collection = database.getCollection("Staff");

            Document document = new Document();
            document.put("code", code);
            document.put("name", name);
            document.put("cccd", cccd);
            document.put("phone", phone);
            document.put("department", department);
            document.put("position", position);
            document.put("dob", dob);
            document.put("gender", gender);

            collection.insertOne(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStaffInMongoDB(String code, String name, String cccd, String phone, String department, String position, String dob, String gender) {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            MongoCollection<Document> collection = database.getCollection("Staff");

            Document updatedDocument = new Document();
            updatedDocument.put("name", name);
            updatedDocument.put("cccd", cccd);
            updatedDocument.put("phone", phone);
            updatedDocument.put("department", department);
            updatedDocument.put("position", position);
            updatedDocument.put("dob", dob);
            updatedDocument.put("gender", gender);

            Document updateQuery = new Document("$set", updatedDocument);

            collection.updateOne(new Document("code", code), updateQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteSelectedStaff() {
        int selectedRow = staffTable.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nhân viên này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                deleteStaffFromDB(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteStaffFromDB(int selectedRow) {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            MongoCollection<Document> collection = database.getCollection("Staff");

            String code = (String) staffTable.getValueAt(selectedRow, 0);
            collection.deleteOne(new Document("code", code));

            DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
            model.removeRow(selectedRow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStaffDataFromMongoDB() {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            MongoCollection<Document> collection = database.getCollection("Staff");

            model = (DefaultTableModel) staffTable.getModel();
            model.setRowCount(0);

            for (Document doc : collection.find()) {
                String code = doc.getString("code");
                String name = doc.getString("name");
                String cccd = doc.getString("cccd");
                String phone = doc.getString("phone");
                String department = doc.getString("department");
                String position = doc.getString("position");
                String dob = doc.getString("dob");
                String gender = doc.getString("gender");

                model.addRow(new Object[]{code, name, cccd, phone, department, position, dob, gender});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateRandomCode() {
        Random rand = new Random();
        int randomNum = rand.nextInt(900) + 100;
        return "NV" + randomNum;
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchField;
    private GoodsTable staffTable;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField cccdField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JTextField departmentField;
    private javax.swing.JTextField positionField;
    private javax.swing.JTextField dobField;
    private javax.swing.JTextField genderField;
    private javax.swing.JTextField codeField;
    private DefaultTableModel model;
}
