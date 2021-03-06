package top.ylcao.hotalmgr.view;




import top.ylcao.hotalmgr.handler.LoginHandler;
import top.ylcao.hotalmgr.main.Log;

import javax.swing.*;
import java.awt.*;


public class LoginView extends JFrame {

    SpringLayout springLayout = new SpringLayout();
    JPanel loginPanel = new JPanel(springLayout);

    JLabel title = new JLabel("连锁酒店房间管理系统", JLabel.CENTER);
    JLabel subTitle = new JLabel("by 20计科曹应龙");
    JLabel accountLabel = new JLabel("账号");
    JTextField accountText = new JTextField();
    JLabel passwordLabel = new JLabel("密码");
    JPasswordField passwordText = new JPasswordField();
    JButton resetButton = new JButton("清空");
    JButton loginButton = new JButton("登陆");

    LoginHandler loginHandler;


    public LoginView(){
        super("连锁酒店房间管理系统");
        loginHandler = new LoginHandler(this);
        Container contentPane = getContentPane();
        // 布局
        loginLayout();
        // 添加组件
        loginPanel.add(subTitle);
        loginPanel.add(accountLabel);
        loginPanel.add(accountText);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordText);
        loginPanel.add(resetButton);


        // 设置login按钮为默认按钮
        getRootPane().setDefaultButton(loginButton);
        loginPanel.add(loginButton);
        // 绑定事件监听器
        resetButton.addActionListener(loginHandler);
        // 监听
        loginButton.addKeyListener(loginHandler);
        loginButton.addActionListener(loginHandler);

        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(loginPanel, BorderLayout.CENTER);

        setSize(350, 250);
        accountText.setPreferredSize(new Dimension(this.getWidth()-200, 30));
        passwordText.setPreferredSize(new Dimension(this.getWidth()-200, 30));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }


    private void loginLayout() {

        title.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        subTitle.setFont(new Font("微软雅黑", Font.PLAIN, 10));
        Font MSYAHEI = new Font("微软雅黑", Font.PLAIN, 15);
        accountLabel.setFont(MSYAHEI);
        accountText.setFont(MSYAHEI);
        passwordLabel.setFont(MSYAHEI);
        passwordText.setFont(MSYAHEI);
        resetButton.setFont(MSYAHEI);
        loginButton.setFont(MSYAHEI);


        // 设置小标题在大标题下面，也就是loginPanel最上面，并水平居中
        springLayout.putConstraint(SpringLayout.NORTH, subTitle, 0, SpringLayout.NORTH, loginPanel);
        springLayout.putConstraint(SpringLayout.WEST, subTitle, -(Spring.width(subTitle).getValue() / 2), SpringLayout.HORIZONTAL_CENTER, loginPanel);

        // 设置账号标签到指定位置
        springLayout.putConstraint(SpringLayout.NORTH, accountLabel, 40, SpringLayout.NORTH, loginPanel);
        springLayout.putConstraint(SpringLayout.WEST, accountLabel, 50, SpringLayout.WEST, loginPanel);
        // 设置账号文本框
        springLayout.putConstraint(SpringLayout.WEST, accountText, 20, SpringLayout.EAST, accountLabel);
        springLayout.putConstraint(SpringLayout.BASELINE, accountText, 0, SpringLayout.BASELINE, accountLabel);


        // 设置密码标签框
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 25, SpringLayout.SOUTH, accountLabel);
        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, accountLabel);
        // 设置密码文本框
        springLayout.putConstraint(SpringLayout.BASELINE, passwordText, 0, SpringLayout.BASELINE, passwordLabel);
        springLayout.putConstraint(SpringLayout.WEST, passwordText, 0, SpringLayout.WEST, accountText);


        // 设置按钮
        springLayout.putConstraint(SpringLayout.NORTH, resetButton, 20, SpringLayout.SOUTH, passwordLabel);
        springLayout.putConstraint(SpringLayout.BASELINE, loginButton, 0, SpringLayout.BASELINE, resetButton);
        springLayout.putConstraint(SpringLayout.WEST, loginButton, 40, SpringLayout.EAST, resetButton);
        springLayout.putConstraint(SpringLayout.WEST, resetButton, -(Spring.sum(Spring.sum(Spring.width(resetButton), Spring.width(loginButton)), Spring.constant(40)).getValue() / 2), SpringLayout.HORIZONTAL_CENTER, loginPanel);


    }

    public JTextField getAccountText() {
        return accountText;
    }

    public JPasswordField getPasswordText() {
        return passwordText;
    }
}
