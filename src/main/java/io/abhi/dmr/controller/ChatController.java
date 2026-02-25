package io.abhi.dmr.controller;

import io.abhi.dmr.service.ChatService;
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

  private final ChatClient gemmaChatClient;
  private final ChatClient qwenChatClient;

  private final ChatService chatService;

  @PostMapping("/qwen")
  ResponseEntity<Map<String, String>> chatWithQwen(@RequestParam("q") final String question) {
    final var response = qwenChatClient.prompt().user(question.trim()).call().content();
    final Map<String, String> responseMap = new HashMap<>();
    responseMap.put("You", question);
    responseMap.put("Qwen", response);
    return ResponseEntity.ok(responseMap);
  }

  @PostMapping("/gemma")
  ResponseEntity<Map<String, String>> chatWithGemma(@RequestParam("q") final String question) {
    final var response = gemmaChatClient.prompt().user(question.trim()).call().content();
    final Map<String, String> responseMap = new HashMap<>();
    responseMap.put("You", question);
    responseMap.put("Qwen", response);
    return ResponseEntity.ok(responseMap);
  }

  @PostMapping
  ResponseEntity<String> chat(@RequestParam("q") final String query) {
    return ResponseEntity.ok(chatService.chat(query));
  }
}
