/**
 * Name: AnimateQuickSort
 * 
 * @author Chris Eardensohn (ceardensohn@sandiego.edu)
 * @author Om Kanwar (okanwar@sandiego.edu)
 *
 * Date: 12/18/2017
 * 
 * Description: This file contains code to generate an 
 * array of 20 random integers from 1-999.  The program will have two 
 * buttons, step and reset. Each step click will show the logical steps of
 * a quicksort sorting method in a javafx window until one partition is
 * complete.  A reset click will reset the window with a new array of
 * random integers. 
 */

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AnimateQuickSort extends Application {
	
	/*
	 * Function to generate unsorted random ints from 1 - 999
	 */
	public static void randomInts(int[] array) {
		for(int i = 0; i < array.length; i++) {
			Random r = new Random();
			array[i] = r.nextInt((999 - 1) + 1) + 1;
		}
	}
	
	/*
	 * Function to add array's info to center window
	 */
	public GridPane addCenterPane(int[] array, Label[] value, Text[] text,
			int pivot, int low, int high) {
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(200,100,100,100));
		gridPane.setPrefHeight(400);
		//add array values to each label
		for(int i = 0; i < array.length; i++) {
			value[i] = new Label();
			value[i].setPrefSize(50,50);
			value[i].setStyle("-fx-border-color: black");
			value[i].setText(" " + array[i]);
			gridPane.add(value[i], i, 2);
		}
		gridPane.add(text[0], pivot, 3);
		gridPane.add(text[1], low, 1);
		gridPane.add(text[2], high, 0);

		return gridPane;
	}
	
	/*
	 * Function to clear the board and update center with new information
	 */
	public void updateBoard(int[] array, Label[] value, Text[] text,
			int pivot, int low, int high, BorderPane borderPane) {
		borderPane.setCenter(null);
		GridPane resetCenter = new GridPane();
		resetCenter = addCenterPane(array, value, text, pivot, low, high);
		borderPane.setCenter(resetCenter);
	}
	
	//local variables to update on each step click
	int pivot = 0;
	int low = 1;
	int high = 19;
	int[] array = new int[20];
	Label[] value = new Label[array.length];
	Text[] text = new Text[3];
	
	@Override
	public void start(Stage quickStage) throws Exception {
		
		randomInts(array);

		text[0] = new Text("Pivot");
		text[1] = new Text("Low");
		text[2] = new Text("High");

		//set up state
		quickStage.setTitle("Quicksort Animation");
		quickStage.setWidth(1000);
		quickStage.setHeight(500);
		final BorderPane borderPane = new BorderPane();
		
		//bottom box to hold buttons
		HBox botBox = new HBox();
		botBox.setSpacing(50);
		botBox.setPadding(new Insets(20, 20, 20, 375));
		
		//buttons to step through quicksort
		Button buttonReset = new Button ("Reset");
		buttonReset.setPrefSize(100, 20);
		
		Button buttonStep = new Button("Step");
		buttonStep.setPrefSize(100, 20);
		
		botBox.getChildren().addAll(buttonReset, buttonStep);
		
		borderPane.setBottom(botBox);
		
		//center pane to hold random ints
		final GridPane centerPane = addCenterPane(array, value, text, pivot, low, high);
		borderPane.setCenter(centerPane);
		
		//action handler for reset button. resets variables and board
		buttonReset.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e){
				randomInts(array);
				pivot = 0;
				low = 1;
				high = 19;
				updateBoard(array, value, text, pivot, low, high, borderPane);
			}
		});
		
		//step through execution and update board
		buttonStep.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				int tmp = 0;
				//check for completed sort
				if (low > high) {
					tmp = array[pivot];
					array[pivot] = array[high];
					array[high] = tmp;
					pivot = high;
					updateBoard(array, value, text, pivot, low, high, borderPane);
					System.out.println("Partition finished.");
				}
				else if(array[low] > array[pivot]) {
					//case to switch array values
					if(array[high] <= array[pivot]){
						tmp = array[low];
						array[low] = array[high];
						array[high] = tmp;
						high--;
						updateBoard(array, value, text, pivot, low, high, borderPane);
					}
					//search backward to find high index to switch
					else{
						high--;
						updateBoard(array, value, text, pivot, low, high, borderPane);
					}
				}
				//search forward to find a low index greater than pivot
				else if (array[low] < array[pivot]) {
					low++;
					updateBoard(array, value, text, pivot, low, high, borderPane);
				}
			}
		});
		
		
		//add panes to scene and show
		Scene quickScene = new Scene(borderPane);
		quickStage.setScene(quickScene);
		quickStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
