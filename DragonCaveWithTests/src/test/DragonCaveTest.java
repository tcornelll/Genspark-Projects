import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class DragonCaveTest {

    DragonCave dc;

    @BeforeEach
    void setUp() {
        dc = new DragonCave();
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Testing DragonCave.printIntro()")
    @Test
    void printIntro() {
        assertEquals("""
                You are in a land full of dragons. In front of you,
                you see two caves. In one cave, the dragon is friendly
                and will share his treasure with you. The other dragon
                is greedy and hungry and will eat you on sight
                Which cave will you go into? (1 or 2)
                """, dc.printIntro());
    }

    @DisplayName("Testing DragonCave.handleEaten()")
    @Test
    void handleEaten() {
        assertEquals("""
                You approach the cave...
                It is dark and spooky...
                A large dragon jumps out in front of you! He opens his jaws and...
                Gobbles you down in one bite!""", dc.handleEaten());
    }

    @DisplayName("Testing DragonCave.handleShared()")
    @Test
    void handleShared() {
        assertEquals("""
                You approach the cave...
                It is dark and spooky...
                A large dragon jumps out in front of you! He opens his jaws and...
                Tells you that he is feeling generous...
                And will share his gold with you today!
                He gives you some of his gold
                And you continue on your journey""", dc.handleShared());
    }

}