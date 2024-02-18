
package analizadorlexico;

public class Token {
    
    private String valor;
    private Tipos tipo;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Tipos getTipo() {
        return tipo;
    }

    public void setTipo(Tipos tipo) {
        this.tipo = tipo;
    }
    
    enum Tipos {
        PALABRA_RESERVADA("\\b(abstract|continue|for|new|switch|assert|default|goto|package|synchronized|boolean|do|if|private|this|break|double|implements|protected|throw|byte|else|import|public|throws|case|enum|instanceof|return|transient|catch|extends|int|short|try|char|final|interface|static|void|class|finally|long|strictfp|volatile|const|float|native|super|while)\\b"),
        NUMERO("\\d+"),
        OPERADOR("[-+*/=]"),
        CADENA("[A-Z]+"),
        SIMBOLO("[.,;:?!()]"),
        COMENTARIO("/\\*.*?\\*/"); 
    
        public final String patron;
    
        Tipos(String s) {
            this.patron = s;
        }
    }
}

