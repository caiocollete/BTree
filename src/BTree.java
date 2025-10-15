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
        if(pai==folha){
            pai = new No();
            raiz = pai;
        }

        int meio = folha.getTL()/2;
        No noDir = new No(), noEsq = new No();
        // copia lado esquerdo para o noEsq
        int i=0;
        while(i<meio){
            noEsq.setvPos(i,folha.getvPos(i));
            noEsq.setvLig(i,folha.getvLig(i));
            noEsq.setTL(noEsq.getTL()+1);
            i++;
        }
        noEsq.setvLig(i, folha.getvLig(i));
        // copia lado direito para o noDir
        i=meio+1;
        while(i<folha.getTL()){
            noDir.setvPos(i,folha.getvPos(i));
            noDir.setvLig(i,folha.getvLig(i));
            noDir.setTL(noDir.getTL()+1);
            i++;
        }
        noDir.setvLig(i, folha.getvLig(i));

        int pos = pai.procurarPosicao(folha.getvInfo(meio));
        pai.remanejarPosicao(pos);
        pai.setvPos(pos, folha.getvPos(meio));
        pai.setvInfo(pos, folha.getvInfo(meio));
        pai.setvLig(pos, noEsq);
        pai.setvLig(pos+1, noDir);
        pai.setTL(pai.getTL() + 1);
        if(pai.getTL() > No.m*2){
            split(pai, localizarPai(pai, pai.getvInfo(0)));
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
        in_ordem_recursivo_chico(this.raiz);
    }

    public void in_ordem_recursivo_chico(No raiz){
        if(raiz!=null){
            for(int i=0; i<raiz.getTL(); i++){
                in_ordem_recursivo_chico(raiz.getvLig(i));
                System.out.println(raiz.getvInfo(i));
            }
            in_ordem_recursivo_chico(raiz.getvLig(raiz.getTL()-1));
        }
    }
}
