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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventosMouse implements MouseListener {

    InterfacePrincipal padrao;

    public EventosMouse(InterfacePrincipal novaInterface) {
        this.padrao = novaInterface;
    }

    @Override
    public void mouseClicked(MouseEvent clique) {
        Controlador.cliqueEsquerdo(padrao, clique.getX(), clique.getY());
    }

    @Override
    public void mousePressed(MouseEvent clique) {
        Controlador.removeSelecao(padrao, clique.getX(), clique.getY());
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
}
