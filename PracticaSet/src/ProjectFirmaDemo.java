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
     pr1.putFirmProject();
     // ������ ������:
     pr1.aboveAvgZP().sortZPAsc().putFirmProject();
     pr1.betweenZP(3.2f,4.2f).sortZPDesc().putFirmProject();
     // �������� �������� �� id
     pr1.delFirm(12345);
     //������� ������ ������ pr1 ����� �������� ��������
     System.out.println ("����� �������� �������� c id=12345:");
     pr1.putFirmProject();
	  
     // ������� ������ ��������� ��� ������ ������
     // �������� ������ ������
     pr2.putFirmProject(); // ��� ����������
     pr2.sortNameAscZPDesc().putFirmProject(); // � ����������� �� 
                                        // ����������� ������� � �������� �����
     // ������ ������:
     pr2.aboveAvgZP().sortZPAsc().putFirmProject();
     pr2.betweenZP(3.2f,4.2f).sortZPDesc().putFirmProject();
	 
    //���������,���� �� �� ������ ������ �������  
    // c �������� ������� �������
     int n=23345;
     Firma s1=pr1.getFirm(n);
     if (s1==null) System.out.println ("� ������ "+pr1.getProjectName()+
                             " ��� �������� � ������� ������� "+n);
     else System.out.println(s1);
     n=70000;
     s1=pr1.getFirm(n);
     if (s1==null) System.out.println ("� ������ "+pr1.getProjectName()+
                             " ��� �������� � ������� ������� "+n);
     else System.out.println(s1);
     //���������,���� �� �� ������ ������ ������� 
     // � �������� ������� �������
     n=53349;
     s1=pr2.getFirm(n);
     if (s1==null) System.out.println ("� ������ "+pr1.getProjectName()+
                             " ��� �������� � ������� ������� "+n);
     else System.out.println(s1);
     
		pr1.UpdateId(34, 24);            //1� ����� ��������������� �������
		pr1.Filtr("��").putFirmProject();//3� ����� ��������������� �������
		pr1.DelObject().putFirmProject();//2� ����� ��������������� �������
     
  } }
