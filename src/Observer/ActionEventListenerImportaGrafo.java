package Observer;

import Control.Controlador;
import View.InterfacePrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEventListenerImportaGrafo implements ActionListener {

    private InterfacePrincipal padrao;

    public ActionEventListenerImportaGrafo(InterfacePrincipal novaInterface) {
        this.padrao = novaInterface;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Controlador.importaConfiguracoes(padrao);
    }

}
