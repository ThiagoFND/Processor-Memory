package so;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Execute {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        while (true) {
            imprimirMenuPrincipal();
            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        executarMenuCriacaoProcesso();
                        break;
                    case 2:
                        deletarProcesso();
                        break;
                    case 3:
                        visualizarProcessos();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida, digite novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida, digite novamente.");
            }
        }
    }

    private static void imprimirMenuPrincipal() {
        System.out.println("\n===== Sistema =====");
        System.out.println("1 - Criar processo ");
        System.out.println("2 - Deletar ");
        System.out.println("3 - Visualizar");
        System.out.println("4 - Sair ");
        System.out.print("Digite: ");
    }

    private static void executarMenuCriacaoProcesso() {
        int option;
        while (true) {
            imprimirMenuCriacaoProcesso();
            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        criarEGravarNaMemoria();
                        break;
                    case 2:
                        executarProcessosExistente();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Opção inválida, digite novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida, digite novamente.");
            }
        }
    }

    private static void imprimirMenuCriacaoProcesso() {
        System.out.println("\n===== Sistema =====");
        System.out.println("1 - Criar processo e escrever na memória");
        System.out.println("2 - Executar processos ");
        System.out.println("3 - Voltar ");
        System.out.print("Digite: ");
    }

    private static void criarEGravarNaMemoria() {
        try {
            System.out.print("Digite o tamanho do processo que deseja criar: ");
            int processSize = Integer.parseInt(scanner.nextLine());
            SOProcess process = SystemOperation.SystemCall(SystemCallType.CREATE, processSize);
            SystemOperation.SystemCall(SystemCallType.WRITE, process);

        } catch (NumberFormatException e) {
            System.out.println("Tamanho do processo inválido, digite um número válido.");
        } catch (Exception e) {
            System.out.println("Erro ao criar e escrever na memória: " + e.getMessage());
        }
    }

    private static void executarProcessosExistente() {
        int algorithmOption;
        while (true) {
            System.out.println("Escolha o algoritmo de escalonamento:");
            System.out.println("1 - SJF");
            System.out.println("2 - Prioridade");
            System.out.println("3 - FCFS");
            System.out.println("4 - Loteria");
            System.out.print("Digite o número correspondente ao algoritmo desejado: ");
            
            try {
                algorithmOption = Integer.parseInt(scanner.nextLine());
                if (algorithmOption < 1 || algorithmOption > 4) {
                    System.out.println("Opção inválida, digite novamente.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida, digite novamente.");
            }
        }
    
        ArrayList<SOProcess> listaProcessos = SystemOperation.getAllProcess(algorithmOption);
        if (listaProcessos == null || listaProcessos.isEmpty()) {
            System.out.println("Não há processos na memória para executar.");
        } else {
            for (int i = 0; i < listaProcessos.size(); i++) {
                try {
                    SOProcess p = listaProcessos.get(i);
                    System.out.println("Executando processo " + p.getId() + " Prioridade: " + p.getPriority());
                    SystemOperation.executeProcesses(p);
                    System.out.println("Iniciando a execução de processos, pressione Enter após a finalização");
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Erro ao executar processo: " + e.getMessage());
                }
            }
        }
    }
    

    private static void deletarProcesso() {
        Set<String> processos = SystemOperation.getUniqueProcesses();
        if (processos == null || processos.isEmpty()) {
            System.out.println("Não há processos na memória para deletar.");
        } else {
            System.out.println("Processos existentes na memória: " + processos);
            System.out.print("Digite o ID do processo que deseja deletar: ");
            String idProcesso = scanner.nextLine();
            try {
                SystemOperation.removeProcessFromMemory(idProcesso);
                System.out.println("Processo deletado com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao deletar processo: " + e.getMessage());
            }
        }
    }

    private static void visualizarProcessos() {
        System.out.println("Processos existentes na memória: " + SystemOperation.getUniqueProcesses());
        System.out.println("Status da memória: " + SystemOperation.statusMemory());
    }
}
