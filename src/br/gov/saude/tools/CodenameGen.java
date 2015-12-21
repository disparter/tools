package br.gov.saude.tools;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.Random;

/**
 * All credits to SISREG Team 2014
 * If you are in Linux use the original .sh generator
 */
public class CodenameGen 
{
	
	private static ArrayList<String> getNames(String filename)
	{
		BufferedReader reader = null;
		ArrayList<String> names = new ArrayList<String>();
		
		try 
		{
			reader = new BufferedReader( new FileReader(filename) );
			
			String newLine;
			while ( (newLine = reader.readLine() ) != null )
			{
				names.add(newLine);
			}			
			
			if (reader != null)
			{
				reader.close();
			}
			
		}
		catch (IOException error)
		{
			System.err.println(error);
		}
		
		return names;
		
	}	
	
	public static void main(String[] args)
	{
		final String fileWordListAdjective = "eng_adjectives.txt";
		final String fileWordListNoun= "eng_nouns.txt";
		final int defaultNumberOfLines = 20;
		final Random random = new Random();
		
		int numberOfLines = 0;
		
		try
		{
			numberOfLines = Integer.parseInt(args[0]);	
		}
		catch(NumberFormatException error)
		{
			numberOfLines = defaultNumberOfLines;
			System.err.println("Invalid Argument. Only argument number are permitted. Default " + numberOfLines);
		}
		catch(Exception error)
		{
			numberOfLines = defaultNumberOfLines;
			System.err.println("Number of names not informed! Default " + numberOfLines);
		}
		
		ArrayList<String> nouns = getNames(fileWordListAdjective);
		ArrayList<String> adjectives = getNames(fileWordListNoun);
		
		for(int i = 0; i < numberOfLines; i++ )
		{
			int min = 1;
			int adjectiveMax = adjectives.size() - 1;
			int nounMax = nouns.size() - 1;
			
			int adjectiveIndex = random.nextInt((adjectiveMax - min) + 1) + min;
			int nounIndex = random.nextInt((nounMax - min) + 1) + min;
			
			String adjective = adjectives.get(adjectiveIndex).substring(0, 1).toUpperCase() + adjectives.get(adjectiveIndex).substring(1);
			String noun = nouns.get(nounIndex).substring(0, 1).toUpperCase() + nouns.get(nounIndex).substring(1);
			
			System.out.println( adjective + noun );
		}
	}
}