/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Aresta;
import DAO.Grafo;
import Model.Aresta;
import Model.Vertice;
import Model.Vertice;
import static Control.LeArquivo.CODIFICACAO_PARA_TRANSCRICAO_DE_DADOS;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

/**
 *
 * @author Marcos
 */
public class CriaArquivo {

    public static final String separador_Informacoes_Vertice = ",";

    private static final String texto_Cabecalho = "Arquivo de configuração:"
            + "\nNo_Inicio,Terminal,Destino,Peso\n";

    public static boolean trasfereParaArquivo(String caminho, Grafo grafo) throws IOException, ExceptionInInitializerError {
        try {
            if (grafo.estaVazio()) {
                return false;
            }
            File diretorio = new File(caminho);
            diretorio.createNewFile();
            try (BufferedWriter gravacao = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(diretorio), CODIFICACAO_PARA_TRANSCRICAO_DE_DADOS))) {
                gravacao.append(texto_Cabecalho);
                LinkedList<Vertice> vertices = grafo.getVertices();
                String linha = "";
                for (int i = 0; i < vertices.size(); i++) {
                    Vertice vertice = vertices.get(i);
                    LinkedList<Aresta> arestas = vertice.getArestas();
                    if (arestas.isEmpty()) {
                        linha += vertice.getNome();
                        linha += separador_Informacoes_Vertice;
                        linha += verificaTerminais(vertice);
                        linha += separador_Informacoes_Vertice;
                        linha += "";
                        linha += separador_Informacoes_Vertice;
                        linha += "";
                        gravacao.append(linha + "\n");
                        linha = "";
                    }
                    else {
                        for (int j = 0; j < arestas.size(); j++) {
                            Aresta aresta = arestas.get(j);
                            linha += vertice.getNome();
                            linha += separador_Informacoes_Vertice;
                            linha += verificaTerminais(vertice);
                            linha += separador_Informacoes_Vertice;
                            linha += aresta.getFim().getNome();
                            linha += separador_Informacoes_Vertice;
                            linha += aresta.getPeso();
                            gravacao.append(linha + "\n");
                            linha = "";
                        }
                    }
                }
                return true;
            }
        }
        catch (IOException ex) {
            throw new IOException(ex);
        }
        catch (ExceptionInInitializerError ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static String verificaTerminais(Vertice vertice) {
        return (vertice.isTerminal()) ? "sim" : "não";
    }
}
