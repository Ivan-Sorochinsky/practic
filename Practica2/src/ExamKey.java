public class ExamKey implements Comparable <ExamKey>{// �������� - ���� ��������
  //���� - ������ � ������
  private final static String EXKEY_FORMAT_STRING = 
       "�����: %1d | ������: %1s | ����������: %1d |"; //������ ������ � ����� ��������
  private int idStudent; //����� �������� ������ ��������
  private String subject; //���� ��������
  private int semester; //����� ��������
  // ����������� ��� ����������
  public ExamKey(){
      this.idStudent=0; this.subject="";
      this.semester=0;
  }
  // ����������� � �����������
  public ExamKey(int idStudent, String Subject, int semester){
      this.idStudent=idStudent; this.subject=Subject; 
      this.semester=semester;
  }
  //������-�������
  public int getIdStudent(){return idStudent;}
  public String getSubject(){return subject;}
  public int getSemester(){return semester;}
  //������-�������    
  public void setIdStudent(int idStudent){this.idStudent = idStudent;}
  public void setSubject(String subject){this.subject = subject;}
  public void setSemester(int semester){this.semester = semester;}
  //���������������� ����� toString ������ Object
  //(���������� ������ �������� �������) 
  public String toString(){
        return String.format(EXKEY_FORMAT_STRING,idStudent,subject,semester);  
  }
  //���������������� ����� equals ������ Object
  //(������ ������ ��������� �������� �� ���������,
  //���������� true, ���� ����������� ������ ����� �������-���������)
  public boolean equals (Object ob){ 
      if (ob==this) return true; // ������ ����� � ���� 
                                                 // � ��� �� ������
      if (ob==null) return false; //� ����� �������� null-������
      if (getClass()!=ob.getClass())return false; //������� ������ �������
      ExamKey ek=(ExamKey)ob; // ����������� Object � Student
      return (idStudent == ek.idStudent)&&(subject.equals(ek.subject))
              &&(semester==ek.semester); 	//true, ���� ������ �� �����
      //�������� (������������) ������� ����� ���������������� ���� 
      //�������-���������         
 }
 //���������������� ����� hashCode ������ Object
 //���������� ���-��� �������
 //(� ������ �������� ������ ���� ������ hash-����)
 public int hashCode(){
       return 7* (new Integer(idStudent)).hashCode()+
              11 * subject.hashCode()+
              13 * (new Integer(semester)).hashCode();
 }
 
 //���������� ����� ��mpareTo ���������� �omporable
 //��� ����������� ������� (�������������) ������������ ���������
 public int compareTo(ExamKey ek){
     //�� ����������� ������ �������,
     //���� ������ ������� ���������� -
     //�� ����������� ����� ��������,
     //���� ����� �������� ���������� -
     //�� ����������� ��������
     if (idStudent < ek.idStudent) return -1;
     if (idStudent > ek.idStudent) return 1;
     //������ ������� ����� (���� � ��� �� �������)
     if  (subject.compareTo(ek.subject)<0) return -1;
     if  (subject.compareTo(ek.subject)>0) return 1;
     //�������� ����� (���� � ��� �� �������)
     if (semester < ek.semester) return -1;
     if (semester > ek.semester) return 1;
     return 0; //�������� �����     
 }    
}