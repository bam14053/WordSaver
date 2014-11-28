package bamgbala.abi.wordsaverv2;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by abi on 24.11.14.
 */
public class FileHandler implements Serializable{
	public ArrayList<String> words_eng = new ArrayList<String>();
	public ArrayList<String> words_arab = new ArrayList<String>();
    public static String file_arabic = Environment.getExternalStorageDirectory()+"/WordSaver/words_arabic.txt";
    public static String file_english = Environment.getExternalStorageDirectory()+"/WordSaver/words_english.txt";

	public FileHandler() throws Exception{
		BufferedReader eng = new BufferedReader(new FileReader(file_english));
		BufferedReader arabic = new BufferedReader(new FileReader(file_arabic));
		while(eng.ready() && arabic.ready()){
			words_arab.add(arabic.readLine());
			words_eng.add(eng.readLine());
		}
		eng.close();
		arabic.close();
		if(words_eng.size() != words_arab.size())
			throw new Exception("Arabic words and English words document do not match");
	}

	public int shuffleWords(){
		ArrayList<String> temp = new ArrayList<String>();
}     		for(int i = 0; i < words_arab.size(); i++)
			temp.add(words_eng.get(i)+"-"+words_arab.get(i));
		Collections.shuffle(temp);
		words_arab.clear();
		words_eng.clear();
		for(String word: temp){
			words_eng.add(word.split("-")[0]);
			words_arab.add(word.split("-")[1]);
		}
		return temp.size();
	}

	public String getEnglishWord(int i) throws ArrayIndexOutOfBoundsException{
		return words_eng.get(i);
	}

	public String getArabicWord(int i)throws ArrayIndexOutOfBoundsException{
		return words_arab.get(i);
	}