package io.abhi.dmr.service;

import io.abhi.dmr.types.LiteraryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PoemAnalyzer {

  private final ChatClient qwenChatClient;

  public LiteraryInfo analyze(final String poemTitle) {

    final String templ =
        """
    You are a literary analysis expert.

    Return ONLY valid JSON matching this structure:

    poemTitle: string
    poemAuthor: string
    publishYear: integer or null
    poemSummary: string
    language: string
    themes: list of strings

    If a value is unknown, return null.

    Now analyze this poem:
    {poem_title}
    """;

    return qwenChatClient
        .prompt()
        .user(u -> u.text(templ).param("poem_title", poemTitle))
        .call()
        .entity(LiteraryInfo.class);
  }
}
