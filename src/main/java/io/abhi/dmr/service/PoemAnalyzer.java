package io.abhi.dmr.service;

import io.abhi.dmr.types.LiteraryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PoemAnalyzer {

  private final ChatClient qwenChatClient;

  public LiteraryInfo analyze(String message) {
    return qwenChatClient.prompt(message).call().entity(LiteraryInfo.class);
  }
}
