package de.techfak.gse.template;

import de.techfak.gse.ymokrane.exceptions.JsonException;
import de.techfak.gse.ymokrane.model.Song;
import de.techfak.gse.ymokrane.model.server.JsonParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonParserTest {

    JsonParser parser = new JsonParser();
    Song song = new Song();

    @Test
        /* default */ void testArtist() throws JsonException {
        song.setArtist("Test");
        String artist = parser.toJSON(song);
        Assertions.assertThat(artist).isEqualTo("{\"id\":0,\"artist\":\"Test\",\"title\":null}");
    }

    @Test
        /* default */ void testTitle() throws JsonException {
        song.setArtist("Test");
        song.setTitle("Test2");
        String title = parser.toJSON(song);
        Assertions.assertThat(title).isEqualTo("{\"id\":0,\"artist\":\"Test\",\"title\":\"Test2\"}");
    }

    @Test
        /* default */ void testId() throws JsonException {
        song.setArtist("Test");
        song.setTitle("Test2");
        song.setId(10);
        String id = parser.toJSON(song);
        Assertions.assertThat(id).isEqualTo("{\"id\":10,\"artist\":\"Test\",\"title\":\"Test2\"}");
    }
}
