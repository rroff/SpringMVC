package us.roff.springtutorial.services.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.roff.springtutorial.domain.DomainObject;

public abstract class AbstractMapService {
	protected Map<Integer, DomainObject> domainMap;
	
	public AbstractMapService() {
		domainMap = new HashMap<>();
	}
	
	public List<DomainObject> listAll() {
		return new ArrayList<>(domainMap.values());
	}
	
	public DomainObject getById(Integer id) {
		return domainMap.get(id);
	}
	
	public DomainObject saveOrUpdate(DomainObject domainObject) {
		if (domainObject == null) {
			throw new RuntimeException("Record cannot be null");
		} else {
			if (domainObject.getId() == null) {
				domainObject.setId(getNextKey());
			}
			domainMap.put(domainObject.getId(), domainObject);
		}
		return domainObject;
	}
	
	public void deleteById(Integer id) {
		domainMap.remove(id);
	}
	
	private Integer getNextKey() {
		Integer nextKey;
		
		if (domainMap.isEmpty()) {
			nextKey = 1;
		} else {
			nextKey = Collections.max(domainMap.keySet()) + 1;
		}
		
		return nextKey;
	}
}
