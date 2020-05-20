package lesson7;

public class MainDZ {

    public static void main(String[] args) {
        testBfsShort();
    }

    private static void testBfsShort() {
        Graph graph = new Graph(11);
        graph.addVertex("Москва");
        graph.addVertex("Тула");
        graph.addVertex("Рязань");
        graph.addVertex("Калуга");
        graph.addVertex("Липецк");
        graph.addVertex("Тамбов");
        graph.addVertex("Орел");
        graph.addVertex("Саратов");
        graph.addVertex("Курск");
        graph.addVertex("Воронеж");
        graph.addVertex("Город без дорог");

        graph.addEdges("Москва", "Тула", "Рязань", "Калуга");
        graph.addEdges("Тула", "Липецк");
        graph.addEdges("Рязань", "Тамбов");
        graph.addEdges("Калуга", "Орел");
        graph.addEdges("Липецк", "Воронеж");
        graph.addEdges("Тамбов", "Саратов");
        graph.addEdges("Орел", "Курск");
        graph.addEdges("Саратов", "Воронеж");
        graph.addEdges("Курск", "Воронеж");

 //       graph.bfs("Москва");

        graph.bfsMin("Москва", "Воронеж");

        graph.bfsMin("Рязань", "Орел");

        graph.bfsMin("Москва", "Город без дорог");
    }
}
