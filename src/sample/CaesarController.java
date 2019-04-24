package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CaesarController implements Initializable {

    public TextField textBeforeEnc;
    public TextField keyShiftEnc;
    public Button buttonEnc;
    public TextArea textAfterEnc;
    public TextField textBeforeDec;
    public TextField keyShiftDec;
    public Button buttonDec;
    public TextArea textAfterDec;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void buttonCeasarEnc(ActionEvent actionEvent) {
        String textBefore = textBeforeEnc.getText();
        String encrypted="";
        int shift = Integer.parseInt(keyShiftEnc.getText());
        char chE;
        String textBeforeWithoutSpaces = textBefore.replaceAll("\\s+", "");
        textBeforeWithoutSpaces.toLowerCase();
        char tab[] = textBeforeWithoutSpaces.toCharArray();
        for(int i=0;i<tab.length;i++) {
            if(Character.isLetter(tab[i])) {
                chE=(char)(((int)tab[i]+shift-97)%26+97);
                //System.out.println(ch1[i]+" = "+ch3);
                encrypted=encrypted+chE;
                System.out.println(encrypted + " " + i);
                textAfterEnc.setText(encrypted);
            } else if(tab[i]==' ') {
                encrypted=encrypted+tab[i];
            }
        }
    }

    public void buttonCeasarDec(ActionEvent actionEvent) {
        String textBefore = textBeforeDec.getText();
        String decrypted="";
        int shift = Integer.parseInt(keyShiftDec.getText());
        char chD;
        String textBeforeWithoutSpaces = textBefore.replaceAll("\\s+", "");
        textBeforeWithoutSpaces.toLowerCase();
        char tab[] = textBeforeWithoutSpaces.toCharArray();
        for(int i=0;i<textBeforeWithoutSpaces.length();i++) {
            if(Character.isLetter(tab[i])) {
                if(((int)tab[i]-shift)<97) {
                    chD=(char)(((int)tab[i]-shift-97+26)%26+97);
                } else {
                    chD=(char)(((int)tab[i]-shift-97)%26+97);
                }
                decrypted=decrypted+chD;
                textAfterDec.setText(decrypted);
            }
            else if(tab[i]==' '){
                decrypted=decrypted+tab[i];
            }
        }
        System.out.println(decrypted);
    }
}
