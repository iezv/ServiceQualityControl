package tel_ran.quality.model.dao;

import java.util.*;
import javax.persistence.*;
public class QualityOrm {
	@PersistenceContext(unitName="springHibernate",type=PersistenceContextType.EXTENDED)
	EntityManager em;
public List<String> getResultAnyJpql(String jpql){
	Query q=em.createQuery(jpql);
	List resList=q.getResultList();
	if(resList==null||resList.isEmpty())
		return new LinkedList<>();
	return resList.get(0).getClass().isArray()?getMultiResult(resList):
		getSingleResult(resList);
}
private List<String> getSingleResult( List<Object> resList) {
	ArrayList<String> res=new ArrayList<>(); 
	for(Object obj:resList)
		res.add(obj.toString());
	return res;
}
private List<String> getMultiResult( List<Object[]> resList) {
	ArrayList<String> res=new ArrayList<>(); 
	for(Object[] array:resList)
		res.add(Arrays.deepToString(array));
	return res;
}

}
