package Control;

import DAO.Grafo;
import Model.Vertice;
import Model.Vertice;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Marcos Vinícius Ferreira dos Santos
 */
public final class LeArquivo {

    protected static String CODIFICACAO_PARA_TRANSCRICAO_DE_DADOS = "ISO-8859-1";

    /**
     * Lê arquivo, analisa seus dados e os transfere para a lista de adjacência
     *
     * @param diretorio Diretório do arquivo de texto
     * @param grafo Matriz que receberá os dados do arquivo
     * @throws java.io.FileNotFoundException Diretório não existe
     * @throws java.io.IOException Erro atribuído aos problemas na abertura do
     * arquivo de texto
     */
    public static void trasfereParaGrafo(String diretorio, Grafo grafo) throws FileNotFoundException, IOException {
        try {
            BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream(diretorio), CODIFICACAO_PARA_TRANSCRICAO_DE_DADOS));
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            String nomeVertice = "";
            String nomeDestino = "";
            boolean terminal = false;
            //Adicionando os vértices sem definir as ligações (etapa de ligações está a partir da linha 73)
            while (linha != null) {// lê até a última linha:
                String[] dadosLinha = linha.split(",");
                if (dadosLinha.length == 4) { //Os vértices precisam conter 4 informações descritas no arquivo
                    try {
                        nomeVertice = dadosLinha[0];
                        terminal = "sim".equals(dadosLinha[1]);
                        nomeDestino = dadosLinha[2];
                        //A linha abaixo, apesar de não parecer, é muito importante para a leitura do arquivo:
                        Integer.parseInt(dadosLinha[3]);
                        /* Através da linha anterior dá pra saber quais são as linhas válidas
                        já que as únicas linhas válidas contém o peso de ligações (em inteiro) após a 3ª vírgula
                        se o valor da posição 3 no vetor não for convertido em inteiros (se não houver apenas números lá), 
                        significa que a linha não é válida*/
                        grafo.adicionaVertice(new Vertice(nomeVertice, terminal));
                    }
                    catch (NumberFormatException e) {
                    }
                }
                else if (dadosLinha.length == 2) { //Caso seja um vértice sem ligações (sem arestas), faça:
                    nomeVertice = dadosLinha[0];
                    terminal = "sim".equals(dadosLinha[1]);
                    if (terminal || "não".equals(dadosLinha[1])) {
                        grafo.adicionaVertice(new Vertice(nomeVertice, terminal));
                    }
                }
                linha = lerArq.readLine();
            }

        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }
        catch (IOException ex) {
            throw new IOException(ex);
        }

        //Lendo o arquivo outra vez para definir as ligações entre os vértices
        try {
            BufferedReader arq = new BufferedReader(new InputStreamReader(new FileInputStream(diretorio), CODIFICACAO_PARA_TRANSCRICAO_DE_DADOS));
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            while (linha != null) {// lê até a última linha:
                String[] dadosLinha = linha.split(",");
                if (dadosLinha.length == 4) {
                    try {
                        int pesoLigacao = Integer.parseInt(dadosLinha[3]);
                        String nomeOrigem = dadosLinha[0];
                        String nomeDestino = dadosLinha[2];
                        Vertice origem = grafo.buscaVertice(nomeOrigem);
                        Vertice destino = grafo.buscaVertice(nomeDestino);
                        grafo.adicionaArestaSimples(pesoLigacao, origem, destino);
                    }
                    catch (NumberFormatException e) {
                    }
                }
                linha = lerArq.readLine();
            }
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }
        catch (IOException ex) {
            throw new IOException(ex);
        }
    }
}
