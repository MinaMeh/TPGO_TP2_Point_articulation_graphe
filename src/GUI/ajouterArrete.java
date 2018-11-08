package GUI;

import Noyau.Arrete;
import Noyau.Graphe;
import Noyau.Noeud;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ajouterArrete  {
	
	public static void display(Graphe graphe, Pane pane) {
		Stage window= new Stage();
		Label n1= new Label("Noeud 1");
		TextField n1_num= new TextField();
		n1_num.setMaxWidth(60.0);
		Label n2= new Label("Noeud 2");
		TextField n2_num= new TextField();
		n2_num.setMaxWidth(60.0);
		Button add= new Button();
		add.setText("Ajouter");
		Button quit= new Button();
		quit.setText("Quitter");
		quit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            	window.close();                
        }});
		add.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            	Noeud noeud1=graphe.getNoeud(Integer.parseInt(n1_num.getText().toString()));
            	Noeud noeud2=graphe.getNoeud(Integer.parseInt(n2_num.getText().toString()));
                Arrete arrete=new Arrete(noeud1,noeud2);
                arrete.getLine().toBack();
               for (int i=0; i<graphe.getNoeuds().size();i++){
            	   graphe.getNoeud(i).getStackpane().toFront();
            	   arrete.getLine().toBack();
               }
                pane.getChildren().add(arrete.getLine());
                System.out.println(graphe.getNoeuds());
                
        }});
		VBox root= new VBox(10);
		root.getChildren().addAll(n1,n1_num,n2,n2_num,add,quit);
		root.setAlignment(Pos.CENTER);
		Scene scene =new Scene(root,200,200);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
}
