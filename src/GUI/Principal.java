package GUI;

import java.util.ArrayList;

import Noyau.Noeud;
import Noyau.Delta;
import Noyau.Arrete;
import Noyau.Graphe;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Principal  extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
			Graphe graphe= new Graphe();
			VBox root =new VBox(20);
			Image imageAdd = new Image(getClass().getResourceAsStream("images/add.png"));
			Button add_noeud=new Button("Ajouter un noeud", new ImageView(imageAdd));
		    add_noeud.setContentDisplay(ContentDisplay.RIGHT);
			Image imagedelete = new Image(getClass().getResourceAsStream("images/delete.png"));
			Button del_noeud=new Button("Supprimer un noeud",new ImageView(imagedelete));
		    del_noeud.setContentDisplay(ContentDisplay.RIGHT);
		   
			Button add_arrete=new Button("Ajouter une arrete",new ImageView(imageAdd));
		    add_arrete.setContentDisplay(ContentDisplay.RIGHT);
			Image imageCalcul = new Image(getClass().getResourceAsStream("images/calcul.png"));
			Button art_pnt=new Button("Trouver les points d'articulation",new ImageView(imageCalcul));
		    art_pnt.setContentDisplay(ContentDisplay.RIGHT);
		    Button new_graph=new Button("Nouveau graphe",new ImageView(imagedelete));
		    new_graph.setContentDisplay(ContentDisplay.RIGHT);
			HBox boutons=new HBox(25);
			boutons.setAlignment(Pos.CENTER);
			HBox.setMargin(add_noeud, new Insets(20,0,20,20));
				
			boutons.getChildren().addAll(add_noeud,del_noeud,add_arrete,art_pnt,new_graph);
			Pane pane=new Pane();
		//	pane.setHgap(20);
		//	pane.setVgap(20);
			root.getChildren().addAll(boutons,pane);
			add_noeud.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent e) ->{
				Noeud n=new Noeud(200,30,40,Color.MEDIUMSLATEBLUE,pane);
				graphe.addNoeud(n);
			
			//	System.out.println(graphe.getNoeuds());
			
							});;
			add_arrete.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        ajouterArrete.display(graphe,pane);
                }});
			art_pnt.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                    	   for (int i=0; i<graphe.getNoeuds().size();i++){
                    		   graphe.getNoeud(i).getStackpane().toFront();
                           	((Shape) graphe.getNoeuds().get(i).getStackpane().getChildren().get(0)).setFill(Color.MEDIUMSLATEBLUE);
                           }
                       ArrayList<Noeud> articulation=graphe.ptsArticulation();
                        for (int i=0; i<articulation.size();i++){
                        	((Shape) articulation.get(i).getStackpane().getChildren().get(0)).setFill(Color.BROWN);
                        	( (Shape) articulation.get(i).getStackpane().getChildren().get(1)).setFill(Color.BLACK);

                        }
                }});
			del_noeud.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                    		supprimerNoeud.display(graphe);
                    }});
			Scene scene=new Scene (root,1000,500);
	
			scene.getStylesheets().add("style.css");
			primaryStage.setScene(scene);
			primaryStage.setTitle("points d'articulation");
			primaryStage.show();
	}
	public static void main(String[] args){
		launch(args);
	}
}
