package emsi.ssii.devoir.services;

import java.util.List;

import emsi.ssii.devoir.models.Software;

public interface SoftwareService {
    Software add(Software software);

    Software update(Software software);

    Software delete(Software software);

    Software findById(int id);

    List<Software> findAll();
}
