package io.abhi.dmr.controller;

import io.abhi.dmr.service.PoemAnalyzer;
import io.abhi.dmr.types.LiteraryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poem/analyze")
@RequiredArgsConstructor
public class AnalyzerController {
  private final PoemAnalyzer poemAnalyzer;

  @PostMapping
  public ResponseEntity<LiteraryInfo> analyze(@RequestParam("poem") final String poem) {
    return ResponseEntity.ok(poemAnalyzer.analyze(poem));
  }
}
