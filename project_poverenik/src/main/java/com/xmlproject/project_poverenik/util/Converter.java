package com.xmlproject.project_poverenik.util;

import pojo.ComplaintsListDTO;

import java.util.ArrayList;
import java.util.Collections;

public class Converter {
    public static ComplaintsListDTO fromStringArray(String[] complaints) {
        ComplaintsListDTO list = new ComplaintsListDTO();
        list.complaint = new ArrayList<>();

        Collections.addAll(list.complaint, complaints);

        return list;
    }
}
