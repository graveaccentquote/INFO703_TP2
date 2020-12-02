package fr.usmb.m1isc.compilation.tp;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import fr.usmb.m1isc.compilation.tp.Tree;
import fr.usmb.m1isc.compilation.tp.LexicalAnalyzer;
import fr.usmb.m1isc.compilation.tp.parser;
import java_cup.runtime.Symbol;

public class Main {

	public static void main(String[] args) throws Exception  {
		LexicalAnalyzer lexer;
        if (args.length > 0)
            lexer = new LexicalAnalyzer(new FileReader(args[0]));
        else {
        	Reader in0 = new StringReader("let prixTtc =  prixHt * 119 / 100;\r\n"
        			+ "prixTtc + 100.");

        	lexer = new LexicalAnalyzer(in0);
            //lexer = new LexicalAnalyzer(new InputStreamReader(System.in));
        }
        @SuppressWarnings("deprecation")
        parser p = new parser(lexer);
        Symbol res = p.parse();
        Tree a = (Tree)res.value;
        a.toString();
	}

}
