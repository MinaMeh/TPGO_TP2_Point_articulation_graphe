import java.util.ArrayList;


public class main {

	public static void  main(String[] args) throws CloneNotSupportedException {
		
		Noeud n0=new Noeud	(0);
		Noeud n1=new Noeud	(1);
		Noeud n2=new Noeud	(2);
		n0.addVoisin(n1);
		n0.addVoisin(n2);
		ArrayList <Noeud> voisins2=new ArrayList<Noeud>();
		voisins2.add(n0);
		voisins2.add(n2);
		
		Noeud n3=new Noeud(3,voisins2);
		//n4.showVoisins();
		//n4.deleteVoisin(0);
		//n4.showVoisins();
		ArrayList <Noeud> noeuds=new ArrayList<Noeud>();
		noeuds.add(n0);
		noeuds.add(n1);
		noeuds.add(n2);
		noeuds.add(n3);
		Noeud n4=new Noeud(4);
		noeuds.add(n4);
		Noeud n5=new Noeud(5);
		n4.addVoisin(n5);
		noeuds.add(n5);
		Noeud n6=new Noeud(6);
	/*	noeuds.add(n6);
		Noeud n7=new Noeud();
		n7.addVoisin(n6);
		noeuds.add(n7);*/
		Graphe graphe=new Graphe(noeuds);
		//System.out.printn(graphe.getNoeud(4));
System.out.println(graphe.getNoeuds());
System.out.println("SCC=   "+graphe.getSCC());

graphe.deleteNoeud(graphe.getNoeud(2));
System.out.println(graphe.getNoeuds());
System.out.println("SCC=   "+graphe.getSCC());

graphe.restoreNoeud(n2);
System.out.println(graphe.getNoeuds());
System.out.println("SCC=    "+graphe.getSCC());


	 /*   System.out.println(graphe.getNbNoeuds());
		System.out.println(graphe.getNoeuds());

		old=graphe.deleteNoeud(graphe.getNoeud(2));
		System.out.println("old= "+old.getNoeuds()+ "taille="+old.getNoeuds().size());

		System.out.println(graphe.getNbNoeuds());
		System.out.println(graphe.getNoeuds());
		//		System.out.println("old= "+old);
		System.out.println(graphe.getNbNoeuds());
		System.out.println(graphe.getNoeuds());
		
		System.out.println(graphe.getSCC());
	/*	for (int i=0; i<graphe.getSCC().size();i++){
			System.out.println(graphe.getSCC().get(i).size());

		}*/

	}
	

}
