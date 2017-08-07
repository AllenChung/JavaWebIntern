package com.allen.util;

import java.util.LinkedList;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

public class PreProcess {

	// split the word
	public static List<String> preProcess(String text) {
		HanLP.Config.ShowTermNature = false; 
		List<Term> listTerm= HanLP.segment(text);
		List<String> list = new LinkedList<String>();
		for (Term t: listTerm) {
			list.add(t.word);
		}
		return list;
	}

}
