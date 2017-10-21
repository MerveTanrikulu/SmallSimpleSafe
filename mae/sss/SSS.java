//M A Eyler 2002

package mae.sss;

import java.awt.*;
import javax.swing.*;
import java.util.Date;
import mae.util.Console;
import mae.util.Scaler;
import mae.util.PropertyManager;

public class SSS extends JFrame {
   
   
   public static final String SSSversion = "V2.08a";
   public static String version() { return "Oct 2017 "+SSSversion; }
   public static final String PREFS = "mae-sss";
   public static final String title = "Small Simple Safe";
   public static SSS instance;
   static PropertyManager pm = new PropertyManager(PREFS, "SSS", SSS.class);
   
   Inspector insp;
   InspectorPanel pan;
   String initCls;

   SSS() {
      Console.getInstance(); 
      if (System.console() == null) Console.start(); //V2.07 
      System.out.printf("SSS %s begins %s%n", SSSversion, new Date());
      //InspectorPanel.initSplash();  //V1.66 added, V2.07 removed
      UIManager.put("ToolTip.font", mae.brow.Fide.ITEM);  //V1.68
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      pan = new InspectorPanel();
      insp = new Inspector(this, pan);
      setContentPane(pan); 
      pack(); //setVisible(true);  
      loadProps();  //name);
      insp.clearPanel(); 
      insp.inspectClass(initCls); setVisible(true);
      InspectorPanel.makeAboutDlg(this);
      //InspectorPanel.disposeSplash();  //V1.66 added, V2.07 removed 
   }
   
   void loadProps() {  //(String name) {
      int W = Scaler.scaledInt(680);  //V1.68
      int H = Scaler.scaledInt(410);
      Dimension d = getToolkit().getScreenSize();
      int x = d.width-W; //, y = d.height-H-20;
      setTitle(pm.getProperty("title", title));
      setBounds(pm.getBounds("frame", x, 0, W, H));
      initCls = pm.getProperty("init.class", "mae.util.Small");
      pan.loadProps(pm);
   }    
   
   void saveProps() {
      pm.setProperty("title", this.getTitle());
      pm.setBounds("frame", this.getBounds());
      pm.setProperty("init.class", initCls);
      //pm.setProperty("saved.on", ""+new Date());  //not used
      pan.saveProps(pm);
      pm.save("SSS properties");  //one-line comment
   }    
   
   public void dispose() {
      saveProps(); Inspector.ins = null;
      super.dispose(); 
      //InspectorPanel.about.dispose();
      System.exit(0);
   }    
    
   public static void main(String[] args) { main(); }
   public static Inspector main() { 
       if (instance == null) instance = new SSS();
       return instance.insp; 
   }
}

