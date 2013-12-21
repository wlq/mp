package sy.comparator;

import java.util.Comparator;

import sy.model.Tproject;

/**
 * 项目排序
 * 
 * @author 
 * 
 */
public class ProjectComparator implements Comparator<Tproject> {

	public int compare(Tproject o1, Tproject o2) {
		int i1 = o1.getCseq() != null ? o1.getCseq().intValue() : -1;
		int i2 = o2.getCseq() != null ? o2.getCseq().intValue() : -1;
		return i1 - i2;
	}

}
