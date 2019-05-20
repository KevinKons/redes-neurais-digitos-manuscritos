package util;

public class OperacaoComMatriz {

    @SuppressWarnings("Duplicates")
    public static int[][] calculaMatrizTransposta(int[][] m) {
        int[][] transposta=new int[m[0].length][m.length];
        System.out.println("MATRIZ NORMAL ");
        for(int i=0; i<m.length; i++)
        {
            for(int j=0; j<m[i].length; j++)
            {
                System.out.print(m[i][j]+"\t");
            }
            System.out.println();
        }
        for(int linha=0;linha<m.length;linha++){
            for(int coluna=0;coluna<m[linha].length;coluna++){
                if(coluna > linha || coluna < linha)
                    transposta[linha][coluna]=m[coluna][linha];
                if(coluna==linha)
                    transposta[linha][coluna]=m[linha][coluna];
            }
        }
        System.out.println();
        System.out.println("MATRIZ TRANSPOSTA ");
        for(int i=0; i<transposta.length; i++)
        {
            for(int j=0; j<transposta[i].length; j++)
            {
                System.out.print(transposta[i][j]+"\t");
            }
            System.out.println();
        }
        return transposta;
    }
}
