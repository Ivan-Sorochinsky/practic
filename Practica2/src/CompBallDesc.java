import java.util.*; 
//���������� ��������� Java ��������. ������� ������� ������� ����������, 
//��� � Java ��� ���� ���� ������� ����������� ���� � ����.
public class CompBallDesc implements Comparator <ExamKey>{
   // ��� �� ������������ ���������� ����� �� ���������,
   // ���� (�� �����������) ��� ����������� �� ������?
   // ����� �������� ������ �� ����������� �����, �����
   // ����� ��� ����� �������� ������ � ���������!
   Map <ExamKey, String> registr;
   public CompBallDesc (Map <ExamKey, String> registr){
      this.registr=registr;
   }   
   public int compare (ExamKey ex1, ExamKey ex2){
      int ball1=ExamRegister.transMark(registr.get(ex1));
      int ball2=ExamRegister.transMark(registr.get(ex2));
      if (ball1>ball2) return -1;
      return 1;
    //����� �� ������ ������� ���������� ����.
    //� ������, ����� �� ������ �� ���� ��� ���������� ��������� 
    //������������� ���������� �����, ��������������� ������ �� ��������
    //�� ���� �� ��������� � �����, ���������� ����������� ����������,
    //�.�. ��������� ��, ��� ����� ������ ��� ��� ����.
   }    
   //��� ������������� �������������� ����� equals
}