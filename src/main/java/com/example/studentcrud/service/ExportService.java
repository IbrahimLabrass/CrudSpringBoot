package com.example.studentcrud.service;

import com.example.studentcrud.domain.Group;
import com.example.studentcrud.domain.School;
import com.example.studentcrud.domain.Student;
import com.example.studentcrud.domain.Teacher;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExportService {


    // Students export
    public void exportToExcel(List<Student> students, OutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");

        // Create header row
        Row headerRow = ((Sheet) sheet).createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Course");
        headerRow.createCell(3).setCellValue("Fee");

        // Populate data rows
        int rowNum = 1;
        for (Student student : students) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getStudentname());
            row.createCell(2).setCellValue(student.getCourse());
            row.createCell(3).setCellValue(student.getFee());
        }

        workbook.write(outputStream);
    }

    public void exportToPDF(List<Student> students, OutputStream outputStream) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 700);
        contentStream.showText("Student Data");
        contentStream.endText();

        int y = 650;
        for (Student student : students) {
            contentStream.beginText();
            contentStream.newLineAtOffset(100, y);
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            contentStream.showText("ID: " + student.getId());
            contentStream.newLine();
            contentStream.showText("Name: " + student.getStudentname());
            contentStream.newLine();
            contentStream.showText("Course: " + student.getCourse());
            contentStream.newLine();
            contentStream.showText("Fee: " + student.getFee());
            contentStream.endText();
            y -= 50;
        }

        contentStream.close();
        document.save(outputStream);
        document.close();
    }



    // teacher export

        public void exportTeachersToExcel(List<Teacher> teachers, OutputStream outputStream) throws IOException {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Teachers");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Teacher Name");
            headerRow.createCell(2).setCellValue("Subject");

            int rowNum = 1;
            for (Teacher teacher : teachers) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(teacher.getId());
                row.createCell(1).setCellValue(teacher.getTeacherName());
                row.createCell(2).setCellValue(teacher.getSubject());
            }

            workbook.write(outputStream);
        }

        public void exportTeachersToPDF(List<Teacher> teachers, OutputStream outputStream) throws IOException {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText("Teacher Data");
            contentStream.endText();

            int y = 650;
            for (Teacher teacher : teachers) {
                contentStream.beginText();
                contentStream.newLineAtOffset(100, y);
                contentStream.setFont(PDType1Font.HELVETICA, 10);
                contentStream.showText("ID: " + teacher.getId());
                contentStream.newLine();
                contentStream.showText("Name: " + teacher.getTeacherName());
                contentStream.newLine();
                contentStream.showText("Subject: " + teacher.getSubject());
                contentStream.endText();
                y -= 50;
            }

            contentStream.close();
            document.save(outputStream);
            document.close();
        }

        // group export
        public void exportGroupsToExcel(List<Group> groups, OutputStream outputStream) throws IOException {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Groups");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Group Name");
            headerRow.createCell(2).setCellValue("Description");

            int rowNum = 1;
            for (Group group : groups) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(group.getId());
                row.createCell(1).setCellValue(group.getGroupName());
                row.createCell(2).setCellValue(group.getDescription());
            }

            workbook.write(outputStream);
        }

    public void exportGroupsToPDF(List<Group> groups, OutputStream outputStream) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 700);
        contentStream.showText("Group Data");
        contentStream.endText();

        int y = 650;
        for (Group group : groups) {
            contentStream.beginText();
            contentStream.newLineAtOffset(100, y);
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            contentStream.showText("ID: " + group.getId());
            contentStream.newLine();
            contentStream.showText("Name: " + group.getGroupName());
            contentStream.newLine();
            contentStream.showText("Description: " + group.getDescription());
            contentStream.endText();
            y -= 50;
        }

        contentStream.close();
        document.save(outputStream);
        document.close();
    }

    // school export

    public void exportSchoolsToExcel(List<School> schools, OutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Schools");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("School Name");
        headerRow.createCell(2).setCellValue("Location");

        int rowNum = 1;
        for (School school : schools) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(school.getId());
            row.createCell(1).setCellValue(school.getSchoolName());
            row.createCell(2).setCellValue(school.getLocation());
        }

        workbook.write(outputStream);


    }

    public void exportSchoolsToPDF(List<School> schools, OutputStream outputStream) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(100, 700);
        contentStream.showText("School Data");
        contentStream.endText();

        int y = 650;
        for (School school : schools) {
            contentStream.beginText();
            contentStream.newLineAtOffset(100, y);
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            contentStream.showText("ID: " + school.getId());
            contentStream.newLine();
            contentStream.showText("Name: " + school.getSchoolName());
            contentStream.newLine();
            contentStream.showText("Location: " + school.getLocation());
            contentStream.endText();
            y -= 50;
        }

        contentStream.close();
        document.save(outputStream);
        document.close();
    }
}
