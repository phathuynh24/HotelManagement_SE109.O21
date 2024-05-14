/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.myproject.forms;

import javax.swing.JTextField;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.myproject.models.Model_Customer;
import com.myproject.models.types.RoomStatus;
import com.myproject.swings.SearchText;
import java.awt.FlowLayout;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.bson.Document;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Projections;
import javax.swing.ImageIcon;

public class Form_Customer extends javax.swing.JPanel {

    public Form_Customer() {
        initComponents();
        fetchDataFromMongoDB();
    }

    private void initComponents() {
     
        searchField = new SearchText();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerTable = new com.myproject.swings.GoodsTable();
        jPanelAddCustomer = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCustomerCCTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jCustomerNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCustomerGenderTextField = new javax.swing.JComboBox();
        jCustomerPhoneTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCustomerDateTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jCustomerTypeTextField = new javax.swing.JComboBox();
        btnSearch = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        

        jScrollPane1.setBackground(new java.awt.Color(28,181,224));
        jCustomerGenderTextField.addItem("Nam");
        jCustomerGenderTextField.addItem("Nữ");
        
        jCustomerTypeTextField.addItem("Dưới 12 tuổi");
        jCustomerTypeTextField.addItem("12 đến 15 tuổi");
        jCustomerTypeTextField.addItem("16 đến 18 tuổi");
        jCustomerTypeTextField.addItem("Trên 18 tuổi");

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        customerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID khách", "Họ tên", "CCCD", "Giới tính", "SĐT", "Loại khách hàng", "Ngày sinh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        customerTable.setSelectionBackground(new java.awt.Color(28, 181, 224));
        customerTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(customerTable);

        jPanelAddCustomer.setBackground(new java.awt.Color(28, 181, 224));
        jPanelAddCustomer.setAutoscrolls(true);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Họ tên khách");
        
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CCCD");
        
        
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Mã khách hàng");
        
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Giới tính");

        jCustomerPhoneTextField.setToolTipText("");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SĐT");
        
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ngày sinh");
        
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Loại khách hàng");

        javax.swing.GroupLayout jPanelAddCustomerLayout = new javax.swing.GroupLayout(jPanelAddCustomer);
        jPanelAddCustomer.setLayout(jPanelAddCustomerLayout);
        
        jPanelAddCustomerLayout.setHorizontalGroup(
            jPanelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                .addGroup(jPanelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCustomerNameTextField)
                .addGroup(jPanelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCustomerCCTextField)
                        
                .addGroup(jPanelAddCustomerLayout.createSequentialGroup()
                    .addGroup(jPanelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCustomerGenderTextField, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCustomerPhoneTextField)
                
                .addGroup(jPanelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCustomerTypeTextField)
                        
                .addGroup(jPanelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCustomerDateTextField)
                        )))))))));
        
        jPanelAddCustomerLayout.setVerticalGroup(
            jPanelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAddCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)                   
                .addComponent(jCustomerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)                
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)                   
                .addComponent(jCustomerCCTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)                
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                
                .addGroup(jPanelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    
                .addGroup(jPanelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCustomerGenderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCustomerPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))                    
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)                   
                .addComponent(jCustomerTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)                   
                .addComponent(jCustomerDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)

            .addContainerGap(23, Short.MAX_VALUE))
        );

        btnSearch.setBackground(new java.awt.Color(28, 181, 224));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        
        btnAdd.setBackground(new java.awt.Color(28, 181, 224));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm khách");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        
        btnDelete.setBackground(new java.awt.Color(28, 181, 224));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Xóa khách");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        
        btnEdit.setBackground(new java.awt.Color(28, 181, 224));
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Sửa thông tin khách");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSearch)
                        .addGap(5)
                        .addComponent(btnAdd)
                        .addGap(5)
                        .addComponent(btnDelete)
                        .addGap(5)
                        .addComponent(btnEdit)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );      
    }
    
        private void btnSearchActionPerformed(java.awt.event.ActionEvent evt){
        String search = searchField.getText();
        searchCustomer(search); 
    }
    
    private void searchCustomer(String searchText) {
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        customerTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // Tìm kiếm theo thông tin người dùng nhập
    }
    
    private String generateCustomerID(MongoCollection<Document> collection) {
        FindIterable<Document> cursor = collection.find();
        
        String id = "KH10000";
        for (Document document : cursor) {           
            String temp = document.getString("CustomerID");           
            if (temp.compareTo(id) >0){
                id = temp;
            }
        }
        int num = Integer.parseInt(id.substring(2)) + 1;
        return "KH" + num ;
    }
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {                                         
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Chọn hoặc tạo database
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");

            // Chọn hoặc tạo collection
            MongoCollection<Document> collection = database.getCollection("Customer");
            
            //Tạo mã khách hàng
            String id = generateCustomerID(collection);
            jLabel7.setText(id);
            
            //Hiển thị dialog để người dùng thêm phòng
            ImageIcon icon = new ImageIcon();
            
            JOptionPane.showMessageDialog(this, jPanelAddCustomer, "Thêm khách hàng", 
                    JOptionPane.INFORMATION_MESSAGE, icon);

                // Lấy các giá trị từ dialog
                String name = jCustomerNameTextField.getText();
                String cccd = jCustomerCCTextField.getText();
                String phone = jCustomerPhoneTextField.getText();
                String gender = (String) jCustomerGenderTextField.getSelectedItem();
                String date = jCustomerDateTextField.getText();
                String type = (String) jCustomerTypeTextField.getSelectedItem();

                if ("".equals(name) || "".equals(phone) || "".equals(gender) || "".equals(date) || "".equals(type)
                        || "".equals(cccd)) {
                    JOptionPane.showMessageDialog(this, "Vui lòng không bỏ trống thông tin để thêm khách thành công!");
                } else {                        
                    //Thêm mới dữ liệu
                    collection.insertOne(     
                        new Document(new Document("CustomerID", id)
                            .append("CustomerName", name)
                            .append("CCCD", cccd)
                            .append("Gender", gender)
                            .append("Phone", phone)
                            .append("Type", type)
                            .append("CustomerDate", date)
                        )
                    );
                    fetchDataFromMongoDB();                   
                } 
            
            clearInput();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
        int confirm = javax.swing.JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa hàng này?", "Xác nhận xóa", javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            deleteSelectedData(selectedRow);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.", "Thông báo", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }
      
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
            int confirm = javax.swing.JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn chỉnh sửa hàng này?", "Xác nhận chỉnh sửa", javax.swing.JOptionPane.YES_NO_OPTION);
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            editSelectedData(selectedRow);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để chỉnh sửa.", "Thông báo", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void clearInput(){
        jCustomerNameTextField.setText("");
        jCustomerGenderTextField.setSelectedIndex(0);
        jCustomerPhoneTextField.setText("");
        jCustomerDateTextField.setText("");
        jCustomerTypeTextField.setSelectedIndex(0);
    }
    
    
    private void fetchDataFromMongoDB(){
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/")) {
            // Chọn cơ sở dữ liệu
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");
            // Chọn bảng
            MongoCollection<Document> collection = database.getCollection("Customer");
            FindIterable<Document> cursor = collection.find();
            
            //Reset cái table
            DefaultTableModel model = (DefaultTableModel)customerTable.getModel();
            model.setRowCount(0);
            
            // Tạo một Document mới chứa dữ liệu bạn muốn thêm            
            for (Document document : cursor) {
                String value1 = document.getString("CustomerID");
                String value2 = document.getString("CustomerName");
                String value7 = document.getString("CCCD");
                String value3 = document.getString("Gender");
                String value4 = document.getString("Phone");
                String value5 = document.getString("Type");
                String value6 = document.getString("CustomerDate");
                

                // Thêm hàng mới vào tableModel
                model.addRow(new Object[]{value1, value2, value7, value3, value4, value5, value6});
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    

    private void deleteSelectedData(int selectedRow) {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Chọn hoặc tạo database
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");

            // Chọn hoặc tạo collection
            MongoCollection<Document> collection = database.getCollection("Customer");

            // Lấy tên phòng từ hàng được chọn trong bảng
            String id = (String) customerTable.getValueAt(selectedRow, 0);

            // Xóa hàng được chọn từ MongoDB
            collection.deleteOne(new Document("CustomerID", id));                          

            // Xóa hàng được chọn từ bảng
            DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
            model.removeRow(selectedRow);
            
            
        } catch (Exception e) {
            e.printStackTrace();    
        }
    }
    
    private void editSelectedData(int selectedRow) {
        String uri = "mongodb+srv://HotelGroup:xfwl2Y6oahXJugda@cluster0.awr6sf9.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Chọn hoặc tạo database
            MongoDatabase database = mongoClient.getDatabase("Hotel_Management");

            // Chọn hoặc tạo collection
            MongoCollection<Document> collection = database.getCollection("Customer");

            // Lấy thông tin phòng từ hàng được chọn trong bảng        
            String id = (String) customerTable.getValueAt(selectedRow, 0);
            String name = (String) customerTable.getValueAt(selectedRow, 1);
            String cccd = (String) customerTable.getValueAt(selectedRow, 2);
    
            String phone = (String) customerTable.getValueAt(selectedRow, 4);

            String date = (String) customerTable.getValueAt(selectedRow, 6);
            
            jLabel7.setText(id);
            jCustomerNameTextField.setText(name);
            jCustomerCCTextField.setText(cccd);
            jCustomerPhoneTextField.setText(phone);
            jCustomerDateTextField.setText(date);
            
            //Hiển thị dialog để người dùng chỉnh sửa phòng
            ImageIcon icon = new ImageIcon();
            
            JOptionPane.showMessageDialog(this, jPanelAddCustomer, "Sửa thông tin khách hàng", 
                    JOptionPane.INFORMATION_MESSAGE, icon);
            
                // Lấy các giá trị mới từ dialog
                String newName = jCustomerNameTextField.getText();
                String newCccd = jCustomerCCTextField.getText();
                String newPhone = jCustomerPhoneTextField.getText();
                String newGender = (String) jCustomerGenderTextField.getSelectedItem();
                String newDate = jCustomerDateTextField.getText();
                String newType = (String) jCustomerTypeTextField.getSelectedItem();

                if ("".equals(newName) || "".equals(newPhone) || "".equals(newGender) || "".equals(newDate) 
                        || "".equals(newType) || "".equals(newCccd)) {
                    JOptionPane.showMessageDialog(this, "Vui lòng không bỏ trống thông tin để cập nhật thông tin khách thành công!");
                } else {    
                    // Cập nhật thông tin mới vào model của bảng
                    customerTable.setValueAt(newName, selectedRow, 1); 
                    customerTable.setValueAt(newCccd, selectedRow, 2);
                    customerTable.setValueAt(newGender, selectedRow, 3); 
                    customerTable.setValueAt(newPhone, selectedRow, 4); 
                    customerTable.setValueAt(newType, selectedRow, 5);
                    customerTable.setValueAt(newDate, selectedRow, 6);
                    
                    // Tìm và cập nhật dòng tương ứng trong cơ sở dữ liệu
                    collection.updateOne(
                        new Document("CustomerID", id),
                        new Document("$set", new Document("CustomerID", id)
                            .append("CustomerName", newName)
                            .append("CCCD", newCccd)
                            .append("CustomerGender", newGender)
                            .append("Phone", newPhone)
                            .append("Type", newType)                  
                            .append("CustomerDate", newDate)
                        )
                    );                   
                    clearInput();
                } 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    
    private javax.swing.JPanel jPanelAddCustomer;
    private javax.swing.JTextField jCustomerPhoneTextField;
    private javax.swing.JTextField jCustomerNameTextField;
    private javax.swing.JComboBox jCustomerGenderTextField;
    private javax.swing.JTextField jCustomerDateTextField;
    private javax.swing.JComboBox jCustomerTypeTextField;
    private javax.swing.JTextField jCustomerCCTextField;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable customerTable;
    private javax.swing.JTextField searchField;
}


