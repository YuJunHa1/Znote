import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Junha
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */


    public MainFrame() {
        initComponents();
        jTabbedPane.setUI(new BasicTabbedPaneUI() { 
	@Override 
	protected void installDefaults() { 
		super.installDefaults(); 
                highlight = new Color(255, 253, 208); 
		lightHighlight = new Color(255, 253, 208); 
		shadow = new Color(255, 253, 208); 
		darkShadow = new Color(255, 253, 208);
		focus = new Color(255, 253, 208); 
	}
        @Override 
        protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) { 
            return 260; // 탭의 가로 크기 설정 
	}
});
        getContentPane().setBackground(new Color(255, 253, 208));
        loginFrame.getContentPane().setBackground(new Color(255, 253, 208));
        joinFrame.getContentPane().setBackground(new Color(255, 253, 208));
        loginFrame.setSize(400, 300);
        joinFrame.setSize(420, 400);
        loginFrame.setVisible(true);
        jTabbedPane.setBackground(new Color(255, 253, 208));
        main_panel.setBackground(new Color(255, 253, 208));
        zettel_panel.setBackground(new Color(255, 253, 208));
        my_panel.setBackground(new Color(255, 253, 208));
        customizeTextField(login_tf_id);
        customizeTextField(login_tf_pw);
        customizeTextField(join_tf_id);
        customizeTextField(join_tf_pw);
        customizeTextField(join_tf_pw2);
        customizeTextField(join_tf_name);
        
    }
    
     private void customizeTextField(JTextField textField) {
        textField.setBackground(new Color(0, 0, 0, 0)); // 배경을 투명하게 설정
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // 밑줄
    }
    
     private void setUnderline(JLabel label, boolean addUnderline) {
    if (addUnderline) {
        label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
    } else {
        label.setBorder(null);
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked") 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginFrame = new javax.swing.JFrame();
        login_lbl_id = new javax.swing.JLabel();
        login_lbl_pw = new javax.swing.JLabel();
        login_tf_id = new javax.swing.JTextField();
        login_tf_pw = new javax.swing.JTextField();
        login_title = new javax.swing.JLabel();
        login_lbl_signup = new javax.swing.JLabel();
        login_lbl_signin = new javax.swing.JLabel();
        joinFrame = new javax.swing.JFrame();
        join_lbl_id = new javax.swing.JLabel();
        join_lbl_pw = new javax.swing.JLabel();
        join_tf_id = new javax.swing.JTextField();
        join_title = new javax.swing.JLabel();
        join_lbl_signup = new javax.swing.JLabel();
        join_tf_pw2 = new javax.swing.JTextField();
        join_tf_name = new javax.swing.JTextField();
        join_lbl_pw2 = new javax.swing.JLabel();
        join_lbl_name = new javax.swing.JLabel();
        join_tf_pw = new javax.swing.JTextField();
        jTabbedPane = new javax.swing.JTabbedPane();
        main_panel = new javax.swing.JPanel();
        zettel_panel = new javax.swing.JPanel();
        my_panel = new javax.swing.JPanel();

        login_lbl_id.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        login_lbl_id.setText("I D");

        login_lbl_pw.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        login_lbl_pw.setText("PW");

        login_tf_pw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_tf_pwActionPerformed(evt);
            }
        });

        login_title.setFont(new java.awt.Font("휴먼편지체", 0, 24)); // NOI18N
        login_title.setText("Login");

        login_lbl_signup.setFont(new java.awt.Font("휴먼편지체", 0, 16)); // NOI18N
        login_lbl_signup.setText("sign up");
        login_lbl_signup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login_lbl_signupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                login_lbl_signupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                login_lbl_signupMouseExited(evt);
            }
        });

        login_lbl_signin.setFont(new java.awt.Font("휴먼편지체", 0, 16)); // NOI18N
        login_lbl_signin.setText("sign in");
        login_lbl_signin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                login_lbl_signinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                login_lbl_signinMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                login_lbl_signinMouseExited(evt);
            }
        });

        javax.swing.GroupLayout loginFrameLayout = new javax.swing.GroupLayout(loginFrame.getContentPane());
        loginFrame.getContentPane().setLayout(loginFrameLayout);
        loginFrameLayout.setHorizontalGroup(
            loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginFrameLayout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(login_lbl_signup)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginFrameLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginFrameLayout.createSequentialGroup()
                        .addGroup(loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(login_lbl_signin)
                            .addGroup(loginFrameLayout.createSequentialGroup()
                                .addComponent(login_lbl_pw)
                                .addGap(18, 18, 18)
                                .addComponent(login_tf_pw, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(loginFrameLayout.createSequentialGroup()
                                .addComponent(login_lbl_id)
                                .addGap(18, 18, 18)
                                .addComponent(login_tf_id, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginFrameLayout.createSequentialGroup()
                        .addComponent(login_title)
                        .addGap(172, 172, 172))))
        );
        loginFrameLayout.setVerticalGroup(
            loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(login_title, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login_tf_id, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(login_lbl_id))
                .addGap(18, 18, 18)
                .addGroup(loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login_tf_pw, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(login_lbl_pw))
                .addGap(18, 18, 18)
                .addComponent(login_lbl_signin)
                .addGap(66, 66, 66)
                .addComponent(login_lbl_signup)
                .addGap(30, 30, 30))
        );

        join_lbl_id.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        join_lbl_id.setText("I D");

        join_lbl_pw.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        join_lbl_pw.setText("PW");

        join_title.setFont(new java.awt.Font("휴먼편지체", 0, 24)); // NOI18N
        join_title.setText("sign up");

        join_lbl_signup.setFont(new java.awt.Font("휴먼편지체", 0, 16)); // NOI18N
        join_lbl_signup.setText("sign up");
        join_lbl_signup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                join_lbl_signupMouseClicked(evt);
            }
        });

        join_tf_pw2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                join_tf_pw2ActionPerformed(evt);
            }
        });

        join_tf_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                join_tf_nameActionPerformed(evt);
            }
        });

        join_lbl_pw2.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        join_lbl_pw2.setText("Check PW");

        join_lbl_name.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        join_lbl_name.setText("Name");

        javax.swing.GroupLayout joinFrameLayout = new javax.swing.GroupLayout(joinFrame.getContentPane());
        joinFrame.getContentPane().setLayout(joinFrameLayout);
        joinFrameLayout.setHorizontalGroup(
            joinFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(joinFrameLayout.createSequentialGroup()
                .addGroup(joinFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(joinFrameLayout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(join_lbl_signup))
                    .addGroup(joinFrameLayout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(join_title)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, joinFrameLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(joinFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(join_lbl_pw, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(join_lbl_id, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(join_lbl_pw2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(join_lbl_name, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(joinFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(join_tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(join_tf_pw2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(join_tf_id, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(join_tf_pw, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        joinFrameLayout.setVerticalGroup(
            joinFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(joinFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(join_title, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(joinFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(join_tf_id, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(join_lbl_id))
                .addGap(18, 18, 18)
                .addGroup(joinFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(join_lbl_pw)
                    .addComponent(join_tf_pw, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(joinFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(join_tf_pw2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(join_lbl_pw2))
                .addGap(18, 18, 18)
                .addGroup(joinFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(join_tf_name, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(join_lbl_name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(join_lbl_signup)
                .addGap(30, 30, 30))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane.setToolTipText("");
        jTabbedPane.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane.addTab("main", main_panel);

        javax.swing.GroupLayout zettel_panelLayout = new javax.swing.GroupLayout(zettel_panel);
        zettel_panel.setLayout(zettel_panelLayout);
        zettel_panelLayout.setHorizontalGroup(
            zettel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        zettel_panelLayout.setVerticalGroup(
            zettel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane.addTab("Zettelkasten", zettel_panel);

        javax.swing.GroupLayout my_panelLayout = new javax.swing.GroupLayout(my_panel);
        my_panel.setLayout(my_panelLayout);
        my_panelLayout.setHorizontalGroup(
            my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        my_panelLayout.setVerticalGroup(
            my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane.addTab("myPage", my_panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void login_tf_pwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_tf_pwActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_login_tf_pwActionPerformed

    private void join_tf_pw2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_join_tf_pw2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_join_tf_pw2ActionPerformed

    private void join_tf_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_join_tf_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_join_tf_nameActionPerformed

    private void login_lbl_signinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_lbl_signinMouseClicked

//        String pwText = login_tf_pw.getText();
//        String idText = login_tf_id.getText();
//        DB_MAN DBM = new DB_MAN();
//        try{
//            DBM.dbOpen();
//            DBM.stmt.execute;
//        }
//        setVisible(true);
    }//GEN-LAST:event_login_lbl_signinMouseClicked

    private void login_lbl_signinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_lbl_signinMouseEntered
        // TODO add your handling code here:
        setUnderline(login_lbl_signin, true);
    }//GEN-LAST:event_login_lbl_signinMouseEntered

    private void login_lbl_signinMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_lbl_signinMouseExited
        // TODO add your handling code here:
        setUnderline(login_lbl_signin, false);
    }//GEN-LAST:event_login_lbl_signinMouseExited

    private void login_lbl_signupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_lbl_signupMouseExited
        // TODO add your handling code here:
        setUnderline(login_lbl_signup, false);
    }//GEN-LAST:event_login_lbl_signupMouseExited

    private void login_lbl_signupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_lbl_signupMouseEntered
        // TODO add your handling code here:
        setUnderline(login_lbl_signup, true);
    }//GEN-LAST:event_login_lbl_signupMouseEntered

    private void join_lbl_signupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_join_lbl_signupMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_join_lbl_signupMouseClicked

    private void login_lbl_signupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_lbl_signupMouseClicked
        // TODO add your handling code here:
        joinFrame.setVisible(true);
    }//GEN-LAST:event_login_lbl_signupMouseClicked

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JFrame joinFrame;
    private javax.swing.JLabel join_lbl_id;
    private javax.swing.JLabel join_lbl_name;
    private javax.swing.JLabel join_lbl_pw;
    private javax.swing.JLabel join_lbl_pw2;
    private javax.swing.JLabel join_lbl_signup;
    private javax.swing.JTextField join_tf_id;
    private javax.swing.JTextField join_tf_name;
    private javax.swing.JTextField join_tf_pw;
    private javax.swing.JTextField join_tf_pw2;
    private javax.swing.JLabel join_title;
    private javax.swing.JFrame loginFrame;
    private javax.swing.JLabel login_lbl_id;
    private javax.swing.JLabel login_lbl_pw;
    private javax.swing.JLabel login_lbl_signin;
    private javax.swing.JLabel login_lbl_signup;
    private javax.swing.JTextField login_tf_id;
    private javax.swing.JTextField login_tf_pw;
    private javax.swing.JLabel login_title;
    private javax.swing.JPanel main_panel;
    private javax.swing.JPanel my_panel;
    private javax.swing.JPanel zettel_panel;
    // End of variables declaration//GEN-END:variables
}
