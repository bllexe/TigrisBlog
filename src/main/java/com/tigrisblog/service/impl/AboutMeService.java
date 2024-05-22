package com.tigrisblog.service.impl;

import com.tigrisblog.entity.AboutMe;
import com.tigrisblog.repos.AboutmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AboutMeService {

  private  final AboutmeRepository aboutmeRepository;


  public AboutMe saveAboutMe(AboutMe aboutMe) {
    return aboutmeRepository.save(aboutMe);
  }

  public AboutMe getAboutMe() {
    return aboutmeRepository.findAll().get(0);
  }

  public void deleteAboutMe(long id){
    aboutmeRepository.deleteById(id);
  }

}
