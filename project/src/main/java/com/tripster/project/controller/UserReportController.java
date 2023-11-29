package com.tripster.project.controller;

import com.tripster.project.dto.UserReportDTO;
import com.tripster.project.mapper.UserReportDTOMapper;
import com.tripster.project.model.User;
import com.tripster.project.model.UserReport;
import com.tripster.project.service.UserReportServiceImpl;
import com.tripster.project.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/userReports")
public class UserReportController {
    @Autowired
    private UserReportServiceImpl userReportService;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<UserReportDTO>> getAll() {

        List<UserReport> reports = userReportService.findAll();
        List<UserReportDTO> dtos = new ArrayList<UserReportDTO>();
        for (UserReport rep : reports) {
            dtos.add(UserReportDTOMapper.fromUserReportToDTO(rep));
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);

    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserReportDTO> getOne(@PathVariable Long id) {
        UserReport report = userReportService.findOne(id);
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserReportDTO dto = UserReportDTOMapper.fromUserReportToDTO(report);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserReportDTO> addNew(@RequestBody UserReportDTO userReportDTO) {

        UserReport report = UserReportDTOMapper.fromDTOToUserReport(userReportDTO);
        User reporter = userService.findOne(userReportDTO.getReporterId());
        if (reporter == null) {
            return new ResponseEntity<>(userReportDTO, HttpStatus.BAD_REQUEST);
        }
        User reportee = userService.findOne(userReportDTO.getReporteeId());
        if (reportee == null) {
            return new ResponseEntity<>(userReportDTO, HttpStatus.BAD_REQUEST);
        }
        report.setReporter(reporter);
        report.setReportee(reportee);
        userReportService.save(report);
        return new ResponseEntity<>(userReportDTO, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        UserReportDTO reportDTO = UserReportDTOMapper.fromUserReportToDTO(userReportService.findOne(id));

        if (reportDTO != null) {
            userReportService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
