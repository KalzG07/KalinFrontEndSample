package Config;


import ReusableMethods.DataHandlingMethods;
import ReusableMethods.InteractionMethods;
import ReusableMethods.SeleniumElementMethods;

/**
 * <h1>Header </h1>
 * description
 * <p>
 *
 * @author Kalin Govender
 * @version 1.1
 * @since 2025/01/19
 */
public interface Base {
    InteractionMethods interactions = new InteractionMethods();
    SeleniumElementMethods waits = new SeleniumElementMethods();
    DataHandlingMethods data = new DataHandlingMethods();
}
