package Config;


import ReusableMethods.DataHandlingMethods;
import ReusableMethods.InteractionMethods;
import ReusableMethods.SeleniumElementMethods;

/**
 * <h1>Base Interface</h1>
 *
 * <p>This interface defines the basic methods and interactions used across different classes in the framework.</p>
 * <p>It serves as a central point for providing access to essential methods such as interactions, element handling, and data management.</p>
 * <p>It contains references to the instances of classes responsible for handling interactions, waiting for elements, and managing data.</p>
 *
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/18
 */
public interface Base {
    InteractionMethods interactions = new InteractionMethods();
    SeleniumElementMethods waits = new SeleniumElementMethods();
    DataHandlingMethods data = new DataHandlingMethods();
}
