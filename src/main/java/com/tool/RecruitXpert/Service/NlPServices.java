package com.tool.RecruitXpert.Service;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class NlPServices {

    private static StanfordCoreNLP stanfordCoreNLP;
    private static Properties properties;
    private static String propertiesName="tokenize, ssplit, pos,  lemma, ner, parse, sentiment";

    private NlPServices(){

    }

    static {
        properties=new Properties();
        properties.setProperty("annotators", propertiesName);
    }

    @Bean(value = "stanfordCoreNLP")
    public static  StanfordCoreNLP getPipeLine(){
        if(stanfordCoreNLP==null)
        {
            return stanfordCoreNLP=new StanfordCoreNLP(properties);
        }
        return stanfordCoreNLP;
    }

}
