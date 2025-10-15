import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BTree b = new BTree();

        for(int i=1; i<=100000; i++)
            b.Inserir(i, i);
    }
}