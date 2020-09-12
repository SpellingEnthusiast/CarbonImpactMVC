package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets; 
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.Scene; 
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text; 
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import controller.Controller;         

public class GUI1 extends Application {
	private Controller controller;
   @Override 
   public void start(Stage stage) throws FileNotFoundException { 
	  controller = new Controller();
	   
      //Creating Text stuff
      Text week = new Text("Week: ");       
      Text date = new Text("Date: ");
      Text activity = new Text("Activity: ");
      Text points = new Text("Points -10 _ + 10");
      TextField weekT = new TextField();       
      DatePicker datePickerT = new DatePicker(); 
      TextField activityT = new TextField();
      TextField pointsT = new TextField();
      TextArea summaryTextArea = new TextArea("Activites to be displayed");
      Label labelIntro = new Label("Welcome to my carbon awareness application, this application allows you to monitor everyday activities and get an ideas to their effect on the environment\n"
      		+ "The \"Intro\" tab gives an overview of the program and its functionality\n"
      		+ "The \"Activity Management\" tab allows you to choose activities from a drop-down list to add them to your activities of each week.\n"
      		+ "The \"Results\" tab allows you to view all of your activities for each week, their impact on your points and allows you to order by certain factors.");
      TextArea summaryOrderedTextArea = new TextArea("Activites to be displayed in order");
      
      //Creating Buttons 
      Button add = new Button("Add"); 
      Button remove = new Button("Remove");  
      Button list = new Button("List");  
      Button summary = new Button("Summary");  
      Button load = new Button("Load");
      Button save = new Button("Save");
      Button exit = new Button("Exit");
      Button orderByDate = new Button("Order by date");
      Button orderByActivity = new Button("Order by activity name");
      Button memoryDestroyer = new Button("Do not press!");
      
      //Making the buttons do things
      
      EventHandler<ActionEvent> addbutton = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) 
          { 
        	  int week = Integer.parseInt(weekT.getText());
        	  LocalDate date = datePickerT.getValue();
        	  String activityname = activityT.getText();
        	  int points = Integer.parseInt(pointsT.getText());
        	  if (points == -10 | points == -9 | points == -8 | points == -7 | points == -6 | points == -5 | points == -4 | points == -3 | points == -2 | points == -1 | points == 0 | points == 1 | points == 2 | points == 3 | points == 4 | points == 5 | points == 6 | points == 7 | points == 8 | points == 9 | points == 10) {
        		  controller.addActivity(week, date, activityname, points);
        	  }
        	  else {
        		  pointsT.clear();
        	  }
          } 
      }; 
      
      EventHandler<ActionEvent> listbutton = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) 
          { 
        	 summaryTextArea.setText(controller.listActivities());
          } 
      }; 
      
      EventHandler<ActionEvent> summarybutton = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) 
          { 
        	 summaryTextArea.appendText("CURRENT TOTAL POINTS: " + controller.calcPoints());
          } 
      }; 
      
      EventHandler<ActionEvent> removebutton = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) 
          { 
        	 String activityname = activityT.getText();
        	 controller.removeActivity(activityname);
          } 
      }; 
      
      EventHandler<ActionEvent> savebutton = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) 
          { 
			controller.saveToFile();
          } 
      }; 
      
      EventHandler<ActionEvent> loadbutton = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) 
          { 
			controller.loadFromFile();
      }
      };
      
      EventHandler<ActionEvent> orderByDateButton = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) 
          { 
			summaryOrderedTextArea.setText(controller.listActivitiesByDate());
			summaryOrderedTextArea.appendText("CURRENT TOTAL POINTS: " + controller.calcPoints());
      }
      };
      
      EventHandler<ActionEvent> memoryDestroyerButton = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) 
          { 
        	  controller.destroyMemory();
      }
      };
      
      EventHandler<ActionEvent> orderByActivityButton = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) 
          { 
            summaryOrderedTextArea.setText(controller.listActivitiesByName());
            summaryOrderedTextArea.appendText("CURRENT TOTAL POINTS: " + controller.calcPoints());
      }
      };
      
      //populating dropdown
      try {
		controller.loadFromFileToList();
	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	}
      
      //Creating dropdown and adding activities to it
      ComboBox<String> comboBox = new ComboBox<String>();
      comboBox.getItems().setAll(controller.activitiesDropDown);
      
      EventHandler<ActionEvent> dropDown = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) 
          { 
        	  String name = (String) comboBox.getValue();
        	  activityT.setText(name);
      }
      };
      
      add.setOnAction(addbutton);
      list.setOnAction(listbutton);
      summary.setOnAction(summarybutton);
      remove.setOnAction(removebutton);
      save.setOnAction(savebutton);
      load.setOnAction(loadbutton);
      comboBox.setOnAction(dropDown);
      orderByDate.setOnAction(orderByDateButton);
      orderByActivity.setOnAction(orderByActivityButton);
      memoryDestroyer.setOnAction(memoryDestroyerButton);
      exit.setOnAction(e -> stage.close());

      //Creating a Grid Pane 
      GridPane gridPane = new GridPane();    
      gridPane.setMinSize(600, 300);  
      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
      gridPane.setVgap(5); 
      gridPane.setHgap(5);  
      
 	 //Creating tabs
      TabPane tp = new TabPane();

      Tab tab1 = new Tab("Intro");
      Tab tab2 = new Tab("Activity Management");
      Tab tab3 = new Tab("Results");
      tab1.setClosable(false);
      tab2.setClosable(false);
      tab3.setClosable(false);

      tp.getTabs().add(tab1);
      tp.getTabs().add(tab2);
      tp.getTabs().add(tab3);
      
      //Creating a new VBOX to store tabbedpane
      VBox tabbedPane = new VBox(tp);
       
      //Arranging all the nodes in the grid 
      gridPane.add(week, 0, 0); 
      gridPane.add(weekT, 1, 0); 
      gridPane.add(date, 0, 1);
      gridPane.add(activity, 0, 2);
      gridPane.add(points, 0, 3);
      gridPane.add(datePickerT, 1, 1); 
      gridPane.add(activityT, 1, 2); 
      gridPane.add(pointsT, 1, 3); 
      gridPane.add(add, 0, 4); 
      gridPane.add(remove, 1, 4);
      gridPane.add(list, 2, 4);
      gridPane.add(summary, 3, 4);
      gridPane.add(memoryDestroyer, 3, 5);
      gridPane.add(comboBox, 3, 0);
      gridPane.add(summaryTextArea, 1, 6);
      gridPane.add(load, 0, 7);
      gridPane.add(save, 1, 7);
      gridPane.add(exit, 3, 7);
      
      //adding an image which will be used in the intro tab
      FileInputStream input = new FileInputStream("resources/images/lol.png");
      Image image = new Image(input);
      ImageView imageView = new ImageView(image);
      
      //Creating a new gridpane which will serve the intro tab
      GridPane gridPaneIntro = new GridPane();    
      gridPaneIntro.setMinSize(600, 300);  
      gridPaneIntro.setPadding(new Insets(10, 10, 10, 10)); 
      gridPaneIntro.setVgap(5); 
      gridPaneIntro.setHgap(5);  
      
      //Arranging all the nodes in the intro tab
      gridPaneIntro.add(labelIntro, 1, 6);
      gridPaneIntro.add(imageView, 1, 5);
      
      //Creating a new gridpane which will serve the results tab
      GridPane gridPaneResults = new GridPane();    
      gridPaneResults.setMinSize(600, 300);  
      gridPaneResults.setPadding(new Insets(10, 10, 10, 10)); 
      gridPaneResults.setVgap(5); 
      gridPaneResults.setHgap(5);  
      
      //Arranging all the nodes in the results tab
      gridPaneResults.add(summaryOrderedTextArea, 1, 6);
      gridPaneResults.add(orderByDate, 1, 7);
      gridPaneResults.add(orderByActivity, 0, 7);
      
      //Setting content of tabs
      tab1.setContent(gridPaneIntro);
      tab2.setContent(gridPane);
      tab3.setContent(gridPaneResults);
      
      //Displaying stage
      Scene scene = new Scene(tabbedPane);
      stage.setTitle("My Carbon Awareness Effort"); 
      stage.setScene(scene);
      stage.show(); 
      
   }      
   public static void main(String args[]){ 
      launch(args); 
   } 
}