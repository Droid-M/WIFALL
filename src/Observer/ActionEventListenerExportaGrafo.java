package Observer;

import Control.Controlador;
import Control.Principal;
import View.InterfacePrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ActionEventListenerExportaGrafo implements ActionListener {
Controlador padrao;
    public ActionEventListenerExportaGrafo(Controlador novo) {
        this.padrao = novo;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        padrao.exportaConfiguracoes();
    }

}
