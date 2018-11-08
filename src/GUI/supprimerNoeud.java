package GUI;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Noyau.Arrete;
import Noyau.Graphe;
import Noyau.Noeud;

public class supprimerNoeud {
	public static void display(Graphe graphe){
		Stage window= new Stage();
		Label n= new Label("Noeud ");
		TextField n_num= new TextField();
		n_num.setMaxWidth(60.0);
		Button del= new Button();
		del.setText("Supprimer");
		Button quit= new Button();
		quit.setText("Quitter");
		quit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            	window.close();                
        }});
		del.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            	Noeud noeud=graphe.getNoeud(Integer.parseInt(n_num.getText().toString()));
                graphe.deleteNoeud(noeud);
                
        }});
		VBox root= new VBox(10);
		root.getChildren().addAll(n,n_num,del,quit);
		root.setAlignment(Pos.CENTER);
		Scene scene =new Scene(root,200,200);
		window.setScene(scene);
		window.showAndWait();
		}
}
