import java.util.*;
public class ExamRegister { // �������� -  �������-��������������� 
    //��������� (������ ���������)
	//����
	//����������� �������� ����������� ����� ��������� ������� ������
	//�.�. �������� ������ ��� ���� ��������
	//�������������� ������ ��������� ������ (����� Arrays 
	//��������� � ������ java.util)� �������� ������  Java Collections Framework
	private final static List <String> MARKS = Arrays.asList("1","2", "3","4"); 
	private final static String EXREG_FORMAT_STRING = "\"������: %s, ����������: %1d\""; //������ ������ � ������ ���������
	private String name; //�������� ������ ��������� (���������)
	private Map <ExamKey, String> register; // ����� (���������) ���������
	// ������������
	public ExamRegister(){
	  name=""; //��� ��������
	  register = new TreeMap <ExamKey, String>(); //��������� ������ �����
    }
	public ExamRegister(String name){
	  this.name=name; //�������� �������� ��������� 
	  register = new TreeMap <ExamKey, String>(); //��������� ������ �����
    }
    public ExamRegister(String name, Comparator comp){
	  this.name=name; //�������� �������� ���������
	  register = new TreeMap <ExamKey, String> (comp);//��������� ������ �����,
            // � ������� ������ �� ����������� �������-����� ����� 
            // ��������������� �� �������� ����������� comp    
    }
    //�����-������ ��� private-����
    public void setRegisterName(String name)
        {this.name=name;}
    //������-������� ��� private-�����
    public String getRegisterName(){return name;}
    public Map <ExamKey, String> getRegister(){
        return register;
    }
    //�������������� ����� toString ������ Object
    //���������� ������ �������� ������� ������ ���������:
    public String toString(){
         return String.format(EXREG_FORMAT_STRING,name,size());  
    }
    //������� �� �������  �  ��������  ������:
    public boolean addExam(ExamKey ek, String mark){
	 //�������� ������� (���� � ��������(������)) � �����
	    //������� �� ����� ��������, ���� � ������ ��� ���� �������
	    //� ����� �� ������
	        if (register.containsKey(ek)) return false;
	        if (!MARKS.contains(mark)) return false;
	        register.put(ek,mark);
	        return true;
	}
    public boolean delExam(ExamKey ek){
        //������� ������� �� ��������� �� ����� 
        if (register.containsKey(ek)){register.remove(ek); return true;}
        else return false;
    }  
   public boolean delExam(int idStudent){
    //������� ��� �������� ����������� ��������
     Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
     Iterator <Map.Entry <ExamKey,String>> it = setE.iterator();
     int i=0; //������� ��������� ���������
     while (it.hasNext()){
        Map.Entry <ExamKey,String> keyVal = it.next();
        if (keyVal.getKey().getIdStudent()==idStudent){
            it.remove();
            i=i+1;
        }    
     }
     if (i==0) return false;
     return true;
     //�������� ����� (���� ����-��������)�� ������ setE ������� 
     //remove() ��������� it ������ �� ����� �������� ����� ����� 
     //�� ����� register (����������� ������� �������� ��� �����). 
     //����� ������ ������ �������� ����� 
     //��� ����������� ��������� ������ ����� ������� ������
     //���� ConcurrentModificationException.
     //���������� ��������� Java ��������. ������� ������� ������� ����������, 
     //��� � Java ��� ���� ���� ������� ����������� ���� � ����.
   }
    public boolean updateExam(ExamKey ek,  String mark){
        //�������� ������ �� ����� (������� �������� �������)
       if (!register.containsKey(ek)) return false;
       if (!MARKS.contains(mark)) return false;
       register.put(ek, mark);
       return true;
    }   
    // ������� �� ������� ������:
    public int size(){
    //���������� ����� ��������� � ���������   
        return register.size();
    }
    public ExamRegister selectStudentData(int idStud){
       //���������� ����� ��������� ��������� ��������
       ExamRegister subExReg = new ExamRegister ( String.format("%s: ������� �� ����� %7d",
                                               name,idStud));// ������� (���)���������
       Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
       for (Map.Entry <ExamKey, String> keyVal:setE)
            if (keyVal.getKey().getIdStudent()==idStud) 
                subExReg.register.put(keyVal.getKey(),keyVal.getValue());
       return  subExReg;        
   }
   
