class Graph {
    int size;
    String[] vertices;
    boolean[][] a; // adjecency matrix

    public Graph(String[] args) {
        size = args.length;
        vertices = new String[size];
        System.arraycopy(args, 0, vertices, 0, size);
        a = new boolean[size][size];
    }

    public void add(String v, String w) {
        int i = index(v), j = index(w);
        if (i == -1 || j == -1) {
            throw new IllegalArgumentException("Both vertices must exist in the graph");
        }
        a[i][j] = a[j][i] = true;
    }

    public String toString() {
        if (size == 0) return "()";
        StringBuffer sb = new StringBuffer("(" + vertex(0));
        for (int i = 1; i < size; i++) {
            sb.append(", " + vertex(i));
        }
        return sb + ")";
    }

    private int index(String v) {
        for (int i = 0; i < size; i++)
            if(vertices[i].equals(v)) return i;
        return -1;
    }
    private String vertex(int i) {
        StringBuffer sb = new StringBuffer(vertices[i] + ":");
        for (int j = 0; j < size; j++) {
            if (a[i][j]) sb.append(vertices[j]);
        }
        return sb + " ";
    }
    public static void main(String[] args) {
        String str[] = {"A", "B", "C"};
        Graph g = new Graph(str);
        g.add("A", "B");
        g.add("A", "C");

        System.out.println(g);
    }
}