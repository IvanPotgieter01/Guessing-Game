

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
/**
 * Write a description of JavaFX class GuessGame here.
 *
 * @author (Ivan Potgieter)
 * @version (21/05/2022)
 */
import java.util.Random;
public class GuessGame extends Application
{
    private Button randButton = new Button("Random number");
    private Label hintLabel = new Label("");
    private Label enterLabel = new Label("Enter a number number: ");
    private TextField userNum = new TextField();
    private Button checkButton = new Button("Check match");
    private TextArea displayArea = new TextArea();
    private Button clearButton = new Button("Clear all components");
    private Button quitButton = new Button("Quit game");
    private int count = 1;
    private int randNumber;

    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage)
    {
        Pane randomPane = new HBox(10); //Creating panes in order to display components correctly
        Pane enterPage = new HBox(10);
        Pane checkPane = new HBox(10);
        Pane displayPane = new HBox(10);
        Pane clearPane = new HBox(10);
        
        // Create a new grid pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setVgap(10);
        pane.setHgap(10);



        // Add the button and label into the pane
        randomPane.getChildren().add(randButton); //Adding components to each page
        randomPane.getChildren().add(hintLabel);
        enterPage.getChildren().add(enterLabel);
        enterPage.getChildren().add(userNum);
        checkPane.getChildren().add(checkButton);
        displayPane.getChildren().add(displayArea);
        clearPane.getChildren().add(clearButton);
        clearPane.getChildren().add(quitButton);
        
        pane.add(randomPane, 0, 0); //Adding each pane to the main pain, in order
        pane.add(enterPage, 0, 1);
        pane.add(checkPane, 0, 2);
        pane.add(displayPane, 0, 3);
        pane.add(clearPane, 0, 4);
        
        
        //pane.add(myButton, 0, 0);
                
        //pane.add(topLabel, 0, 0);


        //set an action on the button using method reference
        randButton.setOnAction(this::randButtonClick);
        checkButton.setOnAction(this::checkButtonClick);
        clearButton.setOnAction(this::clearButtonClick);
        quitButton.setOnAction(this::quitButtonClick);
        

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 500,360); //Setting size and title
        stage.setTitle("Guess game");
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();
    }

    /**
     * This will be executed when the button is clicked
     * It increments the count by 1
     */
    private void randButtonClick(ActionEvent event)
    {
        Random rand = new Random(); //Creating a random number
        int rondomNum = rand.nextInt(10);
        
        randNumber = rondomNum + 1;
        hintLabel.setText("Guess the number...");
        // Counts number of button clicks and shows the result on a label
        //count = count + 1;
        //enterLabel.setText(Integer.toString(count));
    }
    
    private void checkButtonClick(ActionEvent event)
    {
        int userNumb = Integer.parseInt(userNum.getText()); //Obtaining random number that was generated

        if (userNumb > randNumber) //Case if number is too big
        {
            displayArea.appendText("Your guess is: " + userNumb + " - too high\n");
            count++;
            userNum.setText(""); //Setting focus to text box and clearing its contents
            userNum.requestFocus();
        }
        else if (userNumb < randNumber) //Case if number is too small
        {
             displayArea.appendText("Your guess is: " + userNumb + " - too low\n");
             count++;
             userNum.setText("");
             userNum.requestFocus();
        }
        else //Case if number is correct
        {
            displayArea.appendText("Your guess is: " + userNumb + " - Correct!");
            displayArea.appendText("\nNumber of guesses: " + count);
        }
    }
    
    private void clearButtonClick(ActionEvent event)
    {
        displayArea.clear(); //Clearing display area
        userNum.setText(""); //Setting focus to text box and clearing its contents
        userNum.requestFocus();
        hintLabel.setText("Click random number");
    }
    
    private void quitButtonClick(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quit game");
        alert.setContentText("Goodbye"); //Showing aleart box and quitting
        alert.showAndWait();
        System.exit(0);
    }
}
