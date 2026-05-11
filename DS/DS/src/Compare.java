import java.util.Comparator;
import java.util.PriorityQueue;

public class Compare{
    public static void main(String[] args){
        PriorityQueue<Grade> grades = new PriorityQueue<>();
        for(int i = 0; i < 10; i++){
            grades.offer(new Grade(i, i + 1, i + 2));
        }
        for(Grade grade: grades){
            System.out.println(grade);
        }
    }
}
class Grade implements Comparable<Grade>{
    public int chinese;
    public int math;
    public int english;

    public Grade(int ch, int ma, int en){
        chinese = ch;
        math = ma;
        english = en;
    }

    @Override
    public String toString(){
        return "chinese: " + chinese + " math: " + math + " english: " + english;
    }

    @Override
    public boolean equals(Object o){
        Grade other = (Grade)o;
        if(other.chinese == chinese && other.math == math && other.english == english){
            return true;
        }
        return false;
    }
    
    @Override
    public int compareTo(Grade g){
        if(g.equals(this)){
            return 0;
        }
        else{
            if(chinese + math + english < g.chinese + g.english + g.math){
                return -1;
            }
            else{
                return 1;
            }
        }
    }
}

class GradeComparator implements Comparator<Grade>{
    @Override
    public int compare(Grade g1, Grade g2){
        if(g1.equals(g2)){
            return 0;
        }
        else{
            if(g1.chinese + g1.math + g1.english < g2.chinese + g2.english + g2.math){
                return -1;
            }
            else{
                return 1;
            }
        }
    }
}