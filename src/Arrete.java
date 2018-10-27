
public class Arrete {
	private int num;
	private Noeud noeud1;
	private Noeud noeud2;
	
	public Arrete(Noeud noeud1, Noeud noeud2) {
		super();
		this.noeud1 = noeud1;
		this.noeud2 = noeud2;
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
	
	

}
