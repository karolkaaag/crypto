package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RailFenceController implements Initializable {

    public TextField numberOfRailsEnc;
    public Button buttonRailFenceEnc;
    public TextArea textAfterRF;
    public TextField textBeforeEncRF;
    public TextField textBeforeDecRF;
    public TextField numberOfRailsDec;
    public Button buttonRailFenceDec;
    public TextArea textAfterDecRF;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void buttonRailFenceEnc(ActionEvent actionEvent) {

        String textBefore = textBeforeEncRF.getText();
        String encrypted="";
        int rails = Integer.parseInt(numberOfRailsEnc.getText());
        char chE;
        String textBeforeWithoutSpaces = textBefore.replaceAll("\\s+", "");
        textBeforeWithoutSpaces.toLowerCase();
       // char tab[] = textBeforeWithoutSpaces.toCharArray();

        boolean checkdown=false;
        int j=0;
        int row=rails;
        int col=textBeforeWithoutSpaces.length();             //liczba kolumn to dlugosc tekstu
        char[][] tab = new char[row][col];

        for(int i=0;i<col;i++){  //macierz odwiedzana w kolejnosci "schodków", poszcególne elem. trafiają na schodki
            if(j==0||j==row-1){
                checkdown=!checkdown;
            }
            tab[j][i]=textBeforeWithoutSpaces.charAt(i);
            if(checkdown){
                j++;
            } else{
                j--;
            }
        }
        //schodki:
        for(int i=0;i<row;i++){
            for(int k=0;k<col;k++){
                System.out.print(tab[i][k]+"  ");
            }
            System.out.println();
        }

        System.out.println("tekst zakodowany:");
        for(int i=0;i<row;i++){
            for(int k=0;k<col;k++){
                if(tab[i][k]!=0)
                    encrypted=encrypted+tab[i][k];
            }
        }
        System.out.println(encrypted);
        textAfterRF.setText(encrypted);

    }

    public void buttonRailFenceDec(ActionEvent actionEvent) {

        String textBefore = textBeforeDecRF.getText();
        String decrypted="";
        int rails = Integer.parseInt(numberOfRailsDec.getText());
        char chE;
        String textBeforeWithoutSpaces = textBefore.replaceAll("\\s+", "");
        textBeforeWithoutSpaces.toLowerCase();
        //char tab[] = textBeforeWithoutSpaces.toCharArray();

        boolean checkdown=false;
        int j=0;
        int row=rails;
        int col=textBeforeWithoutSpaces.length();
        char[][] tab = new char[row][col];

        //oznaczamy pozycje schodków
        for(int i=0;i<col;i++){
            if(j==0||j==row-1){
                checkdown=!checkdown;
            }
            tab[j][i]='*';
            if(checkdown){
                j++;
            } else{
                j--;
            }
        }

        //odwiedzamy każdy element zakodowanej tablicy, ktory ozn. jest*
        int index=0;

        for(int i=0;i<row;i++){
            for(int k=0;k<col;k++){
                if(tab[i][k]=='*'&&index<textBeforeWithoutSpaces.length()){
                    tab[i][k]=textBeforeWithoutSpaces.charAt(index++);
                }
            }
        }

        // schodki:
        for(int i=0;i<row;i++){
            for (int k=0;k<col;k++){
                System.out.print(tab[i][k]+ "\t");
            }
            System.out.println();
        }

        checkdown=false;
        j=0;

        for(int i=0;i<col;i++){
            if( j==0||j==row-1){
                checkdown=!checkdown;
            }
            decrypted+=tab[j][i];
            if(checkdown){
                j++;
            } else{
                j--;
            }
        }
        System.out.print(decrypted);
        textAfterDecRF.setText(decrypted);
    }
}
