package com.plural.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Shakespeare {
	public static void main(String[] args) {

		try(Stream<String> shakespeare=Files.lines(Paths.get("/Users/administrator/eclipse-workspace/Sample/src/thirdTut/java8Features/words.shakespeare.txt"));
				Stream<String> ospd=Files.lines(Paths.get("/Users/administrator/eclipse-workspace/Sample/src/thirdTut/java8Features/ospd.txt"));	
				){


			Set<String> shakespeareWords=shakespeare.map(w->w.toLowerCase()).collect(Collectors.toSet());
			Set<String> ospdWords=ospd.map(w->w.toLowerCase()).collect(Collectors.toSet());
			//System.out.println(shakespeareWords.size());
			//System.out.println(ospdWords.size());

			int scrableEnScore[]= {
				/*  a, b, c, d, e, f, g, h, i ,j ,k ,l ,m, n, o, p, q, r, s, t, u, v, w, x, y,  z*/		
					1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10	
			};

			Function<String,Integer> intScore=
					wd->{
						return wd.chars().map(mapper->{

							//System.out.println(mapper+ "---"+(char)mapper+ "---"+(mapper-'a')+"--");
							return scrableEnScore[mapper-'a'];
						}
								).sum();

					};
					
					
					ToIntFunction<String> insCoreInt=	wd->{
						return wd.chars().map(mapper->{

							//System.out.println(mapper+ "---"+(mapper-'a')+"--");
							return scrableEnScore[mapper-'a'];
						}
								).sum();

					};
					//System.out.println(intScore.apply("hello"));
					//System.out.println(insCoreInt.applyAsInt("hello"));
					
					//String kk=shakespeareWords.stream().filter(ospdWords::contains).max(Comparator.comparingInt(insCoreInt)).get();
					//String kk=shakespeareWords.stream().filter(ospdWords::contains).max(Comparator.comparing(intScore)).get();
					//System.out.println("Best Word #"+kk);
					
					
					//IntSummaryStatistics intSummStatics=shakespeareWords.stream().filter(ospdWords::contains).mapToInt(insCoreInt).summaryStatistics();
					//System.out.println("Best intSummStatics #"+intSummStatics);
					
					
					Map<Integer, List<String>>  historicalWords=shakespeareWords.stream().collect(Collectors.groupingBy(intScore));
					
					//historicalWords.entrySet().stream().sorted(Comparator.comparing(ent->-ent.getKey())).limit(3).forEach(entry->System.out.println("Key " +entry.getKey()+"-----val"+entry.getValue()));
					
					Function<String,Map<Integer,Long>> histoWord=word->word.chars().boxed().collect(Collectors.groupingBy(
							
							letter->{/*System.out.println(letter); */return letter;},Collectors.counting())
							
							);
					
					//histoWord.apply("whizzing").entrySet().stream().forEach(entry->System.out.println("Key " +entry.getKey()+"-----val"+entry.getValue()));
					int scrableEnDist[]= {
							/*  a, b, c, d, e, f, g, h, i ,j ,k ,l ,m, n, o, p, q, r, s, t, u, v, w, x, y,  z*/		
								9, 2, 2, 1, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1	
						};
					
					Function<String,Long> nBlanks=word->histoWord.apply(word).entrySet().stream().mapToLong(entry->{
			/*			System.out.println(" Key " +entry.getKey()+"-----val "+entry.getValue());
						System.out.println(" Key11 " +entry.getValue()+"-----val11 "+scrableEnDist[entry.getKey()-'a']);
						System.out.println((entry.getValue()-scrableEnDist[entry.getKey()-'a']));*/
						return Long.max(entry.getValue()-(long)scrableEnDist[entry.getKey()-'a'],0L);
								}).sum();
					
					//System.out.println("whizzing word "+nBlanks.apply("whizzing"));
					
					
					
					
					Function<String,Integer> intScore2=word->histoWord.apply(word).entrySet().stream().mapToInt(entry->{
					/*	System.out.println(" Key " +entry.getKey()+"-----val "+entry.getValue());
						System.out.println(" Key11 " +entry.getValue()+"-----val11 "+scrableEnDist[entry.getKey()-'a']);
						System.out.println((entry.getValue()-scrableEnDist[entry.getKey()-'a']));*/
						return scrableEnScore[entry.getKey()-'a']*Integer.min(entry.getValue().intValue(),scrableEnDist[entry.getKey()-'a']);
								}).sum();
					
					
					System.out.println(intScore.apply("whizzing"));
					System.out.println(intScore2.apply("whizzing"));
					
					/*Map<Integer,List<String>> histowordByScore=*/shakespeareWords.stream().filter(ospdWords::contains).filter(word->nBlanks.apply(word)<2)
							.collect(Collectors.groupingBy(score->score,Collectors.counting())).entrySet().stream().
							sorted(Comparator.comparing(entry -> entry.getKey())).limit(3).
							forEach(entry->System.out.println("Key " +entry.getKey()+"-----val"+entry.getValue()));
							
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
