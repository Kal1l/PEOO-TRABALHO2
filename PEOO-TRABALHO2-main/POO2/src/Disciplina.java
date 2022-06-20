import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Disciplina {
    //cria uma disciplina(Adiciona disciplina e aluno juntos)
    public void novaDisciplina(){
        String aux = "";
        String disciplina = "";
        String resposta = "";
        String nomeAluno = "";

        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);

        System.out.println("INSIRA O NOME DA DISCIPLINA :"); 
        disciplina = in.nextLine();
        File file = new File("Diretório/disciplinas/"+ disciplina + ".txt");

      while(true){
        System.out.println("INSIRA A RESPOSTA DO ALUNO :");
        resposta = in2.nextLine();
        System.out.println("INSIRA O NOME DO ALUNO :");
        nomeAluno = in2.nextLine();
        if (resposta.length() == 10){
        aux += resposta + "\t" + nomeAluno + "\n";}
        else{
            System.out.println("RESPOSTA INSERIDA INCORRETAMENTE");
        }
        System.out.println("DESEJA ADICIONAR OUTRO ALUNO?\n1-SIM\n2-NÃO");
        int loop = in.nextInt();
        if(loop == 2){
            break;
        }
    }
        try{
            FileWriter fWriter = new FileWriter(file);
            BufferedWriter bRWriter = new BufferedWriter(fWriter);
            bRWriter.write(aux);// colocando a String auxiliar para resposta e nome aluno
            bRWriter.close();
            fWriter.close();
        }catch(IOException e){System.out.println("ERRO AO SALVAR");}   
          
    }
    
    //método para gerar as pastas
    public void gerarPastas(){
        File pasta = new File("Diretório");
        pasta.mkdir();
        File f1 = new File(pasta,"disciplinas");
        f1.mkdir();
        File f2 = new File(pasta,"gabaritos");
        f2.mkdir();
        File f3 = new File(pasta,"resultados");
        f3.mkdir();
        File f4 = new File(f3,"ordem_alfabetica");
        f4.mkdir();
        File f5 = new File(f3,"ordem_decrescente");
        f5.mkdir();
    }

    //gera os resultados nos diretorios
    public void resultadosGerar(){
        String aux = "";
        String nomeDis = "";
        String nomeResp = "";

        Scanner in = new Scanner(System.in);
        System.out.println("INSIRA O NOME DA DISCIPLINA :");
        nomeDis = in.nextLine();
        File file = new File("Diretório/disciplinas/"+nomeDis+ ".txt");

        try {
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);
            while (bReader.ready()) {
                aux += bReader.readLine() + "\n";
            }
            bReader.close();
            fReader.close();
        } catch (FileNotFoundException e) {System.out.println("ARQUIVO NÃO ENCONTRADO ");}
          catch(IOException e){System.out.println("ERRO AO SALVAR ");}
        String[] line = aux.split("\n");

        System.out.println("INSIRA O NOME DO GABARITO: ");
        nomeResp = in.nextLine();
        File resposta = new File("Diretório/gabaritos/" + nomeResp + ".txt");

        String aux2 = "";
        try {
            FileReader fReader = new FileReader(resposta);
            BufferedReader bReader = new BufferedReader(fReader);
            while (bReader.ready()) {
                aux2 += bReader.readLine() + "\n";
            }
            bReader.close();
            fReader.close();
        } catch (FileNotFoundException e){System.out.println("ARQUIVO NÃO ENCONTRADO");}
          catch(IOException e){System.out.println("ERRO AO SALVAR");}

        String respostas = aux2;

        ArrayList<Aluno> alunos = new ArrayList<Aluno>();

        for (String linha : line) {
            String respostaAluno = linha.split("\t")[0];
            String nomeAluno = linha.split("\t")[1];
            alunos.add(new Aluno(respostaAluno, nomeAluno));
        }

        String respostaAluno = "";
        double media = alunos.stream().mapToDouble(aluno -> aluno.calcularNota(respostaAluno,respostas)).average().getAsDouble();

        System.out.println("MÉDIA GERAL : " + media);

        List<Aluno> ordemAlfa = alunos.stream().sorted(Comparator.comparing(Aluno::getNome))
                .collect(Collectors.toList());

        File resultadoNome = new File("Diretório/resultados/ordem_alfabetica/" + nomeDis + ".txt");
        
        String aux3 = "";

        for (Aluno aluno : ordemAlfa ) {
            aux3 += aluno.getNome() + "\t" + aluno.calcularNota(respostaAluno,respostas) + "\n";
        }

        aux3 += "MÉDIA GERAL : \t" + media;

        try {
            FileWriter fWriter = new FileWriter(resultadoNome);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write(aux3);
            bWriter.close();
            fWriter.close();
        } catch (IOException e) { System.out.println("ERRO AO SALVAR");}
        
        List<Aluno> ordemDecresc = alunos.stream()//Permite acessar os dados de forma declarativa
         .sorted(Comparator.comparing(aluno -> aluno.calcularNota(respostaAluno,respostas), Comparator.reverseOrder()))
         .collect(Collectors.toList());
         //basicamente uma função que pega uma Lista e modifica ela de acordo com que se pede, que no caso aqui é reverseOrder

        File resultadoPorNota = new File("Diretório/resultados/ordem_decrescente/" + nomeDis + ".txt");
        aux3 = "";

        for (Aluno aluno : ordemDecresc) {
            aux3 += aluno.getNome() + "\t" + aluno.calcularNota(respostaAluno,respostas) + "\n";
        }

        aux3 += "MÉDIA GERAL : \t" + media;
        try {
            FileWriter fWriter = new FileWriter(resultadoPorNota);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write(aux3);
            bWriter.close();
            fWriter.close();
        } catch (IOException e){
            System.out.println("ERRO AO SALVAR");}
            
    }
    
    //printa as respostas
    public void mostrarRespostas(){
        File resultadoNome = new File("Diretório/resultados/ordem_alfabetica/");
        System.out.println("RESULTADOS POR ORDEM ALFABETICA : ");
        for (File file : resultadoNome.listFiles()) {
            System.out.println(file.getName().replace(".txt", ""));
            String aux = "";
            try {
                FileReader fReader = new FileReader(file);
                BufferedReader bReader = new BufferedReader(fReader);
                while (bReader.ready()) {
                    aux += bReader.readLine() + "\n";
                }
                bReader.close();
                fReader.close();
            } catch (FileNotFoundException e){System.out.println("ARQUIVO NÃO ENCONTRADO ");} 
              catch (IOException e){System.out.println("ERRO AO SALVAR ");}
        
            System.out.println(aux);
        }
        File resultadorOrdemDecres = new File("Diretório/resultados/ordem_decrescente/");
        System.out.println("RESULTADO POR NOTA :");
        for (File file : resultadorOrdemDecres.listFiles()) {
            System.out.println(file.getName().replace(".txt", ""));
            String aux = "";
            try {
                FileReader fReader = new FileReader(file);
                BufferedReader bReader = new BufferedReader(fReader);
                while (bReader.ready()) {
                    aux += bReader.readLine() + "\n";
                }
                bReader.close();
                fReader.close();
            } catch (FileNotFoundException e){System.out.println("ARQUIVO NÃO ENCONTRADO ");}
              catch (IOException e){System.out.println("ERRO AO SALVAR");}
            
            System.out.println(aux);
        }
    }
    //cria o gabarito de uma disciplina
    public void gabaritoGerar(){
        String aux = "";
        String nomeDis = "";
        Scanner in = new Scanner(System.in);
        System.out.println("INSIRA O NOME DA DISCIPLINA PARA O GABARITO SER FEITO :");
        nomeDis = in.nextLine();
        File resposta = new File("Diretório/gabaritos/"+ nomeDis+".txt");
        System.out.println("INSIRA O GABARITO DA DISCIPLINA :");
        aux += in.nextLine().toUpperCase();
        if (aux.length() == 10){
        try {
            FileWriter fWriter = new FileWriter(resposta);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write(aux);
            bWriter.close();
            fWriter.close();
        } catch (IOException e){
            System.out.println("ERRO AO SALVAR");
        }}
        
    }
}