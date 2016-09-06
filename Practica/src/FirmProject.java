import java.util.*;

public class FirmProject { // �������� � ������ �����
	// ���� (������ � ������)
	private final static String PROJECT_FORMAT_STRING = "������: %-s, %-5d �����"; // ������
																					// ������
																					// �
																					// �������
	private String name; // �������� �������
	private List<Firma> firms; // ������ (�����) ����
	// ������������

	public FirmProject() {
		name = ""; // ��� ��������
		firms = new ArrayList<Firma>(); // ��������� ������ ������
	}

	public FirmProject(String name) {
		this.name = name; // �������� �������� �������
		firms = new ArrayList<Firma>(); // ��������� ������ ������
	}

	public FirmProject(String name, List list) {
		this.name = name; // �������� �������� �������
		firms = new ArrayList<Firma>(list); // ��������� �� ������
											// ������������� ������
	}

	// �����-������ ��� private-����
	public void setProjectName(String name) {
		this.name = name;
	}

	// ������-������� ��� private-�����
	public String getProjectName() {
		return name;
	}

	public List<Firma> getFirm() {
		return firms;
	}

	// �������������� ����� toString ������ Object
	// ���������� ������ �������� ������� ������ �����:
	public String toString() {
		return String.format(PROJECT_FORMAT_STRING, name, getFirmNum());
	}

	// ������� �� �������, ��������, ��������� ������:
	public boolean addFirm(Firma firm) {
		// �������� ����� � ������ ��������
		// (����� ������ ��������, ���� � ������� ��� ���� �����
		// � ����� �� id)
		if (getFirm(firm.getId()) != null)
			return false; // ��������������
							// ����������� ��������
		if (firms.add(firm))
			return true;
		else
			return false;
	}

	public boolean delFirm(int id) {
		// ������� ����� �� ������ �������
		if (firms.remove(new Firma(id, "", 0, 0)))
			return true;
		else
			return false;
	}

	// ������� �� ������� ������:
	public Firma getFirm(int id) {
		// ���������� ����� � �������� ������� (id):
		for (Firma firm : firms)
			if (firm.getId() == id)
				return firm; // ���� ����� �������
		return null; // ����� �� �������
	}

	public int getFirmNum() {
		// ���������� ����� ���� � �������
		return firms.size();
	}

	public double avgZP() {
		// ���������� ������� ���������� ����� �� �������
		int num = firms.size();
		if (num == 0)
			return 0;
		double avg = 0;
		for (Firma firm : firms)
			avg = avg + firm.getZP();
		return avg / num;
	}

	public FirmProject aboveAvgZP() {
		// ������� �����, � ������� �������� ���� ��������
		double avg = avgZP();
		FirmProject project = new FirmProject(name + ": �����, � ������� �������� ���� �������� - " + avg);
		// ������������ �� ������������ ������� - ������ �������,
		// �������, ��� ���������� �� ���, � ������ between
		// ��� ��������� (������������) �������� ������
		// ���������� ���� for-each
		for (Firma firm : firms)
			if (firm.getZP() < avg)
				project.addFirm(firm);
		return project;
	}

	public FirmProject betweenZP(double b1, double b2) {
		// ������� ����� � ��������� � ��������� [b1,b2]
		// ����������� �� ������������ ��� ������������ ����� �������
		FirmProject project = new FirmProject(
				String.format("%s: �����, � ������� �������� � ��������� �� %4.2f �� %4.2f", name, b1, b2));
		// ��� ��������� (������������) �������� ������
		// ���������� ��������
		Iterator<Firma> iter = firms.iterator();
		while (iter.hasNext()) {
			Firma firm = iter.next();
			if ((firm.getZP() >= b1) && (firm.getZP() <= b2))
				project.addFirm(firm);
		}
		return project;
	}

	// ������� �� ���������� ������
	// ��� ���������� ������� List ������������ �����������
	// ������ sort, ������������ � ������ Collections
	public FirmProject sort() {
		// C��������� ���� � ������������ �������
		// ������������ ������� ������ ����� CompareTo,
		// ���������������� � ������ Firma
		FirmProject project = new FirmProject(name, firms);
		Collections.sort(project.firms);
		return project;
	}

	public FirmProject sort(Comparator comp) { // co�������� ����
		// �� �������, ����������� ������������ comp
		FirmProject project = new FirmProject(name, firms);
		Collections.sort(project.firms, comp);
		return project;
	}

	// ������ �� ����� ������
	public void putFirmProject() {
		// ����� ������� ���� � ���� ���������
		System.out.println(name); // ��� �������
		System.out.printf("%3s%11s%23s%21s%22s\n", "�", "ID", "��������", "����� �����������", "������� ��������"); // ���������
																												// ��������
		int i = 1;
		for (Firma firm : firms) {
			System.out.printf("  %-7d  %-15d  %-18s  %-18d  %-10.2f\n", i, firm.getId(), firm.getName(), firm.getRab(), firm.getZP());
			i = i + 1;
		}
	}

	//�������� (� ������ �� � update)  id � ������� � �������� id (������������� �������� �� ������������ ������ id)

public boolean UpdateId(int id1, int newId){ // 1� �����, ���������� Id 
if (getFirm(id1)!=null && !firms.contains(new Firma(newId,"",0,0))){ 
getFirm(id1).setId(newId); 
System.out.println("Id "+id1+" ��� ������� �� "+newId); 
return true; 
} 
else{System.out.println("Id �� ��� �������"); return false; } 
} 

//2� �����, ������� ����� � ������� �������� ��������� ������������� ���� ���� ��������
public FirmProject DelObject(){
	double avg = avgZP();// ���������� ������� ���������� ����� �� �������
	FirmProject project = new FirmProject(name + ": �����, � ������� �������� ���� �������� ��������: " + avg+" ��������� �������");
	for (Firma firm : firms){
		project.addFirm(firm);
		if (firm.getZP() < avg){
			int c = firm.getId();
			project.delFirm(c);
		}
	}
	return project;
}

public FirmProject Filtr (String str){ //3� �����, ����� ���� ������������ �������� ��������������� 
FirmProject project = new FirmProject (
		String.format ("%s: ����� ������������ �������� ���������������: %s", name, str)); 
Iterator <Firma> iter=firms.iterator(); 
while (iter.hasNext()){ 
Firma firm = iter.next (); 
if (firm.getName().indexOf(str)==0){
project.addFirm(firm); 
}
}
return project;
}
} // class
