/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import Control.Controlador;
import View.InterfacePrincipal;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventosMouse extends MouseAdapter {

    public EventosMouse(Controlador aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public EventosMouse(Controlador aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent clique) {
        Controlador.cliqueEsquerdo(clique.getX(), clique.getY());
    }

    @Override
    public void mousePressed(MouseEvent clique) {
        Controlador.removeSelecao(clique.getX(), clique.getY());
    }
}
