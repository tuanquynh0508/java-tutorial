
import java.io.FileReader;
import java.util.Iterator;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.ShortNumberInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nntuan
 */
public class tool extends javax.swing.JFrame {

    private JSONArray countries;
    
    /**
     * Creates new form tool
     */
    public tool() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupTypePhone = new javax.swing.ButtonGroup();
        selCountry = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        btnCheck = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResult = new javax.swing.JTextArea();
        radPhoneTypeNormal = new javax.swing.JRadioButton();
        radPhoneTypeShort = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Kiểm tra số điện thoại");

        jLabel2.setText("Mã nước:");

        jLabel3.setText("Số điện thoại:");

        btnCheck.setText("Kiểm tra");
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        txtResult.setEditable(false);
        txtResult.setColumns(20);
        txtResult.setLineWrap(true);
        txtResult.setRows(5);
        jScrollPane1.setViewportView(txtResult);

        btnGroupTypePhone.add(radPhoneTypeNormal);
        radPhoneTypeNormal.setSelected(true);
        radPhoneTypeNormal.setText("Bình thường");

        btnGroupTypePhone.add(radPhoneTypeShort);
        radPhoneTypeShort.setText("Ngắn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCheck)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(radPhoneTypeNormal)
                                        .addGap(18, 18, 18)
                                        .addComponent(radPhoneTypeShort))
                                    .addComponent(txtPhone)
                                    .addComponent(selCountry, 0, 292, Short.MAX_VALUE))))
                        .addGap(0, 211, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radPhoneTypeNormal)
                    .addComponent(radPhoneTypeShort))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnCheck)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        // TODO add your handling code here:
        checkPhoneNumber();
    }//GEN-LAST:event_btnCheckActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        JSONParser parser = new JSONParser();
        try {
            File file = new File(getClass().getResource("assets/country-phone-code.json").toURI());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            Object json = parser.parse(reader);
            countries = (JSONArray) json;
            Iterator <JSONObject> iterator = countries.iterator();
            while (iterator.hasNext()) {
                JSONObject country = iterator.next();

                String name = (String) country.get("name");
                String dialCode = (String) country.get("dial_code");
                String code = (String) country.get("code");
                
                selCountry.addItem(name);
            }
            selCountry.setSelectedItem("Vietnam");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_formWindowOpened
    
    private void checkPhoneNumber()
    {
        String code = getCountryCode((String)selCountry.getSelectedItem());
        String phone = txtPhone.getText();
        String displayResult = "";
        
        if(btnGroupTypePhone.getSelection() == radPhoneTypeNormal.getModel()) {
            displayResult = checkPhoneNumberNormal(phone, code);
        } else if(btnGroupTypePhone.getSelection() == radPhoneTypeShort.getModel()) {
            displayResult = checkPhoneNumberShort(phone, code);
        }
        
        txtResult.setText(displayResult);
    }
    
    private String checkPhoneNumberNormal(String phone, String code)
    {
        String displayResult;
        
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            PhoneNumber swissNumberProto = phoneUtil.parse(phone, code.toUpperCase());
            boolean isValid = phoneUtil.isValidNumber(swissNumberProto);
            if (isValid == true) {
                // Produces "+41 44 668 18 00"
                displayResult = "INTERNATIONAL: " + phoneUtil.format(swissNumberProto, PhoneNumberFormat.INTERNATIONAL) + "\n";
                // Produces "044 668 18 00"
                displayResult += "NATIONAL: " + phoneUtil.format(swissNumberProto, PhoneNumberFormat.NATIONAL) + "\n";
                // Produces "+41446681800"
                displayResult += "E164: " + phoneUtil.format(swissNumberProto, PhoneNumberFormat.E164) + "\n";
            } else {
                displayResult = "Phone invalid";
            }
        } catch (NumberParseException e) {
            displayResult = "Error: " + e.toString();
        }
        
        return displayResult;
    }
    
    private String checkPhoneNumberShort(String phone, String code)
    {
        String displayResult;
        
        ShortNumberInfo shortInfo = ShortNumberInfo.getInstance();
        
        try {
            boolean isValid = shortInfo.isEmergencyNumber(phone, code.toUpperCase());
            if (isValid == true) {
                displayResult = "Phone valid";
            } else {
                displayResult = "Phone invalid";
            }
        } catch (Exception e) {
            displayResult = "Error: " + e.toString();
        }
        
        return displayResult;
    }
    
    private String getDialCode(String countryName)
    {
        String dialCode = "";
        Iterator <JSONObject> iterator = countries.iterator();
        while (iterator.hasNext()) {
            JSONObject country = iterator.next();
            if(countryName.equals((String) country.get("name"))) {
                dialCode = (String) country.get("dial_code");
                break;
            }
        }
        return dialCode;
    }
    
    private String getCountryCode(String countryName)
    {
        String code = "";
        Iterator <JSONObject> iterator = countries.iterator();
        while (iterator.hasNext()) {
            JSONObject country = iterator.next();
            if(countryName.equals((String) country.get("name"))) {
                code = (String) country.get("code");
                break;
            }
        }
        return code;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tool().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheck;
    private javax.swing.ButtonGroup btnGroupTypePhone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radPhoneTypeNormal;
    private javax.swing.JRadioButton radPhoneTypeShort;
    private javax.swing.JComboBox<String> selCountry;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextArea txtResult;
    // End of variables declaration//GEN-END:variables
}
