package io.abhi.dmr.advisors;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.model.ChatResponse;

@Slf4j
public class TokenUsageAdvisor implements CallAdvisor {

  @Override
  public ChatClientResponse adviseCall(
      ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {

    final var chatCallResponse = callAdvisorChain.nextCall(chatClientRequest);

    Optional.ofNullable(chatCallResponse.chatResponse())
        .map(ChatResponse::getMetadata)
        .ifPresent(
            metadata -> {
              log.debug("Prompt Tokens::{}", metadata.getUsage().getPromptTokens());
              log.debug("Completion Tokens::{}", metadata.getUsage().getCompletionTokens());
              log.debug("Total Tokens::{}", metadata.getUsage().getTotalTokens());
            });

    return chatCallResponse;
  }

  @Override
  public String getName() {
    return getClass().getSimpleName();
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
