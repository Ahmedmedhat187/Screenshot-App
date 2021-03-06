
package screenshot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Screenshots extends javax.swing.JFrame {


    public Screenshots() {
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

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Capture Screen App - Powered By Ahmed");

        jLabel1.setText("Choose Path to start Capture Screen");

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(jLabel1)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

          String s;
        do{
            s = JOptionPane.showInputDialog("Enter Path"); 
        } while (s == null);
        
// info 1
       try {
            File compinfo = new File (s,"Computer Info.txt");
            FileWriter fw = new FileWriter(compinfo);
            BufferedWriter bw = new BufferedWriter(fw);
            RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
            Map<String,String> systeminfo = runtimeMXBean.getSystemProperties();
            for (Map.Entry<String, String> m : systeminfo.entrySet())
            {    bw.write("["+m.getKey()+"]  =  " + m.getValue());
                 bw.newLine();
            }
            bw.close();
         } catch (FileNotFoundException ex) { JOptionPane.showMessageDialog(null, "Invalid Path"); } 
           catch (Exception ex) { JOptionPane.showMessageDialog(null, "Error"); }
 // info 2
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            File compinfo2 = new File (s,"Computer Info2.txt");
            FileWriter fw2 = new FileWriter(compinfo2);
            BufferedWriter bw = new BufferedWriter(fw2);
            bw.write("Current host name : " + ip.getHostName());  bw.newLine();            
            bw.write("Current IP address : " + ip.getHostAddress());   bw.newLine();        
            bw.write("Operating system Name=> "+ System.getProperty("os.name"));   bw.newLine();
            bw.write("Operating system type => "+ System.getProperty("os.arch"));   bw.newLine();
            bw.write("Operating system version =>"+ System.getProperty("os.version"));   bw.newLine();     
            bw.write(System.getenv("PROCESSOR_IDENTIFIER"));  bw.newLine();
            bw.write(System.getenv("PROCESSOR_ARCHITECTURE"));  bw.newLine();
            bw.write(System.getenv("NUMBER_OF_PROCESSORS"));  bw.newLine();
            bw.write("Available processors (cores): "+Runtime.getRuntime().availableProcessors());bw.newLine();
            bw.write("Free memory (bytes): "+Runtime.getRuntime().freeMemory());bw.newLine();
            long maxMemory = Runtime.getRuntime().maxMemory();
            bw.write("Maximum memory (bytes): " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));bw.newLine();
            bw.write("Total memory (bytes): " + Runtime.getRuntime().totalMemory());bw.newLine();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            bw.write("Current MAC address : ");
            StringBuilder sb = new StringBuilder();
               for (int i = 0; i < mac.length; i++) 
                  {
                     sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));     
                  }
            bw.write(sb.toString());bw.newLine();
            bw.close();
        } 
      catch (UnknownHostException e) {   JOptionPane.showMessageDialog(null, "UnknownHostException"); }
      catch (SocketException e){  JOptionPane.showMessageDialog(null, "SocketException");}
      catch (Exception e){   JOptionPane.showMessageDialog(null, "Exception"); }
// choose the time and date
        Date d = new Date();
        String dd = d.toString();
        dd = dd.concat(".txt");
        dd = dd.replaceAll("\\s","_");
        dd = dd.replaceAll(":",".");
        File datescreenshot = new File(s, dd);
        try {
              FileWriter fw = new FileWriter(datescreenshot);
              BufferedWriter bw = new BufferedWriter(fw);
              bw.write("The Screen shot time is");bw.newLine();
              bw.write(dd);
              bw.close();
            } 
        catch  (IOException ex) { JOptionPane.showMessageDialog(null, "IO");}
        
// capture screen shot and save it        
        try {
                String p = d.toString();
                p = p.concat(".jpg");
                p = p.replaceAll("\\s","_");
                p = p.replaceAll(":",".");
              BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
              ImageIO.write(image, "jpg", new File(s+"\\"+p));
              
              FileWriter fw = new FileWriter(datescreenshot);
              BufferedWriter bw = new BufferedWriter(fw);
              bw.write("The Screen shot time is");bw.newLine();
              bw.write(dd);
              bw.close();
            } 
        catch (AWTException ex) { JOptionPane.showMessageDialog(null,"AWT");} 
        catch (IOException ex) { JOptionPane.showMessageDialog(null, "IO");  } 

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
            java.util.logging.Logger.getLogger(Screenshots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Screenshots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Screenshots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Screenshots.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Screenshots().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
