//Tsagan Garyaeva
//Comp-271
//Pizza Lab

package fx;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;


public class FinalLabPizza extends Application{
	private ComboBox<String> sizes;
	private ListView<String> toppings;
	private ComboBox<String> crusts;
	TextArea order;
	TextField name = new TextField();
	TextField address = new TextField();
	TextField city = new TextField();
	TextField phone = new TextField();
	ToggleGroup delGroup = new ToggleGroup();
	private Button orderit, clearit;
	private Label lb_order;
	private ObservableList<String> size =FXCollections.observableArrayList ("Small $12",
	"Medium $14","Large $16", "X-Large $18");
	private ObservableList<String> tps =FXCollections.observableArrayList("Plain Cheese $0.50", 
			"Mushroom $0.75","Olives $0.75", "Extra Cheese $1", "Peppironi $1","Sausage $1");
	private ObservableList<String> crust =FXCollections.observableArrayList ("Thin",
			"Thick ","Deep dish ");
	
	String delivery="";
	String result3 =""; 
	double cost;
	double total;
	double topCost;
	double subtotal;
	final double TAXRATE = 0.07 ;
	double tax;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void start(Stage window) throws Exception {
		
		// areas to place the various components
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-border-color:  #8c2617 ;");
		

		//VBox with crust types
		VBox crust_pane = new VBox(30);
		crusts = new ComboBox(crust);
		crusts.setValue("Crust "); 
		crusts.setMaxWidth(300);
		crust_pane.getChildren().add(crusts);
		
		// VBox with sizes 
		VBox flavor_pane = new VBox(30);		
		sizes = new ComboBox(size);
		sizes.setValue("Size"); 
		sizes.setMaxWidth(300);
		flavor_pane.getChildren().add( sizes);
		
		
		// VBox with toppings
		VBox topping_pane = new VBox(20);
		Label lbltop  = new Label("Choose toppings: ");
		lbltop.setStyle("-fx-text-fill:#ffc87b;-fx-font-size: 17;-fx-padding: 10px;");
		lbltop.setAlignment(Pos.TOP_CENTER);
		toppings = new ListView(tps);
		toppings.setMaxWidth(250);
		toppings.setMaxHeight(200);
		toppings.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		topping_pane.getChildren().addAll(lbltop, toppings);
		topping_pane.setAlignment(Pos.TOP_CENTER);
		//HBox which contains sizes and toppings panes
		HBox main = new HBox(35);
		main.getStyleClass().add("main");
		main.getChildren().addAll(crust_pane,flavor_pane, topping_pane);
		main.setAlignment(Pos.TOP_CENTER);
		main.setPadding(new Insets(10, 10, 10, 10));
		main.setStyle("-fx-background-color:#c44b27; -fx-text-fill:#ffc87b;-fx-font-size: 17;-fx-padding: 10px;");
		
	
		Image img = new Image("pizza.jpg");
		ImageView im = new ImageView(img);
		im.setFitHeight(180);
		im.setFitWidth(200);
		VBox imgBox = new VBox(10);
		imgBox.setPadding(new Insets(5, 10, 5, 10));
		imgBox.getChildren().addAll(im);
		imgBox.setStyle("-fx-background-color:#8c2617;");	
		imgBox.setAlignment(Pos.CENTER);
		
		
		VBox order_pane = new VBox(10);
		lb_order = new Label("You Ordered: ");
		lb_order.setStyle("-fx-text-fill:#ffc87b;-fx-font-size: 17;-fx-padding: 10px;");
		order = new TextArea();
		order.setEditable(false);
		order.setPrefColumnCount(10);
		order.setMinWidth(400);
		order.setMinHeight(200);
		order_pane.getChildren().addAll(lb_order, order);
		
		
		HBox order_pane2 = new HBox(10);
		order_pane2.getChildren().addAll(imgBox,order_pane);
		order_pane2.setAlignment(Pos.TOP_CENTER);
		
		// Bottom section with buttons
		HBox btn_pane = new HBox(20);
		btn_pane.setAlignment(Pos.BOTTOM_CENTER);
		orderit = new Button("Place Order");
		clearit = new Button("Clear Order");
		btn_pane.getChildren().addAll(orderit, clearit);
		btn_pane.setPadding(new Insets(10, 10, 10, 10));
		
		
		//VBox with orderpane and buttons
		VBox b_section = new VBox(10);
		b_section.setPadding(new Insets(10, 10, 10, 10));
		b_section.getChildren().addAll( order_pane2, btn_pane);
		btn_pane.setStyle("-fx-background-color:#8c2617; -fx-text-fill:#c44b27;-fx-font-size: 17;-fx-padding: 10px;");
		order_pane2.setStyle("-fx-background-color:#c44b27; -fx-text-fill:#c44b27;-fx-font-size: 17;-fx-padding: 10px;");
		
		//form  top section
		HBox form = new HBox();
		VBox labels = new VBox();
		VBox inputs = new VBox(10);
		form.setAlignment(Pos.TOP_CENTER);
		form.setStyle("-fx-background-color:#c44b27; -fx-text-fill:#ffc87b;-fx-font-size: 17;-fx-padding: 10px;");
		Label text  = new Label("* LINO'S PIZZERIA *");
		text.setFont(new Font("Arial",30));
		text.setStyle("-fx-text-fill: #ffc87b;"); 
		Label lblform  = new Label("Please, enter your information: ");
		lblform.setStyle("-fx-text-fill:#ffc87b; -fx-font-size: 20px;");
		VBox welcome = new VBox(10);
		welcome.getChildren().addAll(text, lblform);
		welcome.setAlignment(Pos.TOP_CENTER);
		
		
		// define width limits
		name.setMinWidth(350);
		address.setMinWidth(350);
		city.setMinWidth(350);
		phone.setMinWidth(350);
		Label lblname= new Label("Name: ");
		lblname.setStyle(" -fx-text-fill:#ffc87b;");
		Label lbladdress= new Label("Address: ");
		lbladdress.setStyle(" -fx-text-fill:#ffc87b;");
		Label lblcity= new Label("City: ");
		lblcity.setStyle(" -fx-text-fill:#ffc87b;");
		Label lblphone= new Label("Phone Number: ");
		lblphone.setStyle(" -fx-text-fill:#ffc87b;");
		name.setPromptText("Name");
		address.setPromptText("Address");
		city.setPromptText("City");
		phone.setPromptText("Phone");
		labels.getChildren().addAll(lblname, lbladdress, lblcity, lblphone);
		
		inputs.getChildren().addAll(name,address, city, phone );
		form.getChildren().addAll(labels, inputs);
		form.setPadding(new Insets(10, 10, 10, 10));
		
		VBox f_form = new VBox();
		f_form.setPadding(new Insets(10, 10, 10, 10));
		f_form.getChildren().addAll(welcome, form);
		
		
		
		// Subscribe for when the user clicks the buttons
		OrderHandler oh = new OrderHandler();
		orderit.setOnAction(oh);
		clearit.setOnAction(oh);
		clearit.setOnAction(e -> clear());
		
				
		// Add all to the main pane
		pane.setTop( f_form );
		pane.setBottom(b_section);
		pane.setCenter(main);
	
		
		Scene scene = new Scene(pane, 700, 900);
		scene.getStylesheets().add("style2.css");
		window.setTitle("* Lino's Pizzeria *");
		window.setScene(scene);
		window.show();
		
	}
	
	
	public void clear() {
		order.setText("");
		toppings.getSelectionModel().clearSelection();
		name.clear();
		address.clear();
		city.clear();
		phone.clear();
		sizes.setValue("Pick a Size"); 
		crusts.setValue("Pick a Crust"); 
	}
	
	
	class OrderHandler implements EventHandler<ActionEvent> {
		
