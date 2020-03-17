/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import Control.Controlador;
import Control.Principal;
import View.InterfacePrincipal;
import com.mxgraph.layout.mxFastOrganicLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Marcos
 */
public class ActionEventListenerConectaVertices implements ActionListener {

    private InterfacePrincipal padrao;

    public ActionEventListenerConectaVertices(InterfacePrincipal nova) {
        padrao = nova;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Controlador.adicionaAresta(padrao);
    }

}
