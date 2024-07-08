import java.util.Scanner;
public class Task2 {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);               

        System.out.println("Enter the number of subjects: ");
        int n=s.nextInt();

        int[] subjects=new int[n];
        int Total_marks=0;


        for(int i=0;i<n;i++){
            System.out.println("Enter the subject "+(i+1)+":");
            subjects[i]=s.nextInt();

            Total_marks+=subjects[i];
        }

        double avg_marks=(double) Total_marks/n;

        char grade;
        if (avg_marks >= 90) {
            grade = 'A';
        } else if (avg_marks >= 80) {
            grade = 'B';
        } else if (avg_marks >= 70) {
            grade = 'C';
        } else if (avg_marks >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("Total marks: "+Total_marks);
        System.out.println("Percentage: "+avg_marks+"%");
        System.out.println("Grade: "+grade);

    }
}
