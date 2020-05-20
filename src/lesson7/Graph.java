package lesson7;

import java.util.*;

public class Graph {

    private final List<Vertex> vertexList;
    private boolean wayFind;
    private List<String> vertexWay;

    private final boolean[][] adjMat;

    public Graph(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.vertexWay = new ArrayList<>(maxVertexCount);
        this.adjMat = new boolean[maxVertexCount][maxVertexCount];
    }

    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public int getSize() {
        return vertexList.size();
    }

    public void addEdges(String start, String second, String... other) {
        addEdge(start, second);
        for (String another : other) {
            addEdge(start, another);
        }
    }

    private void addEdge(String start, String finish) {
        int startIndex = indexOf(start);
        int finishIndex = indexOf(finish);

        if (startIndex == -1 || finishIndex == -1) {
            throw new IllegalArgumentException("Invalid label for edge");
        }

        adjMat[startIndex][finishIndex] = true;
        adjMat[finishIndex][startIndex] = true;

    }

    public int indexOf(String vertexLabel) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexLabel.equals(vertexList.get(i).getLabel())) {
                return i;
            }
        }

        return -1;
    }

    private  void displayVertex(Vertex vertex) {
        System.out.println(vertex);
    }

    public void display() {
        for (int i = 0; i < getSize(); i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMat[i][j]) {
                    System.out.print(" -> " + vertexList.get(j));
                }
            }
            System.out.println();
        }
    }

    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start lebel");
        }

        Stack<Vertex> stack = new Stack<>();

        Vertex vertex = vertexList.get(startIndex);
        visitVertex(vertex, stack);

        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(vertex,stack);
            }
            else {
                stack.pop();
            }
        }

        resetVertexState();

    }

    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start lebel");
        }

        Queue<Vertex> queue = new LinkedList<>();

        Vertex vertex = vertexList.get(startIndex);
        visitVertex(vertex, queue);

        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(vertex, queue);
            }
            else {
                queue.remove();
            }
        }

        resetVertexState();
    }

    public void bfsMin(String startLabel, String finishLabel) {
        int startIndex = indexOf(startLabel);
        int finishIndex = indexOf(finishLabel);

        wayFind = false;

        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start lebel");
        }

        if (finishIndex == -1) {
            throw new IllegalArgumentException("Invalid finish lebel");
        }

        Queue<Vertex> queue = new LinkedList<>();

        Vertex vertex = getVertexList().get(startIndex);

        visitVertexMinWay(vertex, queue);

        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                if (vertex.getLabel() != finishLabel) {
                    visitVertexMinWay(vertex, queue);
                } else {
                    System.out.println("Путь " + startLabel + " - " + finishLabel + " найден!");
                    wayFind = true;

                    Vertex vertexChild =  vertex;
                    Vertex vertexParent = vertex.getParentVertex();

                    while (vertexParent.getLabel() != startLabel) {
                        vertexWay.add(vertexChild.getLabel());
                        vertexChild = vertexParent;
                        vertexParent = vertexParent.getParentVertex();
                    }
                    vertexWay.add(vertexChild.getLabel());
                    vertexWay.add(vertexParent.getLabel());
                    displayWay();
                    System.out.println();
                    vertexWay.clear();
                    break;
                }
            } else {
                queue.remove();
            }
        }
        if (!wayFind) {
            System.out.println("Путь " + startLabel + " - " + finishLabel + " не найден!");
         }
        resetVertexState();
    }

    private void displayWay() {
        for (int i = vertexWay.size() - 1; i >= 0 ; i--) {
            System.out.println(vertexWay.get(i));
        }
    }

    private void resetVertexState() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex peek) {
        int peakIndex = vertexList.indexOf(peek);
        for (int i = 0; i < getSize(); i++) {
            if (adjMat[peakIndex][i] && !vertexList.get(i).isVisited()) {
                vertexList.get(i).setParentVertex(peek);
                return vertexList.get(i);
            }
        }

        return null;
    }

    private void visitVertex(Vertex vertex, Stack<Vertex> stack) {
        displayVertex(vertex);
        stack.push(vertex);
        vertex.setVisited(true);
    }

    private void visitVertex(Vertex vertex, Queue<Vertex> queue) {
        displayVertex(vertex);
        queue.add(vertex);
        vertex.setVisited(true);
    }

    private void visitVertexMinWay(Vertex vertex, Queue<Vertex> queue) {
        queue.add(vertex);
        vertex.setVisited(true);
    }
}
