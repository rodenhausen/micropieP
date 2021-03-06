package edu.arizona.biosemantics.micropie.extract.regex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.inject.Inject;

import edu.arizona.biosemantics.micropie.classify.ILabel;
import edu.arizona.biosemantics.micropie.classify.Label;

public class ContentExtractorProvider implements ICharacterValueExtractorProvider {

	private Map<ILabel, Set<ICharacterValueExtractor>> labelExtractorsMap = new 
			HashMap<ILabel, Set<ICharacterValueExtractor>>();

	@Inject
	public ContentExtractorProvider(Set<ICharacterValueExtractor> extractors) {
		for(ICharacterValueExtractor extractor : extractors) {
			if(!labelExtractorsMap.containsKey(extractor.getLabel()))
				labelExtractorsMap.put(extractor.getLabel(), new HashSet<ICharacterValueExtractor>());
			labelExtractorsMap.get(extractor.getLabel()).add(extractor);
		}
	}
	
	@Override
	public Set<ICharacterValueExtractor> getContentExtractor(Label label) {
		return labelExtractorsMap.get(label);
	}

	@Override
	public boolean hasExtractor(Label label) {
		return !this.getContentExtractor(label).isEmpty();
	}

}
