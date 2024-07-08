package com.example.gui;

import com.example.dao.Course_dao;
import com.example.dao.Student_dao;
import com.example.model.Course;
import com.example.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MainFrame extends JFrame {
    private Course_dao courseDAO;
    private Student_dao studentDAO;
    private JTextArea courseListArea;
    private JTextField studentIdField;
    private JTextField studentNameField;
    private JTextField courseCodeField;
    private JTextField dropStudentIdField;
    private JTextField dropCourseCodeField;

    public MainFrame() {
        courseDAO = new Course_dao();
        studentDAO = new Student_dao();

        setTitle("Course Registration System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create UI components
        courseListArea = new JTextArea(10, 50);
        courseListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(courseListArea);

        JButton loadCoursesButton = new JButton("Load Courses");
        loadCoursesButton.addActionListener(new LoadCoursesAction());

        JPanel registerPanel = new JPanel(new GridLayout(4, 2));
        registerPanel.setBorder(BorderFactory.createTitledBorder("Register Student"));
        studentIdField = new JTextField();
        studentNameField = new JTextField();
        courseCodeField = new JTextField();
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterAction());

        registerPanel.add(new JLabel("Student ID:"));
        registerPanel.add(studentIdField);
        registerPanel.add(new JLabel("Student Name:"));
        registerPanel.add(studentNameField);
        registerPanel.add(new JLabel("Course Code:"));
        registerPanel.add(courseCodeField);
        registerPanel.add(new JLabel());
        registerPanel.add(registerButton);

        JPanel dropPanel = new JPanel(new GridLayout(3, 2));
        dropPanel.setBorder(BorderFactory.createTitledBorder("Drop Course"));
        dropStudentIdField = new JTextField();
        dropCourseCodeField = new JTextField();
        JButton dropButton = new JButton("Drop Course");
        dropButton.addActionListener(new DropAction());

        dropPanel.add(new JLabel("Student ID:"));
        dropPanel.add(dropStudentIdField);
        dropPanel.add(new JLabel("Course Code:"));
        dropPanel.add(dropCourseCodeField);
        dropPanel.add(new JLabel());
        dropPanel.add(dropButton);

        // Add components to frame
        add(scrollPane, BorderLayout.NORTH);
        add(loadCoursesButton, BorderLayout.CENTER);
        add(registerPanel, BorderLayout.WEST);
        add(dropPanel, BorderLayout.EAST);
    }

    private class LoadCoursesAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Course> courses = courseDAO.getAllCourses();
                courseListArea.setText("");
                for (Course course : courses) {
                    courseListArea.append("Course Code: " + course.getCourseCode() + "\n");
                    courseListArea.append("Title: " + course.getTitle() + "\n");
                    courseListArea.append("Description: " + course.getDescription() + "\n");
                    courseListArea.append("Capacity: " + course.getCapacity() + "\n");
                    courseListArea.append("Schedule: " + course.getSchedule() + "\n\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(MainFrame.this, "Error loading courses.");
            }
        }
    }

    private class RegisterAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String studentId = studentIdField.getText();
            String studentName = studentNameField.getText();
            String courseCode = courseCodeField.getText();
            if (studentId.isEmpty() || studentName.isEmpty() || courseCode.isEmpty()) {
                JOptionPane.showMessageDialog(MainFrame.this, "Please fill all fields.");
                return;
            }
            Student student = new Student();
            student.setStudentId(studentId);
            student.setName(studentName);
            try {
                studentDAO.registerStudent(student, courseCode);
                JOptionPane.showMessageDialog(MainFrame.this, "Student registered successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(MainFrame.this, "Error registering student.");
            }
        }
    }

    private class DropAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String studentId = dropStudentIdField.getText();
            String courseCode = dropCourseCodeField.getText();
            if (studentId.isEmpty() || courseCode.isEmpty()) {
                JOptionPane.showMessageDialog(MainFrame.this, "Please fill all fields.");
                return;
            }
            try {
                studentDAO.dropCourse(studentId, courseCode);
                JOptionPane.showMessageDialog(MainFrame.this, "Course dropped successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(MainFrame.this, "Error dropping course.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
