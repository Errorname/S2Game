
import java.io.*;

/**
* classe qui définit une map
*
* @author Thomas PICARD 
* @version 1.0
*/

public class Map
{
		private String [] file; 
		private Tile [][] mapTile;
		private int [][] mapInt; 
		private String [][] props;   
		private int largeurTile;
		private int longueurTile; 
		private int nbTilesX;
		private int nbTilesY;
		private int nbTilesLargeurMonde;
		private int nbTilesLongueurMonde; 
				


	public Map(String lAdresse)
	{
		file = new String[100];
		loadMap(lAdresse);
		parseFile();
		initTiles(); 
	}

	
	private void loadMap(String lAdresse)
	{
		String fichier =lAdresse;
		int i = 0;  
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				file[i] = ligne; 
				i++;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	private void parseFile() 
	{
		nbTilesLongueurMonde = Integer.parseInt(file[2]); 
		nbTilesLargeurMonde = Integer.parseInt(file[4]);
		nbTilesY = Integer.parseInt(file[6]);
		nbTilesX = Integer.parseInt(file[8]);
		longueurTile = Integer.parseInt(file[10]);
		largeurTile = Integer.parseInt(file[12]);   

		props = new String[nbTilesY*nbTilesX][4];
		
		for(int i=15; i<(nbTilesY*nbTilesX)+15; i++)
		{
			String [] prop = file[i].split("[\\s\\s\\s]");
			for(int j=0; j<4; j++)
			{
				props[i-15][j] = prop[j]; 
			}
		}
	
		mapInt = new int [nbTilesLargeurMonde][nbTilesLongueurMonde];
		
		for(int i=16+(nbTilesY*nbTilesX); i<16+(nbTilesY*nbTilesX)+nbTilesLargeurMonde; i++) 
		{
			String separe = "["; 			
			for( int tailleLigne =0; tailleLigne < nbTilesLongueurMonde; tailleLigne++)
			{
				separe = separe + "\\s";
			} 
			separe = separe + "]"; 
			String [] tile = file[i].split(separe); 
				for(int q=0; q<nbTilesLongueurMonde; q++) 
				{
					mapInt[i-(16+(nbTilesY*nbTilesX))][q] = Integer.parseInt(tile[q]); 
				}
		}
		
		for(int b=0;b<nbTilesLargeurMonde;b++) 
		{
			for(int c=0; c<nbTilesLongueurMonde; c++)
			{
				System.out.print(mapInt[b][c]+" ");
			}
			System.out.println();
		}
	}

	private void initTiles()
	{
		mapTile = new Tile[nbTilesLongueurMonde][nbTilesLargeurMonde]; 

		for(int i=0; i<nbTilesLargeurMonde; i++)
		{
			for(int j=0; j<nbTilesLongueurMonde; j++) 
			{
				boolean breakable = false; 
				boolean solid = false; 

				if(props[mapInt[i][j]][2] =="plein")
				{
					solid = true; 
				}

				if(props[mapInt[i][j]][3] =="cassable")
				{
					breakable = true; 
				}				
				
				mapTile[j][i] = new Tile (mapInt[i][j], solid, breakable);  
			} 
		}

	}
}
