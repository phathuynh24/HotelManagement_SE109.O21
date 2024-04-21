package com.myproject.forms;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.bson.Document;

public class Form_RoomType extends javax.swing.JPanel {

    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable roomTypeTable;

    public Form_RoomType() {
        initComponents();
        fetchDataFromMongoDB();
        setupUI();
        setupEvents();
    }

  private void setupUI() {
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.decode("#f0f0f0")); // Màu nền cho panel top
        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);
        add(topPanel, BorderLayout.NORTH);

        // Đặt màu nền cho table
        roomTypeTable.setBackground(Color.blue);

        // Đặt màu sắc cho các cột
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(Color.decode("#ffffff")); // Màu nền cho header của table
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Canh giữa text
        roomTypeTable.getTableHeader().setDefaultRenderer(headerRenderer);

        // Đặt màu sắc và canh giữa text cho các ô cột
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    component.setBackground(Color.decode("#f2f2f2")); // Màu nền cho hàng chẵn
                } else {
                    component.setBackground(Color.white); // Màu nền cho hàng lẻ
                }
                setHorizontalAlignment(SwingConstants.CENTER); // Canh giữa text
                return component;
            }
        };
        roomTypeTable.setDefaultRenderer(Object.class, cellRenderer);
        roomTypeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(roomTypeTable), BorderLayout.CENTER);
    }


    private void setupEvents() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi nhấn nút Thêm
                showAddDialog();
            }
        });

        editButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = roomTypeTable.getSelectedRow();
            if (selectedRow != -1) {
                // Lấy các giá trị từ hàng được chọn
                String roomTypeName = roomTypeTable.getValueAt(selectedRow, 0).toString();
                String roomTypeDescription = roomTypeTable.getValueAt(selectedRow, 1).toString();
                String roomTypePrice = roomTypeTable.getValueAt(selectedRow, 2).toString();

                // Hiển thị dialog sửa đổi với các giá trị từ hàng được chọn
                showEditDialog(roomTypeName, roomTypeDescription, roomTypePrice);
            } else {
                JOptionPane.showMessageDialog(Form_RoomType.this, "Vui lòng chọn một loại phòng để sửa.");
            }
        }
    });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi nhấn nút Xóa
                int selectedRow = roomTypeTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Xác nhận xóa và xóa loại phòng khỏi cơ sở dữ liệu
                    int option = JOptionPane.showConfirmDialog(Form_RoomType.this, "Bạn có chắc muốn xóa loại phòng này không?", "Xác nhận Xóa", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        deleteSelectedRoom();
                    }
                } else {
                    JOptionPane.showMessageDialog(Form_RoomType.this, "Vui lòng chọn một loại phòng để xóa.");
                }
            }
        });

     
    }
    

    private void showAddDialog() {
    JTextField nameField = new JTextField(30);
    JTextArea descriptionArea = new JTextArea(5,20);
    JTextField priceField = new JTextField(30);

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(10, 10, 10, 10);

    constraints.gridx = 0;
    constraints.gridy = 0;
    panel.add(new JLabel("Tên loại phòng:"), constraints);

    constraints.gridx = 1;
    panel.add(nameField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    panel.add(new JLabel("Giá loại phòng:"), constraints);

    constraints.gridx = 1;
    panel.add(priceField, constraints);


    constraints.gridx = 0;
    constraints.gridy = 2;
    panel.add(new JLabel("Mô tả loại phòng:"), constraints);

    constraints.gridx = 1;
    panel.add(new JScrollPane(descriptionArea), constraints);

    int result = JOptionPane.showConfirmDialog(this, panel, "Thêm loại phòng", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
        String roomTypeName = nameField.getText();
        String roomTypeDescription = descriptionArea.getText();
        String roomTypePrice = priceField.getText();

        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/")) {
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            MongoCollection<Document> collection = database.getCollection("RoomType");
            Document document = new Document("RoomTypeName", roomTypeName)
                    .append("Description", roomTypeDescription)
                    .append("Price", roomTypePrice);
            collection.insertOne(document);
            fetchDataFromMongoDB();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}

    private void showEditDialog(String roomTypeName, String roomTypeDescription, String roomTypePrice) {
    JTextField nameField = new JTextField(roomTypeName, 20);
    JTextArea descriptionArea = new JTextArea(roomTypeDescription, 5, 20);
    JTextField priceField = new JTextField(roomTypePrice, 10);

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(10, 10, 10, 10);

    constraints.gridx = 0;
    constraints.gridy = 0;
    panel.add(new JLabel("Tên loại phòng:"), constraints);

    constraints.gridx = 1;
    panel.add(nameField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    panel.add(new JLabel("Mô tả:"), constraints);

    constraints.gridx = 1;
    panel.add(new JScrollPane(descriptionArea), constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    panel.add(new JLabel("Giá loại phòng:"), constraints);

    constraints.gridx = 1;
    panel.add(priceField, constraints);

    int result = JOptionPane.showConfirmDialog(this, panel, "Sửa loại phòng", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
        // Lấy các giá trị mới từ dialog
        String newRoomTypeName = nameField.getText();
        String newRoomTypeDescription = descriptionArea.getText();
        String newRoomTypePrice = priceField.getText();

        // Cập nhật dữ liệu trong cơ sở dữ liệu
        updateRoomTypeInDatabase(roomTypeName, newRoomTypeName, newRoomTypeDescription, newRoomTypePrice);

        // Cập nhật lại JTable
        fetchDataFromMongoDB();
    }
}

private void updateRoomTypeInDatabase(String oldRoomTypeName, String newRoomTypeName, String newRoomTypeDescription, String newRoomTypePrice) {
    try (MongoClient mongoClient = MongoClients.create("mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/")) {
        MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
        MongoCollection<Document> collection = database.getCollection("RoomType");

        // Tìm và cập nhật dòng tương ứng trong cơ sở dữ liệu
        collection.updateOne(
                new Document("RoomTypeName", oldRoomTypeName),
                new Document("$set", new Document("RoomTypeName", newRoomTypeName)
                        .append("Description", newRoomTypeDescription)
                        .append("Price", newRoomTypePrice)
                )
        );
    } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
}
    private void deleteSelectedRoom() {
        // Lấy chỉ mục hàng được chọn trong JTable
        int selectedRow = roomTypeTable.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy giá trị cột RoomName tại hàng được chọn
            String roomName = roomTypeTable.getValueAt(selectedRow, 0).toString();
            
            try (MongoClient mongoClient = MongoClients.create("mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/")) {
                // Chọn cơ sở dữ liệu
                MongoDatabase database = mongoClient.getDatabase("Hotel_Management");

                // Chọn bảng
                MongoCollection<Document> collection = database.getCollection("RoomType");
                
                // Tạo một Document để xóa dựa trên RoomName
                Document query = new Document("RoomTypeName", roomName);
                
                // Xóa Document từ bảng
                collection.deleteOne(query);
                
                // Sau khi xóa từ cơ sở dữ liệu, cập nhật lại JTable
                fetchDataFromMongoDB();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(Form_RoomType.this, "Vui lòng chọn một loại phòng để xóa.");
        }
    }

    private void fetchDataFromMongoDB() {
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/")) {
            // Chọn cơ sở dữ liệu
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");

            // Chọn bảng
            MongoCollection<Document> collection = database.getCollection("RoomType");

            // Lấy dữ liệu từ MongoDB
            var documents = collection.find().into(new ArrayList<>());

            // Tạo DefaultTableModel để chứa dữ liệu
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Loại phòng");
            model.addColumn("Mô tả");
            model.addColumn("Giá");

            // Thêm dữ liệu từ MongoDB vào DefaultTableModel
            for (Document doc : documents) {
                model.addRow(new Object[]{
                    doc.getString("RoomTypeName"),
                    doc.getString("Description"),
                    doc.getString("Price")
                });
            }

            // Cập nhật JTable với dữ liệu mới
            roomTypeTable.setModel(model);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private void initComponents() {
        addButton = new JButton("Thêm");
        editButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        roomTypeTable = new JTable();

      // Thiết lập màu sắc cho các button
        addButton.setBackground(Color.decode("#4CAF50")); // Màu xanh lá cây
        addButton.setForeground(Color.white); // Màu chữ trắng
        editButton.setBackground(Color.decode("#FFC107")); // Màu cam
        editButton.setForeground(Color.black); // Màu chữ đen
        deleteButton.setBackground(Color.decode("#F44336")); // Màu đỏ
        deleteButton.setForeground(Color.white); // Màu chữ trắng


        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(roomTypeTable, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(addButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(editButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(deleteButton)))
                                .addContainerGap())
                                        );
        
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(addButton)
                                        .addComponent(editButton)
                                        .addComponent(deleteButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roomTypeTable, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }
}
