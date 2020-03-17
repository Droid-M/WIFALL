/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Marcos
 */
public class ActionEventListenerSaida implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }

}
