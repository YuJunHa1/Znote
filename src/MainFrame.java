import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
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
    public void updateFontForAllComponents(Container container, Font font) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setFont(font);
            if (component instanceof Container) {
                updateFontForAllComponents((Container) component, font);
            }
        }
    }
    
    
    // DAO 에서 데이터 get 해온 후 이름 set
     public void setUser(String userID) {
        System.out.println("setUser 호출됨. userID: " + userID);

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUser(userID);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (user != null) {
            mypage_lb_name.setText(user.getUserName());
            mypage_lb_createdat.setText(user.getCreatedAt().format(formatter) + " 가입"); 
            // userModify 페이지
            userModify_lbl_id1.setText(user.getUserID());
        } else {
            mypage_lb_name.setText("사용자 정보 없음");
            mypage_lb_createdat.setText("사용자 정보 없음");
        }
    }
    
    public MainFrame() {
        initComponents();
        //sizeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new Integer[] {16, 18, 20}));
        //탭펜 디자인 설정
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
  
        //프레임들의 크기 및 위치 설정
});     writeFrame.setSize(600, 400);
        loginFrame.setSize(400, 300);
        joinFrame.setSize(420, 400);
        userModifyFrame.setSize(420, 420);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        joinFrame.setLocationRelativeTo(null);
        loginFrame.setLocationRelativeTo(null);
        writeFrame.setLocationRelativeTo(null);
        userModifyFrame.setLocationRelativeTo(null);
        
        // mypage_lbl_image 경로 // 
        String imagePath = "src/siba.jpg";  // 상대 경로
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage(); // 원본 이미지 가져오기
        Image resizedImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH); // 크기 조정
        ImageIcon resizedIcon = new ImageIcon(resizedImage); // JLabel에 사용할 새로운 아이콘 생성
        mypage_lbl_image.setIcon(resizedIcon);
        // mypage_lbl_image 경로 //
        
        //포스트잇 색으로 색상 설정
        getContentPane().setBackground(new Color(255, 253, 208));
        loginFrame.getContentPane().setBackground(new Color(255, 253, 208));
        joinFrame.getContentPane().setBackground(new Color(255, 253, 208));
        userModifyFrame.getContentPane().setBackground(new Color(255, 253, 208));
        jTabbedPane.setBackground(new Color(255, 253, 208));
        main_panel.setBackground(new Color(255, 253, 208));
        zettel_panel.setBackground(new Color(255, 253, 208));
        my_panel.setBackground(new Color(255, 253, 208));
        lst_post.setBackground(new Color(255, 253, 208));
        
        //리스트 테두리
        lst_post.setBorder(null);
        jScrollPane1.setBorder(null);
        
        //텍스트 필드 배경 투명하게, 테두리 X, 밑줄 치기
        customizeTextField(login_tf_id);
        customizeTextField(login_tf_pw);
        customizeTextField(join_tf_id);
        customizeTextField(join_tf_pw);
        customizeTextField(join_tf_pw2);
        customizeTextField(join_tf_name);
        
        loginFrame.setVisible(true);
    }
    
    //텍스트필드 배경 투명하게, 테두리 없애고, 밑줄치는 메서드
     private void customizeTextField(JTextField textField) {
        textField.setBackground(new Color(0, 0, 0, 0)); // 배경을 투명하게 설정
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // 밑줄
    }
    
    //레이블 마우스 올렸을 때 밑줄 긋기
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
        writeFrame = new javax.swing.JFrame();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        userModifyFrame = new javax.swing.JFrame();
        join_lbl_id1 = new javax.swing.JLabel();
        userModify_lbl_pw1 = new javax.swing.JLabel();
        join_title1 = new javax.swing.JLabel();
        userModify_lbl_modify = new javax.swing.JLabel();
        userModify_tf_pw2 = new javax.swing.JTextField();
        userModify_tf_name1 = new javax.swing.JTextField();
        userModify_lbl_pw2 = new javax.swing.JLabel();
        userModify_lbl_name1 = new javax.swing.JLabel();
        userModify_tf_pw1 = new javax.swing.JTextField();
        userModify_lbl_id1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        main_panel = new javax.swing.JPanel();
        lbl_write = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lst_post = new javax.swing.JList<>();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        zettel_panel = new javax.swing.JPanel();
        my_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        mypage_lb_createdat = new javax.swing.JLabel();
        mypage_lbl_image = new javax.swing.JLabel();
        fontComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        sizeComboBox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        mypage_lb_name = new javax.swing.JLabel();
        mypage_lb_modify = new javax.swing.JLabel();
        mypage_btn_change = new javax.swing.JButton();

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
        join_lbl_signup.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                join_lbl_signupAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        join_lbl_signup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                join_lbl_signupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                join_lbl_signupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                join_lbl_signupMouseExited(evt);
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
        join_lbl_pw2.setText("PW Check");

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

        jTextField1.setFont(new java.awt.Font("휴먼편지체", 0, 24)); // NOI18N
        jTextField1.setText("jTextField1");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("휴먼편지체", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel1.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel1.setText("저장하기");

        javax.swing.GroupLayout writeFrameLayout = new javax.swing.GroupLayout(writeFrame.getContentPane());
        writeFrame.getContentPane().setLayout(writeFrameLayout);
        writeFrameLayout.setHorizontalGroup(
            writeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(writeFrameLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(writeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(writeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField1)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        writeFrameLayout.setVerticalGroup(
            writeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(writeFrameLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        join_lbl_id1.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        join_lbl_id1.setText("I D");

        userModify_lbl_pw1.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        userModify_lbl_pw1.setText("PW");

        join_title1.setFont(new java.awt.Font("휴먼편지체", 0, 24)); // NOI18N
        join_title1.setText("사용자 정보 변경 페이지");

        userModify_lbl_modify.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        userModify_lbl_modify.setText("변경 완료");
        userModify_lbl_modify.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                userModify_lbl_modifyAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        userModify_lbl_modify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userModify_lbl_modifyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userModify_lbl_modifyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                userModify_lbl_modifyMouseExited(evt);
            }
        });

        userModify_tf_pw2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userModify_tf_pw2ActionPerformed(evt);
            }
        });

        userModify_tf_name1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userModify_tf_name1ActionPerformed(evt);
            }
        });

        userModify_lbl_pw2.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        userModify_lbl_pw2.setText("PW Check");

        userModify_lbl_name1.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        userModify_lbl_name1.setText("Name");

        userModify_lbl_id1.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel3.setText("              당신의 정보를 변경 하십시오 :)");

        javax.swing.GroupLayout userModifyFrameLayout = new javax.swing.GroupLayout(userModifyFrame.getContentPane());
        userModifyFrame.getContentPane().setLayout(userModifyFrameLayout);
        userModifyFrameLayout.setHorizontalGroup(
            userModifyFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userModifyFrameLayout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(userModify_lbl_modify)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userModifyFrameLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(userModifyFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userModifyFrameLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userModifyFrameLayout.createSequentialGroup()
                        .addGroup(userModifyFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userModify_lbl_pw1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(join_lbl_id1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userModify_lbl_pw2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userModify_lbl_name1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(userModifyFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(join_title1)
                            .addComponent(userModify_tf_name1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(userModify_tf_pw2, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(userModify_tf_pw1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                            .addComponent(userModify_lbl_id1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33))))
        );
        userModifyFrameLayout.setVerticalGroup(
            userModifyFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userModifyFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(join_title1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(userModifyFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(join_lbl_id1)
                    .addComponent(userModify_lbl_id1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addGroup(userModifyFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userModify_lbl_pw1)
                    .addComponent(userModify_tf_pw1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(userModifyFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userModify_tf_pw2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userModify_lbl_pw2))
                .addGap(18, 18, 18)
                .addGroup(userModifyFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userModify_tf_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userModify_lbl_name1))
                .addGap(35, 35, 35)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(userModify_lbl_modify)
                .addGap(28, 28, 28))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane.setToolTipText("");
        jTabbedPane.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N

        lbl_write.setFont(new java.awt.Font("휴먼편지체", 0, 24)); // NOI18N
        lbl_write.setText("글쓰기");
        lbl_write.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_writeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_writeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_writeMouseExited(evt);
            }
        });

        lst_post.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        lst_post.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lst_post);

        jTextField2.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jTextField2.setText("제목을 검색하세요.");

        jLabel5.setText("날짜별 정렬 토글 버튼");

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_write, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_write)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("main", main_panel);

        javax.swing.GroupLayout zettel_panelLayout = new javax.swing.GroupLayout(zettel_panel);
        zettel_panel.setLayout(zettel_panelLayout);
        zettel_panelLayout.setHorizontalGroup(
            zettel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 855, Short.MAX_VALUE)
        );
        zettel_panelLayout.setVerticalGroup(
            zettel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane.addTab("Zettelkasten", zettel_panel);

        jLabel2.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel2.setText("이름 :");

        mypage_lb_createdat.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        mypage_lb_createdat.setText(" ");

        mypage_lbl_image.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N

        fontComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "휴먼 엑스포", "휴먼 옛체", "휴먼 편지체" }));

        jLabel6.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel6.setText("글꼴 변경");

        jLabel7.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel7.setText("글자 크기 변경");

        sizeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "16", "18", "20" }));
        sizeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeComboBoxActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel9.setText("태그 관리");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "여행", "놀이", "연애", "개발" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        mypage_lb_name.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N

        mypage_lb_modify.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        mypage_lb_modify.setText("사용자 정보 변경");
        mypage_lb_modify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mypage_lb_modifyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mypage_lb_modifyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mypage_lb_modifyMouseExited(evt);
            }
        });

        mypage_btn_change.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        mypage_btn_change.setText("글꼴 크기 변경");
        mypage_btn_change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mypage_btn_changeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout my_panelLayout = new javax.swing.GroupLayout(my_panel);
        my_panel.setLayout(my_panelLayout);
        my_panelLayout.setHorizontalGroup(
            my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(my_panelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(mypage_lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, my_panelLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(mypage_btn_change)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(my_panelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(120, 120, 120)
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(my_panelLayout.createSequentialGroup()
                                .addComponent(fontComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(sizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE))
                            .addGroup(my_panelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(mypage_lb_name, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mypage_lb_createdat, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(157, 157, 157))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, my_panelLayout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(mypage_lb_modify)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(my_panelLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel9)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        my_panelLayout.setVerticalGroup(
            my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(my_panelLayout.createSequentialGroup()
                .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mypage_lb_name, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(mypage_lb_createdat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mypage_lb_modify)
                        .addGap(42, 42, 42)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fontComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(mypage_btn_change))
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mypage_lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel9)))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("myPage", my_panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane)
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
        //입력되지 않은 항목이 있는지 확인
        if (login_tf_id.getText().trim().isEmpty() || 
        login_tf_pw.getText().trim().isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "입력되지 않은 사항이 있습니다.", "오류", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
        }
        UserDAO userDAO = new UserDAO();
        int result = userDAO.login(login_tf_id.getText(), login_tf_pw.getText());
        
        if(result == 1){
            loginFrame.setVisible(false);
            this.setVisible(true);
            
            String userID = login_tf_id.getText(); // 로그인 하고 입력된 사용자 ID 가져오기
            setUser(userID); // setUser 메소드 호출해 로그인한 ID 넘겨준 후 이름 값 set 하기!
        }else if(result == 0){
            javax.swing.JOptionPane.showMessageDialog(null, 
            "비밀번호가 틀립니다.", "오류", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }else if(result == -1){
            javax.swing.JOptionPane.showMessageDialog(null, 
            "존재하지 않는 아이디 입니다.", "오류", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }else if(result == -2){
            javax.swing.JOptionPane.showMessageDialog(null, 
            "데이터베이스 오류가 발생했습니다.", "오류", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
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
        //입력되지 않은 사항이 있을 때
        if (join_tf_id.getText().trim().isEmpty() || 
        join_tf_pw.getText().trim().isEmpty() || 
        join_tf_pw2.getText().trim().isEmpty() || 
        join_tf_name.getText().trim().isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "입력되지 않은 사항이 있습니다.", "오류", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
        //비밀번호 확인이 틀렸을 경우
        String pw1 = join_tf_pw.getText();
        String pw2 = join_tf_pw2.getText();
        if(!pw1.equals(pw2)){
            javax.swing.JOptionPane.showMessageDialog(null, "비밀번호 확인이 잘못 되었습니다", "오류", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }    
        UserDAO userDAO = new UserDAO();
        User user = new User();
        
        user.setUserID(join_tf_id.getText());
        user.setUserPassword(join_tf_pw.getText());
        user.setUserName(join_tf_name.getText());
        int result = userDAO.join(user);
        if(result == -1){
            javax.swing.JOptionPane.showMessageDialog(null, "이미 사용중인 아이디 입니다.", "오류", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            javax.swing.JOptionPane.showMessageDialog(null, "회원가입이 성공적으로 완료되었습니다.", "성공", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            joinFrame.setVisible(false);
        }
    }//GEN-LAST:event_join_lbl_signupMouseClicked

    private void login_lbl_signupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_lbl_signupMouseClicked
        // TODO add your handling code here:
        joinFrame.setVisible(true);
    }//GEN-LAST:event_login_lbl_signupMouseClicked

    private void join_lbl_signupAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_join_lbl_signupAncestorAdded

    }//GEN-LAST:event_join_lbl_signupAncestorAdded

    private void join_lbl_signupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_join_lbl_signupMouseEntered
        // TODO add your handling code here:
        setUnderline(join_lbl_signup, true);
    }//GEN-LAST:event_join_lbl_signupMouseEntered

    private void join_lbl_signupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_join_lbl_signupMouseExited
        // TODO add your handling code here:
        setUnderline(join_lbl_signup, false);
    }//GEN-LAST:event_join_lbl_signupMouseExited

    private void lbl_writeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_writeMouseEntered
        // TODO add your handling code here:
        setUnderline(lbl_write, true);
    }//GEN-LAST:event_lbl_writeMouseEntered

    private void lbl_writeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_writeMouseExited
        // TODO add your handling code here:
        setUnderline(lbl_write, false);
    }//GEN-LAST:event_lbl_writeMouseExited

    private void lbl_writeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_writeMouseClicked
        // TODO add your handling code here:
        writeFrame.setVisible(true);
    }//GEN-LAST:event_lbl_writeMouseClicked

    private void mypage_lb_modifyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mypage_lb_modifyMouseEntered
        setUnderline(mypage_lb_modify, true);
    }//GEN-LAST:event_mypage_lb_modifyMouseEntered

    private void mypage_lb_modifyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mypage_lb_modifyMouseExited
        setUnderline(mypage_lb_modify, false);
    }//GEN-LAST:event_mypage_lb_modifyMouseExited

    private void mypage_lb_modifyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mypage_lb_modifyMouseClicked
        userModifyFrame.setVisible(true);
    }//GEN-LAST:event_mypage_lb_modifyMouseClicked

    private void userModify_lbl_modifyAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_userModify_lbl_modifyAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_userModify_lbl_modifyAncestorAdded

    private void userModify_lbl_modifyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userModify_lbl_modifyMouseClicked
        if (
        userModify_tf_pw1.getText().trim().isEmpty() || 
        userModify_tf_pw2.getText().trim().isEmpty() || 
        userModify_tf_name1.getText().trim().isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "입력되지 않은 사항이 있습니다.", "오류", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
        //비밀번호 확인이 틀렸을 경우
        String pw1 = userModify_tf_pw1.getText();
        String pw2 = userModify_tf_pw2.getText();
        if(!pw1.equals(pw2)){
            javax.swing.JOptionPane.showMessageDialog(null, "비밀번호 확인이 잘못 되었습니다", "오류", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }    
        UserDAO userDAO = new UserDAO();
        User user = new User();
        // Update set 을 만들어야 할 것 같은 느낌적인 느낌 updateUser?????
        user.setUserID(userModify_lbl_id1.getText());
        user.setUserPassword(userModify_tf_pw1.getText());
        user.setUserName(userModify_tf_name1.getText());
        user.setUpdatedAt(LocalDateTime.now());
        // Update set 을 만들어야 할 것 같은 느낌적인 느낌 updateUser????
        
        int result = userDAO.update(user);
        if(result == -1){
            javax.swing.JOptionPane.showMessageDialog(null, "예기치 못한 오류가 발생하였습니다.", "오류", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            javax.swing.JOptionPane.showMessageDialog(null, "회원 정보 변경이 성공적으로 완료되었습니다.", "성공", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            userModifyFrame.setVisible(false);
        }
    }//GEN-LAST:event_userModify_lbl_modifyMouseClicked

    private void userModify_lbl_modifyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userModify_lbl_modifyMouseEntered
        setUnderline(userModify_lbl_modify, true);
    }//GEN-LAST:event_userModify_lbl_modifyMouseEntered

    private void userModify_lbl_modifyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userModify_lbl_modifyMouseExited
        setUnderline(userModify_lbl_modify, false);
    }//GEN-LAST:event_userModify_lbl_modifyMouseExited

    private void userModify_tf_pw2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userModify_tf_pw2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userModify_tf_pw2ActionPerformed

    private void userModify_tf_name1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userModify_tf_name1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userModify_tf_name1ActionPerformed

    private void mypage_btn_changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mypage_btn_changeActionPerformed
        String selectedFont = (String)fontComboBox.getSelectedItem();
        String selectedSizeStr = sizeComboBox.getSelectedItem().toString();
        int selectedSize = Integer.parseInt(selectedSizeStr);
            Font newFont = new Font(selectedFont, Font.PLAIN, selectedSize);
            
            // 전체 프레임의 모든 컴포넌트의 폰트를 변경
            updateFontForAllComponents(getContentPane(), newFont);
    }//GEN-LAST:event_mypage_btn_changeActionPerformed

    private void sizeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sizeComboBoxActionPerformed

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
    private javax.swing.JComboBox<String> fontComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JFrame joinFrame;
    private javax.swing.JLabel join_lbl_id;
    private javax.swing.JLabel join_lbl_id1;
    private javax.swing.JLabel join_lbl_name;
    private javax.swing.JLabel join_lbl_pw;
    private javax.swing.JLabel join_lbl_pw2;
    private javax.swing.JLabel join_lbl_signup;
    private javax.swing.JTextField join_tf_id;
    private javax.swing.JTextField join_tf_name;
    private javax.swing.JTextField join_tf_pw;
    private javax.swing.JTextField join_tf_pw2;
    private javax.swing.JLabel join_title;
    private javax.swing.JLabel join_title1;
    private javax.swing.JLabel lbl_write;
    private javax.swing.JFrame loginFrame;
    private javax.swing.JLabel login_lbl_id;
    private javax.swing.JLabel login_lbl_pw;
    private javax.swing.JLabel login_lbl_signin;
    private javax.swing.JLabel login_lbl_signup;
    private javax.swing.JTextField login_tf_id;
    private javax.swing.JTextField login_tf_pw;
    private javax.swing.JLabel login_title;
    private javax.swing.JList<String> lst_post;
    private javax.swing.JPanel main_panel;
    private javax.swing.JPanel my_panel;
    private javax.swing.JButton mypage_btn_change;
    private javax.swing.JLabel mypage_lb_createdat;
    private javax.swing.JLabel mypage_lb_modify;
    private javax.swing.JLabel mypage_lb_name;
    private javax.swing.JLabel mypage_lbl_image;
    private javax.swing.JComboBox<String> sizeComboBox;
    private javax.swing.JFrame userModifyFrame;
    private javax.swing.JLabel userModify_lbl_id1;
    private javax.swing.JLabel userModify_lbl_modify;
    private javax.swing.JLabel userModify_lbl_name1;
    private javax.swing.JLabel userModify_lbl_pw1;
    private javax.swing.JLabel userModify_lbl_pw2;
    private javax.swing.JTextField userModify_tf_name1;
    private javax.swing.JTextField userModify_tf_pw1;
    private javax.swing.JTextField userModify_tf_pw2;
    private javax.swing.JFrame writeFrame;
    private javax.swing.JPanel zettel_panel;
    // End of variables declaration//GEN-END:variables
}
