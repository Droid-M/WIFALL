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

    InterfacePrincipal padrao;

    public ActionEventListenerExportaGrafo(InterfacePrincipal novainterface) {
        this.padrao = novainterface;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Controlador.exportaConfiguracoes(padrao);
    }

}
