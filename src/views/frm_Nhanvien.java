/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Chucvu;
import models.Users;
import repositorys.IUsersReposytory;
import repositorys.imp.UsersReposytory;
import services.IChucvuService;
import services.imp.ChucVuService;
import services.imp.UsersService;
import viewmodels.UsersViewmodel;
import services.IUsersService;

/**
 *
 * @author hungh
 */
public class frm_Nhanvien extends javax.swing.JPanel {

    DefaultTableModel defaultTableModel;
    DefaultComboBoxModel defaultComboBoxModel;
    private final IUsersService nhanVienService;
    private final IChucvuService CVService;

    public frm_Nhanvien() {
        initComponents();
        nhanVienService = new UsersService();
        CVService = new ChucVuService();
        inittable();
        List<Chucvu> cvv = CVService.getAllChucVu();
        cbochucvu.setModel(new DefaultComboBoxModel((cvv.toArray())));
//        txtTaikhoan.setEditable(true);
        defaultTableModel = (DefaultTableModel) tblnhanvien.getModel();
        
        loaddata();
    }

    private void inittable() {
        DefaultTableModel model = (DefaultTableModel) tblnhanvien.getModel();

        model.addColumn("STT");
        model.addColumn("Họ");
        model.addColumn("Tên đệm");
        model.addColumn("Tên");
        model.addColumn("Ngày sinh");
        model.addColumn("Giới tính");
        model.addColumn("SĐT");
        model.addColumn("Tài Khoản");
        model.addColumn("Email");
        model.addColumn("Chức Vụ");
        model.addColumn("Trạng thái");
    }

    public void loaddata() {
        defaultTableModel.setRowCount(0);
        List<UsersViewmodel> nvv = nhanVienService.getAllNhanVien();
        int stt = 1;
        for (UsersViewmodel x : nvv) {
            defaultTableModel.addRow(new Object[]{
                stt,
                x.getHo(),
                x.getTendem(),
                x.getTen(),
                x.getNgaysinh(),
                x.getGioitinh() == 1 ? "Nam" : "Nữ",
                x.getSdt(),
                x.getTk(),
                x.getEmail(),
                x.getChucVu().getTen(),
                x.getTT() == 1 ? "Làm việc" : "Nghỉ Làm"
            });
            stt++;
        }
        lblTongnv.setText("Tổng nhân viên : " + nvv.size());
    }

    public void ClearForm() {
        txtten.setText("");
        txttendem.setText("");
        txtho.setText("");
        txtTaikhoan.setText("");

        txtemail.setText("");
        txtsdt.setText("");
        buttonGroup1.clearSelection();
        chk_tt.setSelected(false);
        cbochucvu.setSelectedIndex(0);
        datengaysinh.setDate(null);
        
        loaddata();
    }

