import java.util.*;

public class CompZPDesc implements Comparator {
	public int compare(Object ob1, Object ob2) {
		Firma firm1 = (Firma) ob1;
		Firma firm2 = (Firma) ob2;
		if (firm1.getZP() < firm2.getZP())
			return 1;
		else if (firm1.getZP() == firm2.getZP())
			return 0;
		else
			return -1;
	}
	// нет необходимости переопределять метод equels
}
