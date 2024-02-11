package com.tool.RecruitXpert.Service;

import com.tool.RecruitXpert.Entities.ResumeEntity;
import com.tool.RecruitXpert.Entities.User;
import com.tool.RecruitXpert.Exceptions.ResumeNotFoundException;
import com.tool.RecruitXpert.Exceptions.UserNotFoundException;
import com.tool.RecruitXpert.Repository.ResumeRepository;
import com.tool.RecruitXpert.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.xmlgraphics.util.MimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.tool.RecruitXpert.ResumeUtility.ResumeUtilities.getMediaType;
import static com.tool.RecruitXpert.Transformer.ResumeBuilderTransformer.buildResumeObject;

@Service
@Slf4j
public class ResumeService {
    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    UserRepository userRepository;


    // get all list of resumes

    // save resume in db
    public String saveResumeToDb(MultipartFile file, int userId) throws IOException {

        if (!userRepository.existsById(userId))
            throw new RuntimeException("User not found");

        User user = userRepository.findById(userId).get();

        ResumeEntity imageData = resumeRepository.save(ResumeEntity.builder()
                .name(file.getOriginalFilename())
                .docType(file.getContentType())
                .imageData(file.getBytes()).user(user).build());

        user.getResumeList().add(imageData);
        userRepository.save(user);

        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        } else return "File not uploaded successfully";
    }

//        long sizeInBytes = file.getSize(); // Getting size of the uploaded file in bytes
//        long sizeInKb=sizeInBytes/1024;
//        long sizeInMb = sizeInBytes /2048; // Converting bytes to megabyte
//        long lowBound=50;
//        long upperBound=1; // 1mb size
//        if(sizeInKb<50) {
//            log.error("resume size is less than  50kb log");
//            throw new IOException("uploaded Resume doc size"+sizeInKb+"KB is lessthan required size"+lowBound);
//        }
//
//        if(sizeInMb>upperBound) {
//            log.error("resume size is greater than  1mb log");
//            throw new IOException("uploaded image size"+sizeInMb+"MB is greaterthan required size"+upperBound);
//        }
//
//        User user = userRepository.findById(userId).get();
//        ResumeEntity resume= buildResumeObject(file, user);
//        resumeRepository.save(resume);
//        user.getResumeList().add(resume);
//        userRepository.save(user);
//
////        get current versioning  == 0
////        set resumeId
//        return "resume uploaded successfully";
//    }

    // download  Resume service

    public ResponseEntity<?> downloadResume(Integer id) {

//        Optional<ResumeEntity> dbImageData = resumeRepository.findById(id);
//        ResumeEntity resumeEntity = dbImageData.get();
//        resumeEntity.setName("resume.docx");
//        byte[] images = dbImageData.get().getImageData();
//
//        String typearray[] = dbImageData.get().getDocType().split("/");
//        String type = typearray[1];
//
//        MediaType type1 = getMediaType(type);
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(getMediaType(type))
//                .body(images);
//    }

        Optional<ResumeEntity> resumeList = resumeRepository.findById(id);
        byte[] images = resumeList.get().getImageData();

        String typearray[] = resumeList.get().getDocType().split("/");
        String type = typearray[1];

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(getMediaType(type)).body(images);
    }

//
//
//    public byte[] generatePDF(List<byte[]> xmlDataList) throws IOException, TransformerException {
//        // Step 1: Prepare the XSLT transformation
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        Transformer transformer = transformerFactory.newTransformer();
//
//        // Step 2: Prepare FOP
//        FopFactory fopFactory = FopFactory.newInstance();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
//
//        for (byte[] xmlData : xmlDataList) {
//            // Step 3: Perform XSLT transformation
//            Source src = new StreamSource(new ByteArrayInputStream(xmlData));
//            Result res = new SAXResult(fop.getDefaultHandler());
//            transformer.transform(src, res);
//        }
//
//        return out.toByteArray();
//    }
//
//    public List<byte[]> showResume(int userId) {
//        Optional<User> op = userRepository.findById(userId);
//        User user = op.get();
//        List<ResumeEntity> resumeList = user.getResumeList();
//
//        if(resumeList.size() == 0)
//            throw new RuntimeException("No resumes present");
//
//        List<byte[]> xmlDataList = new ArrayList<>();
//
//        for(ResumeEntity resume : resumeList){
//            xmlDataList.add(resume.getXmlData());
//        }
//
//        try {
//            return Collections.singletonList(generatePDF(xmlDataList));
//        } catch (IOException | TransformerException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to generate PDF from XML");
//        }
//    }

    public List<byte []> showResume(int userId) {
        Optional<User> op = userRepository.findById(userId);
        User user = op.get();
        List<ResumeEntity> resumeList = user.getResumeList();

        if(resumeList.size() == 0)
            throw new RuntimeException("No resumes present");

        List<byte[]> byteArrList = new ArrayList<>();

        for(ResumeEntity resume : resumeList){
            byteArrList.add(resume.getImageData());
        }
        return byteArrList;
    }


    // find all resumes of particular User with user id
    public List<InputStream> findAllResumes(int userid) throws Exception {
        Optional<User> user = userRepository.findById(userid);

        if (user.isEmpty())
            throw new UserNotFoundException("user not found with userId " + userid);

        User user1 = user.get();
        // check this case is working or not
        List<ResumeEntity> resumeEntitiesList = user.get().getResumeList();
        if (resumeEntitiesList.size() == 0) {
            throw new ResumeNotFoundException("Resume  Not Found Exception");
        }

        List<ResumeEntity> returnResumes = new ArrayList<>();
        for (ResumeEntity resumeEntity : resumeEntitiesList) {
            returnResumes.add(resumeEntity);
        }

        List<InputStream> pdfStreams = new ArrayList<>();
        for (ResumeEntity pdfEntity : resumeEntitiesList) {
            // Create an InputStream from the byte array of the PDF content
            InputStream pdfStream = new ByteArrayInputStream(pdfEntity.getImageData());
            pdfStreams.add(pdfStream);
        }
        return pdfStreams;
    }

    // Delete resume by resumeId service
    public String deleteResumeByResumeId(int resumeId) throws Exception {

        if (!resumeRepository.existsById(resumeId))
            throw new Exception("resume not found exception"); //if resume id not found throw exception

        resumeRepository.deleteById(resumeId);
        return resumeId + " resume : " + resumeId + " deleted successfully";
    }

    //delete resumes of user by user id services
    public String deleteAllResumesByUserId(int userId) {

        if (!userRepository.existsById(userId))
            throw new UserNotFoundException("user not found with userId " + userId);

        User user = userRepository.findById(userId).get();
        List<ResumeEntity> list = user.getResumeList();
        resumeRepository.deleteAll(list);

        return "successfully deleted resumes of userId :" + userId;
    }


    public List<ResumeEntity> getResumeList() {
        return resumeRepository.findAll();
    }

    public String versionControlMethod(int resumeId) {

        // set resume id in user entity
        Optional<ResumeEntity> optional = resumeRepository.findById(resumeId);
        ResumeEntity resume = optional.get();
        int userId = resume.getUser().getUserId();

        User user = userRepository.findById(userId).get();
        user.setCurrentResumeVersion(resumeId);

        userRepository.save(user);

        return "resume updated successfully";
    }


}

