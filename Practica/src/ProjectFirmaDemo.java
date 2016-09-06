public class ProjectFirmaDemo { // �������������� ���� � ��������
	public static void main(String[] args) {
		// ������� ������� ����
		FirmProject pr1 = new FirmProject("Festo");
		FirmProject pr2 = new FirmProject("Kepler");
		// � ������ args ��� ������� ������ main (String[ ] args)
		// ����� 10 ����: �� 5 ��� ������ ������.
		int j = 0; // ������ ������� args
		// ���������� ���� � ������ ������
		for (int i = 0; i < 5; i++) {
			int id = Integer.valueOf(args[j]);
			j++; // ������� int-���������� �� String
			String name = args[j];
			j++;
			int rab = Integer.valueOf(args[j]);
			j++;
			// �������� double-���������� �� String
			double ZP = Double.valueOf(args[j]);
			j++;
			// �������� ����
			pr1.addFirm(new Firma(id, name, rab, ZP));
		}
		// ���������� ���� �� ������ ������
		for (int i = 0; i < 5; i++) {
			int id = Integer.valueOf(args[j]);
			j++;
			String name = args[j];
			j++;
			int rab = Integer.valueOf(args[j]);
			j++;
			double ZP = Double.valueOf(args[j]);
			j++;
			// �������� ����
			pr2.addFirm(new Firma(id, name, rab, ZP));
		}
		// ������� ������
		// ������� ������������ ������ ����
		// ������� �������� ������ ��� ����������
		System.out.println("������ ���� ����������� � ������� (��� ����������)");
		pr1.putFirmProject();
		// �������� �������� ���� � ��� �� ������� id
		System.out.println("������� �������� �����: 3234, ���������, 76, 5000.0");
		pr1.addFirm(new Firma(3234, "���������", 76, 5000.0f));
		// ����� ������� �������� ������
		// (������ � ������������ �������� ����������)
		System.out.println("������ ���� ����������� � ������� (� ������������ �������� ����������)");
		pr1.sort().putFirmProject();
		// ������ ������:
		System.out.println("������ ���� ����������� � ������� (� ����������� �� ����������� ��������)");
		pr1.aboveAvgZP().sort(new CompZPAsc()).putFirmProject();
		System.out.println("������ ���� ����������� � ������� (� ����������� �� �������� ��������)");
		pr1.betweenZP(3000f, 6000f).sort(new CompZPDesc()).putFirmProject();
		// �������� ���� �� id
		pr1.delFirm(3234);
		// ������� ������ ������ pr1 ����� �������� ����
		System.out.println("����� �������� ����� c id=3234:");
		System.out.println("������ ���� ����������� � ������� (� ������������ �������� ����������)");
		pr1.putFirmProject();
		// ������� ������ ���� ��� ������ ������
		System.out.println("������ ���� ����������� � ������� (� ����������� �� ����������� �������� �");
		System.out.println(" �������� ��������)");
		pr2.sort(new CompNameAscZPDesc()).putFirmProject();
		// ������ ������ ��� ������ ������:
		System.out.println("������ ���� ����������� � ������� (� ����������� �� ����������� ��������)");
		pr2.aboveAvgZP().sort(new CompZPAsc()).putFirmProject();
		System.out.println("������ ���� ����������� � ������� (� ����������� �� �������� ��������)");
		pr2.betweenZP(30f, 8000f).sort(new CompZPDesc()).putFirmProject();
		// ���������,���� �� �� ������ ������� �����
		// � �������� ������� id
		int n = 34;
		Firma s1 = pr1.getFirm(n);
		if (s1 == null)
			System.out.println("� ������� " + pr1.getProjectName() + " ��� ����� � �������  " + n);
		else
			System.out.println(s1);
		n = 354;
		s1 = pr1.getFirm(n);
		if (s1 == null)
			System.out.println("� ������� " + pr1.getProjectName() + " ��� ����� � �������  " + n);
		else
			System.out.println(s1);
		// ���������,���� �� �� ������ ������� ����� � ��������
		// ������� id
		n = 53349;
		s1 = pr2.getFirm(n);
		if (s1 == null)
			System.out.println("� ������� " + pr1.getProjectName() + " ��� ����� � �������  " + n);
		else
			System.out.println(s1);
		pr1.UpdateId(34, 24);            //1� ����� ��������������� �������
		pr1.Filtr("��").putFirmProject();//3� ����� ��������������� �������
		pr1.DelObject().putFirmProject();//2� ����� ��������������� �������
		
	}
}