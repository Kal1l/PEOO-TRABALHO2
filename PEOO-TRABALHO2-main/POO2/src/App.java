import java.util.Scanner;
/*Lista de afazeres:
    -Criar um método para calcular a media(calcular nota repetidas vezes)

    -Encontrar alguma forma de transformar os dados do arquivo,
    em um array de objetos(ou de respostas/notas)
    -ler a linha do arquivo e adiconar num array;
    -separar a linha com um .split() separando em respostas e nome do aluno
    e transformar esse array em um objeto novamente
    
    -Usar .split pra separar o nome das repostas*/

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Disciplina disciplina=new Disciplina();
        int opção=0;
        while(opção!=5){
        System.out.println("ESCOLHA UMA OPÇÃO:\n1-CRIAR DISCIPLINA(Para editar digite o nome de uma disciplina já existente)\n2-GERAR GABARITO\n3-GERAR MÉDIA\n4-MOSTRAR RESPOSTAS\n5-SAIR");
        opção=input.nextInt();
            switch(opção){
                case 1:
                disciplina.novaDisciplina();
                continue;
                case 2:
                disciplina.gabaritoGerar();
                continue;
                case 3:
                disciplina.resultadosGerar();
                continue;
                case 4:

                disciplina.mostrarRespostas();
                continue;
                case 5:
                System.exit(opção);
                default:
                System.out.println("INSIRA UMA DAS OPÇÕES VÁLIDAS(1-5)");
      }
    }
        input.close();
  }
}