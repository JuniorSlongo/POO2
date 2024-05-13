abstract class Handler {
    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(Cliente cliente);
}

class VerificaCreditoHandler extends Handler {
    @Override
    public void handleRequest(Cliente cliente) {
        if (cliente.getValorCreditoDesejado() >= 1000) { // Supondo que 1000 é o mínimo
            if (successor != null) {
                successor.handleRequest(cliente);
            }
        } else {
            System.out.println("Crédito abaixo do valor mínimo permitido.");
        }
    }
}

class VerificaRendaHandler extends Handler {
    @Override
    public void handleRequest(Cliente cliente) {
        double parcelaMaxima = cliente.getRendaMensal() * 0.25;
        if (cliente.getValorCreditoDesejado() <= parcelaMaxima) {
            if (successor != null) {
                successor.handleRequest(cliente);
            }
        } else {
            System.out.println("A parcela excede 25% da renda mensal.");
        }
    }
}

class VerificaHistoricoCreditoHandler extends Handler {
    @Override
    public void handleRequest(Cliente cliente) {
        if (!cliente.temRestricoesSPC_SERASA()) {
            if (successor != null) {
                successor.handleRequest(cliente);
            }
        } else {
            System.out.println("Cliente com restrições no SPC/SERASA.");
        }
    }
}

class VerificaGarantiasHandler extends Handler {
    @Override
    public void handleRequest(Cliente cliente) {
        if (cliente.temGarantias()) {
            System.out.println("Crédito aprovado com garantias.");
        } else {
            System.out.println("Crédito aprovado sem garantias.");
        }
    }
}

class Cliente {
    private double valorCreditoDesejado;
    private double rendaMensal;
    private boolean restricaoSPC_SERASA;
    private boolean possuiGarantias;

    // Getters e setters aqui
    // ...
}

public class Main {
    public static void main(String[] args) {
        // Configuração da cadeia de responsabilidade
        Handler verificaCredito = new VerificaCreditoHandler();
        Handler verificaRenda = new VerificaRendaHandler();
        Handler verificaHistorico = new VerificaHistoricoCreditoHandler();
        Handler verificaGarantias = new VerificaGarantiasHandler();

        verificaCredito.setSuccessor(verificaRenda);
        verificaRenda.setSuccessor(verificaHistorico);
        verificaHistorico.setSuccessor(verificaGarantias);

        // Criação de um cliente de exemplo
        Cliente cliente = new Cliente();
        // Defina os valores para o cliente aqui

        // Início do processo de verificação
        verificaCredito.handleRequest(cliente);
    }
}
