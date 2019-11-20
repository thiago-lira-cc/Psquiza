package Psquiza;

public class Aluno implements Especialidade{
	private String email;
	private int semestre;
	private double iEA;
	
	public Aluno(String email, int semestre, double iEA) {
		this.email = email;
		this.semestre = semestre;
		this.iEA = iEA;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public double getIEA() {
		return iEA;
	}

	public void setIEA(double iEA) {
		this.iEA = iEA;
	}

	@Override
	public String toString() {
		return this.semestre + "o SEMESTRE - " + this.iEA;
		
	}

	@Override
	public void setFormacao(String novoValor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUnidade(String novoValor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setData(String novoValor) {
		// TODO Auto-generated method stub
		
	}
	


}
