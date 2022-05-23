package dizionario;
import java.util.*;
import java.io.*;
public class Dizionario {
//List<String> dizionario = new LinkedList<String>();
List<String> dizionario = new LinkedList<String>();
 	
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
			RichWord r = new RichWord (v[i]);
			text.add(r);
		}
		
		for(RichWord w : text) {
			if(dizionario.contains(w.getWord() )) 
				w.setCorrect();
		}
		return text;
	}
	
	public List<RichWord> spellCheclDicotomic(String input){
		List<RichWord> result = new LinkedList<RichWord>();
		
		//algoritmo ricerca ricotomica
		String v[] = input.split(" ");
		for(String s : v) {
			result.add(new RichWord(s));
		}
		
		for(RichWord r : result) {
			
			int min = 0;
			int max = dizionario.size()-1; //perché parte da 1
			boolean trovato = false;
			
			while(r.isCorrect()==false && min<=max) {
				int i = (min+max)/2;
				
				if(dizionario.get(i).compareTo(r.getWord())==0) {
					r.setCorrect();
				}else if(dizionario.get(i).compareTo(r.getWord())<0){
					min = i+1;
				}else if(dizionario.get(i).compareTo(r.getWord())>0)
					max = i-1;		
			}
			
		}
		return result;
		
	}
	
	
	
	
	
	/*
	public List<RichWord> spellCheclDicotomic(String input){
		List<RichWord> text = new LinkedList<RichWord>();
		
		String v[] = input.split(" ");
		
		for(int i=0; i<v.length; i++) {
			RichWord r = new RichWord (v[i]);
			text.add(r);
		}
	
		for(RichWord r : text) {
			//faccio il controllo per ogni parola del testo in input
			boolean trovato = false;
			int min = 1;
			int max = dizionario.size();
			int i = (int) max / 2;
			int minPrec = 1;
			int maxPrec = dizionario.size();
			//int ultimaDim = dizionario.size();
			while(trovato == false && i>=1 && (i>=min && i<=max)) { //controllo che i = l'indice in cui farò il controllo sia almeno 1, perché se
				//è più picccolo di 1 vuol dire che ho finito di scorrere per tutto il dizioanrio e la parola non è corretta
				// l'indice i deve essere dentro il range di min e max .
				
				i = (int) max / 2;
				if(dizionario.get(i).equals(r.getWord())) {
					r.setCorrect();
					trovato = true ; //ESCO DAL LOOP ELEMENTO TROVATO
					break;
				}else {
					//da qui devo capire se è nella prima o nella seconda metà del dizionario
					if(dizionario.get(i).compareTo(r.getWord())<0) {
						//La parola precede quella dell'indice, quindi devo prendere la prima metà dell'intervallo
						min = minPrec;
						max = i;
						maxPrec = i;
					}else { //prendo la seconda parte dell'intervallo
						min = i;
						max = maxPrec;
						minPrec = 1;
					}
					//da qui il loop ricomincia ma la ricerca avverrà nell'intervallo più ristretto
				}
			}
		}
		
		//FARE PROVA SCRIVENDO LUCIC O LUCICCHIO
		
		return text;
	}*/
	
	
	
}
