package de.techfak.gse.ymokrane.model.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.techfak.gse.ymokrane.exceptions.JsonException;
import de.techfak.gse.ymokrane.model.Song;


public class JsonParser {
    //Unser Parser
    private ObjectMapper objectMapper;

    public JsonParser() {
        objectMapper = new ObjectMapper()
            .findAndRegisterModules();
    }

    /**
     * Wir erstellen ein JSON-Objekt (Serialisieren) aus einem Objekt.
     *
     * @param person ist ein beliebiges Personen-Objekt, welches serialisiert werden soll
     * @return unser JSON-Objekt als String
     * @throws JsonException wird geworfen, falls die Serialisierung fehlschlägt
     */
    public String toJSON(final Song person) throws JsonException {
        //Wir nutzen einen Try-Catch-Block, da ein Parsing-Error auftreten kann
        try {
            /*Wir wandeln das Personen-Objekt, in ein JSON-Objekt um, welches durch einen String dargestellt wird
            Dieses nimmt das JSON-Objekt als Parameter entgegen*/
            return objectMapper.writeValueAsString(person);
        } catch (JsonProcessingException e) {
            //Hier müsst ihr eure eigene Exception werfen, die ihr erstellt
            throw new JsonException("Serialisierung fehlgeschlagen");
        }
    }


    /**
     * Wir erstellen ein Objekt (Deserialisieren) aus einem JSON-Objekt.
     *
     * @param json ist ein JSON-Objekt, welches deserialisiert werden soll
     * @return unser neues Personen-Objekt
     * @throws JsonException wird geworfen, falls die Deserialisierung fehlschlägt
     */
    public Song parseJSON(final String json) throws JsonException {

        try {
            //Wir übergeben dem Parser, das JSON-Object und die Klasse, welches wir anvisieren
            return objectMapper.readValue(json, Song.class);
        } catch (JsonProcessingException e) {
            //Hier müsst ihr eure zweite eigene Exception werfen
            throw new JsonException("Deserialisierung fehlgeschlagen");
        }
    }


}
