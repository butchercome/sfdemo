package com.cjw.demo.refactor.book;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Javen on 2017/6/7.
 */
public class SubstituteAlgorithm {
    String foundPerson(String[] people){
        for(int i=0; i < people.length; i++){
            if(people[i].equals("Don")){
                return "Don";
            }
            if(people[i].equals("John")){
                return "John";
            }
            if(people[i].equals("Kent")){
                return "Kent";
            }
        }
        return "";
    }
    class SubstituteAlgorithmRefactor {
        String foundPerson(String[] people) {
            List candidates = Arrays.asList(new String[]{"Don", "John", "kent"});
            for (int i = 0; i <people.length; i++) {
                if (candidates.contains(people[i]))
                    return people[i];
            }
            return "";
        }
    }
}
