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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
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
    public void updateForAllComponents(Container container, Font font, Color color) {
        for (Component component : container.getComponents()) {
            component.setFont(font);
            component.setBackground(color);
            if (component instanceof Container) {
                updateForAllComponents((Container) component, font, color);
            }
        }
    }
    
    String pw_check = null; // my page 에서 비밀번호 검증을 위한 전역 변수 설정.
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
            pw_check = user.getUserPassword(); // 비밀번호 검증 위해서 값 저장 
            userModify_lbl_id1.setText(user.getUserID());
            // paswword check
        } else {
            mypage_lb_name.setText("사용자 정보 없음");
            mypage_lb_createdat.setText("사용자 정보 없음");
        }
    }
    // 캡챠 관련
    String captcha;
    CAPTCHAGenerator initiateProgram;
    public MainFrame() throws IOException {
        initComponents();
        this.initiateProgram = new CAPTCHAGenerator(login_lbl_captcha_image);
        captcha = initiateProgram.Generate();

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
        password_check_dialog.setSize(350, 250);
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
        password_check_dialog.getContentPane().setBackground(new Color(255, 253, 208));
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
        customizeTextField(password_check_tf);
        customizeTextField(mypage_tf_tag);
        
        //버튼 커스터마이징 적용
        customizeButton(btn_write);
        customizeButton(mypage_btn_change);
        customizeButton(mypage_btn_tag_add);
        customizeButton(mypage_btn_user_update);
        customizeButton(mypage_btn_tag_delete);
        customizeButton(mypage_btn_image_change);
        customizeButton(password_check_btn);
        
        //콤보박스 커스터마이징 적용
        //customizeComboBox(backgroundColorComboBox);
        //customizeComboBox(fontComboBox);
        //customizeComboBox(sizeComboBox);
        write_content.setBackground(new Color(255, 253, 208));
        
        loginFrame.setVisible(true);
        
        // listModel을 초기화하고 JList에 설정합니다.    
        listModel = new DefaultListModel<>();
        
        listModel.addElement("개발");
        listModel.addElement("여행");
        listModel.addElement("놀이");
        
        mypage_lst_tag.setModel(listModel);
    }
    
    // 프로필 파일 설정
    private void setProfilePicture(File imageFile) {
             try {
                BufferedImage img = ImageIO.read(imageFile);
                ImageIcon icon = new ImageIcon(img.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
                mypage_lbl_image.setIcon(icon); // JLabel에 이미지 아이콘 설정
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "이미지를 불러오는 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
            }
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
        login_lbl_captcha = new javax.swing.JLabel();
        login_lbl_captcha_image = new javax.swing.JLabel();
        login_tf_captcha = new javax.swing.JTextField();
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
        password_check_dialog = new javax.swing.JDialog();
        password_check_lbl1 = new javax.swing.JLabel();
        password_check_lbl2 = new javax.swing.JLabel();
        password_check_tf = new javax.swing.JTextField();
        password_check_btn = new javax.swing.JButton();
        jFileChooser = new javax.swing.JFileChooser();
        jTabbedPane = new javax.swing.JTabbedPane();
        main_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lst_post = new javax.swing.JList<>();
        tf_search = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btn_write = new javax.swing.JButton();
        zettel_panel = new javax.swing.JPanel();
        lblExplaTitle2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        lblExpla1 = new javax.swing.JTextArea();
        lblExplaTitle1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lblExpla2 = new javax.swing.JTextArea();
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
        mypage_btn_image_change = new javax.swing.JButton();
        mypage_tf_tag = new javax.swing.JTextField();

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

        login_lbl_captcha.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        login_lbl_captcha.setText("문자 입력 :");

        login_tf_captcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_tf_captchaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginFrameLayout = new javax.swing.GroupLayout(loginFrame.getContentPane());
        loginFrame.getContentPane().setLayout(loginFrameLayout);
        loginFrameLayout.setHorizontalGroup(
            loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginFrameLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginFrameLayout.createSequentialGroup()
                        .addGroup(loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(loginFrameLayout.createSequentialGroup()
                                .addComponent(login_lbl_id)
                                .addGap(18, 18, 18)
                                .addComponent(login_tf_id, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(loginFrameLayout.createSequentialGroup()
                                .addComponent(login_lbl_pw)
                                .addGap(18, 18, 18)
                                .addComponent(login_tf_pw, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginFrameLayout.createSequentialGroup()
                        .addComponent(login_title)
                        .addGap(172, 172, 172))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginFrameLayout.createSequentialGroup()
                .addGroup(loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginFrameLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(login_lbl_captcha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(login_tf_captcha))
                    .addGroup(loginFrameLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(login_lbl_signup)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(login_lbl_signin))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginFrameLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(login_lbl_captcha_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(95, 95, 95))
        );
        loginFrameLayout.setVerticalGroup(
            loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(login_title, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login_tf_id, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(login_lbl_id))
                .addGap(18, 18, 18)
                .addGroup(loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login_tf_pw, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(login_lbl_pw))
                .addGap(14, 14, 14)
                .addComponent(login_lbl_captcha_image, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login_lbl_captcha)
                    .addComponent(login_tf_captcha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(loginFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login_lbl_signup)
                    .addComponent(login_lbl_signin))
                .addGap(29, 29, 29))
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

        password_check_lbl1.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        password_check_lbl1.setText("현재 비밀번호 확인");

        password_check_lbl2.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        password_check_lbl2.setText("Password :");

        password_check_btn.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        password_check_btn.setText("확인");
        password_check_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password_check_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout password_check_dialogLayout = new javax.swing.GroupLayout(password_check_dialog.getContentPane());
        password_check_dialog.getContentPane().setLayout(password_check_dialogLayout);
        password_check_dialogLayout.setHorizontalGroup(
            password_check_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(password_check_dialogLayout.createSequentialGroup()
                .addGroup(password_check_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(password_check_dialogLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(password_check_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(password_check_lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(password_check_dialogLayout.createSequentialGroup()
                                .addComponent(password_check_lbl2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(password_check_tf, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(password_check_dialogLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(password_check_btn)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        password_check_dialogLayout.setVerticalGroup(
            password_check_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(password_check_dialogLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(password_check_lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(password_check_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password_check_lbl2)
                    .addComponent(password_check_tf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(password_check_btn)
                .addContainerGap(135, Short.MAX_VALUE))
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
                        .addGap(229, 841, Short.MAX_VALUE)
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
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("main", main_panel);

        lblExplaTitle2.setFont(new java.awt.Font("휴먼편지체", 0, 24)); // NOI18N
        lblExplaTitle2.setText("제텔카스텐(Zettelkasten)의 작성법");

        lblExpla1.setBackground(new java.awt.Color(242, 242, 242));
        lblExpla1.setColumns(20);
        lblExpla1.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        lblExpla1.setRows(5);
        lblExpla1.setText("제텔카스텐은 독일어로 \"메모상자\"라는 뜻으로, 니콜라스 루만이 고안한 독창적인 정보 관리 및 창의적 사고 도구이다.\n그는 아이디어와 지식을 효과적으로 정리하고 연결 해 70권 이상의 저서와 400편 이상의 논문을 집필하였다.\n\n제텔카스텐은 단순히 정보는 저장하는 것을 넘어서, 아이디어 간의 관계를 구축하는데 중점을 둔다. 서로 다른 아이디어가 연결되\n며 새로운 통찰과 창의적인 발상이 가능해진다.\n또한 연결된 각 아이디어들이 서로 조합되며 대주제와 새로운 아이디어를 만들 수 있는 상향식 구조가 대표적인 특징이다.\n\n이를 위해 링크와 태그 시스템을 도입하여 아이디어를 작성하고, 이를 자주 읽으며 아이디어 등을 재조직한다.");
        lblExpla1.setBorder(null);
        jScrollPane4.setViewportView(lblExpla1);

        lblExplaTitle1.setFont(new java.awt.Font("휴먼편지체", 0, 24)); // NOI18N
        lblExplaTitle1.setText("제텔카스텐(Zettelkasten) 이란?");

        lblExpla2.setBackground(new java.awt.Color(242, 242, 242));
        lblExpla2.setColumns(20);
        lblExpla2.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        lblExpla2.setRows(5);
        lblExpla2.setText("1. 하나의 메모에는 하나의 아이디어만 작성\n2. 이해하기 쉽고 직관적인 언어로 작성\n3. 서로 연관된 다른 아이디어와의 링크를 생성하고 문서를 분류할 수 있는 태그를 설정\n4. 주기적으로 아이디어를 읽으며, 링크를 타고 글을 읽으면서 아이디어를 재조직한다.");
        jScrollPane5.setViewportView(lblExpla2);

        javax.swing.GroupLayout zettel_panelLayout = new javax.swing.GroupLayout(zettel_panel);
        zettel_panel.setLayout(zettel_panelLayout);
        zettel_panelLayout.setHorizontalGroup(
            zettel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zettel_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(zettel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane5)
                    .addGroup(zettel_panelLayout.createSequentialGroup()
                        .addComponent(lblExplaTitle1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(zettel_panelLayout.createSequentialGroup()
                .addComponent(lblExplaTitle2)
                .addGap(0, 580, Short.MAX_VALUE))
        );
        zettel_panelLayout.setVerticalGroup(
            zettel_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zettel_panelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblExplaTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(lblExplaTitle2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        jTabbedPane.addTab("Zettelkasten", zettel_panel);

        jLabel2.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel2.setText("이름");

        mypage_lb_createdat.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        mypage_lb_createdat.setText(" ");

        mypage_lbl_image.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N

        fontComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "휴먼편지체", "맑은고딕", "바탕" }));

        jLabel6.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel6.setText("글꼴");

        jLabel7.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        jLabel7.setText("글자 크기");

        sizeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "14", "16", "18", " " }));
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

        backgroundColorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Light Yellow", "Light Green", "Sky Blue", "White" }));

        mypage_btn_tag_add.setFont(new java.awt.Font("휴먼편지체", 0, 14)); // NOI18N
        mypage_btn_tag_add.setText("추가");
        mypage_btn_tag_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mypage_btn_tag_addActionPerformed(evt);
            }
        });

        mypage_btn_tag_delete.setFont(new java.awt.Font("휴먼편지체", 0, 14)); // NOI18N
        mypage_btn_tag_delete.setText("삭제");
        mypage_btn_tag_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mypage_btn_tag_deleteActionPerformed(evt);
            }
        });

        mypage_btn_user_update.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        mypage_btn_user_update.setText("사용자 정보 변경");
        mypage_btn_user_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mypage_btn_user_updateActionPerformed(evt);
            }
        });

        mypage_btn_image_change.setFont(new java.awt.Font("휴먼편지체", 0, 18)); // NOI18N
        mypage_btn_image_change.setText("사진 변경");
        mypage_btn_image_change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mypage_btn_image_changeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout my_panelLayout = new javax.swing.GroupLayout(my_panel);
        my_panel.setLayout(my_panelLayout);
        my_panelLayout.setHorizontalGroup(
            my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(my_panelLayout.createSequentialGroup()
                .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(mypage_lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(mypage_btn_image_change, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mypage_lb_createdat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mypage_btn_user_update, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(my_panelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(50, 50, 50)
                                .addComponent(mypage_lb_name, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66))
                            .addGroup(my_panelLayout.createSequentialGroup()
                                .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(my_panelLayout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fontComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, my_panelLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(my_panelLayout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(sizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(my_panelLayout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(backgroundColorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, my_panelLayout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(my_panelLayout.createSequentialGroup()
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(mypage_tf_tag, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)))
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mypage_btn_tag_add, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mypage_btn_change)
                            .addComponent(mypage_btn_tag_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        my_panelLayout.setVerticalGroup(
            my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(my_panelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addComponent(mypage_lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mypage_btn_image_change)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(my_panelLayout.createSequentialGroup()
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mypage_lb_name, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(mypage_lb_createdat))
                        .addGap(18, 18, 18)
                        .addComponent(mypage_btn_user_update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(fontComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sizeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(mypage_btn_change)
                            .addComponent(backgroundColorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(my_panelLayout.createSequentialGroup()
                                .addGroup(my_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mypage_btn_tag_add)
                                    .addComponent(mypage_tf_tag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(mypage_btn_tag_delete)))
                        .addGap(100, 100, 100))))
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
            try {
                boolean captchaResult = captcha(); // 캡챠 메소드 호출하여 결과를 bool 로 받기
            if (!captchaResult) {
                return; // 캡챠 실패 시, 로그인을 진행하지 않고 리턴
            }

} catch (IOException ex) {
    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
}
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

    
    public boolean captcha() throws IOException {
        if(captcha.equals(login_tf_captcha.getText())){
            return true;
            }else{
                captcha = initiateProgram.Generate();
                JOptionPane.showMessageDialog(null, "자동입력방지 문자를 잘못입력하셨습니다.", "Error_Message", JOptionPane.ERROR_MESSAGE);
                login_tf_captcha.setText(null);
                return false;
            }
    }
    
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
            javax.swing.JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "성공", javax.swing.JOptionPane.INFORMATION_MESSAGE);
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
            
            // 배경 색상 선택
            String selectedColorStr = (String)backgroundColorComboBox.getSelectedItem();
            Color selectedColor = null;
            
            // 배경 색상을 String에서 Color로 매핑
            switch (selectedColorStr) {
                case "Light Yellow":
                    selectedColor = new Color(255, 255, 224); // Light Yellow
                break;
                case "Light Green":
                    selectedColor = new Color(144, 238, 144); // Light Green
                break;
                case "Sky Blue":
                    selectedColor = new Color(135, 206, 235); // Sky Blue
                break;
                case "White":
                    selectedColor = Color.WHITE; // White
                break;
    }
            // 전체 프레임의 모든 컴포넌트의 폰트를 변경
            updateForAllComponents(getContentPane(), newFont, selectedColor);
            
             getContentPane().revalidate(); // 레이아웃 다시 계산
             getContentPane().repaint();    // UI 다시 그리기
    }//GEN-LAST:event_mypage_btn_changeActionPerformed

    private void sizeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sizeComboBoxActionPerformed

    private void mypage_btn_user_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mypage_btn_user_updateActionPerformed
        // TODO add your handling code here:
        password_check_dialog.setVisible(true);
    }//GEN-LAST:event_mypage_btn_user_updateActionPerformed

    private void userModify_tf_pw1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userModify_tf_pw1MouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_userModify_tf_pw1MouseEntered

    private void btn_writeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_writeActionPerformed
        // TODO add your handling code here:
        writeFrame.setVisible(true);
    }//GEN-LAST:event_btn_writeActionPerformed

    private void password_check_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password_check_btnActionPerformed
        if (
        password_check_tf.getText().trim().isEmpty() ) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "비밀번호 입력이 되지 않았습니다.", "오류", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
        //비밀번호 확인이 틀렸을 경우
        String pw1 = password_check_tf.getText();
           
        
        //String DAO_pw = user.getUserPassword();
        
        if(pw_check.equals(pw1)){
            javax.swing.JOptionPane.showMessageDialog(null, "비밀번호 검증이 완료되었습니다.", "성공", javax.swing.JOptionPane.INFORMATION_MESSAGE);
           
            userModifyFrame.setVisible(true);
            password_check_dialog.setVisible(false);
        } else{
            javax.swing.JOptionPane.showMessageDialog(null, "비밀번호가 맞지않습니다.", "오류", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_password_check_btnActionPerformed
    
    private final DefaultListModel<String> listModel;
    
    private void mypage_btn_tag_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mypage_btn_tag_addActionPerformed
        DefaultListModel<String> listModel = (DefaultListModel<String>) mypage_lst_tag.getModel();
        String newTag = mypage_tf_tag.getText().trim();
        if (!newTag.isEmpty()) {
            listModel.addElement(newTag);
            mypage_tf_tag.setText("");
        }
    }//GEN-LAST:event_mypage_btn_tag_addActionPerformed

    private void mypage_btn_tag_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mypage_btn_tag_deleteActionPerformed
        // 선택된 항목의 인덱스를 가져옵니다.
    int selectedIndex = mypage_lst_tag.getSelectedIndex();

    // 선택된 항목이 있는지 확인하고, 있으면 삭제합니다.
    if (selectedIndex != -1) {
        listModel.remove(selectedIndex);
    } else {
        // 항목이 선택되지 않았을 경우 메시지를 띄울 수 있습니다.
        JOptionPane.showMessageDialog(this, "삭제할 항목을 선택해주세요.", "오류", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_mypage_btn_tag_deleteActionPerformed

    private void mypage_btn_image_changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mypage_btn_image_changeActionPerformed
        // 파일 선택 대화상자를 이용해 사진 파일을 가져오기
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("프로필 사진 선택");
    fileChooser.setFileFilter(new FileNameExtensionFilter("이미지 파일", "jpg", "jpeg", "png"));
    
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        setProfilePicture(selectedFile);
    }
    }//GEN-LAST:event_mypage_btn_image_changeActionPerformed

    private void login_tf_captchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_tf_captchaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_login_tf_captchaActionPerformed

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
                try {
                    new MainFrame().setVisible(false);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> backgroundColorComboBox;
    private javax.swing.JButton btn_write;
    private javax.swing.JComboBox<String> fontComboBox;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
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
    private javax.swing.JTextArea lblExpla1;
    private javax.swing.JTextArea lblExpla2;
    private javax.swing.JLabel lblExplaTitle1;
    private javax.swing.JLabel lblExplaTitle2;
    private javax.swing.JFrame loginFrame;
    private javax.swing.JLabel login_lbl_captcha;
    private javax.swing.JLabel login_lbl_captcha_image;
    private javax.swing.JLabel login_lbl_id;
    private javax.swing.JLabel login_lbl_pw;
    private javax.swing.JLabel login_lbl_signin;
    private javax.swing.JLabel login_lbl_signup;
    private javax.swing.JTextField login_tf_captcha;
    private javax.swing.JTextField login_tf_id;
    private javax.swing.JTextField login_tf_pw;
    private javax.swing.JLabel login_title;
    private javax.swing.JList<String> lst_post;
    private javax.swing.JPanel main_panel;
    private javax.swing.JPanel my_panel;
    private javax.swing.JButton mypage_btn_change;
    private javax.swing.JButton mypage_btn_image_change;
    private javax.swing.JButton mypage_btn_tag_add;
    private javax.swing.JButton mypage_btn_tag_delete;
    private javax.swing.JButton mypage_btn_user_update;
    private javax.swing.JLabel mypage_lb_createdat;
    private javax.swing.JLabel mypage_lb_name;
    private javax.swing.JLabel mypage_lbl_image;
    private javax.swing.JList<String> mypage_lst_tag;
    private javax.swing.JTextField mypage_tf_tag;
    private javax.swing.JButton password_check_btn;
    private javax.swing.JDialog password_check_dialog;
    private javax.swing.JLabel password_check_lbl1;
    private javax.swing.JLabel password_check_lbl2;
    private javax.swing.JTextField password_check_tf;
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
