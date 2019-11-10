import java.util.Formatter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.SecurityException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.NullPointerException;

public class Arquivo{
    
    //ATRIBUTOS
    private Formatter arq;
    
    //CONSTRUTOR
    public Arquivo(){
        arq = null;
    }
 
    //MÉTODOS
    public void criarArquivo(String nome){
        try{
            arq = new Formatter("produtos/" + nome + ".txt");
            this.fecharArquivo();
        }catch(SecurityException securityException){
            System.err.println("Permissao negada.");
            System.exit(1);
        }catch(FileNotFoundException FileNotFoundException){
            System.err.println("Erro ao abrir o arquivo.");
            System.exit(1);
        }

    }
    
    public void backup(){
        try{
            DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get("produtos/"));
            int count = 0;     
            Scanner input = null;           
            for(Path p : ds){
                String nome = p.toString().substring(p.toString().indexOf("\\") + 1);
                nome = nome.substring(0, nome.indexOf("."));
                    
                //CARREGAR ARQUIVO
                input = new Scanner(Paths.get("produtos/" + nome + ".txt"));
             
                //CRIAR ARQUIVO BACKUP
                Formatter output = new Formatter("backup/" + nome + ".txt");
             
                //SALVAR ARQUIVO
                while(input.hasNext()){       
                    Scanner linha = null;
                    
                    if(count == 0){
                        output.format("%s\n", input.nextLine());
                    }else{
                        linha = new Scanner(input.nextLine());
                        while(linha.hasNext()){
                            output.format("%s\t\t", linha.next());
                        }
                        output.format("\n");
                    }
                    
                    if(linha != null){
                        linha.close();
                    }
                    count++;
                }
                
                output.close();
                
            }
            
            if(input != null){
                input.close();
            }      
            
        }catch(IOException ioException){
            System.err.println("Erro: " + ioException);
            System.exit(1);
        }
    }
    
    public Produto carregarArquivo(int index){
        Produto produto = new Produto();
        try{
            DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get("produtos/"));
            int count = 0;     
            Scanner input = null;           
            for(Path p : ds){
                String nome = p.toString().substring(p.toString().indexOf("\\") + 1);
                nome = nome.substring(0, nome.indexOf("."));
                if(count == index){
                    input = new Scanner(Paths.get("produtos/" + nome + ".txt"));
                    break;
                }
                count++;
            }
            
            count = 0;
            while(input.hasNext()){       
                Scanner linha = null;
                
                if(count == 0){
                    produto.setNome(input.nextLine());
                }else if(count == 1){
                    linha = new Scanner(input.nextLine());
                    while(linha.hasNext()){
                        produto.adicionarColuna(linha.next());
                    }
                }else{
                    linha = new Scanner(input.nextLine());
                    ArrayList<String> ln = new ArrayList<>();
                    while(linha.hasNext()){
                        ln.add(linha.next());
                    }
                    produto.adicionarLinha(ln);
                }
                
                if(linha != null){
                    linha.close();
                }
                count++;
            }
            
            if(input != null){
                input.close();
            }      
            
        }catch(IOException ioException){
            System.err.println("Erro: " + ioException);
            System.exit(1);
        }
        
        System.out.println(produto.listarTabela());
        return produto;
    } 
    
    public void salvarArquivo(String nome, Produto produto){
        try{
            Formatter output = new Formatter("produtos/" + nome + ".txt");
            
            output.format("%s\n", produto.getNome());
            ArrayList<String> col = produto.pegarColunas();
            for(String c : col){
                output.format("%s\t\t", c);
            }
            output.format("\n");
            
            ArrayList<ArrayList<String>> linhas = produto.pegarDados();
            for(ArrayList<String> linha : linhas){
                for(String dado : linha){
                    output.format("%s\t\t", dado);
                }
                output.format("\n");
            }
            
            output.close();
        }catch(SecurityException securityException){
            System.err.println("Permissao negada.");
            System.exit(1);
        }catch(FileNotFoundException FileNotFoundException){
            System.err.println("Erro ao abrir o arquivo.");
            System.exit(1);
        }catch(NullPointerException nullPointerException){
            System.err.println("Nenhum produto carregado. Erro: " + nullPointerException);
            System.exit(1);
        }
    }
    
    public void deletarArquivo(int index){
		try{
            DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get("produtos/"));
            int count = 0;     
            for(Path p : ds){
                String nome = p.toString().substring(p.toString().indexOf("\\") + 1);
                nome = nome.substring(0, nome.indexOf("."));
                if(count == index){
                    Files.delete(Paths.get("produtos/" + nome + ".txt"));
                    break;
                }
                count++;
            }
            System.out.println();
            
        }catch(IOException ioException){
            System.err.println("Erro: " + ioException);
            System.exit(1);
        }
	}
    
    public void listarArquivos(){
        try{
            DirectoryStream<Path> ds = Files.newDirectoryStream(Paths.get("produtos/"));
            int count = 0;
            for(Path p : ds){
                String nome = p.toString().substring(p.toString().indexOf("\\") + 1);
                nome = nome.substring(0, nome.indexOf("."));
                System.out.println((count++) + " - " + nome);
            }
            System.out.println();
        }catch(IOException ioException){
            System.err.println("Erro: " + ioException);
            System.exit(1);
        }
    }
    
    public String toString(){
        
        return "arquivo";
    }
    
    public void fecharArquivo(){
        if(arq != null){
            arq.close();
        }
    }
  
}//FIM CLASSE PRODUTO