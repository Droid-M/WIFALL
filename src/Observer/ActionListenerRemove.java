package Observer;

import Control.Controlador;
import Control.Principal;
import View.IRemoveVertice;
import View.InterfacePrincipal;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.view.mxGraph;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Hashtable;

public class ActionListenerRemove implements ActionListener {

    private InterfacePrincipal padrao;

    public ActionListenerRemove(InterfacePrincipal novaInteface) {
        this.padrao = novaInteface;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Controlador.removeCelula(padrao);
    }
}
