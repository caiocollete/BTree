public class Main {
    public static void main(String[] args) {
        BTree b = new BTree();

        for(int i=1; i<=100000; i++)
            b.Inserir(i, i);
        b.in_ordem_recursivo();
    }
}