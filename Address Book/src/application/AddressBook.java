package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddressBook extends Application {

	private TableView<Contact> tableView;
	private ObservableList<Contact> contactList;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// Create TableView and contact list
		tableView = new TableView<>();
		contactList = FXCollections.observableArrayList();

		// Set up table columns
		TableColumn<Contact, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Contact, String> phoneNumberColumn = new TableColumn<>("Phone Number");
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

		TableColumn<Contact, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

		TableColumn<Contact, String> addressColumn = new TableColumn<>("Address");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

		tableView.getColumns().addAll(nameColumn, phoneNumberColumn, emailColumn, addressColumn);
		tableView.setItems(contactList); // Set the items of the TableView

		// Set column widths
		nameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
		phoneNumberColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
		emailColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3)); // Double the width
		addressColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3)); // Double the width

		// Create form for adding contacts
		TextField nameField = new TextField();
		nameField.setPromptText("Name");
		TextField phoneNumberField = new TextField();
		phoneNumberField.setPromptText("Phone Number");
		TextField emailField = new TextField();
		emailField.setPromptText("Email");
		TextField addressField = new TextField();
		addressField.setPromptText("Address");

		Button addButton = new Button("Add");
		addButton.setOnAction(e -> {
			String name = nameField.getText();
			String phoneNumber = phoneNumberField.getText();
			String email = emailField.getText();
			String address = addressField.getText();
			contactList.add(new Contact(name, phoneNumber, email, address));
			clearFields(nameField, phoneNumberField, emailField, addressField);
		});

		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> {
			Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
			if (selectedContact != null) {
				contactList.remove(selectedContact);
			}
		});

		HBox formBox = new HBox(10);
		formBox.getChildren().addAll(nameField, phoneNumberField, emailField, addressField, addButton, deleteButton);

		BorderPane root = new BorderPane();
		root.setTop(formBox);
		root.setCenter(tableView);

		Scene scene = new Scene(root, 800, 400); // Adjusted width to accommodate new fields
		primaryStage.setScene(scene);
		primaryStage.setTitle("Address Book");
		primaryStage.show();
	}

	private void clearFields(TextField... fields) {
		for (TextField field : fields) {
			field.clear();
		}
	}

	public static class Contact {
		private String name;
		private String phoneNumber;
		private String email;
		private String address;

		public Contact(String name, String phoneNumber, String email, String address) {
			this.name = name;
			this.phoneNumber = phoneNumber;
			this.email = email;
			this.address = address;
		}

		public String getName() {
			return name;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public String getEmail() {
			return email;
		}

		public String getAddress() {
			return address;
		}
	}
}
