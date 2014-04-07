
import java.io.*;

public class ReadFile {
	public static void main(String[] args){
		String fichier ="1.txt";
		String [] map = new String [100];
		int i = 0;  
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				map[i] = ligne; 
				i++;
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}

	for(int j=0; j<i; j++)
	{
		System.out.println(map[j]); 
	}
}

}
