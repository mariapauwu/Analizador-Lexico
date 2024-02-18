

package analizadorlexico;

import analizadorlexico.Token.Tipos;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AnalizadorLexico {
    public static void main(String[] args) {
        String entrada =
                "/* Este es un comentario */" +
                "public class SumaEjemplo {" +
                "    public static void main(String[] args) {" +
                "        int limiteSuperior = 10;" +
                "        int resultado = calcularSuma(limiteSuperior);" +
                "        System.out.println(\"La suma de los números del 1 al \" + limiteSuperior + \" es: \" + resultado);" +
                "    }" +
                "/* Este es un comentario */" +
                "    static int calcularSuma(int n) {" +
                "        int suma = 0;" +
                "        for (int i = 1; i <= n; i++) {" +
                "            suma += i; " +
                "        }" +
                "        return suma;";
        
        ArrayList<Token> tokens = lex(entrada);

        for (Token token : tokens) {
            System.out.println(token.getTipo() + ": " + token.getValor());
        }
    }
    
    private static ArrayList<Token> lex(String entrada) {
        final ArrayList<Token> tokens = new ArrayList<>();
        
        Pattern patronTokens = Pattern.compile(
            String.join("|",
                Tipos.COMENTARIO.patron,
                Tipos.NUMERO.patron,
                Tipos.OPERADOR.patron,
                Tipos.CADENA.patron,
                Tipos.SIMBOLO.patron,
                Tipos.PALABRA_RESERVADA.patron
            ),
            Pattern.CASE_INSENSITIVE | Pattern.DOTALL
        );

        Matcher busqueda = patronTokens.matcher(entrada);

        while (busqueda.find()) {
            String valor = busqueda.group();
            Tipos tokenTipo = identificarTipo(valor);
            
            if (tokenTipo == Tipos.COMENTARIO) {
                continue;
            }

            if (tokenTipo != null) {
                Token token = new Token();
                token.setTipo(tokenTipo);
                token.setValor(valor);
                tokens.add(token);
            } else {
                System.out.println("No se pudo reconocer el token");
            }
        }

        return tokens;
    }

    private static Tipos identificarTipo(String valor) {
        for (Tipos tokenTipo : Tipos.values()) {
            Pattern patronToken = Pattern.compile(tokenTipo.patron, Pattern.CASE_INSENSITIVE);
            Matcher busquedaToken = patronToken.matcher(valor);

            if (busquedaToken.matches()) {
                return tokenTipo;
            }
        }
        return null;
    }
}

