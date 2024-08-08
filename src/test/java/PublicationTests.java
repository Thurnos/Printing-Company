import org.informatics.exceptions.printingMachineExceptions.NegativeCurrentNumPapersException;
import org.informatics.printingMachine.PrintingMachine;
import org.junit.jupiter.api.Test;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PrintingMachineTest  {

    @Test
    public void testSetCurrentNumPapersWithPositiveValue() {
        // Arrange
        PrintingMachine printingMachine = new PrintingMachine();
        int currentNumPapers = 50;

        // Act
        printingMachine.setCurrentNumPapers(currentNumPapers);

        // Assert
        assertEquals(currentNumPapers, printingMachine.getCurrentNumPapers());
    }

    @Test
    public void testSetCurrentNumPapersWithNegativeValue() {
        // Arrange
        PrintingMachine printingMachine = new PrintingMachine();
        int currentNumPapers = -10;

        // Assert
        assertThrows(NegativeCurrentNumPapersException.class, () -> {
            // Act
            printingMachine.setCurrentNumPapers(currentNumPapers);
        });
    }

    @Test
    public void testLoadPaperWhenNotFull() {
        // Arrange
        PrintingMachine printingMachine = mock(PrintingMachine.class);
        when(printingMachine.getCurrentNumPapers()).thenReturn(50);
        when(printingMachine.getMaxNumPapers()).thenReturn(100);

        // Act
        PrintingMachine.loadPaper(printingMachine);

        // Assert
        verify(printingMachine).setCurrentNumPapers(100);
    }

    @Test
    public void testLoadPaperWhenFull() {
        // Arrange
        PrintingMachine printingMachine = mock(PrintingMachine.class);
        when(printingMachine.getCurrentNumPapers()).thenReturn(100);
        when(printingMachine.getMaxNumPapers()).thenReturn(100);

        // Act
        PrintingMachine.loadPaper(printingMachine);

        // Assert
        verify(printingMachine).setCurrentNumPapers(100);
    }

    @Test
    public void testIsLoadedWhenLoaded() {
        // Arrange
        PrintingMachine printingMachine = mock(PrintingMachine.class);
        when(printingMachine.getCurrentNumPapers()).thenReturn(100);
        when(printingMachine.getMaxNumPapers()).thenReturn(100);

        // Act
        boolean isLoaded = printingMachine.isLoaded(printingMachine);

        // Assert
        assertTrue(isLoaded);
    }

    @Test
    public void testIsLoadedWhenNotLoaded() {
        // Arrange
        PrintingMachine printingMachine = mock(PrintingMachine.class);
        when(printingMachine.getCurrentNumPapers()).thenReturn(50);
        when(printingMachine.getMaxNumPapers()).thenReturn(100);

        // Act
        boolean isLoaded = printingMachine.isLoaded(printingMachine);

        // Assert
        assertFalse(isLoaded);
    }

    @Test
    public void testPagesPerMinuteForBlackAndWhite() {
        // Arrange
        PrintingMachine printingMachine = mock(PrintingMachine.class);
        PaperColor paperColor = PaperColor.BLACK_AND_WHITE;
        int expectedPPM = 35;

        // Act
        printingMachine.pagesPerMinute(printingMachine, paperColor);

        // Assert
        verify(printingMachine).setPPM(expectedPPM);
    }

    @Test
    public void testPagesPerMinuteForColored() {
        // Arrange
        PrintingMachine printingMachine = mock(PrintingMachine.class);
        PaperColor paperColor = PaperColor.COLORED;
        int expectedPPM = 4;

        // Act
        printingMachine.pagesPerMinute(printingMachine, paperColor);

        // Assert
        verify(printingMachine).setPPM(expectedPPM);
    }

    @Test
    public void testPagesPerMinuteWhenNotLoaded() {
        // Arrange
        PrintingMachine printingMachine = mock(PrintingMachine.class);
        when(printingMachine.isLoaded(printingMachine)).thenReturn(false);
        PaperColor paperColor = PaperColor.BLACK_AND_WHITE;

        // Act
        printingMachine.pagesPerMinute(printingMachine, paperColor);

        // Assert
        verify(printingMachine, never()).setPPM(anyInt());
    }
}
