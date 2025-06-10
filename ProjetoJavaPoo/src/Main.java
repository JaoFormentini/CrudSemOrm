import wydem.facimp.sistema.pooJava.projeto.DAO;
import wydem.facimp.sistema.pooJava.projeto.celular;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DAO dao = new DAO();
        Scanner scanner = new Scanner(System.in);

        dao.criarTabela();

        boolean executando = true;

        while (executando) {
            System.out.println("\n📱 MENU ESTOQUE DE CELULARES");
            System.out.println("1 - Adicionar celular");
            System.out.println("2 - Listar celulares");
            System.out.println("3 - Atualizar celular");
            System.out.println("4 - Deletar celular");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1" -> {
                    System.out.print("Nome da marca: ");
                    String name = scanner.nextLine();

                    System.out.print("Modelo: ");
                    String model = scanner.nextLine();

                    System.out.print("Ano: ");
                    int year = Integer.parseInt(scanner.nextLine());

                    System.out.print("Valor (R$): ");
                    double value = Double.parseDouble(scanner.nextLine());

                    System.out.print("Descrição: ");
                    String description = scanner.nextLine();

                    System.out.print("Quantidade: ");
                    int amount = Integer.parseInt(scanner.nextLine());

                    celular novo = new celular(name, model, year, value, description, amount);
                    dao.addCelular(novo);
                } // ADICIONA CELULAR NO BANCO.

                case "2" -> {
                    List<celular> celulares = dao.listarCelulares();
                    if (celulares.isEmpty()) {
                        System.out.println("📭 Nenhum celular cadastrado.");
                    } else {
                        System.out.println("📦 Estoque de Celulares:");
                        for (celular c : celulares) {
                            System.out.printf("ID: %d | %s | %s | (%d) | R$%.2f | Qtd: %d | %s%n",
                                    c.getId(), c.getName(), c.getModel(), c.getYear(),
                                    c.getValue(), c.getAmount(), c.getDescription());
                        }
                    }
                } // LISTA OS CELULARES NO BANCO.

                case "3" -> {
                    System.out.print("ID do celular a atualizar: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    List<celular> celulares = dao.listarCelulares();
                    celular encontrado = celulares.stream().filter(c -> c.getId() == id).findFirst().orElse(null);

                    if (encontrado == null) {
                        System.out.println("⚠️ Celular com ID " + id + " não encontrado.");
                        break;
                    }

                    System.out.print("Novo nome da marca: ");
                    String name = scanner.nextLine();

                    System.out.print("Novo modelo: ");
                    String model = scanner.nextLine();

                    System.out.print("Novo ano: ");
                    int year = Integer.parseInt(scanner.nextLine());

                    System.out.print("Novo valor (R$): ");
                    double value = Double.parseDouble(scanner.nextLine());

                    System.out.print("Nova descrição: ");
                    String description = scanner.nextLine();

                    System.out.print("Nova quantidade: ");
                    int amount = Integer.parseInt(scanner.nextLine());

                    celular atualizado = new celular(id, name, model, year, value, description, amount);
                    dao.updateCelular(atualizado);
                } // ATUALIZA O CELULAR NO BANCO.

                case "4" -> {
                    System.out.print("ID do celular a deletar: ");
                    int id = Integer.parseInt(scanner.nextLine());

                    List<celular> celulares = dao.listarCelulares();
                    boolean existe = celulares.stream().anyMatch(c -> c.getId() == id);

                    if (existe) {
                        dao.deletCelular(id);
                    } else {
                        System.out.println("⚠️ Celular com ID " + id + " não encontrado.");
                    }
                } // DELETA CELULAR NO BANCO.

                case "0" -> {
                    executando = false;
                    System.out.println("👋 Encerrando o sistema. Até mais!");
                } // PARA A EXECUÇÃO DO PROGRAMA.

                default -> System.out.println("❌ Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}