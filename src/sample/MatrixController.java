package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MatrixController implements Initializable {
    @FXML
    public Button buttonMatrixEnc;
    public TextArea textAfterEncMatrix;
    public TextField textBeforeEncMatrix;
    public TextArea textMatrix;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void buttonVigenereEnc(ActionEvent actionEvent) {

        String input = textBeforeEncMatrix.getText();
        String input2 = input.replace(" ", "");

        int dl = (input2.length() / 5) + 1;
        char tab[][] = new char[dl][5];
        int k = 0;

        System.out.println(input2); //input2 wejsciowy bez spacji, input to wczytany tekst
        for (int i = 0; i < dl; i++) {
            for (int j = 0; j < 5; j++) {
                if (k != input2.length()) {
                    tab[i][j] = input2.charAt(k);
                    System.out.print(tab[i][j]);
                    k++;
                }
            }
            System.out.println();
        }

        String output = "";
        int[] kolejnosc = new int[5];

        kolejnosc[0] = 2;
        kolejnosc[1] = 3;
        kolejnosc[2] = 0;
        kolejnosc[3] = 4;
        kolejnosc[4] = 1;

        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < dl; i++) {
                output += Character.toString(tab[i][kolejnosc[j]]);
            }
        }


        output = output.replace(" ", "");
        System.out.println();
        System.out.println(output);
        textAfterEncMatrix.setText(output);


    }


}
