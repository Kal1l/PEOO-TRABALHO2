import java.io.*;
public class DisciplinaGestor {
    
    public void novoArquivo(File dir,String disciplina){
        File d = new File(dir,disciplina+".txt");
        try{d.createNewFile();}catch(IOException e){}
    }

}
