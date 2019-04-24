package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class LFSRController implements Initializable {

    public TextField textSeed;
    public TextField textPolynomial;
    public Button buttonLFSR;
    public TextArea textAfterLSFR;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void buttonClickLFSR(ActionEvent actionEvent) {
        String seed = textSeed.getText();
        System.out.println("seed: " + seed);
        String polynomial = textPolynomial.getText();
        System.out.println("polynomial: " + polynomial);
        int n =8;


        //method
        String[] tabZ = new String[seed.length()];
        String[] tabW = new String[polynomial.length()];
        List<Integer> wielomian = new LinkedList<>();
        String pathResult = new String();
        List<String> listResult = new LinkedList<>();
        String output="";
        int first = 0;

        for (int i = 0; i < seed.length(); i++) {
            tabZ[i] = String.valueOf(seed.charAt(i));
            tabW[i] = String.valueOf(polynomial.charAt(i));
            if (Integer.parseInt(tabW[i]) == 1)
                wielomian.add(i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < polynomial.length() - 1; j++)
                pathResult += tabZ[j];

            for (Integer value : wielomian)
                first ^= Integer.parseInt(tabZ[value]);

            pathResult = first + pathResult;
            output+=pathResult.charAt(polynomial.length()-1);
            listResult.add(pathResult);

            for (int k = 0; k < seed.length(); k++)
                tabZ[k] = String.valueOf(pathResult.charAt(k));

            pathResult = "";
            first = 0;
        }

        //return last element of list res
        List<String> res = listResult;
        for (int i=0; i<res.size(); i++){
            System.out.println(res.get(i).toString());
        }

        System.out.println("encrypted: "+output);
        // textAfterLSFR.setText(res.get(res.size()-1));
        textAfterLSFR.setText(output);
        //System.out.println("encrypted: " + res.get(res.size()-1));
        /*char[] tab = new char[n];
        for (int i=0; i<n; i++){
            tab[i] = ((res.get(i)).toString()).charAt(res.size());
        }

        for (int i=0; i<tab.length; i++){
            System.out.println(tab[i]);
        }*/
    }
}
