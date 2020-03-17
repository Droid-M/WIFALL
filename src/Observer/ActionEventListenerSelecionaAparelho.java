package Observer;

import Control.Controlador;
import Control.Principal;
import View.InterfacePrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class ActionEventListenerSelecionaAparelho implements ActionListener {
    private JComboBox selecaoAparelho;

    public ActionEventListenerSelecionaAparelho(JComboBox selecaoAparelho) {
        this.selecaoAparelho = selecaoAparelho;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Controlador.defineItemSelecionado((String) selecaoAparelho.getSelectedItem());
    }

}
