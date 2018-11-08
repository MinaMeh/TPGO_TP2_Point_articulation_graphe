package Noyau;

import javafx.beans.binding.Bindings;
import javafx.geometry.Bounds;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class Arrete {
	private int num;
	private Noeud noeud1;
	private Noeud noeud2;
	private Line line;
	
	public Arrete(Noeud noeud1, Noeud noeud2) {
		super();
		this.noeud1 = noeud1;
		this.noeud2 = noeud2;
		noeud1.addVoisin(noeud2);
		this.setLine(new Line());
		this.line.setStroke(Color.MEDIUMSLATEBLUE);
		this.line.setStrokeWidth(3.0);
		StackPane n1=noeud1.getStackpane();
		StackPane n2= noeud2.getStackpane();
		getLine().startXProperty().bind(Bindings.createDoubleBinding(() -> {
                Bounds b = n1.getBoundsInParent();
                return b.getMinX() + b.getWidth() / 2+1;
            }, n1.boundsInParentProperty()));

        getLine().startYProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = n1.getBoundsInParent();
            return b.getMinY() + b.getHeight() / 2 +1;
        }, n1.boundsInParentProperty()));

        getLine().endXProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = n2.getBoundsInParent();
            return b.getMinX() + b.getWidth() / 2-1 ;
        }, n2.boundsInParentProperty()));

        getLine().endYProperty().bind(Bindings.createDoubleBinding(() -> {
            Bounds b = n2.getBoundsInParent();
            return b.getMinY() + b.getHeight() / 2 -1;
        }, n2.boundsInParentProperty()));
	//n2.toFront();
        System.out.println("n1= "+noeud1+" n2= "+noeud2);
	
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Noeud get1() {
		return noeud1;
	}
	public void setNoeud1(Noeud noeudDepart) {
		this.noeud1 = noeudDepart;
	}
	public Noeud getNoeud2() {
		return noeud2;
	}
	public void setNoeud2(Noeud noeudArrive) {
		this.noeud2 = noeudArrive;
	}
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	
	

}
