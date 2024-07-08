import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Task4 extends JFrame {
    private static final int time=15;
    private int currentQuestionIndex=0;
    private int score=0;
    private int timeleft=time;

    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optiongroup;
    private JLabel timelabel;
    private Timer timer;
    private JButton submitButton;

    private String[][] questions ={{"Which of the following is not a fundamental concept of OOP?","Inheritance","Encapsulation","Polymorphism","Compilation","3"},
            {"Which concept of OOP is demonstrated by the phrase \"an object can take on many forms?\" " ,"Abstraction","Encapsulation","Inheritance","Polymorphism","3"},
            {"In OOP, what is a class?",
                    "An instance of an object",
                    "A blueprint or template for creating objects",
                    "C) A data type",
                    "D) A method that performs a specific function","1"}
    };
    public Task4() {
        setTitle("QUIZ application");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        questionLabel=new JLabel();
        optionButtons=new JRadioButton[4];
        optiongroup=new ButtonGroup();

        JPanel optionalpanel=new JPanel(new GridLayout(4,1));
        for(int i=0;i<4;i++){
            optionButtons[i]=new JRadioButton();
            optiongroup.add(optionButtons[i]);
            optionalpanel.add(optionButtons[i]);
        }

        timelabel = new JLabel("Time left: " + time + " seconds", JLabel.CENTER);
        submitButton = new JButton("Submit");

        submitButton.addActionListener(new SubmitButtonListener());

        add(questionLabel,BorderLayout.NORTH);
        add(optionalpanel,BorderLayout.CENTER);
        add(timelabel,BorderLayout.SOUTH);
        add(submitButton,BorderLayout.EAST);

        timer = new Timer(1000,new TimerListener());

        loadQuestion(currentQuestionIndex);
        timer.start();

    }
    private void loadQuestion(int index){
        if(index<questions.length){
            String[] questionData=questions[index];
            questionLabel.setText(questionData[0]);
            for(int i=0;i<4;i++){
                optionButtons[i].setText(questionData[i+1]);
            }
            optiongroup.clearSelection();
            timeleft=time;
            timelabel.setText("Time left: " + time + " seconds");
        }else{
            endQuiz();
        }
    }
    private void endQuiz(){
        timer.stop();
        StringBuilder resultmsg=new StringBuilder("Quiz Over!\nYour score: "+score+"\n\nCorrect Answers:\n");
        for(String[] question:questions){
            resultmsg.append(question[0]).append("\nCorrect Answers: \n").append(question[Integer.parseInt(question[5])+1]).append("\n\n")    ;

        }
        JOptionPane.showMessageDialog(null, resultmsg.toString());
        System.exit(0);
    }
    private class SubmitButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i=0;i<4;i++){
                if(optionButtons[i].isSelected()){
                    if(i==Integer.parseInt(questions[currentQuestionIndex][5])){
                        score++;
                    }
                    break;
                }
            }
            currentQuestionIndex++;
            loadQuestion(currentQuestionIndex);
        }
    }
    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            timeleft--;
            timelabel.setText("Time left: " + timeleft + " seconds");
            if (timeleft <= 0) {
                currentQuestionIndex++;
                loadQuestion(currentQuestionIndex);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Task4 app = new Task4();
            app.setVisible(true);
        });
    }
}

