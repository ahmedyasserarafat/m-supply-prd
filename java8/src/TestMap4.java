import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TestMap4 {

    public static void main(String[] args) {
       Map<Integer, Integer> mapd = new HashMap<Integer, Integer>();
        mapd.put(1, 12);
        mapd.put(2, 2);
        mapd.put(3, 6);
        
        Map<Integer, Integer> mapd1 = new HashMap<Integer, Integer>();
        mapd1.put(1, 12);
        mapd1.put(3, 7);
       // mapd.forEach((k,v)->System.out.println(k+"----"+v));
        
        AtomicInteger ac=new AtomicInteger(0);
       mapd1.entrySet().stream().forEach((k1)->{
        	System.out.println(" pppp -"+ac.getAndIncrement()+"---"+k1);
        	  mapd.merge(k1.getKey(), k1.getValue(), (x,y)->{
              	System.out.println(x+"--"+y);
              return 	x*y;
              });
        });
        
   /*    mapd.merge(2, 10, (x,y)->{
        	System.out.println(x+"--"+y);
        return 	x>2?x:y;
        });
        mapd.merge(3, 10, (x,y)->{
        	System.out.println(x+"-div-"+y);
        return 	x*y;
        });*/
        System.out.println(mapd);
    	
    	Map<String, String> hashMap = new HashMap<>();

		hashMap.put("RED", "#FF0000");
		hashMap.put("BLACK", null);
		hashMap.put("BLUE", "#0000FF");
		hashMap.put("GREEN", "#008000");
		hashMap.put("WHITE", null);
		
		hashMap.computeIfAbsent("RED", (k)->k);
		//hashMap.compute("RED", (k,v)->{System.out.println("kkkk "+k+"vvvvvvvv"+v);return k;});
		System.out.println("hashmap-yyyyyy--"+hashMap);
		
	 	 List<Entry<String, String>> gg=hashMap.entrySet().stream().filter(map->map.getKey().length()>1).collect(Collectors.toList());
			System.out.println(gg);
			Map<String, String> ff=hashMap.entrySet().stream().filter(map->
			{
				System.out.println(map);
				return map.getValue()!=null;
			}
			).map(map->map)
			.collect(Collectors.toMap(maps->maps.getKey(),maps->maps.getValue()));
			
		System.out.println(ff);
		
		  Map<String, String> valueLHMap = new LinkedHashMap<>();
		  hashMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(c -> valueLHMap.put(c.getKey(), c.getValue()));
		  
		System.out.println(valueLHMap);

}
}