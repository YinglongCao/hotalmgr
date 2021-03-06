package top.ylcao.hotalmgr.view;


import top.ylcao.hotalmgr.handler.RoomHandler;
import top.ylcao.hotalmgr.handler.StoreHandler;
import top.ylcao.hotalmgr.main.Store;
import java.awt.*;
import java.io.*;
import javax.swing.*;


public class RoomView extends JFrame{

//    public ArrayList<JLabel> labels = new ArrayList<>();
    public Store store;
    public StoreHandler storeHandler;
    public final JPanel panel;

    public RoomView(Store store, StoreHandler sh) throws IOException {
        super("房间管理:" + store.getName());
        this.store = store;
        this.storeHandler = sh;
        this.setSize(700, 600);
        // 设置窗体位置居中显示
        this.setLocationRelativeTo(null);
        // 禁止鼠标拖动修改窗体大小
        this.setResizable(false);
        panel = new JPanel();
        // 设置布局
        panel.setLayout(new GridLayout(9, 9, 5, 5));
        initLayout();
        // 设置右键监听
        panel.addMouseListener(new RoomHandler(this));
        this.add(panel);
        this.setVisible(true);
    }

    private void initLayout() {
        for (int i = 0; i < store.getRoomList().size(); i++) {
            boolean isEmpty = store.getRoomList().get(i).getIsUsing().equals("否");
            String name = "<html>" + i + "<br>" + (isEmpty ? "空房" : "占用") + "</html>";
            JLabel label = new JLabel(name, JLabel.CENTER);
            label.setOpaque(true);
            label.setBorder(BorderFactory.createLineBorder(Color.black));
            if (isEmpty) {
                updateEmptyView(label);
            } else {
                updateNoEmptyView(label);
            }
            panel.add(label);
        }
    }

    public void updateEmptyView(JLabel label) {
        label.setText(label.getText().replace("占用", "空房"));
        label.setBackground(Color.GREEN);
        label.setForeground(Color.GRAY);
        label.setFont(new Font("黑体", Font.ITALIC, 16));
    }

    public void updateNoEmptyView(JLabel label) {
        label.setText(label.getText().replace("空房", "占用"));
        label.setBackground(Color.RED);
        label.setForeground(Color.BLACK);       // 设置白色字体
        label.setFont(new Font("黑体", Font.BOLD, 16));
    }

}
