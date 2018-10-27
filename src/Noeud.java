import java.util.ArrayList;


public class Noeud implements Cloneable{
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return (Noeud)super.clone();
	}

	private	int num;
	private ArrayList<Noeud> voisins =new ArrayList<Noeud>();
	private static int nbNoeuds=0;
	
	
	public Noeud(int num) {
		this.num=num;
		nbNoeuds++;
	}
	
	public Noeud(int num,ArrayList<Noeud> voisins) {
		this.num = num;
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
