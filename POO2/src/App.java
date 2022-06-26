import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Disciplina disciplina=new Disciplina();
        int opção=0;
        while(opção!=5){
        System.out.println("ESCOLHA UMA OPÇÃO:\n1-CRIAR DISCIPLINA\n2-DELETAR DISCIPLINA\n3-GERAR GABARITO\n4-GERAR MÉDIA\n5-MOSTRAR RESPOSTAS\n6-SAIR");
        opção=input.nextInt();
            switch(opção){
                case 1:
                disciplina.gerarPastas();
                disciplina.novaDisciplina();
                continue;
                case 2:
                disciplina.deletarDisciplina();
                continue;
                case 3:
                disciplina.gabaritoGerar();
                continue;
                case 4:
                disciplina.resultadosGerar();
                continue;
                case 5:
                disciplina.mostrarRespostas();
                continue;
                case 6:
                System.exit(opção);
                default:
                System.out.println("INSIRA UMA DAS OPÇÕES VÁLIDAS(1-5)");
      }
    }
        input.close();
  }
}