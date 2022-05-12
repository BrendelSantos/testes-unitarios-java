package estudo.testeunitario.servicos;

import estudo.testeunitario.exceptions.NaoPermitidoDivisaoPorZero;

public class Calculadora {

    public int somar(int a, int b) {
        return a + b;
    }

    public int subtrair(int a, int b) {
        return a - b;
    }

    public int dividir(int a, int b) throws NaoPermitidoDivisaoPorZero {

        if (b == 0) {
            throw new NaoPermitidoDivisaoPorZero();
        }
        return a / b;
    }
    
}
