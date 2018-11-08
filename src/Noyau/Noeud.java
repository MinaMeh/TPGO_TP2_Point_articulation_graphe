package Noyau;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;


public class Noeud {
	private	int num;
	private ArrayList<Noeud> voisins =new ArrayList<Noeud>();
	private StackPane stackpane;
	
	public StackPane getStackpane() {
		return stackpane;
	}

	public void setStackpane(StackPane stackpane) {
		this.stackpane = stackpane;
	}

	private static int nbNoeuds=0;
	
	
	public Noeud(int x, int y, int radius,Color fill, Pane pane) {
		this.num=nbNoeuds;
		int i=this.num;
		this.stackpane=new StackPane();
		Circle circle=new Circle(x+100*i,y,radius,fill);
		Text t=new Text(Integer.toString(i));
		t.setFill(Color.WHITE);
		t.setFont(Font.font(30));

		this.stackpane.getChildren().addAll(circle,t);
		this.stackpane.setLayoutX(100+i*100  %800);
		this.stackpane.setLayoutY(100*(i*100 /800));

		final Delta drag= new Delta();
		this.stackpane.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        drag.x = (stackpane.getLayoutX() - mouseEvent.getSceneX());
                        drag.y = (stackpane.getLayoutY() - mouseEvent.getSceneY());
                }});
		this.stackpane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stackpane.setLayoutX(mouseEvent.getSceneX() + drag.x);
                stackpane.setLayoutY(mouseEvent.getSceneY() + drag.y);
            }
        });
		pane.getChildren().add(this.stackpane);
		nbNoeuds++;
	}
	
	public Noeud(ArrayList<Noeud> voisins) {
		this.num = nbNoeuds;
		nbNoeuds++;
		for (int i=0;i<voisins.size();i++){
			this.addVoisin(voisins.get(i));
		}	
		}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public ArrayList<Noeud> getVoisins() {
		return voisins;
	}
	public void setVoisins(ArrayList<Noeud> voisins) {
		this.voisins = voisins;
	}
	public void addVoisin(Noeud noeud){
		this.voisins.add(noeud);
		noeud.getVoisins().add(this);
	}
	public Noeud getVoisin(int i){
		return this.voisins.get(i);
	}	
	public void showVoisins(){
		for(int i=0; i<this.voisins.size();i++){
			System.out.println(this.voisins.get(i).getNum());
		}
	}
	public void deleteVoisin(Noeud noeud){
		this.voisins.remove(noeud);
	}
	public int getNbNoeuds(){
		return nbNoeuds;
	}
	
	@Override
	public String toString() {
		String voisins="";
		for (int i =0; i<this.voisins.size();i++){
			voisins+=this.voisins.get(i).getNum();
			voisins+=",";
		}
		return "Noeud [num=" + num + ", voisins="  + voisins +"]";
	}

	
	
}

