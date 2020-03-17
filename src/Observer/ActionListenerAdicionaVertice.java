/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import Control.Controlador;
import View.InterfacePrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Marcos
 */
public class ActionListenerAdicionaVertice implements ActionListener {

    InterfacePrincipal padrao;

    public ActionListenerAdicionaVertice(InterfacePrincipal i) {
        this.padrao = i;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Controlador.adicionaVertice(padrao);
    }

}
