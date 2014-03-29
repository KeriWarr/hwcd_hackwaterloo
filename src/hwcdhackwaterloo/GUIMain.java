/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hwcdhackwaterloo;

import static hwcdhackwaterloo.UWAPI.getJSONData;
import hwcdhackwaterloo.OpenRoom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Box;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author Keri
 */
public class GUIMain extends javax.swing.JFrame {

    int posX = 0, posY = 0;

    /**
     * Creates new form NewJFrame
     */
    public GUIMain() {
        initComponents();
        menuPanel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });
        menuPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                //sets frame position when mouse dragged			
                setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);

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

        menuPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        roomChoice = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        timeField = new javax.swing.JTextField();
        findButton = new javax.swing.JButton();
        monthChoice = new javax.swing.JComboBox();
        dayChoice = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        middlePanel = new javax.swing.JPanel();
        resultHeader = new javax.swing.JLabel();
        result = new javax.swing.JLabel();
        bottomPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fuck_Nuts");
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(java.awt.Color.pink);
        setUndecorated(true);
        setResizable(false);

        menuPanel.setBackground(new java.awt.Color(10, 10, 10));

        titleLabel.setBackground(new java.awt.Color(255, 255, 255));
        titleLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 18)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(230, 230, 230));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        titleLabel.setText("HWCD hackWATERLOO");

        closeButton.setBackground(new java.awt.Color(0, 0, 0));
        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/closeButtonAlt.png"))); // NOI18N
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel)
                .addGap(120, 120, 120)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        topPanel.setBackground(new java.awt.Color(135, 0, 71));
        topPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(250, 250, 250));
        jLabel1.setText("SEARCH FOR AVAILABLE ROOMS");

        roomChoice.setBackground(new java.awt.Color(200, 50, 100));
        roomChoice.addItem("MC");

        roomChoice.addItemListener(new ItemListener(){
            Object state = new Object();
            //Object oldState = new Object();
            Object oldState = roomChoice.getSelectedItem();
            public void itemStateChanged(ItemEvent e){

                state = e.getItem();
                if(state != oldState){
                    //System.out.println("changed to:"+ state);
                    // code here to pass info to openroom.java
                }
                oldState = state;

            }
        });
        String json = getJSONData("/buildings/list");
        JSONObject obj = (JSONObject) JSONValue.parse(json);
        JSONArray courses = (JSONArray) obj.get("data");
        ArrayList<String> buildingNames = new ArrayList<>();
        for (Object course : courses) {
            JSONObject courseBlock = (JSONObject) course;
            buildingNames.add(courseBlock.get("building_code").toString());
        }
        Collections.sort(buildingNames);
        for(String buildingName : buildingNames){
            roomChoice.addItem(buildingName);
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(250, 250, 250));
        jLabel3.setText("WHAT'S DA CLOSEST BUILDIN TO YOU?");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(250, 250, 250));
        jLabel5.setText("WHEN DO YOU WANT A ROOM, AND FOR HOW LONG?");

        timeField.setBackground(new java.awt.Color(255, 160, 202));
        timeField.setText("(minutes)");

        findButton.setBackground(new java.awt.Color(255, 170, 202));
        findButton.setText("FIND AVAILABLE ROOM!");
        Object comboBoxState = roomChoice.getSelectedItem();
        findButton.setActionCommand("FIND");
        //findButton.addActionListener(this);
        findButton.setToolTipText("Dooooo itttt!");

        //pubic void actionPerformed(ActionEvent e){
            //    if("FIND".equals(e.getActionCommand())){
                //        System.out.println("Press registered.");
                //    }
            //}
        findButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                findButtonMouseReleased(evt);
            }
        });

        monthChoice.setBackground(new java.awt.Color(200, 50, 100));
        monthChoice.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<select month>", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        monthChoice.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                monthChoiceItemStateChanged(evt);
            }
        });

        dayChoice.setBackground(new java.awt.Color(200, 50, 100));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DURATION:");

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(roomChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addGroup(topPanelLayout.createSequentialGroup()
                                .addComponent(monthChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dayChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(findButton, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(roomChoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(findButton, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        middlePanel.setBackground(new java.awt.Color(0, 129, 16));
        middlePanel.setForeground(new java.awt.Color(240, 240, 240));

        resultHeader.setFont(new java.awt.Font("Tahoma", 0, 18));
        resultHeader.setForeground(new java.awt.Color(250, 250, 250));

        result.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        result.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout middlePanelLayout = new javax.swing.GroupLayout(middlePanel);
        middlePanel.setLayout(middlePanelLayout);
        middlePanelLayout.setHorizontalGroup(
            middlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(middlePanelLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(resultHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
            .addGroup(middlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(result, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        middlePanelLayout.setVerticalGroup(
            middlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(middlePanelLayout.createSequentialGroup()
                .addComponent(resultHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(result, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        bottomPanel.setBackground(new java.awt.Color(159, 238, 0));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel4.setText("BY: KERI WARR, ANTHONY CALANDRA, DANIEL HOPPER, NEIL DE VRIES");

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addGap(0, 132, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(middlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(middlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_closeButtonMouseClicked

    private void findButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_findButtonMouseReleased

        try {
            int time = Integer.parseInt(timeField.getText());
            Object month = monthChoice.getSelectedItem();
            int monthNum;
            if(month == "January") monthNum = 1;
            else if(month == "February") monthNum = 2;
            else if(month == "March") monthNum = 3;
            else if(month == "April") monthNum = 4;
            else if(month == "May") monthNum = 5;
            else if(month == "June") monthNum = 6;
            else if(month == "July") monthNum = 7;
            else if(month == "August") monthNum = 8;
            else if(month == "September") monthNum = 9;
            else if(month == "October") monthNum = 10;
            else if(month == "November") monthNum = 11;
            else monthNum = 12;
            System.out.println("Date:"+monthNum+"/"+dayChoice.getSelectedItem());
            int randomNum = (int)(Math.random()*3);
            if (randomNum == 0) {
                resultHeader.setText("THIS ROOM BE AVAILABLE!");
            } else if (randomNum == 1) {
                resultHeader.setText("HERE IS YOUR DESTINATION:");
            } else {
                resultHeader.setText("WALK THIS WAY:");
            }
            OpenRoom res = new OpenRoom();
            String disp = res.roomComp(roomChoice.getSelectedItem().toString(),time,monthNum,Integer.parseInt(dayChoice.getSelectedItem().toString()));
            result.setText(disp);
            result.setFont(new java.awt.Font("Tahoma", 1, ((int)700/disp.length()))); 
        } catch (NumberFormatException nfe) {
            timeField.setText("Invalid");
        }
    }//GEN-LAST:event_findButtonMouseReleased

    private void monthChoiceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_monthChoiceItemStateChanged
        for(int n = 1; n <= 31; n++){
            dayChoice.addItem(n);
        }
    }//GEN-LAST:event_monthChoiceItemStateChanged

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
            java.util.logging.Logger.getLogger(GUIMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUIMain myFrame = new GUIMain();
                myFrame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton closeButton;
    private javax.swing.JComboBox dayChoice;
    private javax.swing.JButton findButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel middlePanel;
    private javax.swing.JComboBox monthChoice;
    private javax.swing.JLabel result;
    private javax.swing.JLabel resultHeader;
    private javax.swing.JComboBox roomChoice;
    private javax.swing.JTextField timeField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
