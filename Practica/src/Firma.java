public class Firma implements Comparable<Firma> {
	// �������� - �����
	// ���� - ������ � ������
	private final static String FIRM_FORMAT_STRING = "�����: %-7d | %-10s | %-7d | %-8.2f |"; // ������
																								// ������
																								// �
																								// �����
	private int id; // id �����, �������� ����
	private String name; // ��������
	private int rab; // �����_�����������
	private double zp; // �������_��������
	// ����������� ��� ����������

	public Firma() {
		this.id = 0;
		this.name = "";
		this.rab = 0;
		this.zp = 0;
	}

	// ����������� � �����������
	public Firma(int id, String name, int rab, double zp) {
		this.id = id;
		this.name = name;
		this.rab = rab;
		this.zp = zp;
	}

	// ������-�������
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getRab() {
		return rab;
	}

	public double getZP() {
		return zp;
	}

	// ������-�������
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRab(int rab) {
		this.rab = rab;
	}

	public void setZP(double zp) {
		this.zp = zp;
	}

	// ���������������� ����� toString ������ Object
	// (���������� ������ �������� �������)
	public String toString() {
		return String.format(FIRM_FORMAT_STRING, id, name, rab, zp);

	}

	// ���������������� ����� equals ������ Object
	// (������ ������ ��������� �������� �� ���������,
	// ���������� true, ���� ����������� ������
	// ����� �������-���������)
	public boolean equals(Object ob) {
		if (ob == this)
			return true; // ������ ����� � ����
							// � ��� �� ������
		if (ob == null)
			return false; // � ����� �������� null-������
		if (getClass() != ob.getClass())
			return false; // ������� ������ �������
		Firma fr = (Firma) ob; // ����������� Object � Firma
		return id == fr.id; // id � �������� ���� �������
	}

	// ���������������� ����� hashCode ������ Object
	// ���������� ���-��� �������
	// (� ������ �������� ������ ���� ������ hash-����)
	public int hashCode() {
		return 7 * (new Integer(id)).hashCode();
	}

	// ���������� ����� ��mpareTo ���������� �omporable
	// ��� ����������� ������������� ������� ������������ ���������
	public int compareTo(Firma firm) {
		if (id < firm.id)
			return -1;
		else if (id == firm.id)
			return 0;
		else
			return 1;
	}
}
