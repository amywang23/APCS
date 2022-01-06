// Name: Amy Wang
// Date: 3/13/2021

import java.io.*;
import java.util.*;

public class Dictionary
{
	public static void main(String[] args) 
	{
		Scanner infile = null;
		try
		{
			infile = new Scanner(new File("spanglish.txt"));
			System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
		}
		catch(Exception e)
		{
		}

		Map<String, Set<String>> eng2spn = makeDictionary( infile );
		System.out.println("ENGLISH TO SPANISH");
		display(eng2spn);

		Map<String, Set<String>> spn2eng = reverse(eng2spn);
		System.out.println("SPANISH TO ENGLISH");
		display(spn2eng);
	}

	public static Map<String, Set<String>> makeDictionary(Scanner infile)
	{
		Map<String, Set<String>> m = new TreeMap<String, Set<String>>();
		while(infile.hasNextLine())
		{
			String engWord = infile.nextLine();
			String spanWord = "";
			if(infile.hasNextLine()) {
				spanWord = infile.nextLine();
			}
			add(m, engWord, spanWord);
		}
		return m;
	}

	public static void add(Map<String, Set<String>> dictionary, String word, String translation)
	{ 
		if(dictionary.containsKey(word)) {
			dictionary.get(word).add(translation);
		} else {
			Set<String> set = new TreeSet<String>();
			set.add(translation);
			dictionary.put(word, set);
		}
	}

	public static void display(Map<String, Set<String>> m)
	{
		for (Map.Entry<String,Set<String>> entry : m.entrySet()) {
			System.out.println("\t" + entry.getKey() + " " + entry.getValue());
		}
	}

	public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
	{
		Map<String, Set<String>> map = new TreeMap<String, Set<String>>();
		for(Map.Entry<String,Set<String>> entry: dictionary.entrySet()) {
			String eng = entry.getKey();
			Set<String> spanSet = entry.getValue();
			for(String span : spanSet) {
				if(map.containsKey(span)) {
					map.get(span).add(eng);
				} else {
					Set<String> set = new TreeSet<String>();
					set.add(eng);
					map.put(span, set);
				}
			}
			
		}
		return map;
	}
}


/********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
 *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

 **********************/