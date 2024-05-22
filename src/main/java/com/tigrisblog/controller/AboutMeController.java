package com.tigrisblog.controller;

import com.tigrisblog.entity.AboutMe;
import com.tigrisblog.service.impl.AboutMeService;
import com.tigrisblog.utils.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.AboutMeCTRL.CTRL)
public class AboutMeController {

  private final AboutMeService aboutMeService;

  @PostMapping
  public ResponseEntity<AboutMe> saveAboutMe(@RequestBody AboutMe aboutMe){
    return new ResponseEntity<>(aboutMeService.saveAboutMe(aboutMe), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<AboutMe> getAboutMe(){
    return new ResponseEntity<>(aboutMeService.getAboutMe(),HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAboutMe(@PathVariable Long id){
    aboutMeService.deleteAboutMe(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
