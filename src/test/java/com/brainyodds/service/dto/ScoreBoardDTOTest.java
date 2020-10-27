package com.brainyodds.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.brainyodds.web.rest.TestUtil;

public class ScoreBoardDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ScoreBoardDTO.class);
        ScoreBoardDTO scoreBoardDTO1 = new ScoreBoardDTO();
        scoreBoardDTO1.setId(1L);
        ScoreBoardDTO scoreBoardDTO2 = new ScoreBoardDTO();
        assertThat(scoreBoardDTO1).isNotEqualTo(scoreBoardDTO2);
        scoreBoardDTO2.setId(scoreBoardDTO1.getId());
        assertThat(scoreBoardDTO1).isEqualTo(scoreBoardDTO2);
        scoreBoardDTO2.setId(2L);
        assertThat(scoreBoardDTO1).isNotEqualTo(scoreBoardDTO2);
        scoreBoardDTO1.setId(null);
        assertThat(scoreBoardDTO1).isNotEqualTo(scoreBoardDTO2);
    }
}
