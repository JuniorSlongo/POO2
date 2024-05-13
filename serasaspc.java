import java.util.Scanner;

public class VerificacaoEmprestimo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Etapa 1: Verificação de disponibilidade de crédito
        System.out.print("Informe o valor do crédito desejado: ");
        double valorCredito = scanner.nextDouble();

        // Suponha que a instituição financeira tenha um limite mínimo de crédito
        double limiteMinimoCredito = 1000.0;
        if (valorCredito < limiteMinimoCredito) {
            System.out.println("Desculpe, o valor do crédito é inferior ao limite mínimo.");
            return;
        }

        // Etapa 2: Verificação de compatibilidade da renda
        System.out.print("Informe sua renda mensal: ");
        double rendaMensal = scanner.nextDouble();

        double parcelaMaxima = rendaMensal * 0.25;
        if (valorCredito > parcelaMaxima) {
            System.out.println("Desculpe, o valor da parcela excede 25% da sua renda mensal.");
            return;
        }

        // Etapa 3: Verificação de histórico de crédito
        System.out.print("Você possui alguma restrição no SPC/SERASA? (S/N): ");
        String restricao = scanner.next();

        if (restricao.equalsIgnoreCase("S")) {
            System.out.println("Desculpe, não podemos conceder o empréstimo devido à restrição no histórico de crédito.");
            return;
        }

        // Etapa 4: Verificação de garantias
        System.out.print("Você possui algum bem que possa ser dado como garantia? (S/N): ");
        String garantia = scanner.next();

        if (garantia.equalsIgnoreCase("S")) {
            System.out.println("Empréstimo aprovado! Você pode utilizar o bem como garantia.");
        } else {
            System.out.println("Empréstimo aprovado! Aguarde a liberação do crédito.");
        }
    }
}
