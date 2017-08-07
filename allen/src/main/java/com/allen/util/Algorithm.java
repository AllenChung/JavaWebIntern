package com.allen.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.allen.bean.SearchBean;
import com.allen.multiThread.SelectPartItem;

@Component
public class Algorithm {

	@Autowired
	private SelectPartItem select;

	public List<String> process(List<SearchBean> atts, Map<String, String> query) throws Exception {
		List<Future<Map<String, Integer>>> resultList = new LinkedList<Future<Map<String, Integer>>>();
		
		// construct data structure and send to process
		for (String s: atts.get(0).getAttributes().keySet()) {
			// for every attribute
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			for (SearchBean sb: atts) {
				// save the value in this attribute and the partItem name list
				if (map.containsKey(sb.getAttributes().get(s))) {
					List<String> list = map.get(sb.getAttributes().get(s));
					list.add(sb.getName());
					map.put(sb.getAttributes().get(s), list);
				} else {
					List<String> list = new LinkedList<String>();
					list.add(sb.getName());
					map.put(sb.getAttributes().get(s), list);
				}
			}
			// execute task asynchronously
			resultList.add(select.getRelevantPartItemName(map, query.get(s)));
		}
		
		// get the result back and calculate the score
		Map<String, Integer> searchResult = new TreeMap<String, Integer>();
		for (Future<Map<String, Integer>> future: resultList) {
			Map<String, Integer> result = future.get();
			for (String s: result.keySet()) {
				if (searchResult.containsKey(s)) {
					int i = searchResult.get(s);
					i += result.get(s);
					searchResult.put(s, i);
				} else {
					searchResult.put(s, result.get(s));
				}
			}
		}
		
		// sort
		List<Map.Entry<String, Integer>> listResult = new ArrayList<Map.Entry<String, Integer>>(searchResult.entrySet());
		Collections.sort(listResult, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		// pick three to return
		List<String> returnList = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			returnList.add(listResult.get(i).getKey());
		}
		
		return returnList;
	}

}
