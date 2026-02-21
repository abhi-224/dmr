package io.abhi.dmr.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiClientConfig {

  @Bean(name = "qwenChatClient")
  public ChatClient qwenChatClient(@Qualifier("qwenModel") OllamaChatModel qwenModel) {
    return ChatClient.builder(qwenModel).build();
  }

  @Bean(name = "gemmaChatClient")
  public ChatClient gemmaChatClient(@Qualifier("gemmaModel") OllamaChatModel gemmaModel) {
    return ChatClient.builder(gemmaModel).build();
  }
}
