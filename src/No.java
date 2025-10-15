public class No {

    public static final int m = 2;
    private int vInfo[];
    private int vPos[];
    private No vLig[];
    private int TL;

    public No() {
        vInfo = new int[m*2+1];
        vPos = new int[m*2+1];
        vLig = new No[m*2+2];
        TL = 0;
    }

    public No(int info, int posArq){
        this();
        vInfo[TL] = info;
        vPos[TL] = posArq;
        TL++;
    }

    public int procurarPosicao(int info){
        int pos = 0;
        while(pos < TL && vInfo[pos] < info){
            pos++;
        }
        return pos;
    }

    public void remanejarPosicao(int pos){
        vLig[TL+1] = vLig[TL];
        for(int i = TL; i > pos; i--){
            vInfo[i] = vInfo[i-1];
            vPos[i] = vPos[i-1];
            vLig[i] = vLig[i-1];
        }
    }

    public int getvInfo(int pos) {
        return vInfo[pos];
    }

    public void setvInfo(int pos, int info) {
        this.vInfo[pos] = info;
    }

    public int getvPos(int pos) {
        return vPos[pos];
    }

    public void setvPos(int pos, int posArq) {
        this.vPos[pos] = posArq;
    }

    public No getvLig(int pos) {
        return vLig[pos];
    }

    public void setvLig(int pos, No lig) {
        this.vLig[pos] = lig;
    }

    public int getTL() {
        return TL;
    }

    public void setTL(int TL) {
        this.TL = TL;
    }
}
