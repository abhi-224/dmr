package io.abhi.dmr.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/chat")
@RequiredArgsConstructor
public class ChatController {

  //  @Qualifier("gemmaChatClient")
  private final ChatClient gemmaChatClient;
  private final ChatClient qwenChatClient;

  //  @Qualifier("qwenChatClient")

  @PostMapping
  ResponseEntity<Map<String, String>> chat(
      @RequestParam("q") final String question, @RequestParam("model") final String model) {

    if (model.trim().equals("gemma")) {
      final var response = gemmaChatClient.prompt().user(question.trim()).call().content();
      final Map<String, String> responseMap = new HashMap<>();
      responseMap.put("You", question);
      responseMap.put("Gemma", response);
      return ResponseEntity.ok(responseMap);
    }

    if (model.trim().equals("qwen")) {
      final var response = qwenChatClient.prompt().user(question.trim()).call().content();
      final Map<String, String> responseMap = new HashMap<>();
      responseMap.put("You", question);
      responseMap.put("Qwen", response);
      return ResponseEntity.ok(responseMap);
    }

    return ResponseEntity.badRequest().body(Map.of("err", "invalid model name"));
  }
}
