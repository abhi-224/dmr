package io.abhi.dmr.config;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiModelConfig {

  @Value("${ai.models.qwen}")
  private String qwenModelName;

  @Value("${ai.models.gemma}")
  private String gemmaModelName;

  @Bean(name = "qwenModel")
  public OllamaChatModel qwenModel(OllamaApi ollamaApi) {
    return OllamaChatModel.builder()
        .ollamaApi(ollamaApi)
        .defaultOptions(
            OllamaChatOptions.builder()
                .model(qwenModelName)
                .temperature(0.2)
                .numPredict(300)
                .build())
        .build();
  }

  @Bean(name = "gemmaModel")
  public OllamaChatModel gemmaModel(OllamaApi ollamaApi) {
    return OllamaChatModel.builder()
        .ollamaApi(ollamaApi)
        .defaultOptions(
            OllamaChatOptions.builder()
                .model(gemmaModelName)
                .temperature(0.2)
                .numPredict(300)
                .build())
        .build();
  }
}
