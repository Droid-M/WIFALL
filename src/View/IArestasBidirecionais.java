/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.util.Hashtable;
import java.util.Map;
import javafx.scene.paint.Color;

/**
 *
 * @author Marcos
 */
public class IArestasBidirecionais {

    public IArestasBidirecionais(mxGraph grafo) {
        mxStylesheet stylesheet = grafo.getStylesheet();
        String nomeEstilo = "ArestasBidirecionais";
        Hashtable<String, Object> estilo = new Hashtable();
        estilo.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        estilo.put(mxConstants.STYLE_STARTARROW, mxConstants.NONE);
        estilo.put(mxConstants.STYLE_STARTARROW, mxConstants.NONE);
        estilo.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
        estilo.put(mxConstants.STYLE_MOVABLE, false);
        //estilo.put(mxConstants.STYLE_DASHED, true); (IGNORE)
        estilo.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
        estilo.put(mxConstants.STYLE_EDITABLE, false);
        
        stylesheet.putCellStyle(nomeEstilo, estilo);
    }
}
