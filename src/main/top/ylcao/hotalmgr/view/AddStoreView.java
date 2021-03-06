package top.ylcao.hotalmgr.view;

import top.ylcao.hotalmgr.handler.StoreHandler;
import top.ylcao.hotalmgr.main.Log;
import top.ylcao.hotalmgr.main.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class AddStoreView extends JFrame{
    private JLabel storeNameLabel;
    private JTextField storeNameField;
    private JLabel storeAddressLabel;
    private JTextField storeAddressField;
    private JLabel storeManagerLabel;
    private JTextField storeManagerField;
    private JLabel storePhoneLabel;
    private JTextField storePhoneField;
    private JButton confirmButton;
    private StoreHandler storeHandler;
    private Store store;


    public AddStoreView(StoreHandler sh) {
        super("添加门店");
        storeHandler = sh;
        initLayout();
        initHandler();
    }

    private void initLayout() {
        storeNameLabel = new JLabel("酒店名");
        storeNameField = new JTextField();
        storeAddressLabel = new JLabel("酒店地址");
        storeAddressField = new JTextField();
        storeManagerLabel = new JLabel("酒店经理");
        storeManagerField = new JTextField();
        storePhoneLabel = new JLabel("经理电话");
        storePhoneField = new JTextField();
        confirmButton = new JButton("确定");

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 10));
        panel.add(storeNameLabel);
        panel.add(storeNameField);
        panel.add(storeAddressLabel);
        panel.add(storeAddressField);
        panel.add(storeManagerLabel);
        panel.add(storeManagerField);
        panel.add(storePhoneLabel);
        panel.add(storePhoneField);
        panel.add(new JPanel());
        panel.add(confirmButton);
        this.add(panel);

        setSize(300, 200);
        // 居中
        setLocationRelativeTo(null);
        // 设置不可修改改变大小
        setResizable(false);
        // 设置可见
        setVisible(true);
    }

    private void initHandler() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (storeNameField.getText().length() == 0 || storeAddressField.getText().length() == 0 || storeManagerField.getText().length() == 0 || storePhoneField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(AddStoreView.this, "请输入完整信息!");
                    return;
                }
                try {
                    store = new Store(storeNameField.getText(), storeAddressField.getText(), storePhoneField.getText(), storeManagerField.getText(), "1:0,2:0");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // 添加到store列表里
                storeHandler.getStoreView().getAllStoreInfo().add(store);
                // 更新table
                Vector<String> newVector = new Vector<String>();
                newVector.addElement(storeNameField.getText());
                newVector.addElement(storeAddressField.getText());
                newVector.addElement(storeManagerField.getText());
                newVector.addElement(storePhoneField.getText());
                storeHandler.getStoreView().getAllStoreVector().addElement(newVector);
                storeHandler.getStoreView().getTableModel().fireTableDataChanged();

                // 更新store文件
                try {
                    storeHandler.updateAllStoreFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Log.p("添加门店完成:" + store.getFormatStoreInfo());
                AddStoreView.this.dispose();
            }
        });
    }
}
