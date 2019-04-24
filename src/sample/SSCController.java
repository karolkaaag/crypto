package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class SSCController implements Initializable {

    public TextField textPolynomial;
    public Button buttonEncSSC;
    public TextArea textDecryptedFile;
    public TextField textSeed;
    public Button buttonDecSSC;
    public TextField textFileName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void buttonEncSSC(ActionEvent actionEvent) throws IOException {

        String seed = textSeed.getText();
        System.out.println("seed: " + seed);
        String polynomial = textPolynomial.getText();
        System.out.println("polynomial: " + polynomial);
        int n =8;
        List<String> res = lfsr_zad1(seed,polynomial,8);

        String file = textFileName.getText();
        byte[] bytes = null;
        byte[] bytesOut = null;
        bytes = Files.readAllBytes(Paths.get(file + ".bin"));
        bytesOut = new byte[(int) Files.size(Paths.get(file+".bin"))];
        encryption(res, bytes, bytesOut, seed, polynomial);
        Files.write(Paths.get(file+"out.bin"), bytesOut);

    }

    public void buttonDecSSC(ActionEvent actionEvent) throws IOException {
        String seed = textSeed.getText();
        System.out.println("seed: " + seed);
        String polynomial = textPolynomial.getText();
        System.out.println("polynomial: " + polynomial);
        int n =8;
        List<String> res = lfsr_zad1(seed,polynomial,8);

        String file = textFileName.getText();
        decryption(res, file, seed, polynomial);

    }


    public static void encryption(List<String> res, byte[] bytes, byte[] bytesout, String sid, String wiel){
        Byte b;
        for(int i=0; i<bytes.length; i++){
            b = bytes[i];
            String z = String.format("%8s", Integer.toBinaryString(b.intValue() & 0xFF)).replace(' ', '0');
            String out = "";
            for(int j=0; j<res.size(); j++){

                out += (Integer.parseInt(String.valueOf(better_lfsr(sid, wiel).charAt(0))) + Integer.parseInt(String.valueOf(z.charAt(j))))%2;
                sid = better_lfsr(sid, wiel);
            }

            int x = binary_to_dec(out);
            Byte bo = (byte) x;
            //System.out.println(String.format("%8s", Integer.toBinaryString(x & 0xFF)).replace(' ', '0'));
            bytesout[i] = bo;
        }
    }

    public static void decryption(List<String> res, String plikOut, String sid, String wiel) throws IOException{
        byte[] bytes = Files.readAllBytes(Paths.get(plikOut+".bin"));
        byte[] bytesOut = new byte[(int) Files.size(Paths.get(plikOut+".bin"))];
        Byte b;
        for(int i=0; i<bytes.length; i++){
            b= bytes[i];
            String z = String.format("%8s", Integer.toBinaryString(b.intValue() & 0xFF)).replace(' ', '0');
            String out = "";
            for(int j=0; j<res.size(); j++){
                out += (Integer.parseInt(String.valueOf(better_lfsr(sid, wiel).charAt(0))) + Integer.parseInt(String.valueOf(z.charAt(j))))%2;
                sid = better_lfsr(sid, wiel);
            }

            int x = binary_to_dec(out);
            Byte bo = (byte) x;
            bytesOut[i] = bo;
            System.out.println(out);
        }
        Files.write(Paths.get("wynik.bin"), bytesOut);
    }

    public static int binary_to_dec(String val){
        char [] charArray= new char[val.length()];
        charArray=val.toCharArray();
        int liczba=0;
        int i=charArray.length-1;
        while(i>=0){
            if(charArray[i]=='1'){
                liczba+= (int)Math.pow(2,charArray.length-(i+1));
            }
            i--;
        }
        return liczba;
    }

    public static String better_lfsr(String seed, String poly){
        String out = "";
        int x = 0;
        for(int i=0; i<seed.length(); i++){
            if(poly.charAt(i) == '1'){
                if(seed.charAt(i) == '1'){
                    x++;
                }
            }
        }
        if(x%2==1) out+="1";
        else out +="0";
        out += seed.substring(0, poly.length()-1);
        return out;
    }


    public static List<String> lfsr_zad1(String z, String w, int n){
        String[] tabZ = new String[z.length()];
        String[] tabW = new String[w.length()];
        List<Integer> wielomian = new LinkedList<>();
        String pathResult = new String();
        List<String> listResult = new LinkedList<>();
        int first = 0;

        for (int i = 0; i < z.length(); i++) {
            tabZ[i] = String.valueOf(z.charAt(i));
            tabW[i] = String.valueOf(w.charAt(i));
            if (Integer.parseInt(tabW[i]) == 1)
                wielomian.add(i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < w.length() - 1; j++)
                pathResult += tabZ[j];

            for (Integer value : wielomian)
                first ^= Integer.parseInt(tabZ[value]);

            pathResult = first + pathResult;

            listResult.add(pathResult);

            for (int k = 0; k < z.length(); k++)
                tabZ[k] = String.valueOf(pathResult.charAt(k));

            pathResult = "";
            first = 0;
        }
        return listResult;
    }
}
