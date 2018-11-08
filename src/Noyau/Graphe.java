package Noyau;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;


public class Graphe  {
	private ArrayList<Noeud> noeuds;
	private int nbNoeuds=0;
	
	public Graphe(){		
		this.noeuds=new ArrayList<Noeud>();
	}
	
	public Graphe(ArrayList<Noeud> noeuds) {
		super();
		this.noeuds = noeuds;
		this.nbNoeuds+=noeuds.size();
	}
	//retourne les noeuds du graphe
	public ArrayList<Noeud> getNoeuds() {
		return this.noeuds;
	}
//ajouter  la liste des noeuds @noeuds au graphe
	public void setNoeuds(ArrayList<Noeud> noeuds) {
		this.noeuds = noeuds;
	}
	//ajouter le noeud @n au graphe
	public void addNoeud(Noeud n){
		this.noeuds.add(n);
		this.nbNoeuds++;
		}
	//supprimer le noeud @noeud du graphe
	public void deleteNoeud(Noeud noeud){
		
		ArrayList<Noeud> voisins=noeud.getVoisins();
		for (int j=0;j<voisins.size();j++){
			voisins.get(j).deleteVoisin( noeud);
		}
		
		
		for(int i=noeud.getNum()+1;i<this.getNoeuds().size();i++){
			this.getNoeud(i).setNum(this.getNoeud(i).getNum()-1);
		}
		this.noeuds.remove(noeud);
		noeud.getStackpane().getChildren().remove(0);
		noeud.getStackpane().getChildren().remove(1);

		this.nbNoeuds--;
		Collections.sort(this.getNoeuds(), new Comparator<Noeud>() {
	        @Override
	        public int compare(Noeud noeud1, Noeud noeud2)
	        {

	            return ((Integer) (noeud1.getNum())).compareTo((Integer)(noeud2.getNum()));
	        }
	    });
		//return old;
	}
	//retourne le nombre de noeuds du graphe
	public int getNbNoeuds(){
		return this.nbNoeuds;
	}
	
	//retourne la le ième noeud du graphe
	public Noeud getNoeud(int i){

		return this.noeuds.get(i);
	}
	//retourne le noeud dont son numéro est @n
	public Noeud getNoeudNum(int n){
		for (int i=0; i<this.getNoeuds().size();i++){
			if (this.getNoeud(i).getNum()==n)
				return this.getNoeud(i);
		}
		return null;
	}
	//restaurer le noeud @noeud après l'avoir supprimer
	public  void restoreNoeud(Noeud noeud){

		for(int i=noeud.getNum();i<this.getNoeuds().size();i++){
			this.getNoeud(i).setNum(this.getNoeud(i).getNum()+1);
		}


		for (int i=0;i<noeud.getVoisins().size();i++){
			this.getNoeudNum(noeud.getVoisin(i).getNum()).getVoisins().add(noeud);
		}
		this.addNoeud(noeud);
		this.nbNoeuds++;

		Collections.sort(this.getNoeuds(), new Comparator<Noeud>() {
	        @Override
	        public int compare(Noeud noeud1, Noeud noeud2)
	        {

	            return ((Integer) (noeud1.getNum())).compareTo((Integer)(noeud2.getNum()));
	        }
	    });
	}
	// le parcours DFS du graphe à partir du noeud @noeud
	public Boolean[] DFS(Noeud noeud){
		//la pile des noeuds visités
		Stack<Noeud> p=new Stack<Noeud>();
		Boolean[] visited=new Boolean[this.nbNoeuds];
		//Au début tous les noeuds ne sont pas visités
		for (int i=0;i<this.nbNoeuds;i++){
			visited[i]=false;
		}
		Noeud n=noeud;
		//empliler le noeud racine
		p.push(n);
		while(!p.empty()){
			//dépiler un noeud
			n=(Noeud) p.pop();
			//si le noeud n'est pas visité
			if (!visited[n.getNum()]){
				visited[n.getNum()]=true; //marquer le noeud comme visité
				for (int i=0;i<n.getVoisins().size();i++){
					if (!visited[n.getVoisin(i).getNum()]){
						//empiler ses voisins
						p.push(n.getVoisin(i));
					}
				}
			}
		}
		return visited;	
	}
	
	//retourne une liste des composantes fortement connexes du graphe
	public ArrayList<ArrayList<Noeud>> getSCC(){
		 ArrayList<ArrayList<Noeud>> SCC =new   ArrayList<ArrayList<Noeud>>(); //contient la liste des composantes fortement connexes
		 ArrayList<Noeud> notVisited=new   ArrayList<Noeud>(); //contient la liste des noeuds non encore visités
		 SCC.add(new ArrayList<Noeud>());
		for (int i=0; i < this.getNoeuds().size();i++){
			notVisited.add(this.getNoeud(i)); //au début tous les noeuds ne sont pas visités
		}
		 Noeud racine=this.getNoeud(0); //noeud racine
		 int nbSCC=0; //nbr de composantes fortement connexes
		 int nbVisited=0; //nbr de noeuds visités
		 racine=notVisited.get(0);

		 while (racine!=null){

			 Boolean[] visited=this.DFS(racine);
			 for (int i=0;i<visited.length;i++){
				 if (visited[i]) nbVisited++;}
	
				 if (this.getNoeuds().size()!=nbVisited) {
					 SCC.add(new ArrayList<Noeud>());};
				 for (int i=0;i<visited.length;i++){
				 if (visited[i]){

				//	 System.out.println(this.getNoeud(i)+"  "+this.getNoeud(i).getVoisins());
				//	 System.out.println("i= "+i +"scc= "+ nbSCC+ "noeud= "+this.getNoeud(i));
					 	
					 		SCC.get(nbSCC).add(this.getNoeud(i));
					 		nbVisited++;
					 		notVisited.remove(this.getNoeud(i));
							//System.out.println("not visisted"+notVisited.size());

				 	}
				 else{

				 }
				 
				 }
			 nbSCC++;

				if(notVisited.size()==0)

					racine=null;
			 else
				 racine=notVisited.get(0);
			 }	
		return SCC;
		
	}
	public ArrayList<Noeud> ptsArticulation(){
		ArrayList <Noeud> noeuds=new ArrayList<Noeud>();
		int nbSCC=this.getSCC().size(); //nombre de composantes connexes initial
		int nbSCCsup; //nombre de composante connexes après la suppression d'un noeud
		Noeud n;
		for (int i=0;i<this.getNoeuds().size();i++){
			n=this.getNoeud(i);

			this.deleteNoeud(this.getNoeud(i));
		//	System.out.println("n"+n);

			nbSCCsup=this.getSCC().size();
		//	System.out.println("nbSCCsup"+i+" est "+nbSCCsup);
			if (nbSCCsup>nbSCC)  noeuds.add(n);
			this.restoreNoeud(n);
		//	System.out.println("after restore    "+this.getNoeuds());
		}
		return noeuds;
		
	}

}
