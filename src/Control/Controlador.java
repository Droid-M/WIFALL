package Control;

import DAO.Grafo;
import Model.Aresta;
import Model.Vertice;
import View.IRemoveVertice;
import View.InterfacePrincipal;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public final class Controlador {

    private final static int altura_vertice_Rooteador = 30;
    private static String tipo_aparelho = "computador";
    private final static int largura_vertice_Rooteador = 50;
    private final static int altura_vertice_Computador = 60;
    private final static int largura_vertice_Computador = 100;
    private final static int altura_vertice_Internet = 90;
    private final static int largura_vertice_Internet = 200;
    private final static String estilo_aresta = "ArestasBidirecionais";
    private static mxCell primeiroVertice;
    private static mxCell celulaSelecionada1;
    private static mxCell celulaSelecionada2;
    private static int xA = Integer.MIN_VALUE, xB = Integer.MIN_VALUE, yA, yB;
    private static Collection<Object> listaCaminhos = new LinkedList();

    private static HashMap<String, mxCell> listaArestas = new HashMap();
    private static HashMap<String, mxCell> listaVertices = new HashMap();

    public static void adicionaVertice(InterfacePrincipal i) {
        String tipoVertice = tipo_aparelho;
        String nomeVertice = i.recebeNomeVertice();
        Pattern pattern = Pattern.compile("\\w");
        Matcher matcher = pattern.matcher(nomeVertice);
        if (nomeVertice != null && matcher.find()) {
            System.out.println(nomeVertice);
            if (!Principal.adicionaVertice(nomeVertice, tipoVertice)) {
                i.exibeMensagem("O vértice '" + nomeVertice + "' não será adicionado porque já existe no sistema!");
            }
            else {
                insereVerticeInterface(i, nomeVertice, tipoVertice);
            }
        }
        else {
            i.exibeMensagem("Operação interrompida! Nome de vértice informado é inválido!");
        }
    }

    public static void adicionaAresta(InterfacePrincipal i) {
        String[] informacoes = i.recebeInformacoesLigacao();
        Pattern executor1 = Pattern.compile("\\w");
        Matcher buscador1 = executor1.matcher(informacoes[0]);
        Matcher buscador2 = executor1.matcher(informacoes[1]);
        Pattern executor2 = Pattern.compile("\\D");
        Matcher buscador3 = executor2.matcher(informacoes[2]);
        if (!buscador2.find() || !buscador1.find()) {
            i.exibeMensagem("Operação interrompida! Nome de vértice informado é inválido!");
            return;
        }
        if (buscador3.find()) {
            i.exibeMensagem("Operação interrompida! O valor do peso de ligação fornecido é inválido!");
            return;
        }
        int pesoLigacao = Integer.parseInt(informacoes[2]);
        if (!Principal.estabeleceNovaLigacao(informacoes[0], informacoes[1], pesoLigacao) || insereArestaInterface(i, informacoes[0], informacoes[1], informacoes[2]) == null) {
            i.exibeMensagem("Aresta não adicionada! Verifique a existência dos vértices informados e de suas ligações!");
        }
    }

    private static mxCell insereVerticeInterface(InterfacePrincipal i, String nomeVertice, String tipoVertice) {
        mxCell novoVertice = null;
        if (listaVertices.get(nomeVertice) == null) {
            mxGraph grafo = i.getPainel();
            Object pai = grafo.getDefaultParent();
            grafo.getModel().beginUpdate();
            if (tipoVertice.equalsIgnoreCase("internet")) {
                novoVertice = (mxCell) grafo.insertVertex(pai, nomeVertice, nomeVertice, 300, 50, largura_vertice_Internet, altura_vertice_Internet, tipoVertice);
            }
            else if (tipoVertice.equalsIgnoreCase("computador")) {
                novoVertice = (mxCell) grafo.insertVertex(pai, nomeVertice, nomeVertice, 300, 50, largura_vertice_Computador, altura_vertice_Computador, tipoVertice);
            }
            else if (tipoVertice.equalsIgnoreCase("rooteador")) {
                novoVertice = (mxCell) grafo.insertVertex(pai, nomeVertice, nomeVertice, 300, 50, largura_vertice_Rooteador, altura_vertice_Rooteador, tipoVertice);
            }
            listaVertices.put(nomeVertice, novoVertice);
            if (!listaVertices.isEmpty() && primeiroVertice != null) {
                mxCell novaAresta = (mxCell) grafo.insertEdge(pai, "", "", primeiroVertice, novoVertice, estilo_aresta);
                reorganizaLayout(i);
                novaAresta.setVisible(false);
            }
            else {
                primeiroVertice = novoVertice;
            }
            grafo.getModel().endUpdate();
            return novoVertice;
        }
        return null;
    }

    private static mxCell insereArestaInterface(InterfacePrincipal i, String nomeVertice1, String nomeVertice2, String nomeAresta) {
        if (listaArestas.get(criaIdentificador(nomeVertice1, nomeVertice2)) == null
                && listaArestas.get(criaIdentificador(nomeVertice2, nomeVertice1)) == null) {

            mxGraph grafo = i.getPainel();
            Object pai = grafo.getDefaultParent();
            mxCell vertice1 = listaVertices.get(nomeVertice1);
            mxCell vertice2 = listaVertices.get(nomeVertice2);
            grafo.getModel().beginUpdate();
            mxCell novaAresta = (mxCell) grafo.insertEdge(pai, nomeAresta, nomeAresta, vertice1, vertice2, estilo_aresta);
            listaArestas.put(criaIdentificador(nomeVertice1, nomeVertice2), novaAresta);
            reorganizaLayout(i);
            grafo.getModel().endUpdate();
            return novaAresta;
        }
        return null;
    }

    private static void reorganizaLayout(InterfacePrincipal i) {
        mxFastOrganicLayout novaOrganizacao = new mxFastOrganicLayout(i.getPainel());
        novaOrganizacao.setMinDistanceLimit(0.02);
        novaOrganizacao.execute(i.getPainel().getDefaultParent());
    }

    public static void removeCelula(InterfacePrincipal i) {
        mxCell celula = celulaSelecionada2 == null ? celulaSelecionada1 : celulaSelecionada2;
        if (celula != null) {
            i.getPainel().getModel().beginUpdate();
            if (celula.isEdge()) {
                removeAresta(i, celula);
                i.getPainel().getModel().endUpdate();
                i.exibeMensagem("Aresta removida com sucesso!");
            }
            else if (celula.isVertex()) {
                removeArestasDeVertice(i, celula);
                removeVertice(i, celula);
                i.getPainel().getModel().endUpdate();
                i.exibeMensagem("Vértice removido com sucesso!");
                //String nomeCelula = (String) celula.getValue();
                //Principal.removeVertice(nomeCelula);
            }
        }
        celulaSelecionada1 = celulaSelecionada2 = null;
    }

    private static mxCell removeAresta(InterfacePrincipal i, mxCell aresta) {
        mxCell removida = (mxCell) i.getPainel().getModel().remove(aresta);
        for (String identificador : listaArestas.keySet()) {
            if (listaArestas.get(identificador).equals(removida)) {
                return listaArestas.remove(identificador);
            }
        }
        return null;
    }

    private static mxCell removeVertice(InterfacePrincipal i, mxCell vertice) {
        mxCell removido = (mxCell) i.getPainel().getModel().remove(vertice);
        Principal.removeVertice((String) removido.getValue());
        listaVertices.remove((String) removido.getValue());
        if (listaVertices.isEmpty()) {
            primeiroVertice = null;
        }
        return removido;
    }

    private static void removeArestasDeVertice(InterfacePrincipal i, mxCell vertice) {
        if (Principal.removeAresta((String) vertice.getValue())) {
            while (vertice.getEdgeCount() > 0) {
                i.getPainel().getModel().remove(vertice.getEdgeAt(0)); //Removendo sempre a primeira aresta do vértice
            }
        }
    }

    public static void exportaConfiguracoes(InterfacePrincipal padrao) {
        String diretorio = padrao.selecionaDiretorioSalvamento();
        if (diretorio != null) {
            try {
                Principal.salvaArquivoConfiguracao(diretorio);
                padrao.exibeMensagem("As configurações foram salvas com sucesso!");
            }
            catch (IOException | ExceptionInInitializerError ex) {
                padrao.exibeMensagem("Houve um erro na criação do arquivo! Por favor, tente com outro diretório.");
            }
        }
        else {
            padrao.exibeMensagem("Operação cancelada! Nenhum diretorio foi informado!");
        }
    }

    private static String criaIdentificador(String nome1, String nome2) {
        return nome1 + "\n" + nome2;
    }

    public static void cliqueEsquerdo(InterfacePrincipal i, int x, int y) {
        mxCell selecionada = (mxCell) i.getAreaComponentes().getCellAt(x, y);

        if (selecionada == null) {
            celulaSelecionada1 = celulaSelecionada2 = null;
        }
        else if (selecionada.isEdge()) {
            celulaSelecionada1 = celulaSelecionada2 = null;
            removeSelecao(i, x, y);
        }
        else {
            if (celulaSelecionada1 == null) {
                celulaSelecionada1 = selecionada;
                if (selecionada.isVertex()) {
                    LinkedList<Vertice> caminhos = Principal.identificaCaminhos((String) selecionada.getValue(), false);
                    destacaCaminhosInterface(i, caminhos);
                }
            }
            else {
                if (celulaSelecionada1.equals(selecionada)) {
                    celulaSelecionada1 = null;
                    i.getPainel().setSelectionCell(celulaSelecionada1);
                }
                else if (celulaSelecionada2 == null) {
                    celulaSelecionada2 = selecionada;
                    LinkedList<Vertice> caminhos = Principal.identificaCaminhos((String) celulaSelecionada1.getValue(), true);
                    if (caminhos != null) {
                        Vertice v1, v2;
                        v1 = v2 = null;
                        for (int j = 0; j < caminhos.size(); j++) {
                            Vertice verticeAtual = caminhos.get(j);
                            if (verticeAtual.getNome().equals(celulaSelecionada2.getValue())) {
                                v2 = verticeAtual;
                            }
                            else if (verticeAtual.getNome().equals(celulaSelecionada1.getValue())) {
                                v1 = verticeAtual;
                            }
                            if (v1 != null && v2 != null) {
                                if (v1.isTerminal() && v2.isTerminal()) {
                                    listaCaminhos = new LinkedList();
                                    destacaAntecessores(i, verticeAtual);
                                    break;
                                }
                            }
                        }
                    }
                }
                else {
                    celulaSelecionada1 = selecionada;
                    i.getPainel().setSelectionCell(celulaSelecionada1);
                    celulaSelecionada2 = null;
                }
            }
        }
        if (xA == xB && xA == Integer.MIN_VALUE) {
            xA = x;
            yA = y;
        }
        else {
            xB = x;
            yB = y;
        }
        atualizaDistanciaEuclidiana(i);
    }

    public static void removeSelecao(InterfacePrincipal i, int x, int y) {
        mxCell selecionada = (mxCell) i.getAreaComponentes().getCellAt(x, y);
        i.getPainel().removeSelectionCell(selecionada);
    }

    public static void importaConfiguracoes(InterfacePrincipal i) {
        String diretorio = i.selecionaDiretorioAbertura();
        if (diretorio != null) {
            try {
                Principal.leArquivoConfiguacao(diretorio);
                trasfereModelParaInterface(i, Principal.getGrafo());
                i.exibeMensagem("Suas configurações foram importadas com sucesso!");
            }
            catch (IOException | ExceptionInInitializerError ex) {
                i.exibeMensagem("Houve um erro na criação do arquivo! Por favor, tente com outro diretório.");
            }
        }
        else {
            i.exibeMensagem("Operação cancelada! Nenhum diretorio foi informado!");
        }
    }

    private static void trasfereModelParaInterface(InterfacePrincipal i, Grafo grafo) {
        LinkedList<Vertice> vertices = grafo.getVertices();
        for (int j = 0; j < vertices.size(); j++) {
            Vertice verticeAtual = vertices.get(j);
            String tipoVertice = "internet";
            if (!"Internet".equalsIgnoreCase(verticeAtual.getNome())) {
                tipoVertice = verticeAtual.isTerminal() ? "computador" : "rooteador";
            }
            insereVerticeInterface(i, verticeAtual.getNome(), tipoVertice);
        }

        for (int j = 0; j < vertices.size(); j++) {
            Vertice verticeAtual = vertices.get(j);
            LinkedList<Aresta> arestas = verticeAtual.getArestas();
            for (int k = 0; k < arestas.size(); k++) {
                Aresta arestaAtual = arestas.get(k);
                String nomeVerticeDestino = arestaAtual.getFim().getNome();
                int pesoArestaAtual = arestaAtual.getPeso();
                insereArestaInterface(i, verticeAtual.getNome(), nomeVerticeDestino, pesoArestaAtual + "");
            }
        }
    }

    public static void defineZoom(InterfacePrincipal i, int rotacaoRoda, boolean ctrlPressionado) {
        if (ctrlPressionado) {
            if (rotacaoRoda > 0) {
                i.getAreaComponentes().zoomOut();
                i.getAreaComponentes().zoomAndCenter();
            }
            else {
                i.getAreaComponentes().zoomIn();
                i.getAreaComponentes().zoomAndCenter();
            }
        }
    }

    private static void atualizaDistanciaEuclidiana(InterfacePrincipal i) {
        String coordenada1, coordenada2, distanciaE;
        if (xA == Integer.MIN_VALUE) {
            coordenada1 = coordenada2 = distanciaE = "";
        }
        else {
            coordenada1 = "x= " + xA + ",   y= " + yA;
            if (xB == Integer.MIN_VALUE) {
                coordenada2 = distanciaE = "";
            }
            else {
                coordenada2 = "x= " + xB + ",   y= " + yB;
                double BC = Math.pow(yB - yA, 2);
                double AC = Math.pow(xB - xA, 2);
                double distancia = Math.sqrt(BC + AC);
                distanciaE = distancia + "";
                xA = xB = Integer.MIN_VALUE;
            }
        }
        i.exibeNovaDistanciaEuclidiana(coordenada1, coordenada2, distanciaE);
    }

    private static void destacaCaminhosInterface(InterfacePrincipal i, LinkedList<Vertice> caminhos) {
        listaCaminhos = new LinkedList();
        for (int j = 0; j < caminhos.size(); j++) {
            Vertice verticeAtual = caminhos.get(j);
            destacaAntecessores(i, verticeAtual);
        }
    }

    private static void destacaAntecessores(InterfacePrincipal i, Vertice verticeAtual) {
        while (verticeAtual != null) {
            mxCell verticeEncontrado = listaVertices.get(verticeAtual.getNome());
            extendeCaminho(verticeEncontrado);
            if (verticeAtual.getVerticeAntecessor() != null) {
                extendeCaminho(listaArestas.get(criaIdentificador(verticeAtual.getNome(), verticeAtual.getVerticeAntecessor().getNome())));
                extendeCaminho(listaArestas.get(criaIdentificador(verticeAtual.getVerticeAntecessor().getNome(), verticeAtual.getNome())));
            }
            verticeAtual = verticeAtual.getVerticeAntecessor();
        }
        i.getPainel().setSelectionCells(listaCaminhos);
    }

    private static void extendeCaminho(mxCell novaCelula) {
        if (!listaCaminhos.contains(novaCelula)) {
            listaCaminhos.add(novaCelula);
        }
    }

    public static void defineItemSelecionado(String aparelhoSelecionado) {
        tipo_aparelho = aparelhoSelecionado;
    }
}
