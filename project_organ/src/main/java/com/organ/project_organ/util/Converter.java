package com.organ.project_organ.util;

import com.organ.project_organ.pojo.ResourcesListDTO;

import java.util.ArrayList;
import java.util.Collections;

public class Converter {
    public static ResourcesListDTO fromStringArray(String[] resources) {
        ResourcesListDTO list = new ResourcesListDTO();
        list.resource = new ArrayList<>();

        Collections.addAll(list.resource, resources);

        return list;
    }
}
