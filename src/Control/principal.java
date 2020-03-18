package Control;

import Model.Grafo;
import Model.Dijkstra;

public final class principal {

    private static Grafo g = new Grafo();
    private static Dijkstra d = new Dijkstra();

    public static void main(String[] args) {
        new Controlador();
    }
}
