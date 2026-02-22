package io.abhi.dmr.service;

import io.abhi.dmr.types.LiteraryInfo;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PoemAnalyzer {

  private final ChatClient gemmaChatClient;
  private final ChatClient qwenChatClient;

  public LiteraryInfo analyze(final String poemTitle) {

    PromptTemplate systemTemplate = new PromptTemplate(new ClassPathResource("prompts/system.st"));

    PromptTemplate userTemplate = new PromptTemplate(new ClassPathResource("prompts/user.st"));

    return qwenChatClient
        .prompt()
        .system(systemTemplate.create().getContents())
        .user(userTemplate.create(Map.of("poem_title", poemTitle)).getContents())
        .call()
        .entity(LiteraryInfo.class);
  }
}
