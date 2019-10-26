package Psquiza;

import easyaccept.EasyAccept;

public class Main {
	public static void main(String[] args) {
		args = new String[] {"Psquiza.Facade", "testes_aceitacao/use_case_1.txt", "testes_aceitacao/use_case_2.txt",
							"testes_aceitacao/use_case_3.txt", "testes_aceitacao/use_case_4.txt"};
		EasyAccept.main(args);
	}
}
