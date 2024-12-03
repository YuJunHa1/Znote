import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxUI;
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
        jScrollPane1.setBorder(null);
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
});     writeFrame.setSize(630, 500);
        loginFrame.setSize(400, 300);
        joinFrame.setSize(420, 400);
        userModifyFrame.setSize(420, 420);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        joinFrame.setLocationRelativeTo(null);
        loginFrame.setLocationRelativeTo(null);
        writeFrame.setLocationRelativeTo(null);
        userModifyFrame.setLocationRelativeTo(null);
        writeFrame.getContentPane().setBackground(new Color(255, 253, 208));
        
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

        
        //리스트 커스터마이징 적용
        customizeList(lst_post);
        customizeList(mypage_lst_tag);
        
        //텍스트 필드 배경 투명하게, 테두리 X, 밑줄 치기 적용
        customizeTextField(login_tf_id);
        customizeTextField(login_tf_pw);
        customizeTextField(join_tf_id);
        customizeTextField(join_tf_pw);
        customizeTextField(join_tf_pw2);
        customizeTextField(join_tf_name);
        customizeTextField(userModify_tf_name1);
        customizeTextField(userModify_tf_pw1);
        customizeTextField(userModify_tf_pw2);
        customizeTextField(tf_search);
        customizeTextField(write_title);
        
        
        //버튼 커스터마이징 적용
        customizeButton(btn_write);
        customizeButton(mypage_btn_change);
        customizeButton(mypage_btn_tag_add);
        customizeButton(mypage_btn_user_update);
        customizeButton(mypage_btn_tag_delete);
        
        //콤보박스 커스터마이징 적용
        //customizeComboBox(backgroundColorComboBox);
        //customizeComboBox(fontComboBox);
        //customizeComboBox(sizeComboBox);
        write_content.setBackground(new Color(255, 253, 208));
        
        loginFrame.setVisible(true);
        
    }
    
    
    
    //리스트 커스터마이징
    private void customizeList(JList<String> list) {
    // JList 배경 색상 설정
    list.setBackground(new Color(255, 253, 208)); // 원하는 배경 색상으로 설정
    list.setBorder(null); // 테두리 없애기

    // 아이템 커스터마이징
    list.setCellRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // 선택된 아이템 배경 색상
            if (isSelected) {
                label.setBackground(new Color(240, 240, 180)); // 선택된 아이템의 배경 색상
                label.setForeground(Color.BLACK); // 선택된 아이템의 텍스트 색상
            } else {
                label.setBackground(new Color(255, 253, 208)); // 기본 배경 색상
                label.setForeground(Color.DARK_GRAY); // 기본 텍스트 색상
            }

            // 여백 추가
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // 위, 왼쪽, 아래, 오른쪽 여백 추가

            return label;
        }
    });
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
     
     
     
         //버튼 커스터마이징
    private void customizeButton(JButton button) {
    button.setOpaque(true); // 배경을 설정할 수 있도록 함
    button.setBackground(new Color(160, 82, 45)); // 브라운 색상
    button.setForeground(Color.WHITE); // 텍스트는 흰색
    button.setFocusPainted(false); // 포커스 테두리 제거
    button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 클릭 시 손가락 모양 커서

    // 마우스 효과 추가
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setBackground(new Color(139, 69, 19)); // 어두운 브라운으로 변경
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(new Color(160, 82, 45)); // 기본 브라운으로 복원
        }
    });
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
        write_title = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        write_content = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        lst_post = new javax.swing.JList<>();
        tf_search = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btn_write = new javax.swing.JButton();
        zettel_panel = new javax.swing.JPanel();
        lbl_graph_image_viewer = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
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
        mypage_lst_tag = new javax.swing.JList<>();
        mypage_lb_name = new javax.swing.JLabel();
        mypage_btn_change = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        backgroundColorComboBox = new javax.swing.JComboBox<>();
        mypage_btn_tag_add = new javax.swing.JButton();
        mypage_btn_tag_delete = new javax.swing.JButton();
        mypage_btn_user_update = new javax.swing.JButton();

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

        write_title.setFont(new java.awt.Font("휴먼편지체", 0, 24)); // NOI18N
        write_title.setText("jTextField1");

        write_content.setColumns(20);
        write_content.setFont(new java.awt.Font("휴먼편지체", 0, 12)); // NOI18N
        write_content.setRows(5);
        jScrollPane2.setViewportView(write_content);

        jLabel1.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel1.setText("저장하기");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "없음", " " }));

        jLabel8.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel8.setText("태그 설정");

        jLabel10.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel10.setText("링크 연결");

        javax.swing.GroupLayout writeFrameLayout = new javax.swing.GroupLayout(writeFrame.getContentPane());
        writeFrame.getContentPane().setLayout(writeFrameLayout);
        writeFrameLayout.setHorizontalGroup(
            writeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(writeFrameLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(writeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(writeFrameLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, writeFrameLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(write_title, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        writeFrameLayout.setVerticalGroup(
            writeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(writeFrameLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(write_title, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(writeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(writeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10))
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

        userModify_tf_pw1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userModify_tf_pw1MouseEntered(evt);
            }
        });

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

        lst_post.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        lst_post.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "계정 생성 완료!!              2024-12-03", "첫 번째 글 쓰기!!             2024-12-03", "성공! ㅎㅎ                   2024-12-03" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lst_post);

        tf_search.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        tf_search.setText("제목을 검색하세요.");

        jLabel5.setText("오래된 순 ▼");

        btn_write.setFont(new java.awt.Font("휴먼편지체", 0, 24)); // NOI18N
        btn_write.setText("글쓰기");
        btn_write.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_writeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout main_panelLayout = new javax.swing.GroupLayout(main_panel);
        main_panel.setLayout(main_panelLayout);
        main_panelLayout.setHorizontalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(main_panelLayout.createSequentialGroup()
                .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_search)
                            .addComponent(jScrollPane1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                        .addGap(229, 715, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_write, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        main_panelLayout.setVerticalGroup(
            main_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, main_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_search, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_write)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("main", main_panel);

        lbl_graph_image_viewer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/final_graph_image.png"))); // NOI18N

        jButton1.setFont(new java.awt.Font("휴먼편지체", 0, 24)); // NOI18N
        jButton1.setText("편집");
        jButton1.setMaximumSize(new java.awt.Dimension(83, 35));
        jButton1.setMinimumSize(new java.awt.Dimension(83, 35));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("휴먼편지체", 0, 24)); // NOI18N
        jLabel11.setText("링크 개수 : 3개");

        javax.swing.GroupLayout zettel_panelLayout = new javax.swing.GroupLayout(zettel_panel);
        zettel_panel.setLayout(zettel_panelLayout);
        zettel_panelLayout.setHorizontalGroup(
            zettel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zettel_panelLayout.createSequentialGroup()
                .addGroup(zettel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(zettel_panelLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(lbl_graph_image_viewer, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, zettel_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        zettel_panelLayout.setVerticalGroup(
            zettel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zettel_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_graph_image_viewer, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(zettel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(153, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Graph View", zettel_panel);

        jLabel2.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel2.setText("이름");

        mypage_lb_createdat.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        mypage_lb_createdat.setText(" ");

        mypage_lbl_image.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N

        fontComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "휴먼 엑스포", "휴먼 옛체", "휴먼 편지체" }));

        jLabel6.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel6.setText("글꼴");

        jLabel7.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel7.setText("글자 크기");

        sizeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "16", "18", "20" }));
        sizeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizeComboBoxActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel9.setText("태그 관리");

        mypage_lst_tag.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "여행", "놀이", "연애", "개발" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(mypage_lst_tag);

        mypage_lb_name.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N

        mypage_btn_change.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        mypage_btn_change.setText("적용");
        mypage_btn_change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mypage_btn_changeActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel4.setText("배경 색상");

        backgroundColorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "light yellow", "light green", "sky blue", "white" }));

        mypage_btn_tag_add.setFont(new java.awt.Font("휴먼편지체", 0, 14)); // NOI18N
        mypage_btn_tag_add.setText("추가");

        mypage_btn_tag_delete.setFont(new java.awt.Font("휴먼편지체", 0, 14)); // NOI18N
        mypage_btn_tag_delete.setText("삭제");

        mypage_btn_user_update.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        mypage_btn_user_update.setText("사용자 정보 변경");
        mypage_btn_user_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mypage_btn_user_updateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout my_panelLayout = new javax.swing.GroupLayout(my_panel);
        my_panel.setLayout(my_panelLayout);
        my_panelLayout.setHorizontalGroup(
            my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(my_panelLayout.createSequentialGroup()
                .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(mypage_lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mypage_lb_createdat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(my_panelLayout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(50, 50, 50)
                                    .addComponent(mypage_lb_name, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel4)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6)
                                .addComponent(jLabel9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, my_panelLayout.createSequentialGroup()
                                .addComponent(mypage_btn_tag_add, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mypage_btn_tag_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(mypage_btn_user_update, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addGap(482, 482, 482)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(my_panelLayout.createSequentialGroup()
                                .addComponent(backgroundColorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(mypage_btn_change))
                            .addComponent(fontComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        my_panelLayout.setVerticalGroup(
            my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(my_panelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addComponent(mypage_lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mypage_lb_name, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(mypage_lb_createdat))
                        .addGap(18, 18, 18)
                        .addComponent(mypage_btn_user_update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fontComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(sizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(backgroundColorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mypage_btn_change))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mypage_btn_tag_delete)
                            .addComponent(mypage_btn_tag_add))
                        .addGap(72, 72, 72))))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addContainerGap())
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

    private void mypage_btn_user_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mypage_btn_user_updateActionPerformed
        // TODO add your handling code here:
        userModifyFrame.setVisible(true);
    }//GEN-LAST:event_mypage_btn_user_updateActionPerformed

    private void userModify_tf_pw1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userModify_tf_pw1MouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_userModify_tf_pw1MouseEntered

    private void btn_writeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_writeActionPerformed
        // TODO add your handling code here:
        writeFrame.setVisible(true);
    }//GEN-LAST:event_btn_writeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JComboBox<String> backgroundColorComboBox;
    private javax.swing.JButton btn_write;
    private javax.swing.JComboBox<String> fontComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane;
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
    private javax.swing.JLabel lbl_graph_image_viewer;
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
    private javax.swing.JButton mypage_btn_tag_add;
    private javax.swing.JButton mypage_btn_tag_delete;
    private javax.swing.JButton mypage_btn_user_update;
    private javax.swing.JLabel mypage_lb_createdat;
    private javax.swing.JLabel mypage_lb_name;
    private javax.swing.JLabel mypage_lbl_image;
    private javax.swing.JList<String> mypage_lst_tag;
    private javax.swing.JComboBox<String> sizeComboBox;
    private javax.swing.JTextField tf_search;
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
    private javax.swing.JTextArea write_content;
    private javax.swing.JTextField write_title;
    private javax.swing.JPanel zettel_panel;
    // End of variables declaration//GEN-END:variables
}
