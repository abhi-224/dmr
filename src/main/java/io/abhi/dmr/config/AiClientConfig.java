package io.abhi.dmr.config;

import io.abhi.dmr.advisors.TokenUsageAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.BaseChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiClientConfig {

  @Bean(name = "qwenChatClient")
  public ChatClient qwenChatClient(
      @Qualifier("qwenModel") OllamaChatModel qwenModel, ChatMemory chatMemory) {

    BaseChatMemoryAdvisor chatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();

    return ChatClient.builder(qwenModel)
        .defaultAdvisors(chatMemoryAdvisor, new TokenUsageAdvisor(), new SimpleLoggerAdvisor())
        .defaultOptions(ChatOptions.builder().maxTokens(2000).temperature(0.2).build())
        .build();
  }

  @Bean(name = "gemmaChatClient")
  public ChatClient gemmaChatClient(@Qualifier("gemmaModel") OllamaChatModel gemmaModel) {
    return ChatClient.builder(gemmaModel)
        .defaultAdvisors(new TokenUsageAdvisor(), new SimpleLoggerAdvisor())
        .defaultOptions(ChatOptions.builder().maxTokens(400).temperature(0.2).build())
        .build();
  }
}
