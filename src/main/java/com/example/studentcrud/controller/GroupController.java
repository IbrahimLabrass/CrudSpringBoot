package com.example.studentcrud.controller;

import com.example.studentcrud.domain.Group;
import com.example.studentcrud.service.ExportService;
import com.example.studentcrud.service.GroupService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private ExportService exportService;


    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.listAll();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Group> addGroup(@RequestBody Group group) {
        Group savedGroup = groupService.save(group);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
        Group group = groupService.get(id);
        if (group != null) {
            return new ResponseEntity<>(group, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/export/excel")
    public void exportGroupsToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=groups.xlsx");

        List<Group> groups = groupService.listAll();
        OutputStream outputStream = response.getOutputStream();
        exportService.exportGroupsToExcel(groups, outputStream);
        outputStream.close();
    }

    @GetMapping("/export/pdf")
    public void exportGroupsToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=groups.pdf");

        List<Group> groups = groupService.listAll();
        OutputStream outputStream = response.getOutputStream();
        exportService.exportGroupsToPDF(groups, outputStream);
        outputStream.close();
    }
}
