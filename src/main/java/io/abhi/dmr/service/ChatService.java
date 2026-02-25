package io.abhi.dmr.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
  private final ChatClient qwenChatClient;

  public String chat(final String query) {
    return qwenChatClient
        .prompt()
        .system(
            s ->
                s.text(
                    "You are a literary expert. Explain the meaning of following poem. Use plain text no markdown or any code."))
        .user(u -> u.text(query))
        .call()
        .content();
  }
}
