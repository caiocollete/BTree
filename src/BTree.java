import java.util.Stack;

public class BTree {
    private No raiz;

    public BTree() {
        raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    private No navegarAteFolha(int info){
        No folha = raiz;
        int pos;
        while(folha.getvLig(0) != null){
            pos = folha.procurarPosicao(info);
            folha = folha.getvLig(pos);
        }
        return folha;
    }

    private No localizarPai(No folha, int info){
        No ant = raiz;
        No no = raiz;
        while(no != folha){
            ant = no;
            no = no.getvLig(no.procurarPosicao(info));
        }
        return ant;
    }

    private void split(No folha, No pai){
        No noDir = new No();
        No noEsq = new No();
        int i, pos;

        if(folha==pai){
            pai = new No();
            raiz = pai;
        }

        for (i=0; i<No.m; i++)
        {
            noEsq.setvInfo(i,folha.getvInfo(i));
            noEsq.setvPos(i,folha.getvPos(i));
            noEsq.setvLig(i,folha.getvLig(i));
        }
        noEsq.setvLig(No.m, folha.getvLig(No.m));
        noEsq.setTL(No.m);

        for (i=No.m+1; i<2*No.m+1; i++)
        {
            noDir.setvInfo(i-(No.m+1),folha.getvInfo(i));
            noDir.setvPos(i-(No.m+1),folha.getvPos(i));
            noDir.setvLig(i-(No.m+1),folha.getvLig(i));
        }

        noDir.setvLig(No.m, folha.getvLig(No.m*2+1));
        noDir.setTL(No.m);

        pos = pai.procurarPosicao(folha.getvInfo(No.m));
        pai.remanejarPosicao(pos);

        pai.setvPos(pos, folha.getvPos(No.m));
        pai.setvInfo(pos, folha.getvInfo(No.m));

        pai.setTL(pai.getTL() + 1);

        pai.setvLig(pos, noEsq);
        pai.setvLig(pos+1, noDir);

        if(pai.getTL() > No.m*2){
            folha = pai;
            pai = localizarPai(folha, folha.getvInfo(0));
            split(folha, pai);
        }
    }

    private void split_chico(No folha, No pai){
        No noDir = new No();
        No noEsq = new No();
        int i, pos;

        for (i=0; i<No.m; i++)
        {
            noEsq.setvInfo(i,folha.getvInfo(i));
            noEsq.setvPos(i,folha.getvPos(i));
            noEsq.setvLig(i,folha.getvLig(i));
        }
        noEsq.setvLig(No.m, folha.getvLig(No.m));
        noEsq.setTL(No.m);

        for (i=No.m+1; i<2*No.m+1; i++)
        {
            noDir.setvInfo(i-(No.m+1),folha.getvInfo(i));
            noDir.setvPos(i-(No.m+1),folha.getvPos(i));
            noDir.setvLig(i-(No.m+1),folha.getvLig(i));
        }

        noDir.setvLig(No.m, folha.getvLig(No.m*2+1));
        noDir.setTL(No.m);

        if(folha==pai){
            pai.setvInfo(0, folha.getvInfo(No.m));
            pai.setvPos(0, folha.getvPos(No.m));
            pai.setTL(1);

            pai.setvLig(0,noEsq);
            pai.setvLig(1, noDir);
        }
        else{
            pos = pai.procurarPosicao(folha.getvInfo(No.m));
            pai.remanejarPosicao(pos);

            pai.setvPos(pos, folha.getvPos(No.m));
            pai.setvInfo(pos, folha.getvInfo(No.m));

            pai.setTL(pai.getTL() + 1);

            pai.setvLig(pos, noEsq);
            pai.setvLig(pos+1, noDir);

            if(pai.getTL() > No.m*2){
                folha = pai;
                pai = localizarPai(folha, folha.getvInfo(0));
                split_chico(folha, pai);
            }
        }
    }

    public void Inserir(int info, int posArq) {
        No folha, pai;
        int pos;
        if (raiz == null)
            raiz = new No(info, posArq);
        else {
            folha = navegarAteFolha(info);
            pos = folha.procurarPosicao(info);
            folha.remanejarPosicao(pos);

            folha.setvInfo(pos, info);
            folha.setvPos(pos, posArq);
            folha.setTL(folha.getTL() + 1);

            if (folha.getTL() > No.m * 2) {
                pai = localizarPai(folha, info);
                split(folha, pai);
            }
        }
    }

    public void in_ordem_recursivo() {
        inordem(this.raiz);
    }

    public void in_ordem_recursivo_chico(No raiz){
        if(raiz!=null){
            for(int i=0; i<raiz.getTL(); i++){
                in_ordem_recursivo_chico(raiz.getvLig(i));
                System.out.println(raiz.getvInfo(i));
            }
            in_ordem_recursivo_chico(raiz.getvLig(raiz.getTL()));
        }
    }

    public void inordem(No raiz) {
        int i = -1;
        while (raiz!=null && i<raiz.getTL()) {
            if (i > -1) {
                System.out.println(raiz.getvInfo(i));
            }
            inordem(raiz.getvLig(++i));
        }
    }
}
