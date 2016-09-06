import java.util.*;
public class FirmProject { // �������� - ������ �����
	//����
	private final static String project_FORMAT_STRING = 
                     "������: %-s, %-5d �����"; //������ ������ � �������
	private String name; //�������� �������
	private Set <Firma> firms; // ������ (�����) ����
	// ������������
	public FirmProject(){
	  name=""; //��� ��������
	  firms = new TreeSet <Firma>(); //��������� ������ �����
    }
	public FirmProject(String name){
	  this.name=name; //�������� �������� �������
	  firms = new TreeSet <Firma>(); //��������� ������ �����
    }
    public FirmProject(String name, Comparator comp){
	  this.name=name; //�������� �������� �������
	  firms = new TreeSet <Firma>(comp); //��������� ������ �����,
            // � ������� ������ �� ����������� ������� ����� 
            // ��������������� �� �������� ����������� comp
    }
    public FirmProject(String name, Set set){
	  this.name=name; //�������� �������� �������
	  firms = new TreeSet <Firma>(set); //��������� ����� �� ������ 
                                                                                 // ������������� ������ set
    }
    //�����-������ ��� private-����
    public void setProjectName(String name)
        {this.name=name;}
    //������-������� ��� private-�����
    public String getProjectName(){return name;}
    public Set <Firma> getFirms(){
        return firms;
    }
    //�������������� ����� toString ������ Object
    //���������� ������ �������� ������� ������ ����:
    public String toString(){
         return String.format(project_FORMAT_STRING,name,getFirmNum());  
    }
    //������� �� �������  �  ��������  ������:
    public boolean addFirm(Firma firm){
      //�������� ����� � ������ �������
	    //����� �� ����� ��������, ���� � ������ ��� ���� �����
	    //����� �� id 
	    if (firms.add(firm)) return true;
	    else return false;
    }
    

public boolean delFirm(int id){
   //������� ����� �� ������ �������
        if (firms.remove(new Firma(id,"",0,0))) return true;
        else return false;
    }
    // ������� �� ������� ������:
    public Firma getFirm (int id){
       // ���������� ����� � �������� ������� ������� (id):
        for (Firma firm:firms)
           if (firm.getId() == id) return firm; // ���� ����� ������
        return null; // ����� �� ������
    } 
    public int getFirmNum(){
    //���������� ����� ���� � ������    
        return firms.size();
    }    
    public double avgZP(){
       //���������� ������� �������� �� ������
	    int num=firms.size();
	    if (num==0) return 0;
	    double avg=0;
		for (Firma firm:firms)
		    avg=avg+firm.getZP();
		return avg/num;
	}
   
   public FirmProject aboveAvgZP(){
       //���������� ����, � ������� �������� ���� ��������
       double avg=avgZP();
       FirmProject project = new FirmProject (name+
                       ": �����, � ������� �������� ���� �������� - " + avg);
       // ������������ �� ������������ ������� - ������ �������, 
       // �������, ��� ���������� �� ���, � ������ between                
       //��� ��������� (������������) �������� ������ 
       //���������� ���� for-each                 
       for (Firma firm:firms)
           if (firm.getZP()>avg) project.addFirm(firm);
       return project;
   }
   
   public FirmProject betweenZP(double b1, double b2){// ������:
     //���������� ���� � ���������� � ��������� [b1,b2]
      //����������� �� ������������ ��� ������������ ����� �������    
      FirmProject project = new FirmProject (
    		  String.format("%s: �����, � ������� �������� � ��������� �� %4.2f �� %4.2f", name, b1, b2));
	  // ��� ��������� (������������) �������� ������
      // ���������� ��������
      Iterator <Firma> iter=firms.iterator();
       while (iter.hasNext()){
          Firma firm=iter.next(); 
          if ((firm.getZP()>=b1)&&(firm.getZP()<=b2))project.addFirm(firm);
      }    
      return project;
   }
   //������� �� ���������� ������
  public FirmProject sortZPAsc(){
     FirmProject project = new FirmProject(name+
                " (���������� �� ����������� ��������)", new CompZPAsc());
     for (Firma firm:firms) project.addFirm(firm); // ��� ���������� 
     // � ����� ������������ �������������� ������ �� ������� ��
     // �������, �����������  ������������ (����������)
     return project;  
   }
  public FirmProject sortZPDesc(){
     FirmProject project = new FirmProject(name+
            " (���������� �� �������� ���������)", new CompZPDesc());
     for (Firma firm:firms) project.addFirm(firm); // ��� ���������� 
     // � ����� ������������ �������������� ������ �� ������� ��
     // �������, �����������  ������������ (����������)
     return project;
   } 
   public FirmProject  sortNameAscZPDesc (){
     FirmProject project = new FirmProject(name+
        " (���������� �� ����������� ����� � �������� ��������)",
         new CompNameAscZPDesc());
     for (Firma firm:firms) project.addFirm(firm); // ��� ���������� 
     // � ����� ������������ �������������� ������ �� ������� ��
     // �������, �����������  ������������ (����������)
     return project;
   } 
   // ������ �� ����� ������
   public void putFirmProject(){
     //����� ������� ���� � ���� ���������
	   System.out.println(name); // ��� �������
		System.out.printf("%3s%11s%23s%21s%22s\n", "�", "ID", "��������", "����� �����������", "������� ��������"); // ���������
			
		int i = 1;
		for (Firma firm : firms) {
			System.out.printf("  %-7d  %-15d  %-18s  %-18d  %-10.2f\n", i, firm.getId(), firm.getName(), firm.getRab(), firm.getZP());
			i = i + 1;
   }    
  } // putFirmProject   
   
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
} //class
