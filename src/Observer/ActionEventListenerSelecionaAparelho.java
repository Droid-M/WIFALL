package Observer;

import Control.Controlador;
import Control.Principal;
import View.InterfacePrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class ActionEventListenerSelecionaAparelho implements ActionListener {

    public ActionEventListenerSelecionaAparelho(Controlador aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ActionEventListenerSelecionaAparelho(Controlador aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Controlador.defineItemSelecionado((String) selecaoAparelho.getSelectedItem());
    }

}
