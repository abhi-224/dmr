package io.abhi.dmr.types;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LiteraryInfo {
  private String poemTitle;
  private String poemAuthor;
  private Integer publishYear;
  private String poemSummary;
  private String language;
  private List<String> themes;
}
