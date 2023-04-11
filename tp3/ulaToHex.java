import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HexFormat;

public class ulaToHex {
    int x = 0;
    int y = 0;
    int w = 0;
    int contador = 0;

    public void converte() throws IOException
    {
        File file = new File("testeula.hex");
        
        BufferedReader br = new BufferedReader((new FileReader("testeula.ula")));
        String linha = br.readLine();
        while (linha.compareTo("fim.") != 0) {
            linha = br.readLine();
            String linha2 = "";

            if (linha.charAt(0) == 'X') {
                for (int i = 2; i < linha.length() -1; i++) {
                    linha2 = linha2 + linha.charAt(i);
                }
                x =  Integer.parseInt(linha2);
                
            }
            else if(linha.charAt(0) == 'Y')
            {
                for (int i = 2; i < linha.length() -1; i++) {
                    linha2 = linha2 + linha.charAt(i);
                }
                y =  Integer.parseInt(linha2);
                
            }
            else if(linha.charAt(0) == 'W')
            {
                // conversao de comando no ula para INT pro W
                for (int i = 2; i < linha.length() -1; i++) {
                    linha2 = linha2 + linha.charAt(i);
                }
                
                WtoInt(linha2);
                contador++;
                intToHex();
            }

        }

        br.close();
      
       try (FileWriter writer = new FileWriter(file, true)) {
        writer.write("0xffff");
    }
        
    }

    public void intToHex() throws IOException
    {
        
        
        String s1 =   String.format("%X%X%X", x, y, w);
        File file = new File("testeula.hex");
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(s1);
            writer.write('\n');
            writer.close();
        }
        
        

    }

    public void WtoInt(String linha2)
    {

        if(linha2.compareTo("An") == 0) {
            w = 0;
        }
        else if(linha2.compareTo("Bn") == 0) {
            w = 1;
        }
        else if(linha2.compareTo("umL") == 0) {
            w = 2;
        }
        else if(linha2.compareTo("zeroL") == 0) {
            w = 3;
        }
        else if(linha2.compareTo("nAeB") == 0) {
            w = 4;
        }
        else if(linha2.compareTo("nAoB") == 0) {
            w = 5;
        }
        else if(linha2.compareTo("AxB") == 0) {
            w = 6;
        }
        else if(linha2.compareTo("AeBn") == 0) {
            w = 7;
        }
        else if(linha2.compareTo("AnoB") == 0) {
            w = 8;
        }
        else if(linha2.compareTo("nAxB") == 0) {
            w = 9;
        }
        else if(linha2.compareTo("AoB") == 0) {
            w = 10;
        }
        else if(linha2.compareTo("AeB") == 0) {
            w = 11;
        }
        else if(linha2.compareTo("AneB") == 0) {
            w = 12;
        }
        else if(linha2.compareTo("AoBn") == 0) {
            w = 13;
        }
        else if(linha2.compareTo("copiaB") == 0) {
            w = 14;
        }
        else if(linha2.compareTo("copiaA") == 0) {
            w = 15;
        }

    }

    public static void main(String[] args) throws IOException {
        ulaToHex ula = new ulaToHex();
        ula.converte();

    }
}