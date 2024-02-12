package com.tool.RecruitXpert.Controller;


import com.tool.RecruitXpert.DTO.RecruiterDto.responseDto.ResponseForResumeName;
import com.tool.RecruitXpert.ResumeUtility.ResumeUtilities;
import com.tool.RecruitXpert.Service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resume")
@Slf4j
@CrossOrigin(origins = "https://blue-arda-82.tiiny.site/")

public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    ResumeUtilities resumeUtilities;

    // setting current versioning
    @PutMapping("/setCurrentVersion/{resumeID}")
    public ResponseEntity<?> versionControlMethod(@PathVariable int resumeId) {
        try {
            String response = resumeService.versionControlMethod(resumeId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    // upload resume by specific user
    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file,
                                               @RequestParam("userid") int userid) throws IOException {
        try {
            String uploadResume = resumeService.saveResumeToDb(file, userid);
//            return ResponseEntity.status(HttpStatus.OK).body(uploadResume);
            return ResponseEntity.ok().body("{\"message\": \"" + uploadResume + "\"}");
        }
        catch (IOException e) {
            log.error("resume size not in correct format");
            throw new IOException(e.getMessage());
        }
        catch (Exception e){
            return new ResponseEntity<>("Resume size is exceed > 1 mb", HttpStatus.BAD_REQUEST);
        }
    }

    // view resume by id
    @GetMapping("/viewResumes/{resumeid}")
    public ResponseEntity<?> viewImage(@PathVariable Integer resumeid) throws Exception {
        try {
            return resumeService.downloadResume(resumeid);
        }
        catch (Exception e) {
            String error = ("resume with " + resumeid + " not found : " + e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    //Versioning : update this logic to above endpoints
    @GetMapping("/viewAllResume/{id}")
    public ResponseEntity<List<ResponseForResumeName>> showResume(@PathVariable("id") int userId) throws Exception{
        try {
            List<ResponseForResumeName> list =  resumeService.showResume(userId);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }
    }

    // delete resume by resume id
    @DeleteMapping("deleteResumeById/{resumeId}")
    public ResponseEntity<String> deleteResume(@PathVariable Integer resumeid) {
        try {
            resumeService.deleteResumeByResumeId(resumeid);
        } catch (Exception e) {
            log.error("resumeId not found");
            throw new RuntimeException(e.getMessage());
        }
        return new ResponseEntity<>("file deleted successfully", HttpStatus.OK);
    }

    // delete resumes of user by userid
    @DeleteMapping("/deleteByUserId/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer userId) throws Exception {
         try {
             String message = resumeService.deleteAllResumesByUserId(userId);
             return new ResponseEntity<>(message, HttpStatus.OK);
         }
         catch (Exception e){ throw new Exception(e.getMessage()); }
    }

}
