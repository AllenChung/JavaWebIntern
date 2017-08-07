package com.allen.multiThread;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.allen.util.PreProcess;
import com.hankcs.hanlp.suggest.Suggester;

@Component
public class SelectPartItem {
	
	// calculate the similarity
	@Async
	public Future<Map<String, Integer>> getRelevantPartItemName(Map<String, List<String>> map, String query) {
		Suggester suggester = new Suggester();
		for (String s: map.keySet()) {
			 suggester.addSentence(s);
		}
		Map<String, Integer> result = new HashMap<String, Integer>();
		int i = 1;
		for (String s: suggester.suggest(query, 3)) {
			for (String name: map.get(s)) {
				result.put(name, i);
			}
			i++;
		}
		return new AsyncResult<>(result);
	}

	// construct the inverted index
	@Async
	public Future<Set<String>> getRelevantPartItem(Map<String, String> map, String query) {
		Set<String> allWords = new HashSet<String>();
		// get all the word
		for (String s: map.keySet()) {
			allWords.addAll(PreProcess.preProcess(map.get(s)));
		}
		
		// construct the invertedIndex
		Map<String, Set<String>> invertedIndex = new HashMap<String, Set<String>>();
		for (String s: allWords) {
			for (String key: map.keySet()) {
				if (map.get(key).contains(s)) {
					if (invertedIndex.containsKey(s)) {
						Set<String> term = invertedIndex.get(s);
						term.add(key);
						invertedIndex.put(s, term);
					} else {
						Set<String> term = new HashSet<String>();
						term.add(key);
						invertedIndex.put(s, term);
					}
				}
			}
		}
		
		// process the AND operation on the relevant set to get the result
		Set<String> result = map.keySet();
		for (String s: PreProcess.preProcess(query)) {
			result.retainAll(invertedIndex.get(s));
		}
		return new AsyncResult<>(result);
	}
}
