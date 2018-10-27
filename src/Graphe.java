import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;


public class Graphe  implements Cloneable{
	private ArrayList<Noeud> noeuds;
	private int nbNoeuds=0;
	
	public Graphe(){
		
	}
	public Graphe(ArrayList<Noeud> noeuds) {
		super();
		this.noeuds = noeuds;
		this.nbNoeuds+=noeuds.size();
	}
	public Graphe clone(){
		try {
			ArrayList<ArrayList<Noeud>> listVoisins = new ArrayList< ArrayList<Noeud>>();
			
   		for(int i =0 ; i<this.nbNoeuds;i++) {
			System.out.println("i= "+i+"valeur"+this.getNoeud(i));
			listVoisins.add(new ArrayList<Noeud>());
   				Noeud old=this.getNoeud(i);
   				Integer num=new Integer(this.getNoeud(i).getNum());
   				Noeud noeud=(Noeud) this.getNoeud(i).clone();
   			
   				for (int j=0;j<old.getVoisins().size();j++){
   				//	System.out.println("j= "+j+"valeur"+this.getNoeud(1));

   					listVoisins.get(i).add(old.getVoisin(j));
   				}
   				noeud.setVoisins(listVoisins.get(i));
   				System.out.println("cloned   "+noeud);
   				noeuds.add(noeud);
			    }
		Graphe graphe=  new Graphe(noeuds);

			return graphe;
			}
			catch (CloneNotSupportedException e){
				throw new InternalError();
			}
	}

	public ArrayList<Noeud> getNoeuds() {
		return noeuds;
	}

	public void setNoeuds(ArrayList<Noeud> noeuds) {
		this.noeuds = noeuds;
	}
	
	public void addNoeud(Noeud n){
		this.noeuds.add(n);
		this.nbNoeuds++;
		}
	public void deleteNoeud(Noeud noeud){
		/*Graphe old=(Graphe)this.clone();
		System.out.println("before "+old.getNoeuds());*/

		ArrayList<Noeud> voisins=noeud.getVoisins();
		for (int j=0;j<voisins.size();j++){
			voisins.get(j).deleteVoisin( noeud);
		}
		
		
		for(int i=noeud.getNum()+1;i<nbNoeuds;i++){
			this.getNoeud(i).setNum(this.getNoeud(i).getNum()-1);
		}
		this.noeuds.remove(noeud);
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
	public int getNbNoeuds(){
		return this.nbNoeuds;
	}
	public Noeud getNoeud(int i){

		return this.noeuds.get(i);
	}
	public Noeud getNoeudNum(int n){
		for (int i=0; i<this.getNoeuds().size();i++){
			if (this.getNoeud(i).getNum()==n)
				return this.getNoeud(i);
		}
		return null;
	}
	public  void restoreNoeud(Noeud noeud){

		for(int i=noeud.getNum();i<nbNoeuds;i++){
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
	
	public Boolean[] DFS(Noeud noeud){

		Stack<Noeud> p=new Stack<Noeud>();
		Boolean[] visited=new Boolean[this.nbNoeuds];
		for (int i=0;i<this.nbNoeuds;i++){
			visited[i]=false;
		}
		Noeud n=noeud;
		p.push(n);
		while(!p.empty()){
			n=(Noeud) p.pop();

			//System.out.println("le noeud en cours"+n.getNum()+"valeur de visited"+visited[n.getNum()]);

			if (!visited[n.getNum()]){

				visited[n.getNum()]=true;

				for (int i=0;i<n.getVoisins().size();i++){

					if (!visited[n.getVoisin(i).getNum()]){
						p.push(n.getVoisin(i));

					}
				}
			}
		}
		
		return visited;	
	}
	public ArrayList<ArrayList<Noeud>> getSCC(){
		 ArrayList<ArrayList<Noeud>> SCC =new   ArrayList<ArrayList<Noeud>>();
		 ArrayList<Noeud> notVisited=new   ArrayList<Noeud>();
		for (int i=0; i < this.getNoeuds().size();i++){
			notVisited.add(this.getNoeud(i));
		}
		
		 Noeud racine=this.getNoeud(0); //noeud racine
		 int nbSCC=0; //nbr de composantes fortement connexes
		 int nbVisited=0; //nbr de noeuds visités
		 racine=notVisited.get(0);

		 while (racine!=null){

			 Boolean[] visited=this.DFS(racine);
			 for (int i=0;i<visited.length;i++){
				 if (visited[i]) nbVisited++;}
			 		
				 if (this.getNoeuds().size()!=nbVisited) SCC.add(new ArrayList<Noeud>());
				 for (int i=0;i<visited.length;i++){
				 if (visited[i]){

					// System.out.println(this.getNoeud(i)+"  "+this.getNoeud(i).getVoisins());
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

}
