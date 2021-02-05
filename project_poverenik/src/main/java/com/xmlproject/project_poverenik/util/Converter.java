package com.xmlproject.project_poverenik.util;

import com.xmlproject.project_poverenik.model.xml_zalba_na_cutanje.ZalbaNaCutanje;
import com.xmlproject.project_poverenik.model.xml_zalbanaodluku.ZalbaNaOdluku;
import pojo.ComplaintsExtendedDTO;
import pojo.ComplaintsListDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Converter {
    public static ComplaintsListDTO fromStringArray(String[] complaints) {
        ComplaintsListDTO list = new ComplaintsListDTO();
        list.complaint = new ArrayList<>();

        Collections.addAll(list.complaint, complaints);

        return list;
    }

    public static ComplaintsExtendedDTO fromZalbe(ArrayList<?> zalbe) {
        ComplaintsExtendedDTO complaints = new ComplaintsExtendedDTO();
        complaints.complaint = new ArrayList<ComplaintsExtendedDTO.Complaint>();

        for (Object o: zalbe){
            ComplaintsExtendedDTO.Complaint complaint = new ComplaintsExtendedDTO.Complaint();
            try {
                ZalbaNaOdluku z = (ZalbaNaOdluku) o;
                complaint.value = z.getId();
                complaint.status = z.getStatus().getValue();
            }
            catch (Exception e){
                ZalbaNaCutanje z = (ZalbaNaCutanje) o;
                complaint.value = z.getId();
                complaint.status = z.getStatus().getValue();

            }
            complaints.complaint.add(complaint);

        }

        return complaints;

    }
}