package io.abhi.dmr.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/chat")
public class ChatController {

  private final ChatClient chatClient;

  @Autowired
  public ChatController(final ChatClient.Builder builder) {
    this.chatClient = builder.build();
  }

  @PostMapping
  ResponseEntity<Map<String, String>> chat(@RequestParam("q") final String question) {
    final var response = chatClient.prompt().user(question.trim()).call().content();
    final Map<String, String> responseMap = new HashMap<>();
    responseMap.put("You", question);
    responseMap.put("AI", response);
    return ResponseEntity.ok(responseMap);
  }
}
