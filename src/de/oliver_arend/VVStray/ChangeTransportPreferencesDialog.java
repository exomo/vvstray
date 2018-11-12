package de.oliver_arend.VVStray;

import java.awt.*;
import java.awt.Window.Type;
import java.awt.event.*;
import javax.swing.BoxLayout;

public class ChangeTransportPreferencesDialog {
    private static Frame frame;
    private Checkbox sBahnCheckbox;
    private Checkbox uBahnCheckbox;
    private Checkbox busCheckbox;
    private VVStray parent;
    
    public ChangeTransportPreferencesDialog(VVStray parent) {
    	this.parent = parent;
    	
    	frame = new Frame("Change mode of transport preferences");
    	frame.setType(Type.UTILITY);
    	
    	Panel panelInput = new Panel(new FlowLayout(FlowLayout.CENTER));
    	Panel panelButtons = new Panel(new FlowLayout(FlowLayout.CENTER));
        
    	frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));  

        panelInput.add(new Label("Use: "));
        sBahnCheckbox = new Checkbox("S-Bahn");
        uBahnCheckbox = new Checkbox("U-Bahn");
        busCheckbox = new Checkbox("Bus");
    	panelInput.add(sBahnCheckbox);
    	panelInput.add(uBahnCheckbox);
    	panelInput.add(busCheckbox);

        Button OK = new Button("OK");
        Button cancel = new Button("Cancel");

        OK.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	UserSettings u = UserSettingsProvider.getUserSettings();
            	u.setUseSBahn(sBahnCheckbox.getState());
            	u.setUseUBahn(uBahnCheckbox.getState());
            	u.setUseBus(busCheckbox.getState());
            	UserSettingsProvider.setUserSettings(u);
            	parent.update();
            	close();
            }  
        });  

        cancel.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                close();
            }  
        });  

        panelButtons.add(OK);   
        panelButtons.add(cancel);
        
        frame.add(panelInput);
        frame.add(panelButtons);

        frame.pack();
    }
    
    public void open() {
        Point mousePosition = MouseInfo.getPointerInfo().getLocation();
        sBahnCheckbox.setState(UserSettingsProvider.getUserSettings().isUseSBahn());
        uBahnCheckbox.setState(UserSettingsProvider.getUserSettings().isUseUBahn());
        busCheckbox.setState(UserSettingsProvider.getUserSettings().isUseBus());
        frame.setLocation(mousePosition.x - frame.getWidth(), mousePosition.y - frame.getHeight());
        frame.setVisible(true);
    }
    
    public void close() {
    	frame.setVisible(false);
    }

}
