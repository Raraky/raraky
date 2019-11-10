import java.util.ArrayList;

public class Produto{
    
    //ATRIBUTOS
    private String nome;
    private ArrayList<String> coluna;
    private ArrayList<ArrayList<String>> dados;
    
    //CONSTRUTOR
    public Produto(){
        this.nome = null;
        this.coluna = new ArrayList<>();
        this.dados = new ArrayList<ArrayList<String>>();
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void adicionarColuna(String col){
        this.coluna.add(col);
    }
    
    public ArrayList<String> pegarColunas(){
        ArrayList<String> col = new ArrayList<>();
        for(String c : coluna){
            col.add(c);
        }
        return col;
    }
    
    public int getSize(){
        return coluna.size();
    }
    
    public void editarColuna(int x, String novaColuna){
        this.coluna.set(x, novaColuna);
    }
    
    public void apagarColuna(int x){
        this.coluna.remove(x);
        for(ArrayList<String> d : dados){
            d.remove(x);
        }
    }
    
    public void editarDado(int x, int y, String novoDado){
        dados.get(x).set(y, novoDado);
    }
    
    public ArrayList<ArrayList<String>> pegarDados(){
        ArrayList<ArrayList<String>> linhas = new ArrayList<>();
        for(ArrayList<String> d : dados){
            ArrayList<String> linha = new ArrayList<>();
            for(String l : d){
                linha.add(l);
            }
            linhas.add(linha);
        }
        return linhas;
    }
    
    public void adicionarLinha(ArrayList<String> linha){
        this.dados.add(linha);
    }
    
    public String listarDados(){
        String tabela = "";
        int a = 0;
        for(ArrayList<String> linha : dados){
            tabela+= a++ + " - ";
            for(String dado : linha){
                tabela+= dado + "\t";
            }
             tabela+= "\n";
        }
        
        return tabela;
    }
    
    public String listarTabela(){
        String tabela = "";
        
        tabela+= this.nome+"\n";
        for(String col : coluna){
            tabela+= col + "\t";
        }
        
        tabela+= "\n";
        
        for(ArrayList<String> linha : dados){
            for(String dado : linha){
                tabela+= dado + "\t";
            }
             tabela+= "\n";
        }
        
        return tabela;
    }
    
}//FIM CLASSE PRODUTO




