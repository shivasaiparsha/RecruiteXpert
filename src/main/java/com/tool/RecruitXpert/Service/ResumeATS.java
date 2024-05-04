package com.tool.RecruitXpert.Service;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.*;

@Service
public class ResumeATS {


    public String  getThePercentageOfresumeWithJd(MultipartFile resume, MultipartFile jobDesc) throws RuntimeException
    {
             if(resume.isEmpty())
                 throw new RuntimeException("resume file is empty");
             if(jobDesc.isEmpty())
                 throw new RuntimeException("Job Descryption is empty");

             if(!resume.getContentType().equalsIgnoreCase("application/pdf")||!jobDesc.getContentType().equalsIgnoreCase("application/pdf"))
             {
                 throw new RuntimeException("file not supported");
             }

             double percentage=getThePercentage(resume, jobDesc);
             return "percentage Matches "+percentage;
    }
    public double getThePercentage(MultipartFile resume, MultipartFile jobDesc)
    {
        try {
            String resumeText=ParseMultiPartFileText(resume);
            String JdText=ParseMultiPartFileText(jobDesc);
            double percentage=getPercentage(resumeText, JdText);
            return percentage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<CoreLabel> getCoreLableList(String text)
    {
        StanfordCoreNLP stanfordCoreNLP=NlPServices.getPipeLine();
        CoreDocument coreDocument=new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> resumeCoreLabelList=  coreDocument.tokens();
        return resumeCoreLabelList;
    }
    private double getPercentage(String resumeText, String jdText) {

        List<CoreLabel> resumeCoreLableSet =getCoreLableList(resumeText);
        String arr[]=jdText.split(" ");
        Set<String> jd= Arrays.stream(arr).collect(Collectors.toSet());


         int noOfWordsMatching=0;
         HashSet<String> set=new HashSet<>();
         for(CoreLabel coreLabel : resumeCoreLableSet)
         {

             String originalText= coreLabel.originalText();

             if(jd.contains(originalText)&&!set.contains(originalText)){

                 noOfWordsMatching++;
                 set.add(originalText);
                 System.out.println(noOfWordsMatching+" "+originalText);
             }
         }

         return  ((double) noOfWordsMatching/(double) jd.size())*100;

    }

    public String ParseMultiPartFileText(MultipartFile file) throws IOException {


            PDDocument documentFile = PDDocument.load(file.getInputStream());
            PDFTextStripper stripper = new PDFTextStripper();
            String parsedText = stripper.getText(documentFile);
            documentFile.close();
       return parsedText;
    }
}
