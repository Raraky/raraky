import java.util.Scanner;
import java.util.NoSuchElementException;
import java.lang.Runtime;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.ArrayList;

public class Main{
    public static void main(String args[]) throws IOException, InterruptedException{
            int op = 0;
    
            Scanner sc = new Scanner(System.in);
            Arquivo arquivo = new Arquivo();
            Produto produto = null;
            
            //LIMPAR TELA DE COMANDO
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            
            while(op != -1){      
                try{                  
                    System.out.println("1 - criar arquivo novo\n" +
                                        "2 - listar arquivos\n" + 
                                        "3 - deletar arquivo\n" + 
                                        "4 - carregar arquivo\n" +
                                        "5 - salvar arquivo\n" +
                                        "6 - criar produto\n" + 
                                        "7 - editar coluna produto\n" + 
                                        "8 - apagar coluna produto\n" + 
                                        "9 - editar dado produto\n" + 
                                        "10 - backup\n");
                                        
                    switch(sc.nextInt()){
                        case 1:
                            System.out.print("Nome do arquivo: ");
                            arquivo.criarArquivo(sc.next());
                            break;
                        case 2:
                            arquivo.listarArquivos();
                            break;
                        case 3:
                            System.out.println("Qual arquivo voce deseja deletar: ");
                            arquivo.listarArquivos();
                            arquivo.deletarArquivo(sc.nextInt());
                            break;
                        case 4:
                            System.out.println("Qual arquivo voce deseja carregar: ");
                            arquivo.listarArquivos();
                            produto = arquivo.carregarArquivo(sc.nextInt());
                            break;
                        case 5:
                            System.out.println("Digite o nome do novo arquivo: ");
                            arquivo.salvarArquivo(sc.next(), produto);
                            break;
                        case 6:
                            Produto prod = new Produto();
                            System.out.print("Digite o nome da tabela: ");
                            prod.setNome(sc.next());
                            System.out.print("Digite os nomes da colunas(-1 para sair): ");
                            String col = "";
                            while(!col.equalsIgnoreCase("-1")){
                                col = sc.next();
                                if(col.equalsIgnoreCase("-1")){
                                    continue;
                                }
                                prod.adicionarColuna(col);
                            }
                            
                            System.out.println("Digite os valores de cada coluna: ");
                            String dado = "";
                            while(!dado.equalsIgnoreCase("-1")){
                                ArrayList<String> linha = new ArrayList<>();
                                int x = 1;
                                System.out.println("Linha " + x + ":");
                                for(int k = 0; k < prod.getSize(); k++){
                                    dado = sc.next();
                                    linha.add(dado);
                                }
                                prod.adicionarLinha(linha);
                                System.out.print("Adicionar mais uma linha?(-1 para sair): ");
                                dado = sc.next();
                            }
                            
                            produto = prod;
                            System.out.println(produto.listarTabela());
                            break;
                        case 7:
                            System.out.println("Qual coluna deseja editar: ");
                            int x = 0;
                            for(String coluna : produto.pegarColunas()){
                                System.out.println(x++ + " - " + coluna);
                            }
                            int c = sc.nextInt();
                            System.out.print("Digite o novo nome da coluna: ");
                            String novaColuna = sc.next();
                            produto.editarColuna(c, novaColuna);
                            System.out.println(produto.listarTabela());
                            
                            break;
                        case 8:
                            System.out.println("Qual coluna deseja apagar: ");
                            int y = 0;
                            for(String coluna : produto.pegarColunas()){
                                System.out.println(y++ + " - " + coluna);
                            }
                            int d = sc.nextInt();
                            produto.apagarColuna(d);
                            System.out.println(produto.listarTabela());
                            
                            break;
                        case 9:
                            System.out.println("Qual dado deseja editar(linha, coluna): ");                       
                            for(int z = 0; z < produto.getSize(); z++){
                                System.out.print("\t" + z);
                            }
                            System.out.println("\n" + produto.listarDados());
                            int f = sc.nextInt();
                            int g = sc.nextInt();
                            System.out.print("Qual o novo valor do dado: ");
                            String novoDado = sc.next();
                            produto.editarDado(f, g, novoDado);
                            System.out.println(produto.listarTabela());
                            
                            break;
                        case 10:
                            arquivo.backup();
                            System.out.println("Backup feito com sucesso!");
                            
                            break;
                        default:
                            op = -1;
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    }
                }catch(NoSuchElementException elementException){
                    System.err.println("Entrada invalida. Tente de novo.");
                    System.exit(1);
                }
            }
            
            System.exit(0);
        }
}