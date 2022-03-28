package dizionario;
import java.util.*;
import java.io.*;
public class Dizionario {
//List<String> dizionario = new LinkedList<String>();
Set<String> dizionario = new HashSet<String>();
 	
	public void loadDictionary(String language) {
		//lettura da file impostazione lingua
		
		try {
			FileReader fr;
			if(language.compareTo("English")==0) 
				fr = new FileReader("src/main/resources/English.txt");
			else 
				fr = new FileReader("src/main/resources/Italian.txt");
			
			BufferedReader br = new BufferedReader(fr);
			
			String w;
			
			while((w = br.readLine())!=null) {
				dizionario.add(w);
			}
			br.close();
		}catch(IOException e){
			System.out.println("ERRORE LETTURA FILE");
		}
	
	}

	public List<RichWord> spellCheck (String input){
		List<RichWord> text = new LinkedList<RichWord>();
		
		//trasformo stringa in lista
		String []v = input.split(" ");
		for(int i=0; i<v.length; i++) {
			//controllo spelling di una parola
			RichWord r = new RichWord (v[i]);
			text.add(r);
		}
		
		for(RichWord w : text) {
			if(dizionario.contains(w)) 
				w.correct = true;
		}
		
		return text;
	}
	
	
}
