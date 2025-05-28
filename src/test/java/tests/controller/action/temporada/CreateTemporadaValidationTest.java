package tests.controller.action.temporada;

import controller.action.temporada.CreateTemporadaValidation;
import model.Temporada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateTemporadaValidationTest {

    private CreateTemporadaValidation validation;

    @BeforeEach
    public void setUp() {
        validation = new CreateTemporadaValidation();
    }

    @Test
    public void testValidationAlwaysTrue() {
        Temporada temporada = new Temporada(); // pode ter campos nulos, pois isValid retorna true
        assertTrue(validation.isValid(temporada));
        assertNull(validation.getErrorMessage());
    }
}
