package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class VigenereController implements Initializable {

    public TextField keyEncVig;
    public Button buttonVigenereEnc;
    public TextArea textAfterEncVig;
    public TextField textBeforeEncVig;
    public TextField textBeforeDecVig;
    public TextField keyDecVig;
    public Button buttonVigenereDec;
    public TextArea textAfterDecVig;
    private char[][] alphabetTable = new char['z' + 1]['z' + 1];

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void buttonVigenereEnc(ActionEvent actionEvent) {
        String inputString= textBeforeEncVig.getText();
        String passString=keyEncVig.getText();
        String s="";
        int wej=inputString.length();
        int kod=passString.length();
        if(wej!=kod){
            int j=0;
            for(int i=kod;i<wej;i++){
                passString+=passString.charAt(j);
                j++;
            }
        }

        for (char a = 'a'; a <= 'z'; a++) {
            char b = a;
            for (int z = 'a'; z <= 'z'; z++) {
                if (b == 'z' + 1) {
                    b = 'a';
                }
                alphabetTable[a][z] = b;
                b++;
            }
        }

        char[] textCharTable = inputString.toCharArray();
        char[] passCharTable = passString.toCharArray();

        for (int index = 0; index < textCharTable.length; index++) {
            s+= Character.toString(alphabetTable[textCharTable[index]][passCharTable[index]]);
        }
        textAfterEncVig.setText(s);
    }

    public void buttonVigenereDec(ActionEvent actionEvent) {

        String s="";
        String inputString= textBeforeDecVig.getText();
        String passString=keyDecVig.getText();
        int wej=inputString.length();
        int kod=passString.length();

        if(wej!=kod){
            int j=0;
            for(int i=kod;i<wej;i++){
                passString=passString+passString.charAt(j);
                j++;
            }
        }

        for (char a = 'a'; a <= 'z'; a++) {
            char b = a;
            for (int z = 'a'; z <= 'z'; z++) {
                if (b == 'z' + 1) {
                    b = 'a';
                }
                alphabetTable[a][z] = b;
                b++;
            }
        }
        char[] textCharTable = inputString.toCharArray();
        char[] passCharTable = passString.toCharArray();

        for (int index = 0; index < textCharTable.length; index++) {
            if (textCharTable[index] != ' ') {
                for (int z = 'a'; z <= 'z'; z++) {
                    if (textCharTable[index] == alphabetTable[passCharTable[index]][z]) {
                        s+= Character.toString(alphabetTable['a'][z]);
                    }
                }
            } else {
                System.out.print(" ");
            }
        }textAfterDecVig.setText(s);
    }


}