		public void handle(ActionEvent e) {
			// validate phone number
			String phone_number = phone.getText();
			String name_n = name.getText();
			String address_n = address.getText();
			String city_n = city.getText();
			
			
			// checks for exactly 10 digits
			if (!phone_number.matches("^[0-9]{10}$") || (name_n.isEmpty()) ||
					(address_n.isEmpty()) || (address_n.isEmpty()) ||(city_n.isEmpty())) 
			{
				phone.setText("Invalid phone number");
				phone.setStyle("-fx-text-fill: red; -fx-font-size: 16;");
				name.setText("Enter your name");
				name.setStyle("-fx-text-fill: red; -fx-font-size: 16;");
				address.setText("Enter your address");
				address.setStyle("-fx-text-fill: red; -fx-font-size: 16;");
				city.setText("Enter your city");
				city.setStyle("-fx-text-fill: red; -fx-font-size: 16;");
				sizes.setValue("Size"); 
				sizes.setStyle("-fx-text-fill: red; -fx-font-size: 16;");
				crusts.setValue("Crust"); 
				crusts.setStyle("-fx-text-fill: red; -fx-font-size: 16;");
				order.setText("Please, fill the form above.");
				order.setStyle("-fx-text-fill: red; -fx-font-size: 16;");
				return;
			} 
			
			 
			if (e.getSource() == orderit) {
				// flavor
				String result = sizes.getValue();
				// crust types
				String result2 = crusts.getValue();
				
				// toppings
				ObservableList<String> selections = toppings.getSelectionModel().getSelectedItems();
				Object[] objs = selections.toArray();
				
				for(int k = 0; k < objs.length; k++) {
					result3+= objs[k].toString()+ " \n";
					}
				System.out.println(sizes.getValue());
				 if (sizes.getValue().equals("Small $12")){
					cost = 12;
				}
				else if(sizes.getValue().equals("Medium $14")){
					cost = 14;
				}
				else if(sizes.getValue().equals("Large $16")){
					cost = 16;
				}
				else if(sizes.getValue().equals("X-Large $18")){
					cost = 18;
				}
				if(result3.equals(null)){
					result3= "";
				}
				if(result3.contains("Plain Cheese $0.50")){					
					topCost+=.50;					
				}
				
				
				
				
				
				//Cost of Toppings
				 if (result3.contains("Mushroom $0.75")){
					
					topCost+= .75;
				}
				 if (result3.contains("Olives $0.75")){
					 
					topCost+= .75;
				}
				if (result3.contains("Extra Cheese $1")){
					
					topCost+= 1;
				}
				 if (result3.contains("Peppironi $1")){
					
					topCost+= 1;
				}
				 if (result3.contains("Sausage $1")){
					
					topCost+= 1;
				}
				else{
					order.setText("Please, pick type of pizza.");
				}

					System.out.println(topCost);

					System.out.println(cost);
				subtotal = (cost + topCost);
				total = (subtotal* TAXRATE) + subtotal;
				total = Math.round(total);
				
				order.setText( 
						 					"Size: "+ result  + " \n"+ 
						 					"Crust type: "	+ result2  + " \n"+	
						 					"Toppings: "+ " \n"+  result3  + " \n"+			
						 					"Name: " + name.getText() + "\n" +
						 					"Address: " +address.getText() + "\n"+ 
						 					"City: " + city.getText() + "\n" + 	
						 					"Phone Number: " + phone.getText()+ "\n" + 
						 					"Total due: " + "$"+ total + "\n" + 
						 					"Thank You For Your Order!");
						 
				} // end of logic for orderit button
			
			else 
			{	
			order.setText("Please, fill the form above.");
			toppings.getSelectionModel().clearSelection();
			name.clear();
			address.clear();
			city.clear();
			phone.clear();
			
			}
		}
	
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}