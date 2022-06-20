public class Aluno {

    protected String respostas;
    protected String nome;

    public Aluno(String respostas, String nome){
        this.respostas = respostas;
        this.nome = nome;
    }
     public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRespostas() {
		return respostas;
	}
	public void setRespostas(String respostas) {
		this.respostas = respostas;
	}

    public int media(String respostas, String gabarito){
        int nota = 0;
        char[] respostaA = respostas.toCharArray();
        char[] respostaG = gabarito.toCharArray();

        boolean verificador = true;

        for(char letra: respostaA){
            if(letra != respostaA[0]){
                verificador = false;
                break;
            }
        }        	
        if(verificador){
            return 0;
        }
        for(int i = 0 ; i < respostaA.length ; i++){
            if(respostaA[i] == respostaG[i])
                nota++;
        }
        return nota;
     } 
    
    public double calcularNota(String rAluno,String gabarito){
        if(rAluno.equals("VVVVVVVVVV")||rAluno.equals("FFFFFFFFFF")){
            return 0;
        }
        return media(this.respostas, gabarito);
     }
}