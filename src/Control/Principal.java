/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import View.InterfacePrincipal;
import DAO.Grafo;
import static Control.CriaArquivo.trasfereParaArquivo;
import Model.Aresta;
import Model.Dijkstra;
import Model.Vertice;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Marcos
 */
public final class Principal {

    private static Grafo g = new Grafo();
    private static Dijkstra d = new Dijkstra();

    public static void main(String[] args) {
        new Controlador();
    }

    public static boolean adicionaVertice(String nomeVertice, String tipoVertice) {
        boolean terminalogia = "Computador".equalsIgnoreCase(tipoVertice);
        Vertice novo = new Vertice(nomeVertice, terminalogia);
        return g.adicionaVertice(novo);
    }

    public static void removeVertice(String emRemocao) {
        g.removeVertice(emRemocao);
    }

    public static void removeAresta(String nomeVertice1, String nomeVertice2) {
        g.removeAresta(nomeVertice1, nomeVertice2);
    }

    public static boolean estabeleceNovaLigacao(String nomeVertice1, String nomeVertice2, int pesoLigacao) {
        Vertice v1 = g.buscaVertice(nomeVertice1);
        Vertice v2 = g.buscaVertice(nomeVertice2);
        return g.adicionaArestaDupla(pesoLigacao, v1, v2);
    }

    public static void salvaArquivoConfiguracao(String diretorio) throws IOException, ExceptionInInitializerError {
        CriaArquivo.trasfereParaArquivo(diretorio, g);
    }

    public static void leArquivoConfiguacao(String diretorio) throws IOException {
        LeArquivo.trasfereParaGrafo(diretorio, g);
    }

    public static Grafo getGrafo() {
        return g;
    }

    public static LinkedList<Vertice> identificaCaminhos(String nomeVertice, boolean apenasTerminal) {
        Vertice buscado = g.buscaVertice(nomeVertice);
        if (!apenasTerminal || buscado.isTerminal()) {
            Grafo caminho = d.dijkstra(buscado, g);
            return caminho.getVertices();
        }
        return null;
    }

    static boolean removeAresta(String nomeVertice) {
        boolean remocaoConcluida = true;
        Vertice buscado = g.buscaVertice(nomeVertice);
        LinkedList<Aresta> arestas = buscado.getArestas();
        for (int i = 0; i < arestas.size(); i++) {
            Vertice adjacente = arestas.get(i).getFim();
            if (!g.removeAresta(nomeVertice, adjacente.getNome())) {
                remocaoConcluida = false;
            }
        }
        return remocaoConcluida;
    }
}