    public void Showtable() {
        try {
            Integer row = tblnhanvien.getSelectedRow();

            txtho.setText(tblnhanvien.getValueAt(row, 1).toString());
            txttendem.setText(tblnhanvien.getValueAt(row, 2).toString());
            txtten.setText(tblnhanvien.getValueAt(row, 3).toString());
            String gt = (tblnhanvien.getValueAt(row, 5).toString());
            if (gt == "Nam") {
                rd_nam.setSelected(true);
            } else {
                rd_nu.setSelected(true);
            }
            String tt = (tblnhanvien.getValueAt(row, 10).toString());
            if (tt == "Làm việc") {
                chk_tt.setSelected(true);
            } else if (tt == "Nghỉ Làm") {
                chk_tt.setSelected(false);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse((String) tblnhanvien.getValueAt(row, 4));
            datengaysinh.setDate(date);

            txtsdt.setText(tblnhanvien.getValueAt(row, 6).toString());
            txtTaikhoan.setText(tblnhanvien.getValueAt(row, 7).toString());
            txtemail.setText(tblnhanvien.getValueAt(row, 8).toString());

        } catch (ParseException ex) {
            Logger.getLogger(frm_Nhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private UsersViewmodel getInputForm() {
        String sdt = "(0\\d{9})";
        String mail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        UsersViewmodel nv = new UsersViewmodel();
        Pattern p =  Pattern.compile("^[0-9]+$");
// Tên
        try {
            if (txtten.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên!");
                return null;
            }
        } catch (Exception e) {
        }
        if (p.matcher(txtten.getText()).find()==true) {
            JOptionPane.showMessageDialog(this, "Tên không được nhập số");
            return null;
        }
        if (txtten.getText().length()>30) {
            JOptionPane.showMessageDialog(this, "Tên không được quá 30 kí tự");
            return null;
        }
// Tên Đệm
        try {
            if (txttendem.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên đệm!");
                return null;
            }
        } catch (Exception e) {
        }
        if (p.matcher(txttendem.getText()).find()==true) {
            JOptionPane.showMessageDialog(this, "Tên đệm không được nhập số");
            return null;
        }
        if (txttendem.getText().length()>30) {
            JOptionPane.showMessageDialog(this, "Tên đệm không được quá 30 kí tự");
            return null;
        }
// Họ
        try {
            if (txtho.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập họ!");
                return null;
            }
        } catch (Exception e) {
        }
        if (p.matcher(txtho.getText()).find()==true) {
            JOptionPane.showMessageDialog(this, "Họ không được nhập số");
            return null;
        }
        if (txtho.getText().length()>30) {
            JOptionPane.showMessageDialog(this, "Họ không được quá 30 kí tự");
            return null;
        }
// Ngày Sinh        
        try {
            if (datengaysinh.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Bạn chưa Chọn ngày sinh!");
                return null;
            }
        } catch (Exception e) {
        }
// SĐT
        try {
            if (txtsdt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập sdt!");
                return null;
            }
        } catch (Exception e) {

        }

        try {
            if (!txtsdt.getText().matches(sdt)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại của bạn chưa đúng định dạng");
                return null;
            }
        } catch (Exception e) {
        }

        if (nhanVienService.kiemtrasdt(txtsdt.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Số điện thoại của bạn đã tồn tại");
            return null;
        }
// TÀi Khoản
        try {
            if (txtTaikhoan.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Nhập Tài Khoản");
                return null;
            }
        } catch (Exception e) {
        }
        if (nhanVienService.kiemtratk(txtTaikhoan.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản đã tồn tại");
            return null;
        }
// Email        
        try {
            if (txtemail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa nhập email!");
                return null;
            }
        } catch (Exception e) {
        }

        try {
            if (!txtemail.getText().matches(mail)) {
                JOptionPane.showMessageDialog(this, "Email của bạn chưa đúng định dạng");
                return null;
            }
        } catch (Exception e) {
        }
        if (nhanVienService.kiemtra(txtemail.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Email đã tồn tại");
            return null;
        }

        nv.setTen(txtten.getText());
        nv.setTendem(txttendem.getText());
        nv.setHo(txtho.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(datengaysinh.getDate());
        nv.setNgaysinh(date);
        Integer gt;
        if (rd_nam.isSelected()) {
            gt = 1;
        } else {
            gt = 0;
        }
        nv.setGioitinh(gt);
        nv.setSdt(txtsdt.getText());
        nv.setTk(txtTaikhoan.getText());
        nv.setMk("1");
        nv.setEmail(txtemail.getText());
        Chucvu cvv = (Chucvu) cbochucvu.getSelectedItem();
        nv.setChucVu(cvv);
        if (chk_tt.isSelected()) {
            nv.setTT(1);
        } else {
            nv.setTT(0);
        }
        return nv;
    }

    public Integer getNhanVienSelectTedRow() {
        Integer row = tblnhanvien.getSelectedRow();
        Integer id = (Integer) tblnhanvien.getValueAt(row, 0);
        return id;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelGradiente1 = new swing.PanelGradiente();
        jLabel1 = new javax.swing.JLabel();
        txtten = new swing.MyTextField();
        jLabel2 = new javax.swing.JLabel();
        txttendem = new swing.MyTextField();
        jLabel3 = new javax.swing.JLabel();
        txtho = new swing.MyTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtsdt = new swing.MyTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTaikhoan = new swing.MyTextField();
        jLabel8 = new javax.swing.JLabel();
        txtemail = new swing.MyTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnhanvien = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        rd_nu = new javax.swing.JRadioButton();
        rd_nam = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        chk_tt = new javax.swing.JCheckBox();
        lblTongnv = new javax.swing.JLabel();
        btnthem = new swing.MyButton();
        btncapnhat = new swing.MyButton();
        panelBorder2 = new swing.PanelBorder();
        searchtxt = new swing.SearchText();
        lbl_search = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbochucvu = new javax.swing.JComboBox<>();
        datengaysinh = new com.toedter.calendar.JDateChooser();
        btnlmmoi = new swing.MyButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1010, 640));

        panelGradiente1.setColorPrimario(new java.awt.Color(204, 204, 204));
        panelGradiente1.setColorSecundario(new java.awt.Color(204, 204, 204));
        panelGradiente1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tên");
        panelGradiente1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 220, 20));
        panelGradiente1.add(txtten, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 220, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tên đệm");
        panelGradiente1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 220, 20));
        panelGradiente1.add(txttendem, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 220, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Họ");
        panelGradiente1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 220, 20));
        panelGradiente1.add(txtho, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 220, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Ngày sinh");
        panelGradiente1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 220, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("SĐT");
        panelGradiente1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 220, 20));
        panelGradiente1.add(txtsdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 220, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Tài Khoản");
        panelGradiente1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 220, 20));
        panelGradiente1.add(txtTaikhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 220, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Giới tính");
        panelGradiente1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 60, 20));
        panelGradiente1.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 220, 40));

        tblnhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblnhanvien.setGridColor(new java.awt.Color(255, 255, 255));
        tblnhanvien.setRowHeight(25);
        tblnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnhanvienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblnhanvien);

        panelGradiente1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 990, 250));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Trạng thái");
        panelGradiente1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 60, 20));

        rd_nu.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(rd_nu);
        rd_nu.setText("Nữ");
        panelGradiente1.add(rd_nu, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, -1, -1));

        rd_nam.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(rd_nam);
        rd_nam.setSelected(true);
        rd_nam.setText("Nam");
        panelGradiente1.add(rd_nam, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Email");
        panelGradiente1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 220, 20));

        chk_tt.setBackground(new java.awt.Color(204, 204, 204));
        chk_tt.setText("Làm việc");
        panelGradiente1.add(chk_tt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 90, -1));

        lblTongnv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTongnv.setForeground(new java.awt.Color(255, 0, 0));
        lblTongnv.setText("Tổng nhân viên :");
        panelGradiente1.add(lblTongnv, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 200, -1));

        btnthem.setBackground(new java.awt.Color(125, 224, 237));
        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btnthem.setText("Thêm");
        btnthem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });
        panelGradiente1.add(btnthem, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, 120, 40));

        btncapnhat.setBackground(new java.awt.Color(125, 224, 237));
        btncapnhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/floppy-disk.png"))); // NOI18N
        btncapnhat.setText("Cập nhật");
        btncapnhat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btncapnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapnhatActionPerformed(evt);
            }
        });
        panelGradiente1.add(btncapnhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 90, 120, 40));

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));

        searchtxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                searchtxtCaretUpdate(evt);
            }
        });
        panelBorder2.add(searchtxt);
        searchtxt.setBounds(10, 0, 240, 40);

        lbl_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search_24px.png"))); // NOI18N
        lbl_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_searchMouseClicked(evt);
            }
        });
        panelBorder2.add(lbl_search);
        lbl_search.setBounds(260, 0, 40, 40);

        panelGradiente1.add(panelBorder2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 320, 300, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Chức vụ");
        panelGradiente1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 220, 20));

        cbochucvu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        panelGradiente1.add(cbochucvu, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 220, 40));

        datengaysinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));
        datengaysinh.setDateFormatString("yyyy-MM-dd");
        panelGradiente1.add(datengaysinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 220, 40));

        btnlmmoi.setBackground(new java.awt.Color(125, 224, 237));
        btnlmmoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/refresh.png"))); // NOI18N
        btnlmmoi.setText("Làm Mới");
        btnlmmoi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnlmmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlmmoiActionPerformed(evt);
            }
        });
        panelGradiente1.add(btnlmmoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 160, 120, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradiente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        UsersViewmodel nv = getInputForm();
        if (nv == null) {
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm ?","Add",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (nhanVienService.add(nv) != false) {
                JOptionPane.showMessageDialog(this, "Thêm Thành Công");
                loaddata();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm Thất Bại");
            }
        }

    }//GEN-LAST:event_btnthemActionPerformed

    private void tblnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnhanvienMouseClicked

        try {
            int index = tblnhanvien.getSelectedRow();
            String gt = (String) tblnhanvien.getValueAt(index, 5);
            String tt = (String) tblnhanvien.getValueAt(index, 10);
            int trangthai = tt.equals("Làm việc") ? 1 : 0;
            int gioiitinh = gt.equals("Nam") ? 1 : 0;
            txtho.setText(tblnhanvien.getValueAt(index, 1) + "");
            txtten.setText(tblnhanvien.getValueAt(index, 3) + "");
            txttendem.setText(tblnhanvien.getValueAt(index, 2) + "");
            txtsdt.setText(tblnhanvien.getValueAt(index, 6) + "");
            txtemail.setText(tblnhanvien.getValueAt(index, 8) + "");
            txtTaikhoan.setText(tblnhanvien.getValueAt(index, 7) + "");
//            txtMatkhau.setText(tblnhanvien.getValueAt(index, 8) + "");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse((String) tblnhanvien.getValueAt(index, 4));
            datengaysinh.setDate(date1);
            chk_tt.setSelected(trangthai == 1 ? true : false);
            
            if (gioiitinh == 1) {
                rd_nam.setSelected(true);
            } else {
                rd_nu.setSelected(true);
            }
            if (tblnhanvien.getValueAt(index, 9).toString().equals("Quản lý")) {
                cbochucvu.setSelectedIndex(0);
            } else {
                cbochucvu.setSelectedIndex(1);
            }

        } catch (ParseException ex) {
            Logger.getLogger(frm_Nhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tblnhanvienMouseClicked

    private void btncapnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapnhatActionPerformed
        // TODO add your handling code here:
        UsersViewmodel nv = new UsersViewmodel();
        Integer row = tblnhanvien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Bạn Cần Chọn 1 Dòng Muốn Sửa!");
            return;
        }
        
        nv.setTen(txtten.getText());
        nv.setTendem(txttendem.getText());
        nv.setHo(txtho.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(datengaysinh.getDate());
        nv.setNgaysinh(date);
        Integer gt;
        if (rd_nam.isSelected()) {
            gt = 1;
        } else {
            gt = 0;
        }
        nv.setGioitinh(gt);
        nv.setSdt(txtsdt.getText());
        nv.setTk(txtTaikhoan.getText());
        nv.setMk("1");
        nv.setEmail(txtemail.getText());
        Chucvu cvv = (Chucvu) cbochucvu.getSelectedItem();
        nv.setChucVu(cvv);
        if (chk_tt.isSelected()) {
            nv.setTT(1);
        } else {
            nv.setTT(0);
        }

        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa ?","Update",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            IUsersReposytory usersReposytory = new UsersReposytory();
            List<Users> lst = usersReposytory.getAllNhanVien();
            if (nhanVienService.update(nv, lst.get(row).getId()) != false) {
                JOptionPane.showMessageDialog(this, "Sửa Thành Công");
                loaddata();
            } else {
                JOptionPane.showMessageDialog(this, "Sửa Thất Bại");
                Showtable();
            }
        }
    }//GEN-LAST:event_btncapnhatActionPerformed

    private void btnlmmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlmmoiActionPerformed
        //        loaddata();
        ClearForm();
        loaddata();
    }//GEN-LAST:event_btnlmmoiActionPerformed

    private void lbl_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_searchMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_lbl_searchMouseClicked

    private void searchtxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_searchtxtCaretUpdate
        // TODO add your handling code here:
        defaultTableModel.setRowCount(0);
        int stt = 1;
        for (UsersViewmodel x : nhanVienService.SearchNVV(searchtxt.getText())) {
            defaultTableModel.addRow(new Object[]{
                stt,
                x.getHo(),
                x.getTendem(),
                x.getTen(),
                x.getNgaysinh(),
                x.getGioitinh() == 1 ? "Nam" : "Nữ",
                x.getSdt(),
                x.getTk(),
                x.getMk(),
                x.getEmail(),
                x.getChucVu().getTen(),
                x.getTT() == 1 ? "Làm việc" : "Nghỉ Làm"
            });
            stt++;
        }
    }//GEN-LAST:event_searchtxtCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.MyButton btncapnhat;
    private swing.MyButton btnlmmoi;
    private swing.MyButton btnthem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbochucvu;
    private javax.swing.JCheckBox chk_tt;
    private com.toedter.calendar.JDateChooser datengaysinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTongnv;
    private javax.swing.JLabel lbl_search;
    private swing.PanelBorder panelBorder2;
    private swing.PanelGradiente panelGradiente1;
    private javax.swing.JRadioButton rd_nam;
    private javax.swing.JRadioButton rd_nu;
    private swing.SearchText searchtxt;
    private javax.swing.JTable tblnhanvien;
    private swing.MyTextField txtTaikhoan;
    private swing.MyTextField txtemail;
    private swing.MyTextField txtho;
    private swing.MyTextField txtsdt;
    private swing.MyTextField txtten;
    private swing.MyTextField txttendem;
    // End of variables declaration//GEN-END:variables
}