   public ExamRegister selectSubjectData(String subject){
       //���������� ����� ��������� �� ��������� ��������
       ExamRegister subExReg = new ExamRegister ( String.format("%s: ������� �� ������ %s",
                                               name,subject
                                               ));// ������� (���)���������
       Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
       for (Map.Entry <ExamKey, String> keyVal:setE)
            if (keyVal.getKey().getSubject().equals(subject)) 
                subExReg.register.put(keyVal.getKey(),keyVal.getValue());
       return  subExReg;        
   }
   public ExamRegister selectStudentSemestrData(int idStud, int semester){
       //���������� ����� ��������� ��������� �������� � �������� ��������
       ExamRegister subExReg = new ExamRegister (
             String.format("%s: ������� �� ����� %1d � ���������� %1d",
                 name,idStud, semester));// ������� (���)���������
       Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
       for (Map.Entry <ExamKey, String> keyVal:setE)
            if ((keyVal.getKey().getIdStudent()==idStud)&&(keyVal.getKey().getSemester()==semester))
                subExReg.register.put(keyVal.getKey(),keyVal.getValue());
       return  subExReg;        
   }
   public static int transMark(String mark){
        if (mark=="�������") return 5;
        if (mark=="������") return 4;
        if (mark=="�����������������") return 3;
        return 0;
   }    
   public int selectSumBallStud(int idStud){
       //���������� ��������� ���� ��� ����������� �������� 
       int sum = 0;
       //�������� �������(�����) �� ��������� ��������
       ExamRegister subReg = selectStudentData(idStud);
       //�������� �� ����� ����� �������� (������)
       Collection <String> v = register.values();
       //��������� ������
       for (String mark: v) sum+=transMark(mark);
       return sum;
	}
    //������� �� ���������� ������
    public ExamRegister sortIdAscSemesterDesc(){
      //�� ����������� ������ �������  
      ExamRegister subExReg = new ExamRegister(String.format(
          "%s:\n\t ���������� �� ����������� ������ ������� � �������� ��������",
           name),new CompIdAscSemesterDesc());// ������� ����� ���������
      Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
      for (Map.Entry <ExamKey, String> keyVal:setE)
         subExReg.addExam(keyVal.getKey(), keyVal.getValue());// ��� ���������� 
     // � ����� ������������ �������������� ������ �� �������-����� ��
     // �������, �����������  ������������ (����������)
     return  subExReg;   
   }
   public ExamRegister sortSubjectAscSemesterDesc(){
      //�� ����������� ����� ��������  
      ExamRegister subExReg = new ExamRegister(String.format(
          "%s:\n\t ���������� �� ����������� ����� �������� � �������� ��������",
          name),new CompSubjectAscSemesterDesc());// ������� ����� ���������
      Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
      for (Map.Entry <ExamKey, String> keyVal:setE)
         subExReg.addExam(keyVal.getKey(), keyVal.getValue());
      return  subExReg;   
   }
   public ExamRegister sortBallDesc (){
     //�� �������� �����  
      ExamRegister subExReg = new ExamRegister(String.format(
                        "%s:\n\t ���������� �� �������� �����",
                         name),new CompBallDesc(this.register));// ������� ����� ���������
      Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
      for (Map.Entry <ExamKey, String> keyVal:setE)
         subExReg.addExam(keyVal.getKey(), keyVal.getValue());
      return  subExReg;   
   }
   // ������ �� ����� ������
   public void putExamRegister(){
     //����� ��������������� ��������� � ���� ���������
     System.out.println(name); //�������� ���������
       System.out.printf("%5s%17s%19s%15s%15s\n", 
             "�����","����� ���. ��.","���� ��������","�������","������"); //��������� ��������
     int i=1; 
     Set <Map.Entry <ExamKey, String>> setE = register.entrySet();
     for (Map.Entry <ExamKey, String> keyVal:setE){
        ExamKey key=keyVal.getKey();//�������� ���� ��������������� �����
        System.out.printf("  %-7d  %-15d  %-16s  %7d  %20s\n",
             i, key.getIdStudent(), key.getSubject(), key.getSemester(), keyVal.getValue());
        i=i+1;
   }    
  } // putStudGroup         
} //class
